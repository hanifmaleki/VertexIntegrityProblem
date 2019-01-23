package at.tuwien.ac.graph.newgraph;

import java.util.*;

/**
 * Created by root on 4/6/17.
 */
public class GraphNewHelper {
        private static List<GraphNew> getConnectedComponents(GraphNew graph) {
        List<GraphNew> graphs = new ArrayList<>();
        ArrayList<Integer> vertices = new ArrayList<>(graph.getVertices());
        int n = graph.getN();
        boolean[] visited = new boolean[ n ];
        for (int vIdx = 0; vIdx < vertices.size();vIdx++ ){
                Integer v = vertices.get( vIdx );
                if ( !visited[ v ] ) {
                    boolean[] componentVertices = new boolean[n];
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(v);

                    while (!queue.isEmpty()) {
                        Integer vertex = queue.remove();
                        componentVertices[vertex] = true;
                        visited[vertex] = true;
                        List<Integer> neighbors = graph.getNeighbors(vertex);
                        for (Integer neighbor : neighbors) {
                            if ( !visited[ neighbor ] )
                                queue.add(neighbor);
                        }
                    }
                    graphs.add(graph.getInducedGraph(componentVertices));
                }
            }

        return graphs;
        }

    public static List<GraphNew> getConnectedComponentsHS(GraphNew graph) {
        long time = System.currentTimeMillis();
        List<GraphNew> graphs = new ArrayList<>();
        ArrayList<Integer> vertices = new ArrayList<>(graph.getVertices());
        int n = graph.getN();
        boolean[] visited = new boolean[ n ];
        for (int vIdx = 0; vIdx < vertices.size();vIdx++ ){
            Integer v = vertices.get( vIdx );
            if ( !visited[ v ] ) {
                HashSet<Integer> componentVertices = new LinkedHashSet<Integer>( n );
                Queue<Integer> queue = new LinkedList<>();
                queue.add(v);
                //long time = 0 ;
                //int call = 0;
                while (!queue.isEmpty()) {
                    Integer vertex = queue.remove();
                    long temp = System.currentTimeMillis();
                    componentVertices.add( vertex );
                    //time += (System.currentTimeMillis() - temp) ;
                    //call++;
                    visited[vertex] = true;
                    List<Integer> neighbors = graph.getNeighbors(vertex);
                    for (Integer neighbor : neighbors) {
                        if ( !visited[ neighbor ] )
                            if(!componentVertices.contains(neighbor)) {
                                componentVertices.add(neighbor);
                                queue.add(neighbor);
                            }
                    }
                }

                graphs.add(graph.getInducedGraph(componentVertices));
            }
        }
        //System.out.println((System.currentTimeMillis()-time)+" "+graphs.size());
        return graphs;
    }

    public static List<Integer> getCommonVertices(List<Integer> g, List<Integer> vertexList) {
        List<Integer> commonVertices = new ArrayList();
        for (Integer vertex : vertexList) {

            if (g.contains(vertex))
                commonVertices.add(vertex);
        }
        return commonVertices;
    }

    public static List<Integer> getConnectedVertices(GraphNew graph, int k) {
        return getConnectedVertices(graph, k, false);
    }
    public static List<Integer> getConnectedVertices(GraphNew graph, int k, boolean random) {
        int index = 0 ;
        if(random==true) {
            Random rand = new Random();
            index = rand.nextInt(graph.getSize());
        }
        List<Integer> vertices = new ArrayList<>();
        Integer vertex = graph.getVertices().get(index);
        vertices.add(vertex);
        int counter = 0;
        while (true){
            vertex = vertices.get(counter++);
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for(Integer adjacent: neighbors)
                if(!vertices.contains(adjacent)){
                    vertices.add(adjacent);
                    if(vertices.size()>=k)
                        return vertices;
                }

        }

    }

    public static boolean isSimilar(GraphNew graph, Integer vertex1, Integer vertex2){
        LinkedHashSet<Integer> neighbors1 = new LinkedHashSet<>(graph.getNeighbors(vertex1));
        neighbors1.remove(vertex2);
        LinkedHashSet<Integer> neighbors2 = new LinkedHashSet<>(graph.getNeighbors(vertex2));
        neighbors2.remove(vertex1);
        boolean equals = neighbors1.equals(neighbors2);
        return equals ;
    }

    public static boolean isCovered(GraphNew graph, Integer covering, Integer covered){
        Set<Integer> neighbors1 = new LinkedHashSet<>(graph.getNeighbors(covering));

        LinkedHashSet<Integer> neighbors2 = new LinkedHashSet<>(graph.getNeighbors(covered));
        neighbors2.remove(covering);
        boolean isCovered = true ;
        for(Integer vertex: neighbors2){
            if(!neighbors1.contains(vertex)){
                isCovered=false ;
                break ;
            }
        }
        return isCovered ;
    }

    public static List<Integer> getShortestPath(GraphNew graph, Integer source, Integer sink){
        return null;
    }


    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        HashSet<Integer> integers = new LinkedHashSet<>();
        for(int i=0; i < 100000; i++) {
            double random = Math.random();
            int nu = (int) (random * 10);
            //System.out.println(nu);
            integers.add(new Integer(1));
        }

        System.out.println((System.currentTimeMillis()-time));
    }

    public static void testAnsShowBackdoor(GraphNew graph, List<Integer> integers) {
        System.out.println(integers.size());

        GraphNew graph2 = new GraphNew(graph);
        for(Integer vertex : integers){
            graph2 = graph2.removeVertexCopy(vertex);
        }
        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph2);
        System.out.println("The Number of Components: "+connectedComponentsHS.size());
        for(GraphNew component : connectedComponentsHS)
            System.out.print(component.getSize()+"\t");
        System.out.println();
    }

}
