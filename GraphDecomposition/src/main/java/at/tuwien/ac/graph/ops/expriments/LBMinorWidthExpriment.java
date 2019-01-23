package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.Entity;

/**
 * Created by root on 7/28/17.
 */
@Entity
public class LBMinorWidthExpriment extends SimpleExpriment {
    private int size ;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
