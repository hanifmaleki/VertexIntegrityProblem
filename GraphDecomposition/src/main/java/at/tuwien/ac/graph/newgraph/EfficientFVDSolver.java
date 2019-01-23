package at.tuwien.ac.graph.newgraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by root on 4/6/17.
 */
public class EfficientFVDSolver {
    public static long componentsMeasure = 0;

    public static long connectivityMeasure = 0;

    public static long inducedGraphMeasure = 0;

    public static long callCount = 0;

    public static GraphNew globalGraph;


    public static List<Integer> getFVD(List<FVDInstance> components, HashSet<Integer> d, int k, int c, HashSet<Integer> forbiddenVertices) {
        callCount++;
        List<Integer> deletionList = new ArrayList<>();

        if(components.size()==0)
            return deletionList;

        long tmp = System.currentTimeMillis();

        FVDInstance instance = components.remove(0);

        GraphNew graph = instance.getGraph();
        int parameter = k;

        if (graph.getSize() <= c)
            return deletionList;

        if (parameter == 0)
            return null;

        tmp = System.currentTimeMillis();

        List<Integer> h = GraphNewHelper.getConnectedVertices(graph, c + 1);
        connectivityMeasure += (System.currentTimeMillis() - tmp);

        //int min = Integer.MAX_VALUE;
        for (Integer removingVertex : h) {
            if (!d.contains(removingVertex))
                continue;
            if (forbiddenVertices.contains(removingVertex))
                continue;

            HashSet<Integer> newForbiden = new LinkedHashSet<>(forbiddenVertices);
            for (Integer vertex : h) {
                if (vertex <= removingVertex)
                    newForbiden.add(vertex);
                else if (GraphNewHelper.isSimilar(graph, removingVertex, vertex))
                    newForbiden.add(vertex);
            }

            tmp = System.currentTimeMillis();
            GraphNew newGraph = graph.removeVertexCopy(removingVertex);//graph.getInducedGraph(vertexMark);
            inducedGraphMeasure += (System.currentTimeMillis() - tmp);

            //List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);

            KernelizationResult kernelizationResult = KernelizationNew.sortedPreprocess(newGraph, parameter - 1, c);
            List<FVDInstance> newInstances = kernelizationResult.getFvdInstanceList();
            if(newInstances==null)
                return null;

            List<FVDInstance> fvdInstances = new ArrayList<>();
            int lIndex = 0;
            int rIndex = 0;
            while ( lIndex < newInstances.size() && rIndex < components.size() ) {
                if ( newInstances.get( lIndex ).getGraph().getSize() > components.get( rIndex ).getGraph().getSize() )
                    fvdInstances.add( fvdInstances.size(), newInstances.get( lIndex++ ) );
                else
                    fvdInstances.add( fvdInstances.size(), components.get( rIndex++ ) );

            }
            for ( int i = lIndex; i < newInstances.size(); i++ )
                fvdInstances.add( fvdInstances.size(), newInstances.get( i ) );
            for ( int i = rIndex; i < components.size(); i++ )
                fvdInstances.add( fvdInstances.size(), components.get( i ) );

            List<Integer> fvd = getFVD(fvdInstances, d, parameter - 1, c, newForbiden);


            if ( fvd == null )
                continue;

            fvd.add(removingVertex);
            return fvd;
        }

        return null;


    }

    public static String toInform(List<Integer> lostVertex, boolean indention) {
        if (lostVertex == null)
            return "null";
        String string = "";
        if (indention)
            for (int i = 0; i < lostVertex.size(); i++)
                string += "\t";
        string += "{";
        for (Integer index : lostVertex)
            string += globalGraph.getGraphInfo().getNameOf(index) + " ";
        string += "}";
        return string;
    }


    private static List<Integer> getLostVertex(GraphNew graph) {
        List<Integer> origList = new ArrayList<Integer>(globalGraph.getVertices());
        List<Integer> vertices = graph.getVertices();
        for (Integer vertex : vertices)
            origList.remove(vertex);
        return origList;
    }
}
