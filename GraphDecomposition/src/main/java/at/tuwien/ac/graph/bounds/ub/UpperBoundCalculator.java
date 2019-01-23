package at.tuwien.ac.graph.bounds.ub;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;

import java.util.*;

/**
 * Created by root on 4/8/17.
 */

/*
* Important : This class is under refactoring to other classes. e.g. OneMaxDegreeCalculator
 */
public class UpperBoundCalculator {

    public static List<Integer> getHeuristicFVD(GraphNew graph, int c){
        List<Integer> vertexList = new ArrayList<>();
        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);

        Iterator<GraphNew> graphIterator = connectedComponents.iterator();
        while(graphIterator.hasNext()) {
            GraphNew component = graphIterator.next();
            if (component.getSize() <= c)
                graphIterator.remove();
        }

        int size = connectedComponents.size();

        if(size==0)
            return vertexList;

        if(size >1){
            for(GraphNew component: connectedComponents) {
                List<Integer> heuristicFVD = getHeuristicFVD(component, c);
                vertexList.addAll(heuristicFVD);
            }
            return vertexList ;
        }else{
            //int maxDegree = graph.getMaxDegree();
            List<Integer> maxDegreesList = graph.getMaxDegreeList();
            /*Integer selectedVertex = getTowSelectedRemovingVertex(graph, maxDegreesList);
            GraphNew graphNew = graph.removeVertexCopy(selectedVertex);
            List<Integer> heuristicFVD = getUpperBound(graphNew, c);
            heuristicFVD.add(selectedVertex);
            return heuristicFVD;*/
            GraphNew graphNew = null;
            //System.out.println("Vertices are selecting from "+maxDegreesList.size());
            List<Integer> selectedVertex = null;
            if (maxDegreesList.size() == 1) {
                selectedVertex = maxDegreesList;
                graphNew = graph.removeVertexCopy(maxDegreesList.get(0));
            }
            else {
                selectedVertex = getTowSelectedRemovingVertex(graph, maxDegreesList);
                graphNew = graph.removeVertexCopy(selectedVertex.get(0)).removeVertexCopy(selectedVertex.get(1));
            }
            //System.out.println("Vertices are selected from "+maxDegreesList.size());
            List<Integer> heuristicFVD = getHeuristicFVD(graphNew, c);
            heuristicFVD.addAll(selectedVertex);
            return heuristicFVD;
        }
    }

    public static List<Integer> getHeuristicFVD(GraphNew graph) {
        List<Integer> vertexList = new ArrayList<>();

        while(true){
            long time = System.currentTimeMillis();
            List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
            System.out.println((System.currentTimeMillis()-time));

            GraphNew maxComponent = connectedComponents.get(0);

            for (GraphNew component : connectedComponents) {
                if (component.getSize() > maxComponent.getSize())
                    maxComponent = component;
            }

            if (maxComponent.getSize() <= vertexList.size())
                return vertexList;

            List<Integer> maxDegreesList = maxComponent.getMaxDegreeList();

            System.out.println("Maximum Component Size: " + maxComponent.getSize()
                    +"\tVertexList Size: " + vertexList.size()
                    +"\tmax Degree: "+maxComponent.getMaxDegree()
                    +"\tmaxDegreeCount: " + maxDegreesList.size());

            time = System.currentTimeMillis();
            //System.out.println("Vertices are selecting from "+maxDegreesList.size());

            List<Integer> selectedVertex = getSelectedVertex(maxComponent, maxDegreesList, 2);

            for(Integer index: selectedVertex)
                graph = graph.removeVertexCopy(index);

            vertexList.addAll(selectedVertex);
        }

    }

    private static List<Integer> getSelectedVertex(GraphNew graph, List<Integer> maxDegreesList, int count) {
        Integer selected = maxDegreesList.get(0);
        List<Integer> list = new ArrayList<>();
        list.add(selected);
        return list;
        /*
        if((count==1)||(maxDegreesList.size()==1)) {
            Integer selectedRemovingVertex = getSelectedRemovingVertex(graph, maxDegreesList);
            List<Integer> removingList = new ArrayList<>();
            removingList.add(selectedRemovingVertex);
            return removingList;
        }
        return  getTowSelectedRemovingVertex(graph, maxDegreesList);
        */
    }

    private static List<Integer> getTowSelectedRemovingVertex(GraphNew graph, List<Integer> maxDegreesList) {
        List<Integer> selectedIndex = null;
        int min = Integer.MAX_VALUE;
        //try minimum size of maximum component

        //TODO Observation: every iteration of below for(external one) take time of about 23 ms
        for(Integer vertex : maxDegreesList){
            //System.out.println("Before getConnected");
            long time = System.currentTimeMillis();


            //TODO getConnectedComponents work only on the current component
            for(Integer vertex2 : maxDegreesList) {
                GraphNew newGraph = null ;
                if (vertex == vertex2 )
                    newGraph = graph.removeVertexCopy(vertex);
                else
                    newGraph = graph.removeVertexCopy(vertex).removeVertexCopy( vertex2 );

                System.out.print(vertex+"\t");
                List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);

                int max = Integer.MIN_VALUE;
                for (GraphNew component : connectedComponentsHS) {
                    if (component.getSize() > max) {
                        max = component.getSize();
                    }
                }
                if (max < min) {
                    selectedIndex = new LinkedList<Integer>();
                    selectedIndex.add( vertex );
                    selectedIndex.add( vertex2 );
                    min = max;
                }
            }
            //time = System.currentTimeMillis() - time ;
            //System.out.println("After getConnected "+ time);
        }
        return selectedIndex;

    }

    private static Integer getSelectedRemovingVertex(GraphNew graph, List<Integer> maxDegreesList) {
        int selectedIndex = 0;
        int max = 1;
        //try minimum size of maximum component
        for(Integer vertex : maxDegreesList){
            GraphNew newGraph = graph.removeVertexCopy(vertex);
            List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);

            if(connectedComponentsHS.size()>max){
                selectedIndex = maxDegreesList.indexOf(vertex);
                max = connectedComponentsHS.size();
            }
        }
        return maxDegreesList.get(selectedIndex);

    }

    //TODO remove it
    private static GraphNew performTowVertexElimination(GraphNew graph, List<Integer> vertexList, List<Integer> maxDegreesList) {
        List<Integer> selectedVertex = null;
        if (maxDegreesList.size() == 1) {
            selectedVertex = maxDegreesList;
            graph = graph.removeVertexCopy(maxDegreesList.get(0));
            //System.out.println((System.currentTimeMillis()-time));

        } else {
            selectedVertex = getTowSelectedRemovingVertex(graph, maxDegreesList);
            //TODO consider efficiency
            graph = graph.removeVertexCopy(selectedVertex.get(0)).removeVertexCopy(selectedVertex.get(1));
            //System.out.println((System.currentTimeMillis()-time));

        }
        //System.out.println("Vertices are selected from "+maxDegreesList.size());
        vertexList.addAll(selectedVertex);
        return graph;
    }
}
