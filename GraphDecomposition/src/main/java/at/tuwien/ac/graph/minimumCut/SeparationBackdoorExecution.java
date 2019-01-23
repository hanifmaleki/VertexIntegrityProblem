package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.bounds.lb.LBMinorWExecutor;
import at.tuwien.ac.graph.ops.executors.SimpleExecutor;
import at.tuwien.ac.graph.bounds.lb.mdst.MinimumDegreeSpanningTreeLB;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.LBMinorWidthExpriment;
import at.tuwien.ac.graph.ops.expriments.OneMaxDegreeUB;
import at.tuwien.ac.graph.ops.expriments.SeparationBackdoorExpriment;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumMaxComponentExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.sat.NewSatProducer;
import at.tuwien.ac.graph.sat.SatAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by e1528895 on 8/7/17.
 */
public class SeparationBackdoorExecution extends SimpleExecutor<SeparationBackdoorExpriment>{

    Logger logger = LoggerFactory.getLogger(SeparationBackdoorExecution.class);
    InstanceManager instanceManager = new InstanceManager();
    SeparationMinMaxCompDao separationMinMaxCompDao = new SeparationMinMaxCompDao();
    OneMaxDegDao maxDegDao = new OneMaxDegDao();
    LBMinorWidthDao minorWidthDao = new LBMinorWidthDao();
    VertexDao vertexDao = new VertexDao();
    LBMinorWExecutor minorWExecutor = new LBMinorWExecutor();
    MinimumDegreeSpanningTreeLB calculator = new MinimumDegreeSpanningTreeLB();
    Hashtable<GraphNew, Integer> minorBounds = new Hashtable<>();
    Hashtable<GraphNew, Integer> maxDegrees = new Hashtable<>();


    public SeparationBackdoorExpriment findBackdoor(GraphNew graph, SeparationMinimumMaxComponentExpriment separationMinimumMaxComponentExpriment, int lb, int ub){

        for(VertexEntity vertex : separationMinimumMaxComponentExpriment.getSeparation())
            graph = graph.removeVertexCopy(vertex.getVertex());



        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        logger.debug("Separation split the graph into " + connectedComponents.size() + " components");

        //Make Lower Bounds
        int maximumLowerBound = 0;
        for(GraphNew component: connectedComponents){
            int lowerbound = minorWExecutor.getLowerbound(new GraphNew(component));
            logger.debug("for component with size " +component.getSize() + " the minor width lower bound is "+lowerbound);
            minorBounds.put(component, lowerbound);
            if (lowerbound > maximumLowerBound)
                maximumLowerBound = lowerbound ;
        }
        logger.debug("The maximum lowerbound of the components is " + maximumLowerBound);

        //Make Minimum Mex Dgree Trees
        List<Integer> degrees = new ArrayList<>();
        for(GraphNew component: connectedComponents){
            GraphNew minimumDegreeSpanningTreeEff = calculator.getMinimumDegreeSpanningTreeEff(component);
            int maxDegree = minimumDegreeSpanningTreeEff.getMaxDegree();
            degrees.add(maxDegree);
            logger.debug("Max Degree of spanning tree of the component with size "+ component.getSize()+ " is "+ maxDegree);
            maxDegrees.put(component, maxDegree);
        }

        //guess a c
        //int c = separationMinimumMaxComponentExpriment.getSize() + maximumLowerBound;
        List<Integer> backdoor ;
        while(true){
            int c = (lb+ub)/2;
            if(c==lb)
                c++ ;
            logger.debug("c is "+ c + " lb is "+ lb + " ub is "+ ub);
            logger.debug("Checking with c = "+ c);
            int sum = c - separationMinimumMaxComponentExpriment.getSize();
            logger.debug("Sum initialized to "+ sum);
            //TODO check based on the MST bounds
            int totalLbs = 0;
            for(GraphNew component: connectedComponents) {
                Integer maxdeg = maxDegrees.get(component);
                int lbFromDegree = calculator.getLBFromDegree(maxdeg, component.getSize(), c);
                logger.debug("MDMST lower bound for component with size " + component.getSize() + " is " + lbFromDegree);
                totalLbs += lbFromDegree ;
            }
            logger.debug("Total lower bound is "+ totalLbs);
            if(totalLbs > sum){
                logger.debug("The total exceeds the sum. increasing c");
                //c++ ;
                lb = c ;
                logger.debug("c is "+ c + " lb is "+ lb + " ub is "+ ub);
                continue;
            }

            backdoor = findBackdoor(connectedComponents, c, sum, totalLbs);
            if(backdoor!=null) {
                ub = c;
                logger.debug("c is "+ c + " lb is "+ lb + " ub is "+ ub);

            }else{
                lb = c ;
                logger.debug("c is "+ c + " lb is "+ lb + " ub is "+ ub);
            }
            if(ub<=lb+1){
                logger.debug("backdoor has been found with size "+ backdoor.size());
                SeparationBackdoorExpriment expriment = new SeparationBackdoorExpriment();
                expriment.setSize(backdoor.size());
                List<VertexEntity> vertexEntities = new ArrayList<>();
                for(Integer vertex : backdoor) {
                    VertexEntity vertexEntity = new VertexEntity();
                    vertexEntity.setVertex(vertex);
                    vertexEntities.add(vertexEntity);
                }
                vertexEntities.addAll(separationMinimumMaxComponentExpriment.getSeparation());
                expriment.setBackdoor(vertexEntities);
                return expriment;
            }
        }
    }

    private List<Integer> findBackdoor(List<GraphNew> connectedComponents, int c, int ub, int totalLowerbounds) {
        List<Integer> backdoor = new ArrayList<>();
        for(GraphNew component : connectedComponents) {
            logger.debug("component size is "+component.getSize());
            if (component.getSize() <= c) {
                logger.debug("Component size is not more than c");
                continue;
            }
            Integer lb1 = minorBounds.get(component);
            Integer maxDg = maxDegrees.get(component);
            int lb2 = calculator.getLBFromDegree(maxDg, component.getSize(), c);

            int lb = lb1;
            if(lb2>lb)
                lb = lb2;


            Set<Integer> fvd = findBackdoor(component, c, lb, ub);

            if((fvd==null)||(fvd.size()>ub)) {

                return null ;
            }


            backdoor.addAll(fvd);
            ub = ub -  fvd.size();
            logger.debug("ub decrease to "+ ub);
            //check remaining lower bounds
            totalLowerbounds-= lb2 ;
            logger.debug("total lower bound is "+ totalLowerbounds);
            //totalLowerbounds+= fvd.size();
            if(totalLowerbounds> ub){
                logger.debug("total of remaining lower bounds exceeds the current lower bound");
                return null ;
            }
        }
        return backdoor;
    }

    private Set<Integer> findBackdoor(GraphNew component, int c, int lb, int ub) {
        int k = lb;
        while (k <= ub) {
            logger.debug("Trying with k= "+k + " c= "+c);
            SatAnswer answer = NewSatProducer.getFVDBySAT(component, c, k,0);
            Set<Integer> fvdBySAT = answer.getFvd();
            if (fvdBySAT == null) {
                logger.debug("No answer for k="+ k);
                k++;

            }else {
                logger.debug("An answer has been found with size "+ fvdBySAT.size());
                return fvdBySAT;

            }
        }
        return null;
    }


    @Override
    public SeparationBackdoorExpriment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    @Override
    public SeparationBackdoorExpriment doSimpleExperiment(Instance instance) {
            logger.debug(instance.toString());
            GraphNew graph = instanceManager.getGraph(instance);
            SeparationMinimumMaxComponentExpriment exprimentByInstance = separationMinMaxCompDao.getExprimentByInstance(instance);
            List<VertexEntity> verticesOf = vertexDao.getVerticesOf(exprimentByInstance);
            exprimentByInstance.setSeparation(new HashSet<>(verticesOf));

            LBMinorWidthExpriment lbExpriment = minorWidthDao.getExprimentByInstance(instance);
            OneMaxDegreeUB ubExpriment = maxDegDao.getExprimentByInstance(instance);

            Long currentTime = System.currentTimeMillis();
            Date date = new Date();
            SeparationBackdoorExpriment result = findBackdoor(graph, exprimentByInstance, lbExpriment.getSize(), ubExpriment.getSize());
            Long period = System.currentTimeMillis() - currentTime ;


            result.setExecutionTime(date);
            result.setInstance(instance);
            result.setOperationDuration(period);

            return result;

    }
}
