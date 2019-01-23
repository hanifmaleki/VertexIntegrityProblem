package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.SeparationSecondExpriment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 5/15/17.
 */
public class SeparationSecondBiggestComp {
    Logger logger = LoggerFactory.getLogger(SeparationSecondBiggestComp.class);
    public SeparationSecondExpriment getProperSeparation(GraphNew graph) {
        //Lets assume that the graph is connected
        int maximum = Integer.MIN_VALUE;
        int bestBiggestCompSize = Integer.MAX_VALUE;
        SeparationSecondExpriment bestSeparation = null;
        List<Integer> vertices = graph.getVertices();

        for (Integer source : vertices) {
            for (Integer sink : vertices) {
                if (source >= sink)
                    continue;
                if(graph.getNeighbors(source).contains(sink))
                    continue;

                GraphNew graphNew = new GraphNew(graph);
                Set<Integer> separation = MinimumCut.findBiggestSecondCompSeparation(graphNew, source, sink);

                graphNew = new GraphNew(graph);
                for(Integer removingVertex: separation)
                    graphNew = graphNew.removeVertexCopy(removingVertex);

                List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graphNew);

                Collections.sort(connectedComponentsHS, new Comparator<GraphNew>() {
                    @Override
                    public int compare(GraphNew o1, GraphNew o2) {
                        return Integer.compare(o2.getSize(), o1.getSize());
                    }
                });
                int size = connectedComponentsHS.get(1).getSize();


                int biggestSize = connectedComponentsHS.get(0).getSize();
                boolean bo = (size > maximum) || ((size == maximum)&&(bestBiggestCompSize> biggestSize)) ;
                if (bo) {
                    maximum = size;
                    bestBiggestCompSize = biggestSize ;
                    bestSeparation = new SeparationSecondExpriment();
                    Set<VertexEntity> vertexEntities = new HashSet<>();
                    for(Integer vertex : separation){
                        VertexEntity vertexEntity = new VertexEntity();
                        vertexEntity.setExpriment(bestSeparation);
                        vertexEntity.setVertex(vertex);
                        vertexEntities.add(vertexEntity);
                    }
                    bestSeparation.setSeparation(vertexEntities);
                    bestSeparation.setSize(separation.size());

                    logger.debug("New Maximum second biggest: "+ size +  " Separation Size: "+ separation.size() +
                            " The biggest is "+ biggestSize+ " source and sink are "+ source+ ","+sink);
                }
            }
        }
        if(bestSeparation==null){
            bestSeparation = new SeparationSecondExpriment();
            bestSeparation.setSize(-1);
        }
        return bestSeparation;
    }

}
