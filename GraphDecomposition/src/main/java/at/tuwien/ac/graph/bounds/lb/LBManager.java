package at.tuwien.ac.graph.bounds.lb;

import at.tuwien.ac.graph.bounds.lb.mdst.MinimumDegreeSpanningTreeLB;
import at.tuwien.ac.graph.lp.NSquareformulation;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Created by e1528895 on 10/13/17.
 */
public class LBManager {

    Logger logger = LoggerFactory.logger(LBManager.class);

    static MinimumDegreeSpanningTreeLB minimumDegreeSpanningTreeLB = new MinimumDegreeSpanningTreeLB();


    public LBManager(GraphNew graph){

    }

    public static int getLowerBound(GraphNew graph, int k, int c) {
        int n = graph.getSize();
        if (n <= c)
            return 0;
        int delta = graph.getMaxDegree();
        double ceil = Math.ceil(((double) n) / ((double) (delta * c + 1)));
        return (int) ceil;
    }


    public static int getmstLowerBound(GraphNew graph, int c){
        //TODO correct it
        MinimumDegreeSpanningTreeLB minimumDegreeSpanningTreeLB = new MinimumDegreeSpanningTreeLB();
        return minimumDegreeSpanningTreeLB.getLowerBoundEff( graph, c );
    }

    static int getMyMinorLowerbound(GraphNew graph, int c){
        GraphNew  shuffle = new GraphNew(graph);
        while(shuffle.getSize()>100){
            Integer vertex = shuffle.getMinDegreeList().get(0);
            int max = -1 ;
            Integer maxNeighbor = null ;
            List<Integer> neighbors = shuffle.getNeighbors(vertex);
            for(Integer neighbor : neighbors){
                int degree = shuffle.getDegreeof(neighbor);
                if(degree >max){
                    max = degree;
                    maxNeighbor = neighbor ;
                }
            }
            shuffle.contractEdge(vertex, maxNeighbor);
        }

        shuffle = shuffle.shuffle();
        SimpleLPExpriment expriment = new NSquareformulation(shuffle, c).solveModel(600);
        System.out.println(expriment.getK()+" and lb is "+ expriment.getLb());

        return expriment.getLb();
        //List<VertexEntity> selectedVertices = expriment.getSimilarVertexEntities();
        //return selectedVertices.size();
    }

    public static int getBestLowerBound(int k, int c, GraphNew component, boolean lpCalculation) {
        int lb1 = getLowerBound(component, k, c);
        //int lowerBound = getLowerBoundMinorWidth(new GraphNew(component));
        int lb2 = minimumDegreeSpanningTreeLB.getLowerBoundEff(component, c);
        int lb3 = getmstLowerBound(component, c);
        int lb4 = lb1 ;
        if(lpCalculation==true)
            lb4 = getMyMinorLowerbound(component, c);
        int lowerBound = lb1 ;
        if(lb2 > lowerBound)
            lowerBound = lb2 ;
        if(lb3 > lowerBound)
            lowerBound = lb3 ;

        if(lb4 > lowerBound)
            lowerBound = lb4 ;
        //System.out.println("Lowerbound for component " + component.getSize() + " is " + lowerBound+ " k is "+ k);
        return lowerBound;
    }

}
