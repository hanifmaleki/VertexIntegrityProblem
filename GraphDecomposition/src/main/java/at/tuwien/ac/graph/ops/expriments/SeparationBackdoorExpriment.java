package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by e1528895 on 8/7/17.
 */
@Entity
public class SeparationBackdoorExpriment extends SimpleExpriment {
    private int size ;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.DETACH)
    private List<VertexEntity> backdoor;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<VertexEntity> getBackdoor() {
        return backdoor;
    }

    public void setBackdoor(List<VertexEntity> backdoor) {
        this.backdoor = backdoor;
    }
}
