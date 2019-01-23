package at.tuwien.ac.graph.bb;

import at.tuwien.ac.graph.bounds.lb.mdst.MinimumDegreeSpanningTreeLB;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.newgraph.KernelizationNew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by e1528895 on 9/9/17.
 */
public class BranchAndBound {
    Logger logger  = LoggerFactory.getLogger(BranchAndBound.class);

    MinimumDegreeSpanningTreeLB minimumDegreeSpanningTreeLB = new MinimumDegreeSpanningTreeLB();


    public List<Integer> branchAndBound(GraphNew graph, int c){
        //Initialize Similar Vertices
        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        //Removing small Components

        //return if the remaining component count is of size 0

        if(connectedComponents.size()>1){
            //Do something for disconnected graph
        }else { // size == 1 connected graph
            GraphNew graphNew = connectedComponents.get(0);
            BAndBParameter bParameter = new BAndBParameter(graphNew, c, -1, 0,graphNew.getSize());

            //TODO run on a thread
            ComponentRoot root = new ComponentRoot(graphNew, c);
            root.setRoot(true);

            BAndBAnswer bAnswer = branchOn(bParameter, root);

            /*while(true){
                //do some monitoring
                // print some partial result
                // print the progress

            }*/
        }
        return null ;
    }

    private BAndBAnswer branchOn(BAndBParameter bParameter, ComponentRoot root) {
        if(root.isStopFlag()==true){
            BAndBAnswer answer = new BAndBAnswer(null);
            answer.setStatus(BAndBAnswer.Status.STOP_FLAG);
            return answer;
        }
        int ub = root.getUpperBound();
        Integer bbLb = bParameter.getBbLb();
        Integer bestBound = bParameter.getBestBound();
        if(ub<=root.getCurrentBranch().size()){
            //System.out.println("pruned");
            return null;
        }

        GraphNew graph = bParameter.getGraph();
        int c = bParameter.getC();
        Integer lastBranch = bParameter.getLastBranch();

        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        //Removing small Components
        Iterator<GraphNew> graphIterator = connectedComponents.iterator();
        while(graphIterator.hasNext()) {
            GraphNew component = graphIterator.next();
            if (component.getSize() <= c)
                graphIterator.remove();
        }

        int size = connectedComponents.size();

        //return if the remaining component count is of size 0
        if(size==0){
            List<Integer> vertices = new ArrayList<>();
            BAndBAnswer answer = new BAndBAnswer(vertices);
            //Tell the component root about answer???
            //I mean tell about updating lbs
            return answer;
        }

        if(size>1) {
            //do something for components
            //Perhaps create a component root for each component (but what is the bounds ....)
            logger.debug("Disconnected");
            BAndBAnswer totalAnswer = new BAndBAnswer(new ArrayList<>());
            for(GraphNew component: connectedComponents){
                //create new component root
                ComponentRoot componentRoot = new ComponentRoot(component, c);
                componentRoot.setParent(root);
                //call branchon with this new root
                BAndBParameter parameter = new BAndBParameter(component, c, lastBranch, 0, component.getSize());
                logger.debug("branchOn component with size "+ component.getSize());
                System.out.println("branchOn component with size "+ component.getSize());
                BAndBAnswer answer = branchOn(parameter, componentRoot);
                if(answer.vertices==null){
                    return new BAndBAnswer(null);
                }
                totalAnswer.vertices.addAll(answer.vertices);
                //TODO should we run in parallel ???
                //collect answers
            }
        }else {

            GraphNew component = connectedComponents.get(0);

            List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(component, c + 1);

            getSorted(component, connectedVertices, root.getStrategy());

            if(ub<= root.getCurrentBranch().size()+bbLb)
                return null;
            /*if(ub<=root.getCurrentBranch().size()+bestBound){
                bestBound = getBestLowerBound(component, c);
                if(ub<=root.getCurrentBranch().size()+bestBound)
                    return null;
            }*/

            List<Integer> minimumAnswer = null ;

            for (Integer removingVertex : connectedVertices) {
                if(removingVertex<=lastBranch)
                    continue ;

                //check similar vertices

                GraphNew graphNew = component.removeVertexCopy(removingVertex);

                BAndBParameter parameter = new BAndBParameter(graphNew, c, removingVertex, bbLb-1, bestBound);
                root.informBranch(removingVertex);
                BAndBAnswer answer = branchOn(parameter, root);
                root.informAnswer(graphNew, answer);
                if(answer==null) {
                    bbLb = root.getUpperBound() - root.getCurrentBranch().size();
                    continue;
                }
                if(answer.vertices==null) {
                    continue;
                }
                List<Integer> vertices = answer.vertices;
                if((minimumAnswer==null)||(minimumAnswer.size()-1> vertices.size())) {
                    minimumAnswer = vertices;
                    minimumAnswer.add(removingVertex);
                }
                //tell the boss(component root) about this answer
                //do sometihng with bounds
                int lb = 0;
                lb = vertices.size()-1 ;
                //send lowerBount to the root
                //send answer to the root

            }
            //if(minimumAnswer==null)
            //    return null;
            BAndBAnswer answer = new BAndBAnswer(minimumAnswer);
            return answer;
        }
        return null ;
    }

    private void getSorted(GraphNew component, List<Integer> connectedVertices, ComponentRoot.BranchingStrategy strategy) {
        if(strategy== ComponentRoot.BranchingStrategy.MAXDEGREE)
            Collections.sort(connectedVertices, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(component.getDegreeof(o2), component.getDegreeof(o1));
                }
            });

        if(strategy== ComponentRoot.BranchingStrategy.MINDEGREE)
            Collections.sort(connectedVertices, new Comparator<Integer>() {
                @Override
                public int compare(Integer o2, Integer o1) {
                    return Integer.compare(component.getDegreeof(o2), component.getDegreeof(o1));
                }
            });
    }

    public int getBestLowerBound(GraphNew component, int c) {
        //int lowerBound = getLowerBoundMinorWidth(new GraphNew(component));
        int lb1 = minimumDegreeSpanningTreeLB.getLowerBoundEff(component, c);
        int lb2 = getmstLowerBound(component, c);

        int lowerBound = lb1 ;
        if(lb2 > lowerBound)
            lowerBound = lb2 ;

        return lowerBound;
    }

    public static int getmstLowerBound(GraphNew graph, int c){
        //TODO correct it
        MinimumDegreeSpanningTreeLB minimumDegreeSpanningTreeLB = new MinimumDegreeSpanningTreeLB();
        return minimumDegreeSpanningTreeLB.getLowerBoundEff( graph, c );
    }


}
