package at.tuwien.ac.graph.newgraph;

import java.io.Serializable;
import java.util.*;

/**
 * Created by root on 4/6/17.
 */
public class GraphInfo implements Serializable {
    private HashMap<Integer, String> vertexNames = new LinkedHashMap<>();
    private HashMap<String, Integer> vertexNamesReverse = new LinkedHashMap<>();

    //TODO
    public Integer getVertexByName(String name){
        return vertexNamesReverse.get(name);
    }

    public void addVertex(Integer index, String name){

        vertexNames.put(index, name);
        vertexNamesReverse.put(name, index);

    }

    public String getNameOf(Integer index){
        return vertexNames.get(index);
    }

    public int getSize() {
        return vertexNames.size();
    }

    public List<Integer> getVertices() {
        Set<Integer> integers = vertexNames.keySet();
        List<Integer> vertices = new ArrayList<>(integers);
        return vertices;
    }

}
