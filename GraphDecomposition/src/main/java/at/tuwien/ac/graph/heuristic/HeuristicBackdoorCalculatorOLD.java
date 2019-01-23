package at.tuwien.ac.graph.heuristic;

import at.tuwien.ac.graph.bounds.lb.LBManager;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.dao.HeuristicPartialDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.dao.VertexDao;
import at.tuwien.ac.graph.ops.executors.SimpleLPExecutor;
import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by e1528895 on 10/11/17.
 */
public class HeuristicBackdoorCalculatorOLD {
    private static final int TIMEOUT = 3000;
    //public static final String LP = "LP";
    Logger logger = LoggerFactory.getLogger(HeuristicBackdoorCalculatorOLD.class);

    InstanceManager instanceManager = new InstanceManager();
    HeuristicPartialDao heuristicPartialDao = new HeuristicPartialDao();

    VertexDao vertexDao = new VertexDao();


    //List<Separation> mainSeparations = null;
    Hashtable<Integer, SeparationThresholdExperiment> thresholdTable = null;
    int lb ;
    int ub ;

    //TODO add a constructor with instance parameter

    public FinalHeuristicSolutionExperiment getHeuristicBackdoorOf(Instance instance){

        GraphNew graph = new InstanceManager().getGraph(instance);

        //TODO check for connectivity .....


        //TODO replace obtained bounds here ...
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
            //HeuristicPartialSolutionExperiment solution = findSomeSolutionForComponent(graph, c, true);
            HeuristicPartialSolutionExperiment solution = obtainedHeuristicSolution(instance,  c);
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
                if(solution.getLb()>c)
                    sureLb = c ;
            }
        }

        finalSolution.setcLowerBound(sureLb);
        finalSolution.setInstance(instance);
        finalSolution.setExecutionTime(new Date());

        return finalSolution ;
    }

    //private HeuristicPartialSolutionExperiment obtainedHeuristicSolution(HeuristicPartialSolutionExperiment parent, Instance instance, GraphNew graph, int c, boolean root) {
    private HeuristicPartialSolutionExperiment obtainedHeuristicSolution( Instance instance, int c) {
        HeuristicPartialSolutionExperiment saved = heuristicPartialDao.getExperiment(instance, c);

        if(saved==null) {
            /*
            saved = new HeuristicPartialSolutionExperiment();
            saved.setC(c);
            saved.setInstance(instance);
            //saved.setParent(parent);
            heuristicPartialDao.save(saved);
            //String parentString = "null" ;
            //if(parent!=null)
            //    parentString = String.valueOf(parent.getId());
            //logger.debug("Solution has been saved with id "+ saved.getId() + " for parent "+ parentString + " with c="+c);
            logger.debug("Solution has been saved with id "+ saved.getId()  + " with c="+c);
            */
            saved = findSomeSolutionForComponent(instance, c);

        }else{
            logger.debug("Calculation for this instance has been already done before");
            List<VertexEntity> verticesOf = new VertexDao().getVerticesOf(saved);
            saved.setSelectedVertices(verticesOf);
        }

        /*if((saved.getUb()==null)){
            logger.debug("This experiment has not been yet calculated. calculating ...");
            long startTime = System.currentTimeMillis();
            //HeuristicPartialSolutionExperiment experiment = findSomeSolutionForComponent(saved, graph, c, root);
            HeuristicPartialSolutionExperiment experiment = findSomeSolutionForComponent(saved, instance, c);

            saved.setOperationDuration(System.currentTimeMillis()-startTime);
            saved.setExecutionTime(new Date());

            saved.setUb(experiment.getUb());
            saved.setLb(experiment.getLb());
            System.out.println(saved);
            List<VertexEntity> entities = new ArrayList<>();
            for(VertexEntity entity :experiment.getSelectedVertices()) {
                VertexEntity vertexEntity = new VertexEntity();
                vertexEntity.setVertex(entity.getVertex());
                System.out.print("" + entity.getVertex() + ", ");
                vertexEntity.setExpriment(saved);
                entities.add(entity);
            }
            System.out.println();
            saved.setSelectedVertices(entities);
            heuristicPartialDao.update(saved);

        }else {
            logger.debug("Calculation for this instance has been already done before");
            List<VertexEntity> verticesOf = new VertexDao().getVerticesOf(saved);
            saved.setSelectedVertices(verticesOf);
        }*/
        return saved;
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
        for(Instance subinstance: subinstances){
            HeuristicPartialSolutionExperiment solution = obtainedHeuristicSolution(subinstance, c);
            logger.debug(solution.toString());
            total.setLb(solution.getLb()+total.getLb());
            total.setUb(solution.getUb()+total.getUb());
            total.getSelectedVertices().addAll(solution.getSelectedVertices());
        }

        heuristicPartialDao.save(total);
        return total;

        /*

        HeuristicPartialSolutionExperiment saved = heuristicPartialDao.getExperiment(instance, name, c);
        if(saved==null){
            saved = new HeuristicPartialSolutionExperiment();
            saved.setC(c);
            //saved.setParent(parent);
            //saved.setSeparationType(name);
            heuristicPartialDao.save(saved);
            logger.debug("Solution has been saved with id "+ saved.getId() + " for parent "+ parent.getId()+ " with c="+c
            + " and sepa type "+ separation.getClass().getSimpleName());
        }
        if(saved.getUb()==null){
            long startTime = System.currentTimeMillis();
            HeuristicPartialSolutionExperiment experiment = solveWithSeparation(saved, component, separation, c);
            saved.setExecutionTime(new Date());
            saved.setOperationDuration(System.currentTimeMillis()-startTime);
            saved.setLb(experiment.getLb());
            saved.setUb(experiment.getUb());
            List<VertexEntity> entities = new ArrayList<>();
            for(VertexEntity entity: experiment.getSelectedVertices()) {
                VertexEntity vertexEntity = new VertexEntity();
                vertexEntity.setVertex(entity.getVertex());
                vertexEntity.setExpriment(saved);
                entities.add(vertexEntity);
            }
            saved.setSelectedVertices(entities);
            heuristicPartialDao.update(saved);
        }else {
            logger.debug("Calculation for this instance has been already done before");
            List<VertexEntity> verticesOf = new VertexDao().getVerticesOf(saved);
            saved.setSelectedVertices(verticesOf);
        }


        return saved;
        */
    }


    //public HeuristicPartialSolutionExperiment findSomeSolutionForComponent(HeuristicPartialSolutionExperiment saved, GraphNew component, int c, boolean root) {
    public HeuristicPartialSolutionExperiment findSomeSolutionForComponent( Instance instance, int c) {
        //First try with exact algorithms
        GraphNew component = instanceManager.getGraph(instance);

        logger.debug("Finding Heuristic Solution for connected component with size "+ component.getSize()+
        " and c is "+ c );//+ " root is "+ root);

        //HeuristicPartialSolutionExperiment solution = runAnExactMethod(saved, instance, c);
        HeuristicPartialSolutionExperiment solution = runAnExactMethod(instance, c);

        //boolean exactIsEnough = (expriment.getSolutionSize() == expriment.getK())||(expriment.getSolutionSize()>c);
        boolean exactIsEnough = (solution.getLb() == solution.getSelectedVertices().size())||(solution.getLb()>c);
        if(exactIsEnough){
            //exact answer
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
        for(int i = solution.getLb(); i<=solution.getUb(); i++)
            thresholds.add(i);
        List<Separation> separations = new AllSeparationCalculator().getSeparations(instance, thresholds, c);



        //calling backdoor calculator based on separation
        for(Separation separation : separations){
            //HeuristicPartialSolutionExperiment solution1 = obtainHeuristicWithSeparation(saved, component, separation, c);
            HeuristicPartialSolutionExperiment solution1 = obtainHeuristicWithSeparation(instance, separation, c);
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
        }

        return solution;
    }

    /*
    private HeuristicPartialSolutionExperiment solveWithSeparation(HeuristicPartialSolutionExperiment parent, GraphNew graph, Separation separationExperiment, int c) {

        logger.debug("Solving with separation for graph with size "+ graph.getSize()+ " and c is "+ c + " and separation "+ separationExperiment);
        logger.debug("Separation type is "+ separationExperiment.getClass().getName());
        Set<VertexEntity> sepList = separationExperiment.getSeparation();
        List<Integer> separation = new ArrayList<>();
        for(VertexEntity entity : sepList){
            separation.add(entity.getVertex());
        }

        for(Integer vertex : separation){
            graph = graph.removeVertexCopy(vertex);
        }

        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        logger.debug("graph has been disconnected to "+ connectedComponents.size()+ " components");
        HeuristicPartialSolutionExperiment total = new HeuristicPartialSolutionExperiment();
        total.setUb( separation.size());
        total.setLb(0);
        total.setSelectedVertices(VertexDao.toVertexEntity(separation, total));
        for(GraphNew component : connectedComponents){
            if(component.getSize()<=c)
                continue;

            HeuristicPartialSolutionExperiment solution = obtainedHeuristicSolution(parent, null, component, c, false);
            logger.debug(solution.toString());
            total.setLb(solution.getLb()+total.getLb());
            total.setUb(solution.getUb()+total.getUb());
            total.getSelectedVertices().addAll(solution.getSelectedVertices());
        }
        return total;
    }*/




    SimpleLPDao lpDao = new SimpleLPDao();

    //private HeuristicPartialSolutionExperiment runAnExactMethod(HeuristicPartialSolutionExperiment parent, GraphNew component, int c) {
    private HeuristicPartialSolutionExperiment runAnExactMethod( Instance instance, int c) {

        //HeuristicPartialSolutionExperiment saved = heuristicPartialDao.getExperiment(parent, null, LP, c);
        //HeuristicPartialSolutionExperiment saved = heuristicPartialDao.getExperiment(instance, LP, c);

        SimpleLPExpriment experiment = lpDao.getBestExprience(instance, c);
        if((experiment==null)||(experiment.getTimeout()<TIMEOUT)){
            experiment = new SimpleLPExecutor().executeAndStore(instance, c, TIMEOUT);
        }




        /*if(saved!=null) {
            VertexDao vertexDao = new VertexDao();
            List<VertexEntity> verticesOf = vertexDao.getVerticesOf(saved);
            saved.setSelectedVertices(verticesOf);
            return saved;
        }*/

        HeuristicPartialSolutionExperiment solution = new HeuristicPartialSolutionExperiment();

        /*GraphNew component = instanceManager.getGraph(instance);
        GraphNew shuffle = component.shuffle();
        SimpleLPExpriment expriment = new NSquareformulation(shuffle, c).solveModel(TIMEOUT);*/

        System.out.println(experiment);
        logger.debug(experiment.toString());

        solution.setLb( experiment.getLb());

        List<Integer> originalAnswer = new ArrayList<>();
        if(experiment.getSelectedVertices()!=null) {
            List<VertexEntity> selectedVertices = vertexDao.getVerticesOf(experiment);
            GraphNew shuffle = instanceManager.getGraph(instance);
            for (VertexEntity entity : selectedVertices) {
                int value = entity.getVertex();
                if(instance.getType()==InstanceManager.SUB_INSTANCE) {
                    String nameOf = shuffle.getGraphInfo().getNameOf(value);
                    System.out.println(nameOf+ " "+ value);
                    originalAnswer.add(Integer.parseInt(nameOf));
                }else
                    originalAnswer.add(value);
            }
            solution.setUb(originalAnswer.size());
            solution.setSelectedVertices(VertexDao.toVertexEntity(originalAnswer, solution));
        }
        //solution.setParent(parent);
        solution.setInstance(instance);
        solution.setC(c);
        //solution.setSeparationType(LP);
        //TODO set times
        //heuristicPartialDao.save(solution);
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

        EntityManager entityManager  = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        HeuristicPartialSolutionExperiment experiment = entityManager.find(HeuristicPartialSolutionExperiment.class, 2845L);
        myMethod(entityManager, experiment,0);
        entityManager.getTransaction().commit();
        PersistenceManager.close();

    }

    private static void myMethod(EntityManager entityManager, HeuristicPartialSolutionExperiment experiment, int ind) {
        for(int i=0; i < ind; i++)
            System.out.print("  ");
        System.out.println(experiment);
        /*if((experiment.getSeparationType()==null)||!(experiment.getSeparationType().equalsIgnoreCase("LP"))) {
            for(VertexEntity entity: experiment.getSelectedVertices())
                entityManager.remove(entity);
            experiment.setSolutionSize(null);
            experiment.setUb(null);
            experiment.setSelectedVertices(null);
        }*/
        List<HeuristicPartialSolutionExperiment> list =entityManager.createQuery("from " +
                "HeuristicPartialSolutionExperiment ex where ex.parent = :experiment")
                .setParameter("experiment", experiment ).getResultList();
        for(HeuristicPartialSolutionExperiment ex: list)
            myMethod(entityManager, ex, ind+1);
    }
}

