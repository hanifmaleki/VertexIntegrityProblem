package at.tuwien.ac.graph.utils;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.newgraph.NewFVDSolver;
import at.tuwien.ac.graph.newgraph.OldFVDSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by e1528895 on 8/15/17.
 */
public class TestBackdoor {

    Logger logger = LoggerFactory.getLogger(TestBackdoor.class);

    int CONSTANT = 2;

//    static int callCounter = 0;

    public List<Integer> getBetterBackdoor(GraphNew graph, List<Integer> backdoor, int c) {
        int size = backdoor.size();
        if (size <= CONSTANT) {
            return solveSmallInstance(graph, backdoor, c);

        }
        List<Integer> minimumBackdoor = null;

        //List<Integer> betterBackdoor = simpleRecursive(graph, backdoor, c);
        //TODO so inefficient code
        int subCounts = (int) Math.pow(2, size);
        long counter = 1;
        for (long i = 0; i < subCounts; i++) {
            BitSet bitSet = convert(i);
            if (bitSet.cardinality() != size / 2)
                continue;

            if(size>c/2)
                System.out.println(size+"\t"+counter++ + "\t"+ i);

            GraphNew graphNew = graph;
            List<Integer> newBackdoor = new ArrayList<>(backdoor);
            for (int j = bitSet.nextSetBit(0); j != -1; j = bitSet.nextSetBit(j + 1)) {
                Integer vertex = backdoor.get(j);
                graphNew = graph.removeVertexCopy(vertex);
                newBackdoor.remove(vertex);
            }

            List<Integer> betterBackdoor = getBetterBackdoor(graphNew, newBackdoor, c);
            if (betterBackdoor.size() < newBackdoor.size()) {
                for (int j = bitSet.nextSetBit(0); i != -1; i = bitSet.nextSetBit(j + 1)) {
                    Integer vertex = backdoor.get(j);
                    betterBackdoor.add(vertex);
                }
                return betterBackdoor;
            }

        }


        //if (betterBackdoor != null) return betterBackdoor;
        //if (size > 7)
        //    System.out.println(size);
        //return minimumBackdoor ;
        return backdoor;
    }

    public List<Integer> solveSmallInstance(GraphNew graph, List<Integer> backdoor, int c) {
        int size = backdoor.size();

        List<Integer> fvd = OldFVDSolver.getFVD(graph, new HashSet<>(graph.getVertices()), size, c, new HashSet<>(), graph.getSize());

        //TODO assert fvd==null should never happen
        if(fvd==null){
            GraphNewHelper.testAnsShowBackdoor(graph, backdoor);
            return backdoor ;
        }
        if (fvd.size() == size)
            return backdoor;
        System.out.println("After Graph size " + graph.getSize() + "\t" + fvd.size() + "\t" + size + "\t" + c);
        GraphNewHelper.testAnsShowBackdoor(graph, fvd);
        return fvd;
    }

    public List<Integer> simpleRecursive(GraphNew graph, List<Integer> backdoor, int c) {
        int size = backdoor.size();
        if (size <= CONSTANT) {
            return solveSmallInstance(graph, backdoor, c);
        }
        for (Integer vertex : backdoor) {
            GraphNew newGraph = graph.removeVertexCopy(vertex);
            List<Integer> newBackdoor = new ArrayList<>(backdoor);
            newBackdoor.remove(vertex);
            List<Integer> betterBackdoor = getBetterBackdoor(newGraph, newBackdoor, c);
            //TODO why the vertex is not added here??????
            if (betterBackdoor.size() < backdoor.size() - 1) {
                betterBackdoor.add(vertex);
                return betterBackdoor;
            }
            //if((minimumBackdoor==null)||(minimumBackdoor.size()>betterBackdoor.size()))
            //    minimumBackdoor = betterBackdoor ;
        }
        return null;
    }


    public BitSet convert(long value) {
        BitSet bits = new BitSet();
        int index = 0;
        while (value != 0L) {
            if (value % 2L != 0) {
                bits.set(index);
            }
            ++index;
            value = value >>> 1;
        }
        return bits;
    }

    public long convert(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }

    public List<Integer> testBackdoor2(GraphNew graph, List<Integer> backdoor, int c){
        int size = backdoor.size();
        if (size <= CONSTANT) {
            logger.debug("Solving simple instance with k size "+ backdoor.size()+" and graph size is "+ graph.getSize());
            return solveSmallInstance(graph, backdoor, c);

        }

        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        List<Integer> newBackdoor = newBackdoor = new ArrayList<>(backdoor);
        if(connectedComponents.size()>1) {
            logger.debug("Graph is disconnected into " + connectedComponents.size() + " Components.");
            for (GraphNew component : connectedComponents) {
                if(component.getSize()<=c)
                    continue;
                List<Integer> intersection = getIntersectionOf(backdoor, component.getVertices());
                List<Integer> compBack = testBackdoor2(component, intersection, c);
                //TODO Assert should never happend
                //if(compBack==null)
                //    return null ;
                if (compBack.size() < intersection.size()) {
                    logger.debug("A better backdoor has been discovered");
                    for (Integer vertex : intersection) {
                        newBackdoor.remove(vertex);
                    }
                    for (Integer vertex : compBack) {
                        newBackdoor.add(vertex);
                    }
                    return newBackdoor;
                }
            }
            return backdoor;
        }else {
            List<Integer> connectedVertices = getAppropriateConnectedVertices(graph, backdoor, c+1);
            List<Integer> instersectionList = getIntersectionOf(backdoor, connectedVertices);
            logger.debug("The intersection size is "+ instersectionList.size()+ " out of "+ backdoor.size());
            logger.debug(Arrays.toString(instersectionList.toArray()));
            GraphNew graphNew = graph;
            for (Integer vertex : instersectionList) {
                newBackdoor = new ArrayList<>(backdoor);
                graphNew = graph.removeVertexCopy(vertex);
                newBackdoor.remove(vertex);
                List<Integer> backList = testBackdoor2(graphNew, newBackdoor, c);
                if(backList.size()<newBackdoor.size()){
                    backList.add(vertex);
                    return backList ;
                }
            }
            return backdoor;
        }

    }

    public List<Integer> getIntersectionOf(List<Integer> list1, List<Integer> list2) {
        List<Integer> instersectionList = new ArrayList<>();
        for(Integer vertex: list2){
            if(list1.contains(vertex))
                instersectionList.add(vertex);
        }
        return instersectionList;
    }

    private List<Integer> getAppropriateConnectedVertices(GraphNew graph, List<Integer> backdoor, int c) {
        //List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(graph, c + 1, true);

        List<Integer> connectedVertices = null;
        int counter = 1 ;
        while(connectedVertices==null) {
            logger.debug("Finding connected Vertices of size "+ counter+ " graph size "+ graph.getSize()+ " and backdoor size "+ backdoor.size());
            connectedVertices = getAppropriateConnectedVertices(graph, backdoor, c, counter++);
        }
        logger.debug(Arrays.toString(connectedVertices.toArray()));
        return connectedVertices;
    }

    private List<Integer> getAppropriateConnectedVertices(GraphNew graph, List<Integer> backdoor, int c, int backdoorCounter) {
        if(backdoorCounter<1)
            return null;
        backdoorCounter-- ;
        for(Integer vertex : backdoor){
            List<Integer> vertices = new ArrayList<>();
            vertices.add(vertex);
            int counter = 0;
            while (true) {
                vertex = vertices.get(counter++);
                List<Integer> neighbors = graph.getNeighbors(vertex);
                for (Integer adjacent : neighbors)
                    if (!vertices.contains(adjacent)) {
                        if (backdoor.contains(adjacent)) {
                            backdoorCounter--;
                            if (backdoorCounter >= 0)
                                vertices.add(adjacent);
                        }else
                            vertices.add(adjacent);
                        if (vertices.size() >= c)
                            return vertices;
                    }
                if(counter==vertices.size())
                    break ;
            }
        }
        return null ;
    }

}
