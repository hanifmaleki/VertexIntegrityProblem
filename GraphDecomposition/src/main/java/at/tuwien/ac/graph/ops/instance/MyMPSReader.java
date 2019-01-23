package at.tuwien.ac.graph.ops.instance;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.graph.UndirectedGraph;
import at.tuwien.ac.graph.graph.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by root on 4/20/17.
 */
public class MyMPSReader {

    private int counter=0;

    enum STATE {ROWS, COLUMNS, RHS, BOUNDS}

    Logger logger = LoggerFactory.getLogger(MyMPSReader.class);

    public Graph getInducedGraph(String filename) {
        logger.debug(filename);
        Graph graph = new UndirectedGraph();
        String objectiveFunctionName = null;
        try {
            File file = new File(filename);
            //System.out.println(file.getAbsolutePath());
            //System.out.println(file.getPath());
            FileReader reader = new FileReader(file);
            Scanner scan = new Scanner(reader);
            STATE status = null;
            while (scan.hasNextLine()) {
                String string = scan.nextLine();
//                System.out.println(string);
                if (string.trim().equals(""))
                    continue;
                if (string.contains("MARK"))
                    continue;
                if (string.contains("NAME"))
                    continue;
                if (string.contains("ENDATA"))
                    continue;
                String[] result = string.split("\\s+");
                String first = result[0].toUpperCase();
                switch (first) {
                    case "ROWS":
                        logger.debug("Reading Rows");
                        status = STATE.ROWS;
                        break;
                    case "COLUMNS":
                        logger.debug("Reading Columns");
                        status = STATE.COLUMNS;
                        break;
                    case "RHS":
                        status = STATE.RHS;
                        break;
                    case "BOUNDS":
                        status = STATE.BOUNDS;
                        break;
                    default: {
                        if ((status == STATE.ROWS) && (result[1].equals("N"))) {
                            objectiveFunctionName = result[2];
                        } else
                            addToGraph(result, graph, status, objectiveFunctionName);
                    }
                }

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading File");
            e.printStackTrace();
        }
        /*
        //Remove Objective Function Before Returning
        List<Vertex> vertices = graph.getVertices();
        vertices.remove(objectiveFunctionVertex);
        Graph inducedGraph = graph.getInducedGraph(vertices);
        vertices = inducedGraph.getVertices();
        for(Vertex vertex: vertices)
            vertex.setIndex(vertex.getIndex()-1);

        return inducedGraph;
        */
        return graph;
    }

    public void addToGraph(String[] result, Graph graph, STATE status, String objectiveFunctionName) {
        switch (status) {
            case ROWS:
//                System.out.println(result[0] + "| |" + result[1] + "| |" + result[2] + "| |" + result.length);

                //if (result[1].trim().equalsIgnoreCase("N"))
                //    break;
                graph.addVertex(result[2]);
                break;
            case COLUMNS:
                //System.out.println(result[0] + "| |" + result[1] + "| |" + result[2] + "| |" + result.length);

                Vertex from = graph.getVertexByName(result[1]);
                if (from == null)
                    from = graph.addVertex(result[1]);
                if (!result[2].equals(objectiveFunctionName)) {
                    Vertex to = graph.getVertexByName(result[2]);
                    //                Edge edge = new Edge(from, to);
                    graph.addEdge(from, to);
                    logger.debug("The edge has been addad from "+from +" to "+to+".\t" + counter++);
                }
                if (result.length < 5)
                    break;
                if (!result[4].equals(objectiveFunctionName)) {
                    Vertex to = graph.getVertexByName(result[4]);
//                  edge = new Edge(from, to);
                    graph.addEdge(from, to);
                }
                break;
            case RHS:
                //Nothing at the moment
                break;
            case BOUNDS:
                //Nothing at the moment
                status = STATE.BOUNDS;
                break;
        }
    }

}
