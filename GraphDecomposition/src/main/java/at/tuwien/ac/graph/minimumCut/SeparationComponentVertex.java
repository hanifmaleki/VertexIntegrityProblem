package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.ops.dao.BasicEntity;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumMaxComponentExpriment;

/**
 * Created by e1528895 on 8/2/17.
 */
public class SeparationComponentVertex extends BasicEntity {
    private Long id ;
    private Integer componentId ;
    private Integer vertexId ;
    private SeparationMinimumMaxComponentExpriment expriment;
}
