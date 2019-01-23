package at.tuwien.ac.graph.utils;

import at.tuwien.ac.graph.bounds.lb.LBMinorWExecutor;
import at.tuwien.ac.graph.bounds.lb.mdst.MDSTLBExecutor;
import at.tuwien.ac.graph.bounds.ub.OneMaxDegreeCalculator;
import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.graph.Vertex;
import at.tuwien.ac.graph.grid.GridFrame;
import at.tuwien.ac.graph.lp.SimpleLPDecision;
import at.tuwien.ac.graph.minimumCut.*;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.executors.*;
import at.tuwien.ac.graph.newgraph.*;
import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.samples.Grid;
import at.tuwien.ac.graph.sat.NewSatProducer;
import at.tuwien.ac.graph.sat.SatAnswer;


import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import java.util.*;

public class MPSReaderHelper {


    public static void main(String[] args) {
        //Graph inducedGraph = MyMPSReader.getInducedGraph("samples/noswot.mps");
        //Graph inducedGraph = new MyMPSReader().getInducedGraph("samples/instances/ran16x16.mps");
        //Graph inducedGraph = MyMPSReader.getInducedGraph("samples/instances/glass4.mps"); //Answer : 23
        //Graph inducedGraph = MyMPSReader.getInducedGraph("samples/instances/ns1208400.mps");
        //Graph inducedGraph = new Clique(100).getGraph();
        //Graph inducedGraph = new Grid(40).getGraph();
        //Graph inducedGraph = new CliqueCycle().getGraph();

        //Graph inducedGraph = new SplitGraph().getGraph();
        //Graph inducedGraph = new CliqueCycle().getGraph();

        //new InstanceManager().extractAllLPGraphs();

        //new LBMinorWExecutor().executeOnRemainingInstances();
        //new MDSTLBExecutor().executeOnRemainingInstances();
        //new OneMaxDegreeCalculator().executeOnRemainingInstances();

        //new SeparationExecution().executeOnRemainingInstances();

        //SeparationBackdoorExecution execution = new SeparationBackdoorExecution();
        //execution.executeOnSpecialInstances(fileNames, false);
        //new SimpleSatExecutor().runOnSimpleInstances(3000);
        //new SimilarVerticesExecutor().executeOnRemainingInstances();
        //new SimpleFVDExecutor().runOnSimpleInstances(3000);

        //InstanceManager instanceManager = new InstanceManager();
        //Instance instanceByFileName = instanceManager.getInstanceByName("noswot.mps");
        //Instance instanceByFileName = instanceManager.getInstanceByName("ns1766074.mps");
        //GraphNew graph = instanceManager.getGraph(instanceByFileName);

        //new SeparationAlgorithm().getProperSeparation(graph);

        //new SimpleSatExecutor().executeAndStore(instanceByFileName, 28,28, 7000);
        //new SimpleSatExecutor().executeAndStore(instanceByFileName, 29,29, 7000);
        //new SimpleSatExecutor().executeAndStore(instanceByFileName, 30,30, 7000);
        //new SimpleLPDecisionExecutor().executeAllLPForInstance(instanceByFileName, 6000);
        //new SeparationSecondBiggestExecutor().executeOnRemainingInstances();
        //SeparationSecondExpriment properSeparation = new SeparationSecondBiggestComp().getProperSeparation(graph);
        //SeparationMinimumSizeMinimumMaxExpriment properSeparation = new SeparationMinSepMinCom().getProperSeparation(graph);
        //SeparationSecondExpriment properSeparation = new SeparationMinimum().getProperSeparation(graph, 10);
        //MinimumCut.findBiggestSecondCompSeparation(graph, 0 , 99);
        //SeparationMinimumMaxComponentExpriment properSeparation = new SeparationAlgorithm().getProperSeparation(graph);
        //System.out.println(properSeparation);
        //new Separation2Executor().executeOnRemainingInstances();

        //SimpleLPExpriment expriment = new SimpleLPDecision(graph, 23, 23).solveModel(3000);
        //new SimpleSatExecutor().executeAndStore(instanceByFileName, 23, 23, 6000);
        //System.out.println(expriment);
        //new Separation2Executor().executeOnRemainingInstances();
        //new HeuristicPartialDao().removeAll();
        //new FinalHeuristicDao().removeAll();


        //new FinalHeuristicExecutor().executeOnRemainingInstances();

        //SimpleSatDao satDao = new SimpleSatDao();
        //SimpleLPDao lpDao = new SimpleLPDao();
        //DecisionLPDao decisionLPDao = new DecisionLPDao();
        //CoveredSimilarDao coveredSimilarDao = new CoveredSimilarDao();
        //Instance instanceByFileName = instanceManager.getInstanceByName("Grid-15");


        //List<SimpleLPExpriment> experiments = lpDao.getAllExprimentsByInstance(instanceByFileName);

        //List<SimpleSatExperiment> experiments = satDao.getAllExprimentsByInstance(instanceByFileName);

        //List<SimpleLPDecisionExpriment> experiments = decisionLPDao.getAllExprimentsByInstance(instanceByFileName);

        //List<FVDSimilarCoveredExperiment> experiments = coveredSimilarDao.getAllExprimentsByInstance(instanceByFileName);

        //Collections.sort(experiments);



        /*EntityManager entityManager = PersistenceManager.getEntityManager();

        Date date = new Date();
        date.setTime(date.getTime()-4L*24L*3600L*1000L);

        List<FVDSimilarCoveredExperiment> experiments = entityManager.createQuery("From FVDSimilarCoveredExperiment ex where ex.executionTime <= :date")
                .setParameter("date", date).getResultList();
*/
        /*for(FVDSimilarCoveredExperiment experiment:experiments) {
            System.out.println(experiment);
            //if((experiment.getC()!=15))

        }*/

        //new HeuristicPartialDao().removeAll();
        //new FinalHeuristicDao().removeAll();
        //new FinalHeuristicExecutor().executeOnRemainingInstances();

        //new FinalHeuristicExecutor().executeOnAllInstances();
        //System.out.println(experiment);
        //List<VertexEntity> selectedVertices = experiment.getSelectedVertices();
        //List<Integer> integers = new ArrayList<>();
        //for(VertexEntity entity:selectedVertices)
        //    integers.add(entity.getVertex());

        /*List<FVDSimilarCoveredExperiment> experiments = coveredSimilarDao.getAllExprimentsByInstance(instanceByFileName);

        for(FVDSimilarCoveredExperiment experiment : experiments) {
            System.out.println(experiment);
        }*/
        /*List<SimpleLPExpriment> experiments = lpDao.getAllExprimentsByInstance(instanceByFileName);
        for(SimpleLPExpriment experiment: experiments)
            System.out.println(experiment);
            */
        /*List<SimpleSatExperiment> experiments = satDao.getAllExprimentsByInstance(instanceByFileName);
        for(SimpleSatExperiment experiment: experiments)
            System.out.println(experiment);*/

        /*SimpleSatExperiment exprimentBy = satDao.getExprimentBy(instanceByFileName, 8, 8);
        List<VertexEntity> verticesOf = new VertexDao().getVerticesOf(exprimentBy);
        List<Integer> integers = new ArrayList<>();
        for(VertexEntity entity: verticesOf)
            integers.add(entity.getVertex());
        */



       // new SimpleSatExecutor().executeAndStore(instanceByFileName, 34, 34, 150000);

      /*List<SimpleSatExperiment> allExprimentsByInstance = satDao
          .getAllExprimentsByInstance(instanceByFileName);
      for(SimpleSatExperiment experiment: allExprimentsByInstance){
        System.out.println(experiment);
      }*/
      //new SimpleLPDecisionExecutor().runOnSimpleInstances(3000);
        //System.out.println(experiment);
        //new SimpleLPExecutor().runOnSimpleInstances(3000);



        //new FVDBothExecutor().runOnSimpleInstances( 3000);
        //new FVDSymetryExecutor().runOnSimpleInstances(3000);
        //new FVDBothExecutor().executeAndStore(instanceByFileName, 14,14, 3000);


        //new LBMinorWExecutor().executeOnRemainingInstances();
        //new MDSTLBExecutor().executeOnRemainingInstances();
        //new OneMaxDegreeCalculator().executeOnRemainingInstances();



        PersistenceManager.close();
        //findNumberOnGrid(7,4);
    }

    public static int findNumberOnGrid(int l, int c){
        int m =2 ;
        int min = Integer.MAX_VALUE;
        while(m<=l*l){
            Integer number = myGridTester(l, c, m);

            if((number!=null)&&(number<min)){
                //System.out.println("p is "+m +" number is "+number);
                min = number;
            }
            m++;
            if (m % 10000 == 0) {
                System.out.println("m is "+m);
            }
        }
        return min;
    }

    public static Integer myGridTester(int l, int c, int p){


        Set<Integer> removingSet = findCycle(l, p);
        if(removingSet.size()==l*l)
            return null;

        int maximumComponentSize = getMaximumComponentSize(l, removingSet);
        System.out.println("for p ="+p+" the number is "+removingSet.size()+ " and c is "+ maximumComponentSize);
        return 0;
    }

    private static int getMaximumComponentSize(int l,  Set<Integer> removingSet) {
        Graph graph = new Grid(l).getGraph();
        GraphNew newStructure = IncidentGraphHelper.getNewStructure(graph);

        for(Integer vertex: removingSet)
            newStructure = removeVertexFromGrid(l, newStructure, vertex);

        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newStructure);
        connectedComponentsHS.sort(new Comparator<GraphNew>() {
            @Override
            public int compare(GraphNew o1, GraphNew o2) {
                return Integer.compare(o1.getSize(),o2.getSize());
            }
        });
        //if(connectedComponentsHS.get(connectedComponentsHS.size()-1).getSize()<=c)
        //    return count;
        return connectedComponentsHS.get(connectedComponentsHS.size()-1).getSize();
    }

    private static GraphNew removeVertexFromGrid(int l, GraphNew newStructure, int n) {
        int row = n/l;
        int col =n % l;
        String name = "n" + row + "," + col;
        Integer vertexByName = newStructure.getGraphInfo().getVertexByName(name);
        newStructure = newStructure.removeVertexCopy(vertexByName);
        return newStructure;
    }

    static Set<Integer> findCycle(int l , int p){
        int n = 0;
        Set<Integer> removingSet = new HashSet<>();
        int count = 0;
        while(true){
            n = (n+p) % (l*l);
            if(removingSet.contains(n))
                break;
            removingSet.add(n);
            count++;
        }
        return removingSet ;
    }

}