package at.tuwien.ac.graph.newgraph;


import at.tuwien.ac.graph.bounds.lb.LBManager;
import at.tuwien.ac.graph.bounds.lb.mdst.MinimumDegreeSpanningTreeLB;
import at.tuwien.ac.graph.lp.NSquareformulation;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;

import java.util.*;

/**
 * Created by root on 4/4/17.
 */
public class KernelizationNew {


    public static KernelizationResult sortedPreprocess(GraphNew graph, int k, int c) {
        List<FVDInstance> fvdInstances = new ArrayList<>();
        List<Integer> removingVertices = new ArrayList<>();
        KernelizationResult kernelizationResult = new KernelizationResult(fvdInstances, removingVertices);


        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);


        //Removing Small Components
        Iterator<GraphNew> graphIterator = connectedComponents.iterator();
        while(graphIterator.hasNext()) {
            GraphNew component = graphIterator.next();
            if (component.getSize() <= c)
                graphIterator.remove();
        }


        int size = connectedComponents.size();

        if(size==0)
            return kernelizationResult;

        if(k==0)
            return null;

        for (GraphNew component : connectedComponents) {
            fvdInstances.add(new FVDInstance(component, k, 0));

        }

        Collections.sort(fvdInstances);
        return kernelizationResult;
    }

    public static KernelizationResult preprocess(GraphNew graph, int k, int c, int bestLB) {
        List<FVDInstance> fvdInstances = new ArrayList<>();
        List<Integer> removingVertices = new ArrayList<>();
        KernelizationResult kernelizationResult = new KernelizationResult(fvdInstances, removingVertices);

        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);


        //Removing Small Components
        Iterator<GraphNew> graphIterator = connectedComponents.iterator();
        while(graphIterator.hasNext()) {
            GraphNew component = graphIterator.next();
            if (component.getSize() <= c)
                graphIterator.remove();
        }

        int size = connectedComponents.size();

        if(size==0)
            return kernelizationResult;

        if (size > 1) {

            int lbs[] = new int[size];
            int i = 0;
            int total = 0;
            for (GraphNew component : connectedComponents) {
                int lowerBound = LBManager.getBestLowerBound(k, c, component, false);

                lbs[i++] = lowerBound;
                total+= lowerBound ;
            }
            if(total > k)
                return null;


            i = 0;
            for (GraphNew component : connectedComponents) {
                total = 0;
                for (int j = 0; j < size; j++) {
                    if (j == i) continue;
                    total += lbs[j];
                }
                KernelizationResult result = preprocessConnectedComponent(component, k - total, c,lbs[i]);


                if(result==null)
                    return null;

                fvdInstances.addAll(result.getFvdInstanceList());

                i++;
            }
            return kernelizationResult;
        } else {
            GraphNew component = connectedComponents.get(0);
            if(bestLB>=component.getSize())
                bestLB = LBManager.getBestLowerBound(k, c, component, false);
            return preprocessConnectedComponent(component, k, c, bestLB);
        }
    }

    private static KernelizationResult preprocessConnectedComponent(GraphNew component, int k, int c, int bestLB) {
        LinkedHashSet<Integer> vertexSet = new LinkedHashSet<>(component.getVertices());
        List<FVDInstance> fvdInstances = new ArrayList<>();
        List<Integer> removingVertices = new ArrayList<>();
        KernelizationResult kernelizationResult = new KernelizationResult(fvdInstances, removingVertices);


        //There is no need for checking small components
        if (vertexSet.size() <= c) {
            return kernelizationResult;
        }

        if(k<1)
            return null;

        //if(k< getLowerBound(component,k ,c))
        //TODO make sure equal = is not necessary in the following condition
        if(k < bestLB) {
            boolean lpCalculation = false;
            //System.out.println("Prevoius Best LB is " + bestLB);
            bestLB = LBManager.getBestLowerBound(k, c, component, lpCalculation);
            if (k < bestLB)
                return null;
        }

        for (Integer vertex : vertexSet) {
            if (component.getDegreeof(vertex) > k + c) {
                //System.out.println("Removing vertex "+ vertex + " with degree of "+ component.getDegreeof(vertex));
                vertexSet.remove(vertex);

                GraphNew newGraph = component.getInducedGraph(vertexSet);
                KernelizationResult result = preprocess(newGraph, k - 1, c, bestLB);
                if(result==null)
                    return null;
                result.getRemovedVertices().add(vertex);
                return result;
            }
        }

        int maxDegree = component.getMaxDegree();
        //int vertexub = k+ (k*c*(k+c));
        int vertexub = k+ (k*c*maxDegree);
        if(vertexSet.size()> vertexub)
            return null;

        fvdInstances.add(new FVDInstance(component, k, bestLB));

        return kernelizationResult;

    }



}
