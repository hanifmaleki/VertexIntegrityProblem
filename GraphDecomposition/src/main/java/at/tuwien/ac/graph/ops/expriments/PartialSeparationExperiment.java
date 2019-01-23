package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by e1528895 on 10/28/17.
 */
@Entity
public class PartialSeparationExperiment extends SimpleExpriment{

    @ManyToOne
    @JoinColumn(name = "expriment_id")
    private SimpleExpriment parent ;

    private Integer c ;

    private String SeparationType ;

    private Integer threshold ;

    private Integer lb ;

    private Integer ub ;

    private List<VertexEntity> selectedVertices ;

}
