package at.tuwien.ac.graph.lp;

import at.tuwien.ac.graph.newgraph.GraphNew;
import gurobi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by e1528895 on 8/4/17.
 */
public class NSquareSymmetryBreak {
    private final GraphNew graph;
    private final int c;
    private final int n;
    private GRBVar[] vars;
    private int maxComponentsCount = 20 ;

    Logger logger = LoggerFactory.getLogger(NSquareSymmetryBreak.class);

    public NSquareSymmetryBreak(GraphNew graph, int c) {
        this.graph = graph;
        this.c = c;
        n = graph.getSize();
    }

    public void solveModel(){
        try {
            GRBEnv env = new GRBEnv("vip.log");
            GRBModel model = new GRBModel(env);

            logger.debug("Model Created");

            GRBVar[] x = new GRBVar[n];
            GRBVar[][] y = new GRBVar[n][n];

            for (int i = 0; i < n; i++) {
                x[i] = model.addVar(0.0, 1.0, 1,
                        GRB.BINARY,
                        "x" + String.valueOf(i));
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < maxComponentsCount; j++) {
                    y[i][j] = model.addVar(0.0, 1.0,1,GRB.BINARY, "y"+ String.valueOf(i)+","+String.valueOf(j));
                }
            }

            logger.debug("Variables Added");

            //for every i in nodes x_i= sum_{k}y_ik
            for (int i = 0; i < n; i++) {
                GRBLinExpr linExpr = new GRBLinExpr();
                for (int j = 0; j < maxComponentsCount; j++) {
                    linExpr.addTerm(1,y[i][j]);
                }
                linExpr.addTerm(-1, x[i]);
                model.addConstr(linExpr, GRB.EQUAL, 0, "c"+i);
            }

            logger.debug("for every i in nodes x_i= sum_{k}y_ik constraints added");

            //for every edge(i,j) y_ik + y_jk =x_i + x_j
            for(Integer vertex : graph.getVertices()){
                for(Integer neighbor: graph.getNeighbors(vertex)){
                    for(int k = 0; k < maxComponentsCount ; k++) {
                        GRBLinExpr linExpr = new GRBLinExpr();
                        linExpr.addTerm(1, y[vertex][k]);
                        linExpr.addTerm(-1, y[neighbor][k]);
                        linExpr.addTerm(1, x[vertex]);
                        linExpr.addTerm(1, x[neighbor]);
                        model.addConstr(linExpr, GRB.LESS_EQUAL, 2, "c_"+vertex+","+neighbor+","+k);
                        logger.debug("for every edge("+vertex+","+neighbor+") y_"+vertex+","+k + "y_"+neighbor+","+
                                k +"=x_"+vertex +"+ x_"+neighbor);
                    }

                }
            }
            logger.debug("Edge Component Constrains added");

            //for every k sum_{k}y_ik <= c
            for(int k= 0; k < maxComponentsCount; k++) {
                GRBLinExpr linExpr = new GRBLinExpr();
                for (int i = 0; i < n; i++) {
                    linExpr.addTerm(1, y[i][k]);
                }
                model.addConstr(linExpr, GRB.LESS_EQUAL, c, "cs-"+k);
            }

            //Symmetry Breaking
            //sum_(k>=K)y_ik <= sum(k>K) y_jk - x_j + 1 forall k and i<j
            for(int k= 0 ; k < maxComponentsCount ; k ++)
                for(int i=0; i < n-1 ; i++)
                    for(int j=i+1; j < i+2; j++){
                        GRBLinExpr linExpr = new GRBLinExpr();
                        for(int l=0;l <= k; l++)
                            linExpr.addTerm(-1, y[i][l]);
                        for(int l = k; l < maxComponentsCount; l++)
                            linExpr.addTerm(1, y[j][l]);
                        linExpr.addTerm(-1, x[j]);
                        model.addConstr(linExpr, GRB.GREATER_EQUAL, -1, "sb-"+i+","+j+","+k);


                    }

            logger.debug("Symmetry Breaking Constraints added");




            logger.debug("for every k sum_{i}y_ik <= c constraint added");

            GRBLinExpr expr = new GRBLinExpr();
            for (int i = 0; i < n; i++)
                expr.addTerm(1.0, x[i]);
            model.setObjective(expr, GRB.MAXIMIZE);

            logger.debug("Start Optimizeng");

            model.optimize();

            logger.debug("Finish Optimizing with "+ model.getObjective());

            for(int i = 0; i < n ; i++) {
                double xi = x[i].get(GRB.DoubleAttr.X);
                if(xi>0)
                    logger.debug("x["+i+"]=1");
                for (int j = 0; j < maxComponentsCount; j++) {
                    double v = y[i][j].get(GRB.DoubleAttr.X);
                    if (v > 0)
                        logger.debug(i + "," + j + ": " + v);
                }
            }


            model.dispose();
            env.dispose();

        } catch (GRBException e) {
            e.printStackTrace();
        }
    }
}
