package at.tuwien.ac.graph.ops.expriments;

import at.tuwien.ac.graph.ops.dao.BasicEntity;
import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;

import javax.persistence.*;

/**
 * Created by root on 7/27/17.
 */
@Entity
public class VertexEntity extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id ;
    private Integer vertex ;

    //@OneToMany(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "expriment_id")
    private SimpleExpriment expriment ;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getVertex() {
        return vertex;
    }

    public void setVertex(Integer vertex) {
        this.vertex = vertex;
    }

    public SimpleExpriment getExpriment() {
        return expriment;
    }

    public void setExpriment(SimpleExpriment expriment) {
        this.expriment = expriment;
    }

    @Override
    public String toString() {
        return "VertexEntity{" +
                "vertex=" + vertex +
                '}';
    }
}
