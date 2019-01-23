package at.tuwien.ac.graph.utils;

import at.tuwien.ac.graph.labeled.*;
import at.tuwien.ac.graph.newgraph.GraphInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 8/14/17.
 */
public class GFReader {
    private Logger logger = LoggerFactory.getLogger(GFReader.class);

    public LabeledGraph readFromFile(String filename){
        String line = null;
        List<LabeledEdge> edgeList = new ArrayList<>();
        List<Integer> vertices = new ArrayList<>();
        int labelCount = 0;
        LabeledEdge[][] edgeMatrix = null;
        GraphInfo graphInfo = null;
        Integer size = null ;

        try {
            graphInfo = readVerticesFromFile(filename);
            size = graphInfo.getSize();
            edgeMatrix = new LabeledEdge[size][size];
            for(int i= 0; i < size; i++)
                for(int j= 0; j < size; j++)
                    edgeMatrix[i][j]=null ;
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String[] splited = line.split("\\s+");
                Integer vertex1 = graphInfo.getVertexByName(splited[0]);
                Integer vertex2 = graphInfo.getVertexByName(splited[1]);
                if((vertex1==vertex2)||(vertex1==null)||(vertex2==null)) {
                    logger.debug("Error! in line "+ line);
                    logger.debug("Error!. Edge Between " + vertex1 + "(" + splited[0] + ") and " + vertex2 + "(" + splited[1] + ")");
                    continue ;
                }



                LabeledEdge edge = edgeMatrix[vertex1][vertex2];
                if(edge==null){
                    edge = new LabeledEdge();
                    edge.from = vertex1 ;
                    edge.to = vertex2 ;
                    edge.labels = new HashSet<>();
                    edgeList.add(edge);
                    edgeMatrix[vertex1][vertex2] = edge ;
                    edgeMatrix[vertex2][vertex1] = edge ;
                }
                int label = Integer.parseInt(splited[2]);
                edge.labels.add(label);
                //It is assumed that labels in input file are sorted
                if(label >= labelCount)
                    labelCount = label +1 ;
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filename + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filename + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        LabeledGraph graph = new LabeledGraph(graphInfo.getVertices(), edgeList, labelCount, graphInfo);
        //TODO remove it
        int myCounter = 0;
        for(int i= 0; i < size; i++)
            for(int j= 0; j < size; j++)
                if(edgeMatrix[i][j]!=null)
                    myCounter++ ;
        logger.debug("The number of edges based on the matrix is " + myCounter);
        //TODO move it
        writeInstanceToFile(graph, filename);
        return graph ;
    }

    private GraphInfo readVerticesFromFile(String filename) throws IOException {
        String line = null;
        int vertexCounter = 0;
        List<Integer> vertices = new ArrayList<>();
        int labelCount = 0;
        GraphInfo graphInfo = new GraphInfo();


        // FileReader reads text files in the default encoding.
        FileReader fileReader =
                new FileReader(filename);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            //System.out.println(line);
            String[] splited = line.split("\\s+");
            Integer vertex1 = graphInfo.getVertexByName(splited[0]);
            if (vertex1 == null) {
                graphInfo.addVertex(vertexCounter, splited[0]);
                vertices.add(vertexCounter);
                vertexCounter++;
            }
            Integer vertex2 = graphInfo.getVertexByName(splited[1]);
            if (vertex2 == null) {
                graphInfo.addVertex(vertexCounter, splited[1]);
                vertices.add(vertexCounter);
                vertexCounter++;
            }
        }

        // Always close files.
        bufferedReader.close();

        logger.debug("The number of vertices is " + graphInfo.getSize());

        return graphInfo;
    }

    public static void main(String[] args) {
        LabeledGraph labeledGraph = new GFReader().readFromFile("samples/planning/elevator3.gf");
        System.out.println(labeledGraph.getSize());
        System.out.println("Edges "+ labeledGraph.getGraphNew().getEdgeSize());
        System.out.println("Label Count "+ labeledGraph.getLabelCount());
        Integer maxLabel = labeledGraph.getMaxLabel();
        //List<Set<LabeledEdge>> lableEdges = labeledGraph.getLableEdges();
        System.out.println("Number of Edges "+ labeledGraph.getEdges().size());

        int c = 20;
        int k = c ;
        List<Integer> backdoor = new CausalDetection().getBackdoor(labeledGraph, c, k, -1);
        if(backdoor!= null) {
            System.out.println("The size of backdoor " + backdoor.size());

            for (Integer label : backdoor) {
                labeledGraph = labeledGraph.removeLabelCopy(label);
            }
            List<LabeledGraph> connectedComponents = new LabeledGraphHelper().getConnectedComponents(labeledGraph);
            for (LabeledGraph component : connectedComponents)
                System.out.println("Component: " + component.getSize());
        }else
            System.out.println("Backdoor is null");


        //List<Integer> upperBound = new Upperbound().getUpperBound2(labeledGraph);
        //System.out.println(upperBound.size());

    }


    //TODO
    private List<Integer> myLowerBound(LabeledGraph graph){
        List<Integer> lowerbounds = null;

        for(Integer vertex : graph.getGraphNew().getVertices()){
            Set<Integer> list = null;
        }
        return null ;
    }

    public void writeInstanceToFile(LabeledGraph graph, String fileName){
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream( fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(graph);
            oos.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LabeledGraph readInstanceFromFile(String name){
        FileInputStream fis = null;
        LabeledGraph graph = null;
        String fileName = name.substring(0, name.lastIndexOf("."));
        try {
            fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            graph = (LabeledGraph) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
