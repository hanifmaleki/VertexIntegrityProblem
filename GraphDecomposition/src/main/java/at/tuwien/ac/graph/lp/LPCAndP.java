package at.tuwien.ac.graph.lp;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import at.tuwien.ac.graph.ops.instance.MyMPSReader;
import gurobi.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/23/17.
 */
public class LPCAndP {

    private static double newOpt;
    private int c;
    private final GraphNew graph ;

    public LPCAndP(int c, GraphNew graph){
        this.c = c ;
        this.graph = graph;
    }

    public void solveRepeatedly(){
        List<List<Integer>> exprs = new ArrayList<>();
        double previousOpt = graph.getSize()+5;
        while(true) {
            List<Double> weights = solveRelax(exprs);

            VertexWeightedGraph gph = new VertexWeightedGraph(graph, weights);
            List<Integer> aSingleBadConnectedVertices = gph.getASingleBadConnectedVertices(null, null, c + 1, c);
            //if((previousOpt - newOpt < 0.00001  )||(aSingleBadConnectedVertices==null)){
            if(aSingleBadConnectedVertices==null){
                //System.out.println("previousOpt:"+previousOpt+"\tnewOpt: "+newOpt);


                for(int i =0; i > weights.size();i++)
                    System.out.println("i"+weights.get(i));

                return;
            }
            // else
                //previousOpt = newOpt ;
            double total = 0;
            List<Integer> newExpr = new ArrayList<>();
            for (Integer vertex : aSingleBadConnectedVertices) {
                total+= weights.get(vertex);
                newExpr.add(vertex);
            }
            System.out.println("Total: "+total);
            exprs.add(newExpr);
            //if(exprs.size()>2000)
            //    System.out.println(exprs);
        }

    }

    public  List<Double> solveRelax(List<List<Integer>> exprs) {
        Graph inducedGraph = new MyMPSReader().getInducedGraph("samples/noswot.mps");
        GraphNew graph = IncidentGraphHelper.getNewStructure(inducedGraph);
        int n = graph.getSize();
        System.out.println(n);

        try {
            GRBEnv env = new GRBEnv("vip");
            GRBModel model = new GRBModel(env);

            // Must set LazyConstraints parameter when using lazy constraints

            model.set(GRB.IntParam.LazyConstraints, 1);


            // Create variables
            //TODO something

            GRBVar[] x = new GRBVar[n];

            for (int i = 0; i < n; i++) {
                x[i] = model.addVar(0.0, 1.0, 1,
                        GRB.CONTINUOUS,
                        "x" + String.valueOf(i));
            }

            /*GRBLinExpr linExpr = new GRBLinExpr();
            for(int i = 0; i < n ; i++)
                linExpr.addTerm(1, x[i]);
            model.addConstr(linExpr, GRB.LESS_EQUAL, 268, "c1");*/
            int counter =0;

            for(List<Integer> indexes: exprs) {
                GRBLinExpr expr = new GRBLinExpr();
                for (Integer vertex : indexes) {
                    expr.addTerm(1, x[vertex]);
                }

                model.addConstr(expr, GRB.LESS_EQUAL, c, "c" + counter++);
            }

            // Set objective: maximize x + y + 2 z

            GRBLinExpr expr = new GRBLinExpr();
            for (int i = 0; i < n; i++)
                expr.addTerm(1.0, x[i]);
            model.setObjective(expr, GRB.MAXIMIZE);


            // Optimize model

            //model.setCallback(new CallBack(x, graph, c));
            model.optimize();
            //System.out.println(x.get(GRB.StringAttr.VarName)
            //        + " " +x.get(GRB.DoubleAttr.X));
            //System.out.println(y.get(GRB.StringAttr.VarName)
            //        + " " +y.get(GRB.DoubleAttr.X));
            //System.out.println(z.get(GRB.StringAttr.VarName)
            //       + " " +z.get(GRB.DoubleAttr.X));

            newOpt = model.get(GRB.DoubleAttr.ObjVal);
            System.out.println("Obj: " + newOpt+ "\tconstraints:"+exprs.size());

            List<Double> answers = new ArrayList<>();
            for(int i = 0 ; i < n; i++) {
                double d = x[i].get(GRB.DoubleAttr.X);
                //System.out.println(d);
                answers.add(d);
            }


            // Dispose of model and environment

            model.dispose();
            env.dispose();

            return answers ;

        } catch (GRBException e) {
            System.out.println("Error code: " + e.getErrorCode() + ". " +
                    e.getMessage());
        }

        return null;
    }
}
