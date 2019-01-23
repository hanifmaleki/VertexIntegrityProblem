package at.tuwien.ac.graph.labeled;

import at.tuwien.ac.graph.utils.Kernelization;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 8/22/17.
 */
public class LabeledKernelization {

    private LabeledGraphHelper labeledGaphHelper = new LabeledGraphHelper();

    private LabeledKernelizationResult getKernelizationofAConnectedComponent(LabeledGraph graph, int c, int k){
        List<Integer> labels = graph.getLabels();
        //checking lower bound
        //TODO in case of finding other lower bounds, it can be replaced by a method like getLowerbound(graph, c, k)
        if(graph.getSize()> c*c*k)
            return null ;
        for(Integer label : labels){
            Set<Integer> vertices = graph.getAdjacentVertices(label);
            if(vertices.size()>c){
                LabeledGraph newGraph = graph.removeLabelCopy(label);
                LabeledKernelizationResult kernelizationResult = getKernelization(graph, c, k - 1);
                if(kernelizationResult==null)
                    return null ;

                kernelizationResult.labels.add(label);

                return kernelizationResult;
            }
        }

        LabeledKernelizationInstance instance = new LabeledKernelizationInstance();
        instance.graph = graph ;
        instance.k = k ;

        LabeledKernelizationResult result = new LabeledKernelizationResult();
        result.labels = new ArrayList<>();
        result.instances = new ArrayList<>();
        result.instances.add(instance);
        return result ;
    }

    public LabeledKernelizationResult getKernelization(LabeledGraph graph, int c, int k) {
        List<LabeledGraph> connectedComponents = labeledGaphHelper.getConnectedComponents(graph);
        List<LabeledKernelizationInstance> instances = new ArrayList<>();
        List<Integer> selectedLabels = new ArrayList<>();
        for(LabeledGraph component : connectedComponents){
            if(component.getSize()>c) {
                LabeledKernelizationResult kernelization = getKernelizationofAConnectedComponent(component, c, k);
                instances.addAll(kernelization.instances);
                selectedLabels.addAll(kernelization.labels);
                if(selectedLabels.size()>c)
                    return null ;
            }
        }
        LabeledKernelizationResult result = new LabeledKernelizationResult();
        result.instances =instances;
        result.labels = selectedLabels ;
        return result;
    }
}
