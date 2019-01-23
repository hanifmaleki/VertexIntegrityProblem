package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumMaxComponentExpriment;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import at.tuwien.ac.graph.ops.instance.MyMPSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 5/15/17.
 */
public class SeparationAlgorithm {
    Logger logger = LoggerFactory.getLogger(SeparationAlgorithm.class);
    public SeparationMinimumMaxComponentExpriment getProperSeparation(GraphNew graph) {
        //Lets assume that the graph is connected
        int minMaxComponentSize = Integer.MAX_VALUE;
        SeparationMinimumMaxComponentExpriment bestSeparation = null;
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
                /*for(GraphNew component : separationExpriment.getComponents())
                    if(component.getSize()> maxComponentSize)
                        maxComponentSize = component.getSize();
                */
                graphNew = new GraphNew(graph);
                for(Integer removingVertex: separation)
                    graphNew = graphNew.removeVertexCopy(removingVertex);

                List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graphNew);
                /*for (GraphNew component: connectedComponentsHS){
                    if(component.getSize()> maxComponentSize)
                        maxComponentSize = component.getSize();
                }*/
                Collections.sort(connectedComponentsHS, new Comparator<GraphNew>() {
                    @Override
                    public int compare(GraphNew o1, GraphNew o2) {
                        return Integer.compare(o1.getSize(), o2.getSize());
                    }
                });
                maxComponentSize = connectedComponentsHS.get(connectedComponentsHS.size()-1).getSize();

                if (maxComponentSize < minMaxComponentSize) {
                    minMaxComponentSize = maxComponentSize;
                    bestSeparation = new SeparationMinimumMaxComponentExpriment();
                    Set<VertexEntity> vertexEntities = new HashSet<>();
                    for(Integer vertex : separation){
                        VertexEntity vertexEntity = new VertexEntity();
                        vertexEntity.setExpriment(bestSeparation);
                        vertexEntity.setVertex(vertex);
                        vertexEntities.add(vertexEntity);
                    }
                    bestSeparation.setSeparation(vertexEntities);
                    bestSeparation.setSize(separation.size());

                    logger.debug("Better separation found with max component size "+ maxComponentSize);
                    //bestSeparation.setComponents(connectedComponentsHS);
                }
            }
        }
        if(bestSeparation==null){
            bestSeparation = new SeparationMinimumMaxComponentExpriment();
            bestSeparation.setSize(-1);
        }
        return bestSeparation;
    }




    private void testGetProperSeparation() {
        //GraphNew graph = IncidentGraphHelper.getNewStructure(new CliqueCycle().getGraph());
        GraphNew graph = IncidentGraphHelper.getNewStructure(new MyMPSReader().getInducedGraph("samples/noswot.mps"));
        SeparationMinimumMaxComponentExpriment properSeparation = getProperSeparation(graph);
        for(VertexEntity vertex : properSeparation.getSeparation())
            System.out.print(vertex+"-"+graph.getGraphInfo().getNameOf(vertex.getVertex())+ "\t");
        System.out.println();

        for(VertexEntity removingVertex: properSeparation.getSeparation())
            graph = graph.removeVertexCopy(removingVertex.getVertex());

        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);
        for (GraphNew component: connectedComponentsHS) {
            System.out.println(component.getSize());
        }
    }

    private static void getBestSeparationTest() {
        GraphNew graph = null ;
        Integer source = graph.getVertices().get(0);
        Integer sink = graph.getVertices().get(graph.getSize()/2);

        Set<Integer> bestSeparation = MinimumCut.findBestSeparation(graph, source, sink);

        System.out.print("Source is: " +graph.getGraphInfo().getNameOf(source)+ "\t");
        System.out.print("Sink is: " +graph.getGraphInfo().getNameOf(sink)+ "\t");
        System.out.println();
        for(Integer vertex : bestSeparation)
            System.out.print(graph.getGraphInfo().getNameOf(vertex)+ "\t");
    }


}
