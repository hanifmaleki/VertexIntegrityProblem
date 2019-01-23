package at.tuwien.ac.graph.lp;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import at.tuwien.ac.graph.ops.instance.MyMPSReader;

import gurobi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by root on 7/18/17.
 */
public class CallBack extends GRBCallback {
    private final GraphNew graph;
    private final int c;
    private GRBVar[] vars;

    Logger logger = LoggerFactory.getLogger(CallBack.class);

    public CallBack(GRBVar[] x, GraphNew graph, int c) {
        vars = x;
        this.graph = graph;
        this.c = c;

    }

    public static void main(String[] args) {
        Graph inducedGraph = new MyMPSReader().getInducedGraph("samples/noswot.mps");
        GraphNew graph = IncidentGraphHelper.getNewStructure(inducedGraph);
        int c = 43;
        new LPCAndP(c, graph).solveRepeatedly();
        /*if(EPSILON>0.1){
            EPSILON -= 0.1
            logger.debug("decrease epsilon to " + EPSILON);

        }*/

    }

    private static void solveBAndP(GraphNew graph, int c) {

        int n = graph.getSize();
        System.out.println(n);

        try {
            GRBEnv env = new GRBEnv("vip.log");
            GRBModel model = new GRBModel(env);

            // Must set LazyConstraints parameter when using lazy constraints

            model.set(GRB.IntParam.LazyConstraints, 1);


            // Create variables
            //TODO something

            GRBVar[] x = new GRBVar[n];

            for (int i = 0; i < n; i++) {
                x[i] = model.addVar(0.0, 1.0, 1,
                        GRB.BINARY,
                        "x" + String.valueOf(i));
            }

            GRBLinExpr linExpr = new GRBLinExpr();
            for(int i = 0; i < n ; i++)
                linExpr.addTerm(1, x[i]);
            model.addConstr(linExpr, GRB.LESS_EQUAL, 268, "c1");


            // Set objective: maximize x + y + 2 z

            GRBLinExpr expr = new GRBLinExpr();
            for (int i = 0; i < n; i++)
                expr.addTerm(1.0, x[i]);
            model.setObjective(expr, GRB.MAXIMIZE);


            // Optimize model

            model.setCallback(new CallBack(x, graph, c));
            model.optimize();
            //System.out.println(x.get(GRB.StringAttr.VarName)
            //        + " " +x.get(GRB.DoubleAttr.X));
            //System.out.println(y.get(GRB.StringAttr.VarName)
            //        + " " +y.get(GRB.DoubleAttr.X));
            //System.out.println(z.get(GRB.StringAttr.VarName)
            //       + " " +z.get(GRB.DoubleAttr.X));

            System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));

            // Dispose of model and environment

            model.dispose();
            env.dispose();

        } catch (GRBException e) {
            System.out.println("Error code: " + e.getErrorCode() + ". " +
                    e.getMessage());
        }
    }

    double lastiter = -GRB.INFINITY;
    double lastnode = -GRB.INFINITY;


    @Override
    protected void callback() {
        //System.out.println("Hello. Im Here " + where);
        try {
            if (where == GRB.CB_MIPSOL) {
                //System.out.println("MIPSOL");
                double[] solution = getSolution(vars);
                List<Double> weights = new ArrayList<>();
                String msg = "";
                for (int i = 0; i < vars.length; i++) {
                    double EPSILON = 0.01;
                    double e = solution[i];
                    if (e < EPSILON)
                        e = 0;
                    if (e > 1 - EPSILON)
                        e = 1;
                    if (e < 1 - EPSILON)
                        msg += i + "_" + e + ", ";// + e+"\t\t";
                    weights.add(e);
                }
                //logger.debug(msg);
                VertexWeightedGraph gph = new VertexWeightedGraph(graph, weights);
                List<Integer> aSingleBadConnectedVertices = gph.getASingleBadConnectedVertices(null, null, c + 1, c);
                double total = 0;
                for (Integer ff : aSingleBadConnectedVertices) {
                    Double aDouble = weights.get(ff);
                    total += aDouble;
                    //logger.debug(aDouble + "\t");
                }
                logger.debug("MIPSOL_sum:" + total);
                if (aSingleBadConnectedVertices != null) {
                    //System.out.println("Adding constraint:");
                    GRBLinExpr expr = new GRBLinExpr();
                    for (Integer vertex : aSingleBadConnectedVertices)
                        expr.addTerm(1, vars[vertex]);
                    addLazy(expr, GRB.LESS_EQUAL, c);
                }
            } else if (where == GRB.CB_SIMPLEX) {
                // Simplex callback
                double itcnt = getDoubleInfo(GRB.CB_SPX_ITRCNT);
                if (itcnt - lastiter >= 100) {
                    lastiter = itcnt;
                    double obj = getDoubleInfo(GRB.CB_SPX_OBJVAL);
                    int ispert = getIntInfo(GRB.CB_SPX_ISPERT);
                    double pinf = getDoubleInfo(GRB.CB_SPX_PRIMINF);
                    double dinf = getDoubleInfo(GRB.CB_SPX_DUALINF);
                    char ch;
                    if (ispert == 0) ch = ' ';
                    else if (ispert == 1) ch = 'S';
                    else ch = 'P';
                    System.out.println(itcnt + " " + obj + ch + " "
                            + pinf + " " + dinf);
                }
            }


            if (where == GRB.CB_MIPNODE) {
                // MIP node callback
                //System.out.println("**** New node ****");
                if (getIntInfo(GRB.CB_MIPNODE_STATUS) == GRB.OPTIMAL) {
                    int      nodecnt = (int) getDoubleInfo(GRB.CB_MIPSOL_NODCNT);
                    double   obj     = getDoubleInfo(GRB.CB_MIPSOL_OBJ);
                    int      solcnt  = getIntInfo(GRB.CB_MIPSOL_SOLCNT);
                    logger.debug("**** New solution at node " + nodecnt
                            + ", obj " + obj + ", sol " + solcnt
                            + " ****");

                    double[] x = getNodeRel(vars);
                    List<Double> weights = new ArrayList<>();
                    String msg = "";
                    double EPSILON = 0.1;
                    double answer = 0 ;
                    for (int i = 0; i < x.length; i++) {
                        if (x[i] < 1 - EPSILON)
                            msg += i + "_" + x[i] + ",";
                        answer+=x[i];
                        weights.add(x[i]);
                    }
                    //logger.debug("Rel " + msg);
                    VertexWeightedGraph gph = new VertexWeightedGraph(graph, weights);
                    List<Integer> aSingleBadConnectedVertices = gph.getASingleBadConnectedVertices(null, null, c + 1, c+EPSILON);
                    double total = 0;
                    for (Integer ff : aSingleBadConnectedVertices) {
                        Double aDouble = weights.get(ff);
                        total += aDouble;
                        //logger.debug(aDouble + "\t");
                    }
                    logger.debug("MIPNODE_sum:" + total+ "\tAnswer: "+ answer);
                    if (aSingleBadConnectedVertices != null) {
                        //System.out.println("Adding constraint:");
                        GRBLinExpr expr = new GRBLinExpr();
                        for (Integer vertex : aSingleBadConnectedVertices)
                            expr.addTerm(1, vars[vertex]);
                        addLazy(expr, GRB.LESS_EQUAL, c);
                    }
                    //setSolution(vars, x);
                }
            }
            /*if (where == GRB.CB_MIP) {
                System.out.println("MIP");
                // General MIP callback
                double nodecnt = getDoubleInfo(GRB.CB_MIP_NODCNT);
                double objbst  = getDoubleInfo(GRB.CB_MIP_OBJBST);
                double objbnd  = getDoubleInfo(GRB.CB_MIP_OBJBND);
                int    solcnt  = getIntInfo(GRB.CB_MIP_SOLCNT);
                if (nodecnt - lastnode >= 100) {
                    lastnode = nodecnt;
                    int actnodes = (int) getDoubleInfo(GRB.CB_MIP_NODLFT);
                    int itcnt    = (int) getDoubleInfo(GRB.CB_MIP_ITRCNT);
                    int cutcnt   = getIntInfo(GRB.CB_MIP_CUTCNT);
                    System.out.println(nodecnt + " " + actnodes + " "
                            + itcnt + " " + objbst + " " + objbnd + " "
                            + solcnt + " " + cutcnt);
                }
                if (Math.abs(objbst - objbnd) < 0.1 * (1.0 + Math.abs(objbst))) {
                    System.out.println("Stop early - 10% gap achieved");
                    abort();
                }
                if (nodecnt >= 10000 && solcnt > 0) {
                    System.out.println("Stop early - 10000 nodes explored");
                    abort();
                }
                System.out.println("END MIP");

            }*/

        } catch (GRBException e) {
            e.printStackTrace();
        }
    }
}
