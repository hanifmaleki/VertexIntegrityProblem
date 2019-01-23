package at.tuwien.ac.graph.labeled;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by root on 7/13/17.
 */
public class LabeledEdge implements Serializable {
    public Integer from;
    public Integer to ;
    public Set<Integer> labels ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabeledEdge that = (LabeledEdge) o;

        if (!from.equals(that.from)) return false;
        return to.equals(that.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LabeledEdge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
