package at.tuwien.ac.graph.newgraph;

import at.tuwien.ac.graph.ops.executors.SimilarVerticesExecutor;
import at.tuwien.ac.graph.ops.expriments.SimilarVertexEntity;
import at.tuwien.ac.graph.ops.expriments.SimilarVerticesExperiment;

import java.util.*;

/**
 * Created by root on 4/6/17.
 */
public class OldFVDSolver {
    public static long componentsMeasure = 0 ;

    public static long connectivityMeasure =0;

    public static long inducedGraphMeasure=0;

    public static long callCount = 0;

    public static GraphNew globalGraph;


    public static List<Integer> getFVD(GraphNew graph, HashSet<Integer> d, int k, int c, HashSet<Integer> forbiddenVertices, int bestLB) {
        callCount++;
        List<Integer> deletionList = new ArrayList<>();

        long tmp = System.currentTimeMillis();

        KernelizationResult preprocess = KernelizationNew.preprocess(graph, k, c, bestLB);


        //Check if kernelizeation return NO instance
        if(preprocess==null)
            return null;

        List<FVDInstance> fvdInstances = preprocess.getFvdInstanceList();
        deletionList.addAll(preprocess.getRemovedVertices());

        //Check if kernelization return YES instance
        if(fvdInstances.size()==0)
            return deletionList ;

        componentsMeasure += (System.currentTimeMillis()-tmp);
        if (fvdInstances.size() > 1) {

            for (FVDInstance instance : fvdInstances) {
                //for (GraphNew h : components) {
                GraphNew h=instance.getGraph();
                int parameter = instance.getParameter();

                List<Integer> fvd = getFVD(h, d, parameter, c, forbiddenVertices, instance.getGraphBestLB());
                if (fvd == null)
                    return null;

                for (Integer vertex : fvd)
                    deletionList.add(vertex);
            }

            if (deletionList.size() > k)
                return null;

            return deletionList;
        } else {
            //TODO remove it, because it has been already done in kernelization
//            if (graph.getSize() <= c)
//                return deletionList;

//            if (k == 0)
//                return null;

            FVDInstance instance = fvdInstances.get(0);
            GraphNew instanceGraph = instance.getGraph();
            int parameter = instance.getParameter();




            tmp = System.currentTimeMillis();

            List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(instanceGraph, c + 1);
            List<Integer> h = connectedVertices;
            connectivityMeasure += (System.currentTimeMillis()-tmp);

            int min = Integer.MAX_VALUE;
            for (Integer removingVertex : h) {
                if ( !d.contains( removingVertex ) )
                    continue;
                if(forbiddenVertices.contains(removingVertex))
                    continue;

                HashSet<Integer> newForbiden = new LinkedHashSet<>(forbiddenVertices);
                for(Integer vertex: h) {
                    if (vertex <= removingVertex)
                        newForbiden.add(vertex);
                    else
                        if(GraphNewHelper.isSimilar(instanceGraph, removingVertex, vertex))
                            newForbiden.add(vertex);

                }

                tmp = System.currentTimeMillis();
                GraphNew newGraph = instanceGraph.removeVertexCopy(removingVertex);//graph.getInducedGraph(vertexMark);
                inducedGraphMeasure += (System.currentTimeMillis()-tmp);

                List<Integer> fvd = getFVD(newGraph, new HashSet<>(newGraph.getVertices()), parameter - 1, c, newForbiden, instance.getGraphBestLB());
                if ((fvd != null) && (fvd.size() < min - 1 )) {
                    fvd.add(removingVertex);
                    deletionList = fvd;
                    min = fvd.size();

                }

            }

            if (min > parameter) {

                if(k>c-3)
                    //System.out.println("No response for graph:  c: "+ c + "   k: "+parameter);
                    System.out.println("No response for graph: "+ toInform(getLostVertex(graph), true)+ " c: "+ c + "   k: "+parameter);
                return null;
            }
        }

        if(k>c-3)
            //System.out.println("Response for c: "+ c + "   k: "+k);
           System.out.println("Response "+toInform(deletionList, false)+" \nfor graph: {"+ toInform(getLostVertex(graph), true)+ "} c: "+ c + "   k: "+k);
        deletionList.addAll(preprocess.getRemovedVertices());
        return deletionList;
    }

    public static String toInform(List<Integer> lostVertex, boolean indention) {
        if(lostVertex==null)
            return "null";
        String string = "";
        if(indention)
            for(int i=0; i < lostVertex.size();i++)
                string+="\t";
        string+="{";
        for(Integer index: lostVertex)
            string+= index+"("+globalGraph.getGraphInfo().getNameOf(index)+") ";
        string+="}";
        return string;
    }


    private static List<Integer> getLostVertex(GraphNew graph) {
        List<Integer> origList = new ArrayList<Integer>(globalGraph.getVertices());
        List<Integer> vertices = graph.getVertices();
        for(Integer vertex: vertices)
            origList.remove(vertex);
        return origList;
    }

  //public static List<Integer> getFVD(GraphNew graph, HashSet<Integer> d, int k, int c, HashSet<Integer> forbiddenVertices) {
    //public static List<Integer> getFVDForLargeInstance(GraphNew graph, int k , int c ){
        //boolean heuristicCondition = graph.;

        //while(heuristicCondition){

        //}

    //}
}
