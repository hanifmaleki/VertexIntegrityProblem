package at.tuwien.ac.graph.tw;

import at.tuwien.ac.graph.newgraph.GraphNew;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/25/17.
 */
public class MinMaxLBGetter {

    public static int getAllStartMaximumMinimumDegree(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        AllStartMaximumMinimumDegree allStartMaximumMinimumDegree = new AllStartMaximumMinimumDegree();

        allStartMaximumMinimumDegree.setInput(nGraph);

        allStartMaximumMinimumDegree.run();

        return allStartMaximumMinimumDegree.getLowerBound();

    }

    public static int getLBMinorMinWidth(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        MinorMinWidth minWidth = new MinorMinWidth();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

    public static int getLBAllStartMinorMinWidth(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        AllStartMinorMinWidth minWidth = new AllStartMinorMinWidth();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

    public static int getLBAllStartMaximumMinimumDegreePlusLeastC(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        AllStartMaximumMinimumDegreePlusLeastC minWidth = new AllStartMaximumMinimumDegreePlusLeastC();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

    public static int getLBRamachandramurthi(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        Ramachandramurthi minWidth = new Ramachandramurthi();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

    public static int getLBAllStartMaximumCardinalitySearch(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        AllStartMaximumCardinalitySearch minWidth = new AllStartMaximumCardinalitySearch();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

 public static int getLBMaximumMinimumDegreePlusMinD(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

     MaximumMinimumDegreePlusMinD minWidth = new MaximumMinimumDegreePlusMinD();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

    public static int getLBMaximumMinimumDegreePlusMinD2(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

     MaximumMinimumDegreePlusMinD2 minWidth = new MaximumMinimumDegreePlusMinD2();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }


    public static int getLBMaximumMinimumDegreePlusMaxD(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        MaximumMinimumDegreePlusMaxD minWidth = new MaximumMinimumDegreePlusMaxD();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }


    public static int getLBMaximumCardinalitySearch(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        MaximumCardinalitySearch minWidth = new MaximumCardinalitySearch();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }

    public static int getLBMaximumMinimumDegreePlusLeastC(GraphNew graph){
        NGraph nGraph = getnGraph(graph);

        MaximumMinimumDegreePlusLeastC minWidth = new MaximumMinimumDegreePlusLeastC();

        minWidth.setInput(nGraph);

        minWidth.run();

        return minWidth.getLowerBound();

    }




    private static NGraph getnGraph(GraphNew graph) {
        NGraph nGraph = new ListGraph();
        List<Integer> vertices = graph.getVertices();
        List<NVertex> nVertices = new ArrayList<>();
        for(Integer vertex : vertices){
            NVertex nVertex = new ListVertex(vertex);
            nVertices.add(vertex, nVertex);
        }

        for (Integer vertex : vertices) {
            List<Integer> neighbors = graph.getNeighbors(vertex);
            NVertex nVertex1 = nVertices.get(vertex);
            for (Integer neighbor : neighbors) {
                NVertex nVertex2 = nVertices.get(neighbor);
                nVertex1.addNeighbor(nVertex2);
            }
            nGraph.addVertex(nVertex1);
        }
        return nGraph;
    }
}
