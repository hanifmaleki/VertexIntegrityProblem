package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.SimilarVerticesExperiment;

import java.util.*;

/**
 * Created by e1528895 on 9/8/17.
 */
public class SimilarVerticesExecutor extends SimpleExecutor<SimilarVerticesExperiment> {
    @Override
    public SimilarVerticesExperiment doSimpleExperiment(GraphNew graph) {
        List<Integer> list = new ArrayList<>(graph.getVertices());
        List<Set<Integer>> similarVertices = new ArrayList();

        while (list.size() > 0) {
            Integer vertex1 = list.remove(0);
            Set<Integer> vertexSet = new HashSet<>();
            vertexSet.add(vertex1);
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer vertex2 = iterator.next();
                if (GraphNewHelper.isSimilar(graph, vertex1, vertex2)) {
                    iterator.remove();
                    vertexSet.add(vertex2);
                }
            }
            similarVertices.add(vertexSet);
        }

        SimilarVerticesExperiment experiment = new SimilarVerticesExperiment();
        experiment.setSimilarVertices(similarVertices);
        experiment.setBagCount(similarVertices.size());
        return experiment;
    }

}
