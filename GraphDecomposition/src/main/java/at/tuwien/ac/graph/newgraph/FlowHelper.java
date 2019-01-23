package at.tuwien.ac.graph.newgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/10/17.
 */
public class FlowHelper {

    /*Ford-Fulkerson (G, s, t, c)
    foreach e ∈ E f (e) ← 0
    G f ← residual graph
while (there exists augmenting path P)
    f ← Augment (f, c, P)
    update G f
return f*/
    /*public static List<Integer> findMinimumCut(GraphNew g, Integer s, Integer t){
        List<Integer> cutVertices = new ArrayList<>();

        GraphNew graphNew = getResidualGraph(g);

        List<Integer> path = getAugmentedPath(g);

        return cutVertices;
    }

    private static GraphNew getResidualGraph(GraphNew g) {
        return null;
    }*/

}
