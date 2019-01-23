package at.tuwien.ac.graph.heuristic;

import at.tuwien.ac.graph.minimumCut.MinimumCut;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by e1528895 on 10/12/17.
 */
public class AllSeparationCalculator {
    private static Logger logger = LoggerFactory.getLogger(AllSeparationCalculator.class);

    SeparationMinMaxCompDao separationMinMaxCompDao = new SeparationMinMaxCompDao();
    SeparationMMDao separationMMDao = new SeparationMMDao();
    SeparationSecondDao separationSecondDao = new SeparationSecondDao();
    SeparationThresholdDao separationThresholdDao = new SeparationThresholdDao();
    SeparationMaxDegreeDao separationMaxDegreeDao = new SeparationMaxDegreeDao();
    InstanceManager instanceManager = new InstanceManager();


    private int minMaxComponentSize = Integer.MAX_VALUE;
    Set<Integer> bestGenericSeparation ;

    int minCompMinSep = Integer.MAX_VALUE ;
    Set<Integer> bestMinMin = null;

    int maxSecondComponentSize = Integer.MIN_VALUE;
    Set<Integer> bestSecond = null;


    int[] minThreshhold ;
    Set<Integer>[] bestThreshhold ;

    public List<Separation> getProperSeparation(Instance instance, List<Integer> thresholds, int c) {
        GraphNew graph = instanceManager.getGraph(instance);
        SeparationMaximumDegreeExperiment maxDegreeSeparation = getMaxDegreeSeparation(new GraphNew(graph));
        //Lets assume that the graph is connected
        minThreshhold = new int[thresholds.size()];
        bestThreshhold = new Set[thresholds.size()];
        for(int i = 0; i < thresholds.size(); i++ ){
            minThreshhold[i] = Integer.MAX_VALUE ;
        }
        //TODO tobe other variables initialize
        long startTime = System.currentTimeMillis();

        List<Integer> vertices = graph.getVertices();
        for (Integer source : vertices) {
        //int source = 380 ;
        //int sink = 19 ;
            logger.debug("source is "+ source);
            for (Integer sink : vertices) {
                if (source <= sink)
                    continue;
                if (graph.getNeighbors(source).contains(sink))
                    continue;

                GraphNew graphNew = new GraphNew(graph);
                searchForBetterSeparations(graphNew, source, sink, thresholds);
            }
        }
        long duration = System.currentTimeMillis() - startTime ;

        List<Separation> returnList = new ArrayList<>();
        if(bestGenericSeparation!=null) {
            SeparationMinimumMaxComponentExpriment genericSeparation = new SeparationMinimumMaxComponentExpriment();
            setSeparationParametrs(instance, duration, bestGenericSeparation, genericSeparation);
            separationMinMaxCompDao.save(genericSeparation);
            returnList.add(genericSeparation);
        }else{
            logger.debug("There is no generic separation for this graph");
        }

        if(bestMinMin!=null) {
            SeparationMinimumSizeMinimumMaxExpriment minMinExpriment = new SeparationMinimumSizeMinimumMaxExpriment();
            setSeparationParametrs(instance, duration, bestMinMin, minMinExpriment);
            separationMMDao.save(minMinExpriment);
            returnList.add(minMinExpriment);
        }else{
            logger.debug("There is no Min Min separation for this graph");
        }

        if(bestSecond!=null) {
            SeparationSecondExpriment secondExpriment = new SeparationSecondExpriment();
            setSeparationParametrs(instance, duration, bestSecond, secondExpriment);
            separationSecondDao.save(secondExpriment);
            returnList.add(secondExpriment);
        }else{
            logger.debug("There is no second component separation for this graph");
        }

        for(int i = 0; i < thresholds.size(); i++) {
            if(bestThreshhold[i]!=null) {
                SeparationThresholdExperiment minimumExpriment = new SeparationThresholdExperiment();
                setSeparationParametrs(instance, duration, bestThreshhold[i], minimumExpriment);
                minimumExpriment.setThreshold(thresholds.get(i));
                separationThresholdDao.save(minimumExpriment);
                if(thresholds.get(i)==c)
                    returnList.add(minimumExpriment);
            }else{
                logger.debug("There is no threshold separation for this graph with threshold = "+thresholds.get(i));
            }
        }

        if(maxDegreeSeparation!=null) {
            //TODO please make it like others (using setSepParams method)
            maxDegreeSeparation.setInstance(instance);
            maxDegreeSeparation.setOperationDuration(duration);
            maxDegreeSeparation.setExecutionTime(new Date());
            separationMaxDegreeDao.save(maxDegreeSeparation);
            returnList.add(maxDegreeSeparation);
        }

        return returnList;
    }

    private void setSeparationParametrs(Instance instance, long duration, Set<Integer> source, Separation dest) {
        dest.setSize(source.size());
        List<VertexEntity> entities = VertexDao.toVertexEntity(new ArrayList<>(source), dest);
        dest.setSeparation(new HashSet<>(entities));
        dest.setInstance(instance);
        dest.setExecutionTime(new Date());
        dest.setOperationDuration(duration);
    }

    static int flowTime = 0;
    static int compareTotal = 0;
    static int contractTotal = 0;

    public void/*List<Set<Integer>>*/ searchForBetterSeparations(GraphNew graph, Integer source, Integer sink, List<Integer> thresholds) {
        int sourceSize= 1;
        int sinkSize = 1;
        Set<Integer> previousSeparation = null ;
        List<GraphNew> previousComponentList = null;
        while (!graph.getNeighbors(source).contains(sink)) {

            long startTime = System.currentTimeMillis();
            Set<Integer> separation = MinimumCut.createJGraphAndComputeSeparation(graph, source, sink);
            flowTime += (System.currentTimeMillis() - startTime) ;

            GraphNew newGraph = new GraphNew(graph);
            for (Integer removingVertex : separation)
                newGraph = newGraph.removeVertexCopy(removingVertex);

            List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(newGraph);

            GraphNew sourceComponent = null;
            GraphNew sinkComponent = null;

            List<Integer> sizeList = new ArrayList();
            for (GraphNew component : connectedComponentsHS) {
                int ssSize =1;
                startTime = System.currentTimeMillis();
                if(sourceComponent==null)
                    if (component.getVertices().contains(source)) {
                        sourceComponent = component;
                        ssSize = sourceSize ;
                    }
                if(sinkComponent==null)
                    if (component.getVertices().contains(sink)) {
                        sinkComponent = component;
                        ssSize = sinkSize ;
                    }
                compareTotal += (System.currentTimeMillis()-startTime);
                int size = component.getSize();


                sizeList.add(ssSize+size-1);

            }

            sizeList.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });

            int biggestComponent = sizeList.get(0);
            int secondBiggestComponent = sizeList.get(1);
            int minMinValue = biggestComponent + separation.size();

            //Minimum biggest component
            boolean minMaxMetric = (biggestComponent < minMaxComponentSize)||
                    ((biggestComponent==minMaxComponentSize)&&(separation.size()<bestGenericSeparation.size()));
            if(minMaxMetric){
                logger.debug("A better common separation found with max component size "+ biggestComponent+
                        " and separation size "+ separation.size());
                minMaxComponentSize = biggestComponent;
                bestGenericSeparation = separation ;
            }

            //Minimum Minimum Metric
            boolean minMinMetric=  minMinValue<minCompMinSep;
            if(minMinMetric){
                logger.debug("A better min min separation found with max component size "+ biggestComponent+
                        " and separation size "+ separation.size()+ " = "+minMinValue);
                minCompMinSep = minMinValue;
                bestMinMin =separation ;
            }

            //Maximum Second Biggest Component
            boolean secondMetric = (secondBiggestComponent > maxSecondComponentSize)
                    || ((secondBiggestComponent == maxSecondComponentSize)&&(separation.size()<bestSecond.size()));
            if(secondMetric){
                logger.debug("A better second comp separation found with max component size "+ biggestComponent+
                        ", second component size "+ secondBiggestComponent +" and separation size "+ separation.size());
                logger.debug("Source is "+ source+ " sink is "+ sink);
                maxSecondComponentSize = secondBiggestComponent ;
                bestSecond = separation ;
            }

            for(int i = 0 ; i < thresholds.size(); i++){
                Integer threshold = thresholds.get(i);
                boolean thresholdMetric = (secondBiggestComponent>=threshold)
                        && (separation.size()< minThreshhold[i]);
                if(thresholdMetric){
                    logger.debug("A better threshold separation found with max component size "+ biggestComponent+
                            " second component size "+ secondBiggestComponent+" and separation size "+ separation.size()
                    + " for threshold "+ thresholds.get(i));
                    minThreshhold[i]= separation.size();
                    bestThreshhold[i]=separation ;
                }
            }


            Integer vertex = null;
            GraphNew selectedComponent = null;
            if(sourceSize+ sourceComponent.getSize()>sinkSize+sinkComponent.getSize()) {
                selectedComponent = sinkComponent;
                sinkSize += sinkComponent.getSize() + separation.size()-1;
                vertex = sink ;
            }else {
                selectedComponent = sourceComponent;
                sourceSize += sourceComponent.getSize() + separation.size()-1;
                vertex = source ;
            }


            //TODO reconsider it
            //Here we assume that union of minComponent and separation set is connected

            startTime = System.currentTimeMillis();
            while (true) {
                List<Integer> neighbors = graph.getNeighbors(vertex);
                Integer selectedNeighbor = null ;
                for (Integer neighbor : neighbors)
                    if ((separation.contains(neighbor)) || (selectedComponent.getVertices().contains(neighbor))) {
                        selectedNeighbor = neighbor ;
                        break;
                    }
                if(selectedNeighbor!=null)
                    graph.contractEdge(vertex, selectedNeighbor);
                else
                    break;
            }
            contractTotal+=(System.currentTimeMillis()-startTime);

        }
    }

    private SeparationMaximumDegreeExperiment getMaxDegreeSeparation(GraphNew graph) {
        List<Integer> separationList = new ArrayList<>();
        List<GraphNew> connectedComponents = null;
        do{
            Integer vertex = graph.getMaxDegreeList().get(0);
            separationList.add(vertex);
            graph = graph.removeVertexCopy(vertex);
            connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        }while(connectedComponents.size()==1);
        connectedComponents.sort(new Comparator<GraphNew>() {
            @Override
            public int compare(GraphNew o1, GraphNew o2) {
                return Integer.compare(o2.getSize(),o1.getSize());
            }
        });
        int secondBiggestSize = connectedComponents.get(1).getSize();
        int biggestComponentSize = connectedComponents.get(0).getSize();
        if((biggestComponentSize / secondBiggestSize)>9)
            return null;
        //System.out.println(Arrays.toString(separationList.toArray()));
        //for(GraphNew component: connectedComponents)
        //    System.out.println(component.getSize());
        SeparationMaximumDegreeExperiment maximumDegreeSeparation = new SeparationMaximumDegreeExperiment();
        maximumDegreeSeparation.setSize(separationList.size());
        Set<VertexEntity> entities = new HashSet<>();
        for(Integer vertex : separationList){
            VertexEntity entity = new VertexEntity();
            entity.setVertex(vertex);
            entity.setExpriment(maximumDegreeSeparation);
            entities.add(entity);
        }
        maximumDegreeSeparation.setSeparation(entities);
        return maximumDegreeSeparation ;
    }





    public List<Separation> getSeparations(Instance instance, List<Integer> thresholds, int c) {
        List<Separation> separations =new ArrayList<>();
        SeparationMinimumMaxComponentExpriment separationMinimumMaxComponentExpriment = separationMinMaxCompDao.getExprimentByInstance(instance);
        SeparationMinimumSizeMinimumMaxExpriment separationMMDaoExpriment = separationMMDao.getExprimentByInstance(instance);
        SeparationSecondExpriment separationSecondExpriment = separationSecondDao.getExprimentByInstance(instance);
        SeparationMaximumDegreeExperiment separationMaximumDegreeExperiment = separationMaxDegreeDao.getExprimentByInstance(instance);
        SeparationThresholdExperiment separationThresholdExperiment = separationThresholdDao.getExperimentByInstance(instance, c);
        //List<SeparationThresholdExperiment> allthreshold = separationThresholdDao.getAllExprimentsByInstance(instance);

        boolean sepAlreadyStored = (separationMinimumMaxComponentExpriment!=null);
        if(sepAlreadyStored) {
            separations.add(separationMinimumMaxComponentExpriment);
            if(separationMMDaoExpriment!=null)
                separations.add(separationMMDaoExpriment);
            if(separationSecondExpriment!=null)
                separations.add(separationSecondExpriment);
            if(separationMaximumDegreeExperiment!=null)
                separations.add(separationMaximumDegreeExperiment);
            if(separationThresholdExperiment!=null)
                separations.add(separationThresholdExperiment);

            removeDuplicateSeparaations(separations);


            return separations;
        }

        logger.debug("Callculating separations");
        separations = getProperSeparation(instance, thresholds, c);

        //check for duplicate separations
        removeDuplicateSeparaations(separations);


        return separations;
    }

    private void removeDuplicateSeparaations(List<Separation> separations) {
        Set<Integer> duplicateSepIndices = new HashSet<>();
        for(int i=0; i < separations.size(); i++)
            for(int j=i+1; j < separations.size(); j++){
                Separation separation = separations.get(j);
                if(isEqual(separations.get(i),separation)) {
                    logger.debug("separation "+ j + " with type "+ separation.getClass().getName() + " is duplicate");
                    duplicateSepIndices.add(j);
                }
            }
        //It is assumed that the list is sorted
        List<Integer> duplicateIndicesList = new ArrayList(duplicateSepIndices);
        Collections.sort(duplicateIndicesList);
        for(int i=duplicateSepIndices.size()-1;i>=0; i--){
            int index = duplicateIndicesList.get(i);
            logger.debug("removing separation with index "+ index);
            Separation remove = separations.remove(index);
            System.out.println(remove);
        }
    }

    private boolean isEqual(Separation separation1, Separation separation2) {
        if(separation1.getSize()!=separation2.getSize())
            return false ;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        VertexDao vertexDao = new VertexDao();
        List<VertexEntity> realseps1 = vertexDao.getVerticesOf(separation1);
        List<VertexEntity> realseps2 = vertexDao.getVerticesOf(separation2);
        for(VertexEntity entity : realseps1){
            list1.add(entity.getVertex());
        }
        for(VertexEntity entity : realseps2){
            list2.add(entity.getVertex());
        }

        for(Integer vertex:list1){
            if(!list2.contains(vertex))
                return false ;
        }
        return true;
    }

    public static void main(String[] args) {

        InstanceManager instanceManager = new InstanceManager();
        Instance instance = instanceManager.getInstanceByName("Grid-15");
        List<Integer> thresholds = new ArrayList<>();
        for(int i = 2; i <40; i++)
            thresholds.add(i);
        List<Separation> properSeparation = new AllSeparationCalculator().getProperSeparation(instance, thresholds, 20);
        for(Separation separation: properSeparation) {
            List<VertexEntity> verticesOf = new VertexDao().getVerticesOf(separation);
            System.out.println(separation);
            System.out.println(Arrays.toString(verticesOf.toArray()));
            System.out.println();
            //if(separation instanceof SeparationThresholdExperiment)
            //    new SeparationThresholdDao().save(separation);

        }
    }

}
