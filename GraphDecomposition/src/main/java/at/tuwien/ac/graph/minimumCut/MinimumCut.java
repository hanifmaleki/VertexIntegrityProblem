package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.newgraph.OldFVDSolver;
import org.jgrapht.*;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MinimumSTCutAlgorithm;
import org.jgrapht.graph.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 4/21/17.
 */
public class MinimumCut {

    static Logger logger = LoggerFactory.getLogger(MinimumCut.class);

    public static final int LARGE_VALUE = 10;

    public static long graphTime =0;
    public static long flowTime =0;

    public static Set<Integer> createJGraphAndComputeSeparation(GraphNew graph, Integer source, Integer sink) {

        long startTime = System.currentTimeMillis();
        WeightedGraph<Integer, DefaultWeightedEdge> network = new DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);


        List<Integer> vertices = graph.getVertices();

        for (Integer vertex : vertices) {
            if (vertex.equals(source)) {
                network.addVertex(2*source + 1);
            } else if (vertex.equals(sink)) {
                network.addVertex(2*sink + 0);
            } else {
                Integer s = 2*vertex + 0;
                network.addVertex(s);
                Integer d = 2*vertex + 1;
                network.addVertex(d);
                DefaultWeightedEdge edge = network.addEdge(s, d);
                network.setEdgeWeight(edge, 1);
            }
        }

        for (Integer vertex : vertices) {
            if (vertex.equals(sink))
                continue;
            Integer d = 2 * vertex + 1;
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer neighbor : neighbors) {
                if (neighbor.equals(source))
                    continue;
                Integer n = 2* neighbor + 0;
                DefaultWeightedEdge edge = network.addEdge(d, n);
                network.setEdgeWeight(edge, LARGE_VALUE);
            }
        }

        graphTime +=(System.currentTimeMillis()-startTime);
        startTime = System.currentTimeMillis();

        //MaximumFlowAlgorithm alg = new EdmondsKarpMFImpl(network);
        //MaximumFlowAlgorithm.MaximumFlow maximumFlow = alg.getMaximumFlow(source + "d", sink + "s");

        MinimumSTCutAlgorithm algorithm = new EdmondsKarpMFImpl<Integer, DefaultWeightedEdge>(network);
        double v = algorithm.calculateMinCut(2*source + 1, 2*sink + 0);
        Set<DefaultWeightedEdge> cutEdges = algorithm.getCutEdges();

        flowTime += (System.currentTimeMillis()-startTime);

        Set<Integer> newSet = new LinkedHashSet();
        for(DefaultWeightedEdge edge: cutEdges) {
            Integer edgeSource = network.getEdgeSource(edge);
            newSet.add(edgeSource/2);
        }


        return newSet;

        //return maximumFlow ;

    }

    public static Set<Integer> getMaximumFlow(GraphNew graph) {
        List<Integer> vertices = graph.getVertices();
        Set<Integer> minSet = null;
        //TODO one with min degree for v1
        for (Integer v1 : vertices)
            for (Integer v2 : vertices)
                if ((!v1.equals(v2))&&(!graph.getNeighbors(v1).contains(v2))) {
                    Set<Integer> set = createJGraphAndComputeSeparation(graph, v1, v2);
                    if ((minSet == null) || (set.size() < minSet.size()))
                        minSet = set;
                }
        if(minSet==null) {
            System.out.println("Minimum is :null     For Graph of Size " + graph.getSize());
            return null;
        }

        System.out.println("Minimum is :"+minSet.size()+" For Graph of Size "+graph.getSize());
        OldFVDSolver.globalGraph = graph ;
        System.out.println(OldFVDSolver.toInform(new ArrayList(minSet),false));
        return minSet;
    }

    public static Set<Integer> getMaxMinimumCut(GraphNew graph, int c){
        while(true) {
            List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);
            GraphNew maxComponent = connectedComponentsHS.get(0);
            for (GraphNew component : connectedComponentsHS)
                if (maxComponent.getSize() < component.getSize())
                    maxComponent = component;

            Set<Integer> minimumCut = getMaximumFlow(maxComponent);

            List<Integer> vertices = maxComponent.getVertices();

            for(Integer vertex : minimumCut)
                vertices.remove(vertex);


            graph = maxComponent.getInducedGraph(new HashSet<>(vertices));

            if(graph.getSize()<=c)
                return minimumCut ;
        }
    }

    public static Set<DefaultEdge> getMinimumCut(GraphNew graph){
        UndirectedGraph<Integer, DefaultEdge> network = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        List<Integer> vertices = graph.getVertices();

        for (Integer vertex : vertices)
                network.addVertex(vertex);


        for (Integer vertex : vertices) {
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer neighbor : neighbors) {
                network.addEdge(vertex, neighbor);
            }
        }
        StoerWagnerMinimumCut stoerWagnerMinimumCut = new StoerWagnerMinimumCut(network);

        Set<DefaultEdge> set = stoerWagnerMinimumCut.minCut();

        System.out.println(stoerWagnerMinimumCut.minCutWeight());



        return set;
    }


    public static Set<Integer> findBestSeparation(GraphNew graph, Integer source, Integer sink) {
        int previousMaxComponentSize = Integer.MAX_VALUE;
        int sourceSize= 1;
        int sinkSize = 1;
        Set<Integer> previousSeparation = null ;
        List<GraphNew> previousComponentList = null;
        while (true) {
            if(graph.getNeighbors(source).contains(sink))
                break;
            Set<Integer> separation = createJGraphAndComputeSeparation(graph, source, sink);
            GraphNew newGraph = new GraphNew(graph);
            for (Integer removingVertex : separation)
                newGraph = newGraph.removeVertexCopy(removingVertex);

            List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);
            GraphNew sourceComponent = null;
            GraphNew sinkComponent = null;
            int maxComponentSize = Integer.MIN_VALUE;
            for (GraphNew component : connectedComponentsHS) {
                int ssSize =0;
                if(sourceComponent==null)
                    if (component.getVertices().contains(source)) {
                        sourceComponent = component;
                        ssSize = sourceSize ;
                    }
                if(sinkComponent==null)
                    if (component.getVertices().contains(sink)) {
                        sinkComponent = component;
                        ssSize = sinkSize ;
                    }

                int size = component.getSize();
                //TODO a -1 should be added to lhs
                if (size +ssSize > maxComponentSize)
                    maxComponentSize = size+ssSize;
            }

            //if (maxComponentSize > previousMaxComponentSize)
            //    break ;


            previousMaxComponentSize = maxComponentSize;
            previousSeparation = separation ;
            previousComponentList = connectedComponentsHS ;

            Integer vertex = null;
            GraphNew selectedComponent = null;
            if(sourceSize+ sourceComponent.getSize()>sinkSize+sinkComponent.getSize()) {
                selectedComponent = sinkComponent;
                sinkSize += sinkComponent.getSize() + separation.size();
                vertex = sink ;
            }else {
                selectedComponent = sourceComponent;
                sourceSize += sourceComponent.getSize() + separation.size();
                vertex = source ;
            }


            //TODO reconsider it
            //Here we assume that union of minComponent and separation set is connected

            while (true) {
                List<Integer> neighbors = graph.getNeighbors(vertex);
                Integer selectedNeighbor = null ;
                for (Integer neighbor : neighbors)
                    if ((separation.contains(neighbor)) || (selectedComponent.getVertices().contains(neighbor))) {
                        selectedNeighbor = neighbor ;
                        break;
                    }
                if(selectedNeighbor!=null)
                    graph.contractEdge(vertex, selectedNeighbor);
                else
                    break;
            }

        }


        return previousSeparation;

    }


    public static Set<Integer> findBiggestSecondCompSeparation(GraphNew graph, Integer source, Integer sink) {
        int previousMaxComponentSize = Integer.MAX_VALUE;
        int sourceSize= 1;
        int sinkSize = 1;

        //Set<Integer> previousSeparation = null ;
        //List<GraphNew> previousComponentList = null;

        int myMax = Integer.MIN_VALUE ;
        Set<Integer> bestSeparation = null ;

        int myCounter = 1 ;
        while (true) {
            if(graph.getNeighbors(source).contains(sink))
                break;
            Set<Integer> separation = createJGraphAndComputeSeparation(graph, source, sink);
            GraphNew newGraph = new GraphNew(graph);
            for (Integer removingVertex : separation)
                newGraph = newGraph.removeVertexCopy(removingVertex);

            List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);

            GraphNew sourceComponent = null;
            GraphNew sinkComponent = null;
            int maxComponentSize = Integer.MIN_VALUE;
            String msg = "" ;
            List<Integer> sizeList = new ArrayList();
            for (GraphNew component : connectedComponentsHS) {
                int ssSize =0;
                if(sourceComponent==null)
                    if (component.getVertices().contains(source)) {
                        sourceComponent = component;
                        ssSize = sourceSize ;
                        msg+="s";
                    }
                if(sinkComponent==null)
                    if (component.getVertices().contains(sink)) {
                        sinkComponent = component;
                        ssSize = sinkSize ;
                        msg+="d";
                    }

                int size = component.getSize();

                if (size +ssSize > maxComponentSize)
                    maxComponentSize = size+ssSize-1;

                sizeList.add(ssSize+size);
                msg += size+"("+ssSize+"), ";

            }
            Collections.sort(sizeList, new Comparator<Integer>(){

                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });

            int mySize = sizeList.get(1); // second biggest component
            //logger.debug(msg+ " Sep size is "+ separation.size()+ " max comp size "+ maxComponentSize+
            //" second max comp "+ mySize);

            if(mySize> myMax){
                myMax = mySize ;
                bestSeparation = separation ;
                //logger.debug("find better separation of size "+separation.size()+ " biggest "+
                //        sizeList.get(0) +"  second biggest "+ mySize );
            }

            //if (maxComponentSize > previousMaxComponentSize)
            //    break ;



            previousMaxComponentSize = maxComponentSize;

            Integer vertex = null;
            GraphNew selectedComponent = null;
            if(sourceSize+ sourceComponent.getSize()>sinkSize+sinkComponent.getSize()) {
                selectedComponent = sinkComponent;
                sinkSize += sinkComponent.getSize() + separation.size()-1;
                vertex = sink ;
            }else {
                selectedComponent = sourceComponent;
                sourceSize += sourceComponent.getSize() + separation.size()-1;
                vertex = source ;
            }


            //TODO reconsider it
            //Here we assume that union of minComponent and separation set is connected

            while (true) {
                List<Integer> neighbors = graph.getNeighbors(vertex);
                Integer selectedNeighbor = null ;
                for (Integer neighbor : neighbors)
                    if ((separation.contains(neighbor)) || (selectedComponent.getVertices().contains(neighbor))) {
                        selectedNeighbor = neighbor ;
                        break;
                    }
                if(selectedNeighbor!=null)
                    graph.contractEdge(vertex, selectedNeighbor);
                else
                    break;
            }

        }


        return bestSeparation ;

    }

    public static Set<Integer> findMinimumCutWithThreshold(GraphNew graph, Integer source, Integer sink, int threshold) {
        int previousMaxComponentSize = Integer.MAX_VALUE;
        int sourceSize= 1;
        int sinkSize = 1;

        //Set<Integer> previousSeparation = null ;
        //List<GraphNew> previousComponentList = null;

        int minimumSeparationSize = Integer.MAX_VALUE ;
        Set<Integer> bestSeparation = null ;


        while (true) {
            if(graph.getNeighbors(source).contains(sink))
                break;
            Set<Integer> separation = createJGraphAndComputeSeparation(graph, source, sink);
            GraphNew newGraph = new GraphNew(graph);
            for (Integer removingVertex : separation)
                newGraph = newGraph.removeVertexCopy(removingVertex);

            List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);

            GraphNew sourceComponent = null;
            GraphNew sinkComponent = null;
            int maxComponentSize = Integer.MIN_VALUE;
            String msg = "" ;
            List<Integer> sizeList = new ArrayList();
            for (GraphNew component : connectedComponentsHS) {
                int ssSize =1;
                if(sourceComponent==null)
                    if (component.getVertices().contains(source)) {
                        sourceComponent = component;
                        ssSize = sourceSize ;
                        msg+="s";
                    }
                if(sinkComponent==null)
                    if (component.getVertices().contains(sink)) {
                        sinkComponent = component;
                        ssSize = sinkSize ;
                        msg+="d";
                    }

                int size = component.getSize();

                if (size +ssSize > maxComponentSize)
                    maxComponentSize = size+ssSize-1;

                sizeList.add(ssSize+size-1);
                msg += size+"("+ssSize+"), ";

            }
            Collections.sort(sizeList, new Comparator<Integer>(){

                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });

            int mySize = sizeList.get(1); // second biggest component


            boolean b = (mySize >= threshold) && (separation.size() < minimumSeparationSize);
            if(b){
                minimumSeparationSize = separation.size();
                bestSeparation = separation ;
            }

            if(mySize>= threshold)
                logger.debug("Sep Size: "+ separation.size()+ " source: "+ source + "  sink: "+ sink+"   "+Arrays.toString(sizeList.toArray()));
            //if (maxComponentSize > previousMaxComponentSize)
            //    break ;



            previousMaxComponentSize = maxComponentSize;

            Integer vertex = null;
            GraphNew selectedComponent = null;
            if(sourceSize+ sourceComponent.getSize()>sinkSize+sinkComponent.getSize()) {
                selectedComponent = sinkComponent;
                sinkSize += sinkComponent.getSize() + separation.size()-1;
                vertex = sink ;
            }else {
                selectedComponent = sourceComponent;
                sourceSize += sourceComponent.getSize() + separation.size()-1;
                vertex = source ;
            }


            //TODO reconsider it
            //Here we assume that union of minComponent and separation set is connected

            while (true) {
                List<Integer> neighbors = graph.getNeighbors(vertex);
                Integer selectedNeighbor = null ;
                for (Integer neighbor : neighbors)
                    if ((separation.contains(neighbor)) || (selectedComponent.getVertices().contains(neighbor))) {
                        selectedNeighbor = neighbor ;
                        break;
                    }
                if(selectedNeighbor!=null)
                    graph.contractEdge(vertex, selectedNeighbor);
                else
                    break;
            }

        }


        return bestSeparation ;

    }



}
