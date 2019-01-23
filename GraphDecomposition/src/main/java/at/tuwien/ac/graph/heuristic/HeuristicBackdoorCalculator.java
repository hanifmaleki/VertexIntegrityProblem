package at.tuwien.ac.graph.heuristic;

import at.tuwien.ac.graph.bounds.lb.LBManager;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.executors.SimpleLPExecutor;
import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;

import at.tuwien.ac.graph.utils.PersistenceManager;
import com.sun.org.apache.bcel.internal.classfile.PMGClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by e1528895 on 10/11/17.
 */
public class HeuristicBackdoorCalculator {
    private static final int TIMEOUT = 3000;
    //public static final String LP = "LP";
    Logger logger = LoggerFactory.getLogger(HeuristicBackdoorCalculator.class);

    InstanceManager instanceManager = new InstanceManager();
    HeuristicPartialDao heuristicPartialDao = new HeuristicPartialDao();
    SimpleLPDao lpDao = new SimpleLPDao();


    VertexDao vertexDao = new VertexDao();

    int lb ;
    int ub ;

    //TODO add a constructor with instance parameter

    public FinalHeuristicSolutionExperiment getHeuristicBackdoorOf(Instance instance){

        GraphNew graph = new InstanceManager().getGraph(instance);

        //TODO check for connectivity .....


        lb = 2;
        logger.debug("Calculating initial Lowerbound");
        while(true) {
            int obtained = LBManager.getmstLowerBound(graph, lb);
            if(obtained< lb)
                lb++;
            else
                break ;
        }
        logger.debug("The obtained initial lowerbound is " + lb );

        int sureLb = 0;
        ub = lb ;
        FinalHeuristicSolutionExperiment finalSolution = null;
        logger.debug("Calculating Intitial Upperbound");
        while(true){
            List<Integer> upperBound = getUpperBound(graph, ub);
            if(upperBound.size()>ub)
                ub++;
            else {
                finalSolution = new FinalHeuristicSolutionExperiment();
                finalSolution.setC(ub);
                finalSolution.setK( upperBound.size());
                finalSolution.setSelectedVertices(VertexDao.toVertexEntity(upperBound, finalSolution));
                finalSolution.setcLowerBound(lb);
                //TODO reconsider
                finalSolution.setkLowerBound(0);
                break;
            }
        }
        logger.debug("Initial Upperbound is "+ ub);


        boolean upperboundTested = false ;
        boolean lowerboundTested = false ;
      //total time of finding a fracture number
      long totalTime= 0;
      //A set of tested c
      Set<Integer> cSet = new HashSet<>();
        while((!upperboundTested)||(!lowerboundTested)) {
            int c = (lb+ub+1)/ 2 ;
            if(c==ub){
                if(upperboundTested){
                    c = lb ;
                    lowerboundTested = true ;
                }else
                    upperboundTested = true ;
            }

            logger.debug("trying with cLowerBound = " + lb + " and ub = "+ ub+ " c =" +c);
            //HeuristicPartialSolutionExperiment solution = obtainedHeuristicSolution(instance,  c);
            HeuristicPartialSolutionExperiment solution = findSomeSolutionForComponent(instance,  c);
            if(!cSet.contains(c))
              totalTime += solution.getOperationDuration();
            cSet.add(c);

            System.out.println("for c="+ c + " solution is " + solution);
            logger.debug("for c="+ c + " solution is " + solution);


            if(solution.getUb()<=c){
                ub = c ;
                finalSolution = new FinalHeuristicSolutionExperiment();
                finalSolution.setC(c) ;
                finalSolution.setK(solution.getUb());
                finalSolution.setkLowerBound(solution.getLb());
                List<VertexEntity> selectedVertices = solution.getSelectedVertices();
                List<VertexEntity> newList = new ArrayList<>();
                for(VertexEntity entity: selectedVertices){
                    VertexEntity newEntity = new VertexEntity();
                    newEntity.setExpriment(finalSolution);
                    newEntity.setVertex(entity.getVertex());
                    newList.add(newEntity);
                }
                finalSolution.setSelectedVertices(newList);
            }else{
                lb = c ;
                //if((solution.getLb()>c)&&(c>sureLb))
                //    sureLb = c ;
            }
            int lowerbound= c ;
            if(solution.getLb()<c)
                lowerbound = solution.getLb() ;
            if(lowerbound>sureLb){
                logger.debug("cLowerbound is updated from "+ sureLb+ " to "+ lowerbound);
                sureLb = lowerbound ;
            }
        }

        finalSolution.setcLowerBound(sureLb);
        finalSolution.setInstance(instance);
        finalSolution.setExecutionTime(new Date());
        finalSolution.setOperationDuration(totalTime);

        return finalSolution ;
    }

    private HeuristicPartialSolutionExperiment obtainedHeuristicSolution( Instance instance, int c) {
        //HeuristicPartialSolutionExperiment saved = heuristicPartialDao.getExperiment(instance, c);

        //if(saved==null) {

        //    saved = findSomeSolutionForComponent(instance, c);
            //TODO save it

        //}else{
        //    logger.debug("Calculation for this instance has been already done before");
        //    List<VertexEntity> verticesOf = new VertexDao().getVerticesOf(saved);
        //    saved.setSelectedVertices(verticesOf);
        //}

        //return saved;
        return findSomeSolutionForComponent(instance, c);
    }

    private HeuristicPartialSolutionExperiment obtainHeuristicWithSeparation(Instance instance, Separation separation, int c) {
        String name = separation.getClass().getName();
        List<Instance> subinstances = instanceManager.getSubinstances(instance, separation);
        GraphNew graph = instanceManager.getGraph(instance);
        logger.debug("Solving with separation for graph with size "+ graph.getSize()+ " and c is "+ c + " and separation "+ separation);
        logger.debug("Separation type is "+ name);
        List<VertexEntity> vertexEntities = vertexDao.getVerticesOf(separation);
        List<Integer> separationVertices = new ArrayList<>();

        for(VertexEntity entity : vertexEntities){
            separationVertices.add(entity.getVertex());
        }

        if(subinstances==null){
            //create subinstance

            for(Integer vertex : separationVertices){
                graph = graph.removeVertexCopy(vertex);
            }

            List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
            logger.debug("graph has been disconnected to "+ connectedComponents.size()+ " components");
            List<GraphNew> list = new ArrayList<>();
            for(GraphNew component : connectedComponents) {
                if (component.getSize() <= c)
                    continue;

                list.add(component);
            }
            subinstances = instanceManager.storeSubinstances(instance, list, separation);
        }

        HeuristicPartialSolutionExperiment total = new HeuristicPartialSolutionExperiment();
        total.setUb( vertexEntities.size());
        total.setLb(0);
        total.setSelectedVertices(VertexDao.toVertexEntity(separationVertices, total));
        //set time
      long totalTime = 0 ;
        for(Instance subinstance: subinstances){
            //HeuristicPartialSolutionExperiment solution = obtainedHeuristicSolution(subinstance, c);
            HeuristicPartialSolutionExperiment solution = findSomeSolutionForComponent(subinstance, c);
            logger.debug(solution.toString());
            total.setC(c);
            total.setLb(solution.getLb()+total.getLb());
            total.setUb(solution.getUb()+total.getUb());
            totalTime += solution.getOperationDuration();
            GraphNew shuffle = instanceManager.getGraph(subinstance);

            Integer integer = shuffle.getVertices().get(0);
            if(shuffle.getGraphInfo().getNameOf(integer).startsWith("n")){
                logger.error("The prps of instance is "+instance);
                System.out.println("The prps of instance is "+instance);
                logger.error("The prps of subinstance is "+subinstance);
                System.out.println("The prps of subinstance is "+subinstance);
            }

                List<VertexEntity> selectedVertices = solution.getSelectedVertices();
            List<VertexEntity> deshuffleSelectedVertices = new ArrayList<>();
            for(VertexEntity entity: selectedVertices){
                Integer vertex = entity.getVertex();
                String nameOf = shuffle.getGraphInfo().getNameOf(vertex);
                VertexEntity vertexEntity = new VertexEntity();
                vertexEntity.setVertex(Integer.parseInt(nameOf));
                vertexEntity.setExpriment(total);
                deshuffleSelectedVertices.add(vertexEntity);
            }
            total.getSelectedVertices().addAll(deshuffleSelectedVertices);
        }

        //set time
        total.setOperationDuration(totalTime);
        return total;
    }


    public HeuristicPartialSolutionExperiment findSomeSolutionForComponent( Instance instance, int c) {
        //First try with exact algorithms
        GraphNew component = instanceManager.getGraph(instance);

        logger.debug("Finding Heuristic Solution for connected component with size "+ component.getSize()+
        " and c is "+ c );//+ " root is "+ root);

        HeuristicPartialSolutionExperiment solution = runAnExactMethod(instance, c);

        boolean exactIsEnough = (solution.getLb() == solution.getSelectedVertices().size())||(solution.getLb()>c);
        if(exactIsEnough){
            solution.setInstance(instance);
            heuristicPartialDao.save(solution);
            return solution ;
        }

        //If exact algorithm did not answer

        List<Integer> upperBound = getUpperBound(component, c);
        logger.debug("Founded max degree upperbound is  "+ upperBound.size());
        if(upperBound.size()<= solution.getUb()){
            solution.setUb(upperBound.size()) ;
            solution.setSelectedVertices(VertexDao.toVertexEntity(upperBound, solution));
        }

        //TODO test some lowerbound ....
        //int obtainedLB = LBManager.getmstLowerBound(component, c);
        //if(obtainedLB> solution.)

        //Calling separations
        List<Integer> thresholds = new ArrayList<>();
        int lb = solution.getLb();
        if(c<lb)
            lb=c ;
        for(int i = lb; i<=upperBound.size()+1; i++)
            thresholds.add(i);
        List<Separation> separations = new AllSeparationCalculator().getSeparations(instance, thresholds, c);

        long totalTime = solution.getOperationDuration();
        //Extract separation calculation time
        if(separations.size()>0){
            Separation firstSep = separations.get(0);
            totalTime += firstSep.getOperationDuration();
        }

        //calling backdoor calculator based on separation
        for(Separation separation : separations){
            HeuristicPartialSolutionExperiment solution1 = obtainHeuristicWithSeparation(instance, separation, c);
            totalTime+=solution1.getOperationDuration();
            logger.debug(solution1.toString());
            if(solution1.getUb() < solution.getUb()){
                solution.setUb(solution1.getUb());
                solution.setSelectedVertices( solution1.getSelectedVertices());
                for(VertexEntity entity : solution.getSelectedVertices())
                    entity.setExpriment(solution);
            }

            if(solution1.getLb()>solution.getLb()){
                solution.setLb(solution1.getLb());
            }
            /*
            boolean returnCondition = (solution.getLb()>c) || (solution.getUb()<c);
            if(returnCondition) {
                logger.debug("returning because of returning condition "+ solution);
                System.out.println("returning because of returning condition "+ solution);
                return solution;
            }
            */
        }
        solution.setInstance(instance);
        solution.setOperationDuration(totalTime);
        heuristicPartialDao.save(solution);
        return solution;
    }


    private HeuristicPartialSolutionExperiment runAnExactMethod( Instance instance, int c) {

        SimpleLPExpriment experiment = lpDao.getBestExprience(instance, c);
        if((experiment==null)||(experiment.getTimeout()<TIMEOUT)){
            experiment = new SimpleLPExecutor().executeAndStore(instance, c, TIMEOUT);
        }

        HeuristicPartialSolutionExperiment solution = new HeuristicPartialSolutionExperiment();

        System.out.println(experiment);
        logger.debug(experiment.toString());

        solution.setLb( experiment.getLb());

        List<Integer> originalAnswer = new ArrayList<>();
        if(experiment.getSelectedVertices()!=null) {
            List<VertexEntity> selectedVertices = vertexDao.getVerticesOf(experiment);
            //GraphNew shuffle = instanceManager.getGraph(instance);
            for (VertexEntity entity : selectedVertices) {
                int value = entity.getVertex();
                /*if(instance.getType()==InstanceManager.SUB_INSTANCE) {
                    String nameOf = shuffle.getGraphInfo().getNameOf(value);
                    System.out.println(nameOf+ " "+ value);
                    originalAnswer.add(Integer.parseInt(nameOf));
                }else*/
                    originalAnswer.add(value);
            }
            solution.setUb(originalAnswer.size());
            solution.setSelectedVertices(VertexDao.toVertexEntity(originalAnswer, solution));
        }

        solution.setInstance(instance);
        solution.setC(c);
        // set times
        solution.setOperationDuration(experiment.getOperationDuration());
        return solution;
    }

    //TODO fetch from database
    private List<Integer> getUpperBound(GraphNew graph, int c) {
        List<Integer> vertexList = new ArrayList<>();
        GraphNew newGraph = new GraphNew(graph);

        while(true) {
            List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(newGraph);

            GraphNew maxComponent = connectedComponents.get(0);

            for (GraphNew component : connectedComponents) {
                if (component.getSize() > maxComponent.getSize())
                    maxComponent = component;
            }

            if (maxComponent.getSize() <= c)
                return vertexList;

            List<Integer> maxDegreesList = maxComponent.getMaxDegreeList();

            Integer vertex = maxDegreesList.get(0);
            vertexList.add(vertex);
            newGraph = newGraph.removeVertexCopy(vertex);
        }
    }

    public static void main(String[] args) {
        InstanceManager instanceManager = new InstanceManager();
        Instance instance = instanceManager.getInstanceByName("Grid-12");
        EntityManager em = PersistenceManager.getEntityManager();

        writeTree(instanceManager, instance, em);
        em.close();
        PersistenceManager.close();

    }

    private static void writeTree(InstanceManager instanceManager, Instance instance, EntityManager em) {
        //List<Instance> resultList = em.createQuery("fom Instance i where i.parent is not null and i.parent = :inst").
        //        setParameter("inst", instance).getResultList();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Instance> query = builder.createQuery(Instance.class);
        Root<Instance> root = query.from(Instance.class);
        CriteriaQuery<Instance> parent = query.select(root).where(builder.equal(root.get("parent"), instance));
        List<Instance> resultList = em.createQuery(query).getResultList();

        for(Instance item : resultList){
            GraphNew graph = instanceManager.getGraph(item);
            Integer vertex = graph.getVertices().get(0);
            System.out.print(item.getId()+ "\t" + item+"\t");
            System.out.println(graph.getGraphInfo().getNameOf(vertex));
            writeTree(instanceManager, item, em);
        }
    }

}

