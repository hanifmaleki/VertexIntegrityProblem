package at.tuwien.ac.graph.ops.expriments;

import at.tuwien.ac.graph.ops.dao.BasicEntity;

import javax.persistence.*;

/**
 * Created by e1528895 on 9/8/17.
 */
@Entity
public class SimilarVertexEntity extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "expriment_id")
    private SimilarVerticesExperiment expriment ;

    private Integer bagNumber ;

    private Integer vertex ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimilarVerticesExperiment getExpriment() {
        return expriment;
    }

    public void setExpriment(SimilarVerticesExperiment expriment) {
        this.expriment = expriment;
    }

    public Integer getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(Integer bagNumber) {
        this.bagNumber = bagNumber;
    }

    public Integer getVertex() {
        return vertex;
    }

    public void setVertex(Integer vertex) {
        this.vertex = vertex;
    }
}
