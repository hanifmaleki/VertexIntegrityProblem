package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumSizeMinimumMaxExpriment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 5/15/17.
 * metric = maxComponentSize + separation.size
 */
public class SeparationMinSepMinCom {
    Logger logger = LoggerFactory.getLogger(SeparationMinSepMinCom.class);
    public SeparationMinimumSizeMinimumMaxExpriment getProperSeparation(GraphNew graph) {
        //Lets assume that the graph is connected
        int minimum = Integer.MAX_VALUE;
        SeparationMinimumSizeMinimumMaxExpriment bestSeparation = null;
        List<Integer> vertices = graph.getVertices();

        for (Integer source : vertices) {
            for (Integer sink : vertices) {
                if (source <= sink)
                    continue;
                if(graph.getNeighbors(source).contains(sink))
                    continue;

                GraphNew graphNew = new GraphNew(graph);
                Set<Integer> separation = MinimumCut.findBestSeparation(graphNew, source, sink);
                int maxComponentSize = 0;
                graphNew = new GraphNew(graph);
                for(Integer removingVertex: separation)
                    graphNew = graphNew.removeVertexCopy(removingVertex);

                List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graphNew);

                Collections.sort(connectedComponentsHS, new Comparator<GraphNew>() {
                    @Override
                    public int compare(GraphNew o1, GraphNew o2) {
                        return Integer.compare(o1.getSize(), o2.getSize());
                    }
                });
                maxComponentSize = connectedComponentsHS.get(connectedComponentsHS.size()-1).getSize();

                int metric = maxComponentSize + separation.size();

                if (metric < minimum) {
                    minimum = maxComponentSize;
                    bestSeparation = new SeparationMinimumSizeMinimumMaxExpriment();
                    Set<VertexEntity> vertexEntities = new HashSet<>();
                    for(Integer vertex : separation){
                        VertexEntity vertexEntity = new VertexEntity();
                        vertexEntity.setExpriment(bestSeparation);
                        vertexEntity.setVertex(vertex);
                        vertexEntities.add(vertexEntity);
                    }
                    bestSeparation.setSeparation(vertexEntities);
                    bestSeparation.setSize(separation.size());

                    logger.debug("new Min: max compo: "+ maxComponentSize + " + Sep: " +separation.size() + "="+ metric);
                }
            }
        }
        if(bestSeparation==null){
            bestSeparation = new SeparationMinimumSizeMinimumMaxExpriment();
            bestSeparation.setSize(-1);
        }
        return bestSeparation;
    }

}
