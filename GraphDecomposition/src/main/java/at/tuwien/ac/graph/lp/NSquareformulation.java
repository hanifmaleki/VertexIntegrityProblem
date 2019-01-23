package at.tuwien.ac.graph.lp;

import at.tuwien.ac.graph.graph.Vertex;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import gurobi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e1528895 on 8/4/17.
 */
public class NSquareformulation {
    private final GraphNew graph;
    private final int c;
    private final int n;
    private GRBVar[] vars;
    private int maxComponentsCount;

    Logger logger = LoggerFactory.getLogger(NSquareformulation.class);

    public NSquareformulation(GraphNew graph, int c) {
        this.graph = graph;
        this.c = c;
        n = graph.getSize();
        maxComponentsCount = (n *2) / c + 1;
        logger.debug("max component is "+ maxComponentsCount);
    }

    public SimpleLPExpriment solveModel(int timeout){
        try {
            GRBEnv env = new GRBEnv("gurobi.log");
            GRBModel model = new GRBModel(env);


            //logger.debug("Model Created");

            GRBVar[] x = new GRBVar[n];
            GRBVar[][] y = new GRBVar[n][maxComponentsCount];

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

            //logger.debug("Variables Added");

            //for every i in nodes x_i= sum_{k}y_ik
            for (int i = 0; i < n; i++) {
                GRBLinExpr linExpr = new GRBLinExpr();
                for (int j = 0; j < maxComponentsCount; j++) {
                    linExpr.addTerm(1,y[i][j]);
                }
                linExpr.addTerm(-1, x[i]);
                model.addConstr(linExpr, GRB.EQUAL, 0, "c"+i);
            }

            //logger.debug("for every i in nodes x_i= sum_{k}y_ik constraints added");

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
                        //logger.debug("for every edge("+vertex+","+neighbor+") y_"+vertex+","+k + "y_"+neighbor+","+
                        //        k +"=x_"+vertex +"+ x_"+neighbor);
                    }

                }
            }
            //logger.debug("Edge Component Constrains added");

            //for every k sum_{k}y_ik <= c
            for(int k= 0; k < maxComponentsCount; k++) {
                GRBLinExpr linExpr = new GRBLinExpr();
                for (int i = 0; i < n; i++) {
                    linExpr.addTerm(1, y[i][k]);
                }
                model.addConstr(linExpr, GRB.LESS_EQUAL, c, "cs-"+k);
            }

            //logger.debug("for every k sum_{i}y_ik <= c constraint added");

            //My Special Constraint
            /*List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(graph, c+1);
            addConnectedVerticesConstraint(model, x, connectedVertices);
            for(Integer vertex : connectedVertices){
                GraphNew graphNew = graph.removeVertexCopy(vertex);
                List<Integer> vertices = GraphNewHelper.getConnectedVertices(graphNew, c + 1);
                addConnectedVerticesConstraint(model, x, vertices);
            }*/



            GRBLinExpr expr = new GRBLinExpr();
            for (int i = 0; i < n; i++)
                expr.addTerm(1.0, x[i]);
            model.setObjective(expr, GRB.MAXIMIZE);

            //TODO reconsider it
            //model.set(GRB.DoubleParam.Cutoff, 253);

            //TODO reconsider it
            if(timeout>0)
            model.set(GRB.DoubleParam.TimeLimit, timeout);

            //logger.debug("Start Optimizeng");

            model.optimize();

            //logger.debug("Finish Optimizing with "+ model.getObjective());

            List<Integer> backdoor = new ArrayList<>();

            for(int i = 0; i < n ; i++) {
                double xi = x[i].get(GRB.DoubleAttr.Xn);
                if(xi>0) {
                    //logger.debug("x["+i+"]=1");
                }else
                    backdoor.add(i);
                for (int j = 0; j < maxComponentsCount; j++) {
                    double v = y[i][j].get(GRB.DoubleAttr.Xn);
                    //if (v > 0)
                    //    logger.debug(i + "," + j + ": " + v);
                }
            }
            int lb = n -  (int)model.get(GRB.DoubleAttr.ObjBound);
            int ub = n -  (int)model.get(GRB.DoubleAttr.ObjVal);

            SimpleLPExpriment expriment = new SimpleLPExpriment();
            expriment.setC(c);
            expriment.setK(ub);
            expriment.setLb(lb);
            expriment.setTimeout(timeout);
            List<VertexEntity> vertexEntities = new ArrayList<>();
            for(Integer vertex : backdoor){
                VertexEntity vertexEntity = new VertexEntity();
                vertexEntity.setVertex(vertex);
                vertexEntity.setExpriment(expriment);
                vertexEntities.add(vertexEntity);
            }
            expriment.setSelectedVertices(vertexEntities);

            model.dispose();
            env.dispose();

            return expriment ;

        } catch (GRBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void addConnectedVerticesConstraint(GRBModel model, GRBVar[] x, List<Integer> connectedVertices) throws GRBException {
        GRBLinExpr linExpr = new GRBLinExpr();
        for(Integer vertex : connectedVertices){
            linExpr.addTerm(1, x[vertex]);
        }
        model.addConstr(linExpr, GRB.LESS_EQUAL, c, "c_sum");
    }*/
}
