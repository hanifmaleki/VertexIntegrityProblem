package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SeparationMinimumSizeMinimumMaxExpriment;
import at.tuwien.ac.graph.ops.expriments.SeparationThresholdExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;

import java.util.List;

/**
 * Created by e1528895 on 8/3/17.
 */
public class SeparationThresholdDao extends BasicExprimentDao<SeparationThresholdExperiment> {

    public SeparationThresholdExperiment getExperimentByInstance(Instance instance, int c){
        List<SeparationThresholdExperiment> all = getAllExprimentsByInstance(instance);
        for(SeparationThresholdExperiment experiment : all)
            if(experiment.getThreshold()==c)
                return experiment;
        return null;
    }


}
