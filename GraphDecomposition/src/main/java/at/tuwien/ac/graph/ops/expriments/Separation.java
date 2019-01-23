package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by root on 5/15/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Separation extends SimpleExpriment {

    private int size;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.ALL)
    private Set<VertexEntity> separation;

    //public List<GraphNew> components;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Set<VertexEntity> getSeparation() {
        return separation;
    }

    public void setSeparation(Set<VertexEntity> separation) {
        this.separation = separation;
    }

    @Override
    public String toString() {
        String s = getClass().getSimpleName();
        return s +
                "size=" + size +
                '}';
    }


}
