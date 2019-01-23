package at.tuwien.ac.graph.labeled;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 8/22/17.
 */
public class Upperbound {
    private LabeledGraphHelper labeledGraphHelper = new LabeledGraphHelper();
    private Logger logger = LoggerFactory.getLogger(Upperbound.class);

    //TODO move to another class
    public int getUpperBound(LabeledGraph graph){
        int c = 300;
        while (true){
            logger.debug("Trying for c = "+ c);
            List<Integer> bound = getUpperBoundForSpecificC(graph, c);
            int size = bound.size();
            logger.debug("Bound for c = "+ c +" is " + size);
            if(size <=c ){
                //TODO return also bound
                System.out.println("BOund size is "+ size);
                return c ;
            }
            c++;
        }
    }

    private List<Integer> getUpperBoundForSpecificC(LabeledGraph graph, int c) {
        List<Integer> removingLabels = new ArrayList<>();
        Integer maxLabel = graph.getMaxLabel();
        //logger.debug("Max Label is "+ maxLabel);
        removingLabels.add(maxLabel);
        //logger.debug("Max Label Removed");
        LabeledGraph newGraph = graph.removeLabelCopy(maxLabel);
        //logger.debug("The label has been removed from the graph");
        List<LabeledGraph> connectedComponents = labeledGraphHelper.getConnectedComponents(newGraph);
        logger.debug("The number of components is " + connectedComponents.size());
        Iterator<LabeledGraph> graphIterator = connectedComponents.iterator();
        while(graphIterator.hasNext()) {
            LabeledGraph component = graphIterator.next();
            if (component.getSize() <= c)
                graphIterator.remove();
        }
        logger.debug("The number of components is " + connectedComponents.size());
        for(LabeledGraph component : connectedComponents){
            logger.debug("Component size is "+ component.getSize());
            if(component.getSize()>c)
                removingLabels.addAll(getUpperBoundForSpecificC(component, c));
        }
        return removingLabels;
    }

    public List<Integer> getUpperBound2(LabeledGraph graph){
        List<Integer> removingLabels = new ArrayList<>();
        if(graph.getSize()< 2)
            return removingLabels;

        int c = 1 ;
        Integer maxLabel = graph.getMaxLabel();
        LabeledGraph newGraph = graph.removeLabelCopy(maxLabel);
        removingLabels.add(maxLabel);
        while(true){
            List<LabeledGraph> connectedComponents = labeledGraphHelper.getConnectedComponents(newGraph);
            /*Iterator<LabeledGraph> graphIterator = connectedComponents.iterator();
            while(graphIterator.hasNext()) {
                LabeledGraph component = graphIterator.next();
                if (component.getSize() <= c)
                    graphIterator.remove();
            }*/
            int maxComponentSize = 0;
            for(LabeledGraph component : connectedComponents)
                if(maxComponentSize< component.getSize())
                    maxComponentSize = component.getSize();

            if(maxComponentSize<=c){
                List<Set<LabeledEdge>> lableEdges = newGraph.getLableEdges();
                /*int counter = 1;
                for(Set<LabeledEdge> set: lableEdges){
                    int size = set.size();
                    if(size >43)
                        System.out.println(counter++ +" " + size);
                }*/
                System.out.println("Number of Edges "+ newGraph.getEdges().size());
                return removingLabels;
            }
            c++ ;
            logger.debug("The Component size is "+connectedComponents.size()+". The size of maximum component is "+maxComponentSize+". C is incresed to " + c);
            maxLabel = newGraph.getMaxLabel();
            newGraph = newGraph.removeLabelCopy(maxLabel);
            removingLabels.add(maxLabel);
        }
    }
}
