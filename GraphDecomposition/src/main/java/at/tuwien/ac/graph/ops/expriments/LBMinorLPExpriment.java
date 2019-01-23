package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.Entity;

/**
 * Created by e1528895 on 8/28/17.
 */
 @Entity
public class LBMinorLPExpriment extends SimpleExpriment {
     private Integer k ;
     private Integer c ;
     private Integer vertexCount ;
     private Integer timeout ;
     private boolean exact ;

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getVertexCount() {
        return vertexCount;
    }

    public void setVertexCount(Integer vertexCount) {
        this.vertexCount = vertexCount;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public boolean isExact() {
        return exact;
    }

    public void setExact(boolean exact) {
        this.exact = exact;
    }
}
