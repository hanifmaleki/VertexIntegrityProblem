package at.tuwien.ac.graph.newgraph;


import java.io.Serializable;
import java.util.*;

/**
 * Created by root on 4/4/17.
 */
public class GraphNew implements Serializable{
    //private /*final*/ ArrayList<ArrayList<Integer>> adjList;
    protected /*final*/ List<Integer>[] adjList;
    protected /*final*/ List<Integer> vertices;

    //private int n;

    private GraphInfo graphInfo;
    private int maxDegree = -1;

    private List<Integer> maxDegreesList = null;
    private Integer  minDegree= null;
    private List<Integer> minDegreesList = null;

    public GraphNew( int n, GraphInfo graphInfo) {
        //this.n = n;
        this.graphInfo = graphInfo;
        adjList = (ArrayList<Integer>[]) new ArrayList[ n ];
        //adjList = new ArrayList<ArrayList<Integer>>();
        vertices = new ArrayList<Integer>( n );
        for ( int i = 0; i < n; i++ ) {
            adjList[i]= new ArrayList<Integer>();
            vertices.add(i, i );
        }
    }

    public GraphNew(GraphNew graph){
        vertices = new ArrayList<>(graph.getVertices());
        adjList = (ArrayList<Integer>[]) new ArrayList[graph.getN()];
        for(int i=0; i < adjList.length ; i++){
                List<Integer> neighbors = graph.getNeighbors(i);
                if(neighbors!=null)
                    adjList[i] = new ArrayList<>(neighbors);
        }
        this.graphInfo = graph.graphInfo ;
    }

    public GraphNew( GraphInfo graphInfo, List<Integer> vertexList, List<Integer>[] adjList) {
        //this.n = n;
        this.graphInfo = graphInfo;
        this.vertices = vertexList;
        this.adjList = adjList;
    }

    public GraphNew getInducedGraph(boolean[] vertices ) {
        int n = adjList.length;
        ArrayList<Integer> vertexList = new ArrayList<>();
        ArrayList<Integer>[] newAdjList =  (ArrayList<Integer>[]) new ArrayList[ n ];

        for ( int i = 0; i < n; i++ ) {
            newAdjList[i] = new ArrayList<Integer>();
            if ( vertices[i] ) {
                vertexList.add(i);
                for ( Integer neighbor : getNeighbors(i) ) {
                    if ( vertices[ neighbor ] )
                        newAdjList[i].add(neighbor);
                }
            }
        }
        GraphNew graph = new GraphNew(graphInfo, vertexList, adjList);
        return graph;
    }

    public GraphNew removeVertexCopy( Integer vertex ) {
        int n = adjList.length;
        ArrayList<Integer> vertexList = new ArrayList<>();
        ArrayList<Integer>[] newAdjList =  (ArrayList<Integer>[]) new ArrayList[ n ];
        for ( int i = 0; i < n; i++ ) {
            if(adjList[i]==null)
                continue;
            if (( !vertex.equals( i )) &&(adjList[i]!=null)) {
                newAdjList[i] = new ArrayList<Integer>();
                vertexList.add(i);
                for ( Integer neighbor : getNeighbors(i) ) {
                    if ( !neighbor.equals( vertex ) )
                        newAdjList[i].add(neighbor);
                }
            }else
                newAdjList[i] = null;
        }
        /*for ( int i = 0; i < n; i++ ) {

            newAdjList[i] = new ArrayList<Integer>();
            if ( !vertex.equals( i ) ) {
                vertexList.add(i);
                for ( Integer neighbor : getNeighbors(i) ) {
                    if ( !neighbor.equals( vertex ) )
                        newAdjList[i].add(neighbor);
                }
            }
        }*/
        GraphNew graph = new GraphNew(graphInfo, vertexList, newAdjList);
        return graph;
    }

    public GraphNew getInducedGraph(HashSet<Integer> vertices ) {
        int n = adjList.length;
        ArrayList<Integer> vertexList = new ArrayList<>();
        ArrayList<Integer>[] newAdjList =  (ArrayList<Integer>[]) new ArrayList[ n ];

        for ( int i = 0; i < n; i++ ) {
            if ( vertices.contains( i ) ) {
                newAdjList[i] = new ArrayList<Integer>();
                vertexList.add(i);
                for ( Integer neighbor : getNeighbors(i) ) {
                    if ( vertices.contains( neighbor ) )
                        newAdjList[i].add(neighbor);
                }
            }
        }
        GraphNew graph = new GraphNew(graphInfo, vertexList, newAdjList);
        return graph;
    }

    /*public GraphNew getInducedGraph(ArrayList<Integer> vertices ) {
        int n = adjList.length;
        ArrayList<Integer>[] newAdjList =  (ArrayList<Integer>[]) new ArrayList[ n ];

        for ( int i = 0; i < n; i++ )
            newAdjList[i] = new ArrayList<Integer>();

        for(Integer vertex: vertices){

            newAdjList[vertex]
        }

    }*/

    public int getSize(){
        return vertices.size();
    }

    public int getN() {
        return adjList.length;
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public void addEdge( int i, int j ) {
        adjList[i].add(j);
        //adjList[j].add( i );
        maxDegree = -1 ;
    }

    public List<Integer> getNeighbors( int v ) {
        return adjList[v];
    }

    /*public Integer getVertexByName(String name){
        return graphInfo.getVertexByName(name);
    }*/

    /*public Integer addVertex(String name){
        Integer index = vertices.size();
        graphInfo.addVertex(name, index);
        return index;
    }*/

    public int getMaxDegree() {
        if (maxDegree > -1)
            return maxDegree;

        int max = -1;

        /*for(List<Integer> list: adjList)
            if(list.size()> max)
                max= list.size();*/
        for(Integer vertex : vertices){
            int degree = getDegreeof(vertex);
            if(degree >max)
                max = degree;
        }

        maxDegree = max;
        return max;
    }

    public int getDegreeof(Integer index){
        return adjList[index].size();
    }

    public GraphInfo getGraphInfo() {
        return graphInfo;
    }

    @Override
    public String toString() {
        String verticesString = "";
        int size = vertices.size();
        for(int i = 0; i < size; i++){
            verticesString+="V["+i+", "+graphInfo.getNameOf(i)+"] ";
        }
        verticesString+="\n";

        String edgesString = "";
        size = adjList.length;
        for(int i = 0; i < size; i++){
            if(vertices.contains(i)) {
                edgesString += "V(" + graphInfo.getNameOf(i) + "): ";
                for(Integer index : adjList[i])
                    edgesString+= graphInfo.getNameOf(index)+", ";

                edgesString += "\n";
            }
        }

        return "Graph{" +
                verticesString + edgesString +
                '}';
    }

    public int getEdgeSize() {
        //TODO consider it : a diveide by 2 is needed at the end
        int size = 0 ;
        for (List<Integer> neighborhood:adjList)
            if(neighborhood!=null)
                size+=neighborhood.size();
        return size/2;
    }

    public List<Integer> getMaxDegreeList() {
        if(maxDegreesList!=null)
            return maxDegreesList;
        if(maxDegree<1)
            getMaxDegree();
        maxDegreesList = new ArrayList<>();
        for(Integer vertex: getVertices()){
            if(getDegreeof(vertex)==maxDegree)
                maxDegreesList.add(vertex);
        }
        return maxDegreesList;
    }

    public void contractEdge(Integer v1, Integer v2) {
        for(Integer neieghbor : adjList[v2]){
            //if (neieghbor.equals(v2))
            //    System.out.println( "shit happened: " + v2);
            adjList[neieghbor].remove(v2);
            if(!neieghbor.equals(v1)){
                if(!adjList[v1].contains(neieghbor)) {
                    adjList[v1].add(neieghbor);
                    adjList[neieghbor].add(v1);
                }
            }
        }
        //adjList[v2].clear();
        adjList[v2]= null;
        /*for(int i=0; i < adjList.length ; i++)
            if(adjList[i].remove(v2))
                if(i!=v1.intValue())
                    adjList[i].add(v1);
        */
        vertices.remove(v2);
        maxDegree = -1 ;
        minDegree = null ;
        minDegreesList = null ;
        maxDegreesList = null ;
    }

    /*private List<HashSet<Integer>> getModules(GraphNew graph, List<Integer> vertices){
        List<HashSet<Integer>> hashSetList = new ArrayList<>();
        for(Integer vertex: vertices){

        }
    }*/

    public GraphNew shuffle(/*boolean reserveName*/){
        int n = adjList.length;
        int size = getSize();
        ArrayList<Integer> vertexList = new ArrayList<>();
        ArrayList<Integer>[] newAdjList =  (ArrayList<Integer>[]) new ArrayList[ size ];

        Map<Integer, Integer> firstMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> reverseMap = new HashMap<Integer, Integer>();
        GraphInfo gInfo = new GraphInfo();
        int counter = 0;
        for(Integer vertex: vertices){
            vertexList.add(counter);
            Integer vertexName = vertex ;
            if(false)
                vertexName=Integer.parseInt(graphInfo.getNameOf(vertex));

            firstMap.put(counter, vertexName);
            reverseMap.put(vertexName, counter);
            gInfo.addVertex(counter, (""+vertexName));
            counter++;
        }

        for(Integer vertex: vertexList){
            List<Integer> neighbors = getNeighbors(firstMap.get(vertex));
            newAdjList[vertex] = new ArrayList<>();
            for(Integer neighbor: neighbors)
                newAdjList[vertex].add(reverseMap.get(neighbor));

        }


        GraphNew graph = new GraphNew(gInfo, vertexList, newAdjList);
        return graph;
    }


    public Integer getMinDegree() {
        if(minDegree==null){
            for(Integer vertex : vertices){
                int degreeof = getDegreeof(vertex);
                if((minDegree==null)||(degreeof < minDegree))
                    minDegree = degreeof;
            }
        }
        return minDegree;
    }
    public List<Integer> getMinDegreeList() {
        if(minDegreesList!=null)
            return minDegreesList;
        if(minDegree==null)
            getMinDegree();
        minDegreesList = new ArrayList<>();
        for(Integer vertex: getVertices()){
            if(getDegreeof(vertex)==minDegree)
                minDegreesList.add(vertex);
        }
        return minDegreesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNew graphNew = (GraphNew) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(adjList, graphNew.adjList)) return false;
        return vertices.equals(graphNew.vertices);
    }


    @Override
    public int hashCode() {
        int result = Arrays.hashCode(adjList);
        result = 31 * result + vertices.hashCode();
        return result;
    }

}

