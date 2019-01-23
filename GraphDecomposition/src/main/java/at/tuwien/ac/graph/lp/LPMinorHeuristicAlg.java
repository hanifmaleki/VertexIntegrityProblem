package at.tuwien.ac.graph.lp;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.executors.LBMinorLPExecutor;
import at.tuwien.ac.graph.ops.expriments.LBMinorLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.ops.instance.MyMPSReader;
import at.tuwien.ac.graph.utils.MPSReaderHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by e1528895 on 9/3/17.
 */
public class LPMinorHeuristicAlg {

    public static void main(String[] args) {
        InstanceManager instanceManager = new InstanceManager();
        Instance instance = instanceManager.getInstanceByName("ns1766074.mps");
        GraphNew graph = instanceManager.getGraph(instance);
        List<Integer> heuristicFVD = new LPMinorHeuristicAlg().getHeuristicFVD(graph, 26);
        System.out.println(heuristicFVD.size());
        GraphNewHelper.testAnsShowBackdoor(graph, heuristicFVD);
    }



    public Integer getMinimumLB(GraphNew graph, int c) {
        List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(graph, 27);
        int min = Integer.MAX_VALUE;
        Integer minVertex = null ;
        for(Integer vertex : graph.getVertices()){
            GraphNew newGraph = graph.removeVertexCopy(vertex);
            LBMinorLPExpriment lpExpriment = new LBMinorLPExecutor().calculateExperiment(newGraph, 26, 50, 300);
            if(lpExpriment.getK()< min){
                min = lpExpriment.getK() ;
                minVertex = vertex ;
            }
        }
        return minVertex;
    }

    List<Integer> getHeuristicFVD(GraphNew graph, int c){
        List<Integer> fvd = new ArrayList<>();
        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);

        Iterator<GraphNew> graphIterator = connectedComponents.iterator();
        while(graphIterator.hasNext()) {
            GraphNew component = graphIterator.next();
            if (component.getSize() <= c)
                graphIterator.remove();
        }

        if(connectedComponents.size()==0)
            return fvd ;

        if(connectedComponents.size() > 1){
            for(GraphNew component: connectedComponents) {
                List<Integer> heuristicFVD = getHeuristicFVD(component, c);
                fvd.addAll(heuristicFVD);
            }
            return fvd ;
        }
        else{
            Integer vertex = getMinimumLB(graph, c);
            GraphNew graphNew = graph.removeVertexCopy(vertex);
            List<Integer> heuristicFVD = getHeuristicFVD(graphNew, c);
            fvd.add(vertex);
            fvd.addAll(heuristicFVD);
            return fvd ;
        }
    }
}
