package at.tuwien.ac.graph.bb;

import at.tuwien.ac.graph.newgraph.GraphNew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by e1528895 on 9/9/17.
 */
public class ComponentRoot implements Runnable{

    enum BranchingStrategy {MAXDEGREE, MINDEGREE}

    static Logger logger = LoggerFactory.getLogger(ComponentRoot.class);

    private GraphNew graph ;

    boolean isRoot ;

    List<Integer> bestAnswer ; //it is upper bound

    int lb ;//???

    private List<ComponentRoot> currentChildren ;//can convert to a class of a separation and a list of children

    private List<Integer> currentBranch = new ArrayList<>();

    ComponentRoot parent ;

    BranchingStrategy strategy = BranchingStrategy.MAXDEGREE ;

    int c ;

    private boolean stopFlag = false ;

    public ComponentRoot(GraphNew graph, int c) {
        this.graph = graph;
        this.c = c ;
        // do some kernelization ...
        // calculate some bounds ...
        new Thread(this).start();
    }


    public void informBranch(Integer removingVertex){
        if(parent!=null)
            parent.informBranch(removingVertex);
        currentBranch.add(removingVertex);
    }

    public void informAnswer(GraphNew graphNew, BAndBAnswer answer){
        if(answer.getStatus()== BAndBAnswer.Status.STOP_FLAG)
            return ;
        int size = currentBranch.size();
        if(answer==null){
            //TODO ("I dont know what should I do")
            int theBound = getUpperBound() - currentBranch.size();
            if(theBound> lb){
                lb = theBound ;
                logger.debug("Bound increased to "+ lb);
                System.out.println("Bound increased to "+ lb);
            }
            currentBranch.remove(size-1);
            return;
        }
        List<Integer> vertices = answer.vertices;
        if(vertices ==null) {
            //TODO add the code
            //new LB
            currentBranch.remove(size - 1);
            return;
        }

        int ansSize = vertices.size();
        if(ansSize> lb) {
            lb = ansSize;
            logger.debug("Bound increased to "+ lb);
            System.out.println("Bound increased to "+ lb);
        }
        boolean betterAnswer ;
        if(bestAnswer==null)
            betterAnswer=true ;
        else
            betterAnswer = ansSize + currentBranch.size() < bestAnswer.size();
        if(betterAnswer) {
            bestAnswer = new ArrayList<>(vertices);
            bestAnswer.addAll(currentBranch);
            logger.debug("The best founded answer size "+ bestAnswer.size() + " Lowebound "+ lb+
                    " Current Branch Size "+ currentBranch.size());
            if(bestAnswer.size() < c+5){
                logger.debug("Changing Strategy to min degree");
                strategy = BranchingStrategy.MINDEGREE;
                stopFlag = true ;
            }
        }

        if(parent!=null)
            parent.informAnswer(graphNew, answer);
        currentBranch.remove(size-1);
    }

    public void informSeparation(){

    }

    public GraphNew getGraph() {
        return graph;
    }

    public void setGraph(GraphNew graph) {
        this.graph = graph;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public List<Integer> getBestAnswer() {
        return bestAnswer;
    }

    public void setBestAnswer(List<Integer> bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    public int getLb() {
        return lb;
    }

    public void setLb(int lb) {
        this.lb = lb;
    }

    public List<ComponentRoot> getCurrentChildren() {
        return currentChildren;
    }

    public void setCurrentChildren(List<ComponentRoot> currentChildren) {
        this.currentChildren = currentChildren;
    }

    public List<Integer> getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(List<Integer> currentBranch) {
        this.currentBranch = currentBranch;
    }

    public ComponentRoot getParent() {
        return parent;
    }

    public void setParent(ComponentRoot parent) {
        this.parent = parent;
    }

    public int getUpperBound() {
        if(bestAnswer==null)
            return graph.getSize();
        return bestAnswer.size();
    }

    public BranchingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(BranchingStrategy strategy) {
        this.strategy = strategy;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    @Override
    public void run() {
        if(parent==null)
            while(true){
                try {
                    Thread.sleep(30*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String best = "null";
                if(bestAnswer!=null)
                    best = ""+bestAnswer.size();
                String msg = "The best founded answer size " + best + " Lowebound " + lb +
                        " Current Branch Size " + currentBranch.size() + " Branch " + Arrays.toString(currentBranch.toArray());
                logger.debug(msg);
                System.out.println(msg);

                if(stopFlag==true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    restartBranching();
                }
            }
    }

    private void restartBranching() {
        logger.debug("restarting");
        stopFlag = false ;
        currentBranch = new ArrayList<>();
        new BranchAndBound().branchAndBound(graph, c);
    }
}
