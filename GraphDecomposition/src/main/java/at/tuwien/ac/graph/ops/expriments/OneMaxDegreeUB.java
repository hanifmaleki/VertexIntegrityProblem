package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 7/27/17.
 */
@Entity
public class OneMaxDegreeUB extends SimpleExpriment implements Serializable{
    private Integer size ;
    @OneToMany(mappedBy="expriment", cascade=CascadeType.ALL)
    private List<VertexEntity> selectedVertices;


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<VertexEntity> getSelectedVertices() {
        return selectedVertices;
    }

    public void setSelectedVertices(List<VertexEntity> selectedVertices) {
        this.selectedVertices = selectedVertices;
    }
}
