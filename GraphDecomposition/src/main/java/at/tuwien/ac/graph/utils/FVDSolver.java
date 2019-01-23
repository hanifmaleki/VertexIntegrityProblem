package at.tuwien.ac.graph.utils;

import at.tuwien.ac.graph.graph.ComposeDS;
import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.graph.Vertex;

import java.util.*;

/**
 * Created by hanif on 3/20/17.
 */

public class FVDSolver {

    //public static HashMap<ComposeDS, BitSet> callsMap = new LinkedHashMap<>();
    
    public static long componentsMeasure = 0 ;

    public static long connectivityMeasure =0;

    public static long inducedGraphMeasure=0;

    public static long callCount = 0;

    public static Graph globalGraph;



    public static List<Vertex> getFVD(Graph graph, List<Vertex> d, int k, int c, HashSet<Vertex> forbiddenVertices) {
        callCount++;
        List<Vertex> deletionList = new ArrayList<>();
        /*ComposeDS composeDS = getComposeDS(graph, k);

        if(callsMap.containsKey(composeDS)) {
            BitSet bitSet = callsMap.get(composeDS);
            List<Vertex> vertexList = getVertexList(bitSet);
            return vertexList;
        }*/

        if (graph.getSize() == 1) {
            //callsMap.put(composeDS, getBitSet(deletionList));
            return deletionList;
        }

        long tmp = System.currentTimeMillis();
        List<Graph> components = IncidentGraphHelper.getConnectedComponents3(graph);
        componentsMeasure += (System.currentTimeMillis()-tmp);
        if (components.size() > 1) {

            for (Graph h : components) {
                List<Vertex> vertices = IncidentGraphHelper.getCommonVertices(h.getVertices(), d);
                List<Vertex> fvd = getFVD(h, vertices, k, c, forbiddenVertices);
                if (fvd == null) {
                    //callsMap.put(composeDS, null);
                    return null;
                }
                for (Vertex vertex : fvd)
//                    if (!deletionList.contains(vertex))
                    deletionList.add(vertex);
            }
            if (deletionList.size() > k) {
                //callsMap.put(composeDS, null);
                return null;
            }
            //callsMap.put(composeDS, getBitSet(deletionList));
            return deletionList;
        } else {
            if (graph.getSize() <= c){
                //callsMap.put(composeDS, getBitSet(deletionList));
                return deletionList;
            }

            if (k == 0) {
                //callsMap.put(composeDS, null);
                return null;
            }

            tmp = System.currentTimeMillis();

            List<Vertex> h = IncidentGraphHelper.getConnectedVertices(graph, c + 1);
            connectivityMeasure += (System.currentTimeMillis()-tmp);

            List<Vertex> commonVertices = IncidentGraphHelper.getCommonVertices(h, d);
            if (commonVertices.size() == 0) {
                //callsMap.put(composeDS, null);
                return null;
            }

            int min = Integer.MAX_VALUE;
            for (Vertex removingVertex : commonVertices) {

                if(forbiddenVertices.contains(removingVertex))
                    continue;
                HashSet<Vertex> newForbiden = new LinkedHashSet<>(forbiddenVertices);
                for(Vertex vertex: commonVertices)
                    if(vertex.getIndex()<=removingVertex.getIndex())
                        newForbiden.add(vertex);

                //TODO reconsider it
                List<Vertex> vertices = graph.getVertices();
                vertices.remove(removingVertex);

                tmp = System.currentTimeMillis();
                Graph newGraph = /*graph.removeVertex(removingVertex);*/graph.getInducedGraph(vertices);
                inducedGraphMeasure += (System.currentTimeMillis()-tmp);



                List<Vertex> fvd = getFVD(newGraph, d, k - 1, c, newForbiden);
                if ((fvd != null) && (fvd.size() < min-1)) {
                    fvd.add(removingVertex);
                    deletionList = fvd;
                    min = fvd.size();

                }

            }
            if (min > k) {
                //callsMap.put(composeDS, null);
                if(k>c-3)
                    System.out.println("No response for graph: "+ getLostVertex(graph));
                return null;
            }
        }
        //callsMap.put(composeDS, getBitSet(deletionList));
        if(k>c-3)
            System.out.println("Response "+deletionList+" \nfor graph: "+ getLostVertex(graph));
        return deletionList;
    }

    private static List<Vertex> getLostVertex(Graph graph) {
        List<Vertex> origList = globalGraph.getVertices();
        List<Vertex> vertices = graph.getVertices();
        for(Vertex vertex: vertices)
            origList.remove(vertex);
        return origList;
    }

    private static ComposeDS getComposeDS(Graph graph, int k) {
        List<Vertex> vertices = graph.getVertices();
        BitSet bitSet = getBitSet(vertices);
        return new ComposeDS(k,bitSet);
    }

    private static BitSet getBitSet(List<Vertex> vertices) {
        BitSet bitSet = new BitSet(globalGraph.getSize());
        for(Vertex vertex : vertices){
            bitSet.flip(vertex.getIndex());
        }
        return bitSet ;
    }

    private static List<Vertex> getVertexList(BitSet bitSet){
        if(bitSet==null)
            return null;
        List<Vertex> vertexList = new ArrayList<>();
        List<Vertex> globalList = globalGraph.getVertices();
        for(int i=0 ; i< bitSet.length();i++){
            if(bitSet.get(i))
                vertexList.add(globalList.get(i));
        }
        return vertexList;
    }
}
