package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 9/8/17.
 */
@Entity
public class SimilarVerticesExperiment extends SimpleExpriment {

    @OneToMany(mappedBy="expriment", cascade= CascadeType.ALL)
    private List<SimilarVertexEntity> similarVertexEntities;

    private Integer bagCount;

    public List<SimilarVertexEntity> getSimilarVertexEntities() {
        return similarVertexEntities;
    }

    public void setSimilarVertexEntities(List<SimilarVertexEntity> similarVertexEntities) {
        this.similarVertexEntities = similarVertexEntities;
    }

    public Integer getBagCount() {
        return bagCount;
    }

    public void setBagCount(Integer bagCount) {
        this.bagCount = bagCount;
    }

    public void setSimilarVertices(List<Set<Integer>> vertexList) {
        similarVertexEntities = new ArrayList<>();
        for(int i = 0; i < vertexList.size(); i++){
            Set<Integer> set = vertexList.get(i);
            for(Integer vertex: set){
                SimilarVertexEntity similarVertex = new SimilarVertexEntity();
                similarVertex.setExpriment(this);
                similarVertex.setBagNumber(i);
                similarVertex.setVertex(vertex);
                similarVertexEntities.add(similarVertex);
            }
        }
    }

    @Override
    public String toString() {
        return "SimilarVerticesExperiment{" +
                "bagCount=" + bagCount +
                '}';
    }
}
