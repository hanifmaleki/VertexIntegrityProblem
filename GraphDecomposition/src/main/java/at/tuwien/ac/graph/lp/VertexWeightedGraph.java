package at.tuwien.ac.graph.lp;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.newgraph.GraphInfo;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.instance.MyMPSReader;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 7/18/17.
 */
public class VertexWeightedGraph extends GraphNew {

    org.slf4j.Logger logger = LoggerFactory.getLogger(VertexWeightedGraph.class.getCanonicalName());


    private static final double EPSILON = 0.9;
    private List<Double> weights;

    public VertexWeightedGraph(int n, GraphInfo graphInfo) {
        super(n, graphInfo);
    }

    public VertexWeightedGraph(GraphNew graph) {
        super(graph);
    }

    public VertexWeightedGraph(GraphInfo graphInfo, ArrayList<Integer> vertexList, ArrayList<Integer>[] adjList) {
        super(graphInfo, vertexList, adjList);
    }

    public VertexWeightedGraph(GraphNew graph, List<Double> weights) {
        super(graph);
        this.weights = weights;
    }

    @Override
    public GraphNew removeVertexCopy(Integer vertex) {
        GraphNew graphNew = super.removeVertexCopy(vertex);

        return new VertexWeightedGraph(graphNew, weights);
    }

    public List<Integer> getASingleBadConnectedVertices(Set<Integer> extended, Set<Integer> extendable, int c, double weight) {

        if (weight >= c)
            return null;
        if ((c == 0) && weight > 0)//TODO add epsilon
            return null;
        if (weight < 0)
            return new ArrayList<>();

        if(getSize()<weight)
            return null ;

        if ((extendable == null) && (extended == null)) {
            //if (extended == null) {
            //for(Integer vertex : vertices ){
            Random random = new Random();
            long id = random.nextLong();

            Integer vertex = vertices.get(0);
            if (weights.get(vertex) > EPSILON) {
                Set<Integer> set = new HashSet<>();
                set.add(vertex);
                List<Integer> connectedVertices = getASingleBadConnectedVertices(null, set, c - 1, weight - weights.get(vertex));
                if (connectedVertices != null) {

                    double total = 0;
                    for (Integer item : connectedVertices) {
                        total += weights.get(item);
                    }
                    logger.debug( total + "\tvertex "+ vertex+ "  with weight:" + weights.get(vertex)+ "\ttotal: "+(weights.get(vertex)+total));
                    connectedVertices.add(vertex);
                    return connectedVertices;
                }

            }
            VertexWeightedGraph graphNew = (VertexWeightedGraph) removeVertexCopy(vertex);
            logger.debug("\tremoving vertex:"+vertex);
            return graphNew.getASingleBadConnectedVertices(null, null, c, weight);
        }

        if (extendable.size() == 0)
            return null;

        if (extended == null)
            extended = new HashSet<>();
        //System.out.println(c + "\t"+ weight+"\t"+extended.size()+"\t"+extendable.size());


        for (Integer vertex : extendable) {
            if(c>12)
                logger.debug("c:"+c+"\tweight"+ weight+"\textendable size: "+extendable.size());
            List<Integer> neighbors = getNeighbors(vertex);
            List<Integer> allowedNeighbors = new ArrayList<>();

            for (Integer neighbor : neighbors) {
                if (!extendable.contains(neighbor))
                    if (!extended.contains(neighbor))
                        allowedNeighbors.add(neighbor);
            }


            int size = allowedNeighbors.size();
            if(c>12)
                logger.debug("Extending Vertex: "+ vertex+"\t neighbors: "+neighbors.size()+ "\tallowed neighbors: "+allowedNeighbors.size());
            int pow = (int) Math.pow(2, size);
            int length = Integer.toBinaryString(pow).length() - 1;

            for (int i = 0; i < pow; i++) {
                String s = Integer.toBinaryString(i);
                //logger.debug(i + " out of "+ pow);

                //Prevent selecting more than c of allowedNeighbors
                int counter = 0;
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '1')
                        counter++;
                }
                if (counter > c)
                    continue;

                for (int j = s.length(); j < length; j++)
                    s = "0" + s;

                if(c==13)
                    logger.debug("S= "+ s);

                double newWeight = weight;
                int newC = c;
                Set<Integer> newExtendable = new HashSet<>();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '1') {
                        //TODO check it
                        Integer neighbor = allowedNeighbors.get(length - j - 1);
                        newExtendable.add(neighbor);
                        newWeight -= weights.get(neighbor);
                        newC--;

                        if (newWeight < 0) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.addAll(newExtendable);
                            //System.out.println("newWeight: "+newWeight);
                            return list;
                        }

                    }
                }

                Set<Integer> newParam = new HashSet<>();
                newParam.addAll(extendable);
                newParam.addAll(newExtendable);
                newParam.remove(vertex);
                extended.add(vertex);
                List<Integer> aSingleBadConnectedVertices = getASingleBadConnectedVertices(extended, newExtendable, newC, newWeight);
                extended.remove(vertex);
                if (aSingleBadConnectedVertices != null) {
                    double totalWegiht = 0;
                    aSingleBadConnectedVertices.addAll(newExtendable);
                    for (Integer item : aSingleBadConnectedVertices)
                        totalWegiht += weights.get(item);
                    if (totalWegiht - weight > EPSILON) {
                        return aSingleBadConnectedVertices;
                    }//else
                    //logger.debug("c: "+c+"\tweight: "+ weight+"\ttotal: "+totalWegiht);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Graph inducedGraph = new MyMPSReader().getInducedGraph("samples/noswot.mps");
        GraphNew graph = IncidentGraphHelper.getNewStructure(inducedGraph);
        List<Double> weightList = new ArrayList<>();
        for(int i = 0; i< graph.getSize(); i++)
            weightList.add(0.5);
        VertexWeightedGraph weightedGraph = new VertexWeightedGraph(graph, weightList);
        List<Integer> aSingleBadConnectedVertices = weightedGraph.getASingleBadConnectedVertices(null, null, 20, 19);
        if(aSingleBadConnectedVertices!=null)
            System.out.println(aSingleBadConnectedVertices.size());
        else System.out.println("This is null");
    }
}
    