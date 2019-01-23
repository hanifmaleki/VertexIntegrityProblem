package at.tuwien.ac.graph.labeled;


import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by root on 7/13/17.
 */
public class CausalDetection {
    Logger logger = LoggerFactory.getLogger(CausalDetection.class);
    LabeledGraphHelper labeledGraphHelper = new LabeledGraphHelper();
    LabeledKernelization labeledKernelization = new LabeledKernelization();

    public List<Integer> getBackdoor(LabeledGraph graph, int c, int k, int lastSelectedLabel) {
        List<Integer> labeledEdges = new ArrayList<Integer>();
        // TODO Do some kernelization

        if (graph.getSize() <= c)
            return labeledEdges;

        if(k==0)
            return null ;

        //List<LabeledGraph> connectedComponents = labeledGraphHelper.getConnectedComponents(graph);
        LabeledKernelizationResult kernelization = labeledKernelization.getKernelization(graph, c, k);
        labeledEdges.addAll(kernelization.labels);
        if(kernelization.instances.size()==0)
            return labeledEdges ;
        if (kernelization.instances.size() > 1) {
            for (LabeledKernelizationInstance instance : kernelization.instances) {
                LabeledGraph component = instance.graph;
                if (component.getSize() > c) {
                    List<Integer> backdoor = getBackdoor(component, c, k, lastSelectedLabel);
                    if(backdoor==null)
                        return null;
                    labeledEdges.addAll(backdoor);
                    if (labeledEdges.size() > c)
                        return null;
                }

            }
            return labeledEdges ;
        } else {
            graph = kernelization.instances.get(0).graph;
            k = kernelization.instances.get(0).k ;
            Set<Integer> labels = labeledGraphHelper.getAppropriateConnectedComponent(graph, c + 1);
            for (Integer label : labels) {
                //TODO add symmetry breaking and add something like forbidden vertices
                if(label < lastSelectedLabel)
                    continue ;
                LabeledGraph graph2 = graph.removeLabelCopy(label);
                List<Integer> newbackdoor = getBackdoor(graph2, c, k - 1, label);
                if ((newbackdoor!=null)&&(newbackdoor.size() <= c-labeledEdges.size()-1)) {
                    labeledEdges.add(label);
                    labeledEdges.addAll(newbackdoor);
                    if(k>c-3)
                        System.out.println("There is result for k = " + k);
                    return labeledEdges;
                }
            }
            if(k>c-3)
                System.out.println("There is no result for k = " + k);
            return null ;
        }
        //Unreachable
        //return labeledEdges;
    }





    private void doSomeKernelizstion(){
        //Because G is action labeled, it follows that G[{l}] is connected ...
        //TODO If there is any lable which is common in more than c edges, add it to deletion set, k--
        //else
        //check the number of vertices <= k+c^2k
        //check the number of edges <= (k
    }





}
