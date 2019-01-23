package at.tuwien.ac.graph.labeled;

import at.tuwien.ac.graph.newgraph.GraphInfo;
import at.tuwien.ac.graph.newgraph.GraphNew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * Created by root on 7/13/17.
 */
public class LabeledGraph /*extends GraphNew*/ implements Serializable {
    Logger logger = LoggerFactory.getLogger(LabeledGraph.class);

    List<Set<LabeledEdge>> lableEdges = new ArrayList<>();
    LabeledEdge[][] edgeMatrix = null;
    List<LabeledEdge> edges ;
    //private List<Integer> vertices;
    //private GraphInfo graphInfo ;
    GraphNew graphNew ;
    private Integer maxLabel = null;


    /*public LabeledGraph(List<Integer> vertices)
    {
        this(vertices, null, 0);
    }*/

    /*public LabeledGraph(List<Integer> vertices, List<LabeledEdge> edges, int labelCount) {
        this( vertices, edges, labelCount, null);
    }*/

    public LabeledGraph(List<Integer> vertices, List<LabeledEdge> edges, List<Set<LabeledEdge>> lableEdges, GraphInfo graphInfo){
        this.edges = edges ;
        this.lableEdges = lableEdges ;
        int n = graphInfo.getSize();
        List<Integer>[] lists = buildAdjListFrom(n);
        graphNew = new GraphNew(graphInfo, vertices, lists);
    }

    public LabeledGraph(List<Integer> vertices, List<LabeledEdge> edges, int labelCount, GraphInfo graphInfo) {
        this.edges = edges ;
        List<Integer>[] lists = buildAdjListFrom(graphInfo.getSize());
        graphNew = new GraphNew(graphInfo, vertices, lists);
        for(int i= 0; i < labelCount; i++){
            lableEdges.add(new HashSet<LabeledEdge>());
        }
        for(LabeledEdge edge : edges){
            for(Integer label : edge.labels){
                lableEdges.get(label).add(edge);
            }
        }

    }

    private List<Integer>[] buildAdjListFrom(int n) {
        List<Integer>[] adjList ;
        adjList = (ArrayList<Integer>[]) new ArrayList[ n ];
        for(int i = 0; i <n ; i++)
            adjList[i]= new ArrayList<>();
        for(LabeledEdge edge: edges){
            List<Integer> fromList = adjList[edge.from];
            List<Integer> toList = adjList[edge.to];
            if(!fromList.contains(edge.to))
                fromList.add(edge.to);

            if(!toList.contains(edge.from))
                toList.add(edge.from);
        }

        //TODO remove this
        int size = 0 ;
        for (List<Integer> neighborhood:adjList)
            size+=neighborhood.size();
        logger.debug("size based in list is "+ size);

        return adjList ;

    }

    //TODO omplete it: A graph created just by edge
    //public LabeledGraph(List<LabeledEdge> edges){ }
    public void createGraphFromEdgeList(List<LabeledEdge> edgesList){}


    public int getSize() {
        return graphNew.getSize();
    }

    public LabeledGraph removeLabelCopy(Integer label) {
        List<LabeledEdge> newEdgeList = new ArrayList<>(edges);
        for (LabeledEdge edge : lableEdges.get(label)) {
            if (edge.labels.size() == 1) {
                newEdgeList.remove(edge);
                //logger.debug("Removing edge " + edge);
            } else
                edge.labels.remove(label);
        }

        List<Set<LabeledEdge>> newLabelEdge = new ArrayList<>(lableEdges);
        newLabelEdge.get(label).clear();

        //TODO correct it
        return new LabeledGraph(graphNew.getVertices(), newEdgeList, newLabelEdge, graphNew.getGraphInfo());
    }

    public List<Integer> getVertices() {
        return graphNew.getVertices();
    }


    public List<Integer> getNeighbors(Integer vertex) {
        return graphNew.getNeighbors(vertex);
    }


    public LabeledEdge getEdge(Integer vertex, Integer adjacent)
    {
        if (edgeMatrix ==null)
            fillMatrix();

        return edgeMatrix[vertex][adjacent];
    }

    public GraphInfo getGraphInfo() {
        return graphNew.getGraphInfo();
    }

    public GraphNew getGraphNew() {
        return graphNew;
    }

    public void fillMatrix(){
        int n = getGraphNew().getN();
        edgeMatrix = new LabeledEdge[n][n];
        for(LabeledEdge edge : edges){
            edgeMatrix[edge.from][edge.to] = edge ;
            edgeMatrix[edge.to][edge.from] = edge ;
        }
    }

    public int getLabelCount() {
        return lableEdges.size();
    }

    public Integer getMaxLabel() {
        if(maxLabel==null) {
            int max = 0;
            for (int i = 0; i < lableEdges.size(); i++) {
                Set<LabeledEdge> edgeSet = lableEdges.get(i);
                int size = edgeSet.size();
                if (size > max) {
                    maxLabel = i;
                    max = size ;
                }
           }
           logger.debug("Max Label is " + maxLabel + " with size "+ max);

        }

        return maxLabel;
    }

    public int getN(){
        return graphNew.getN();
    }

    public List<Set<LabeledEdge>> getLableEdges() {
        return lableEdges;
    }

    public List<LabeledEdge> getEdges() {
        return edges;
    }

    public List<Integer> getLabels() {
        List<Integer> labelList = new ArrayList<>();
        for(int i = 0 ; i < lableEdges.size(); i++){
            Set<LabeledEdge> list = lableEdges.get(i);
            if((list!=null)&&(list.size()<0))
                labelList.add(i);
        }
        return labelList;
    }

    public Set<Integer> getAdjacentVertices(Integer label) {
        Set<Integer> vertices = new HashSet<>();
        Set<LabeledEdge> edgeSet = lableEdges.get(label);
        for(LabeledEdge edge : edgeSet) {
            vertices.add(edge.from);
            vertices.add(edge.to);
        }
        return vertices;
    }

}
