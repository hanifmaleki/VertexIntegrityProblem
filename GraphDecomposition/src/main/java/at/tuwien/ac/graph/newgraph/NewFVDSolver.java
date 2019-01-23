package at.tuwien.ac.graph.newgraph;

import at.tuwien.ac.graph.ops.executors.SimilarVerticesExecutor;
import at.tuwien.ac.graph.ops.expriments.SimilarVertexEntity;
import at.tuwien.ac.graph.ops.expriments.SimilarVerticesExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 4/6/17.
 */
public class NewFVDSolver implements Runnable{

    Logger logger = LoggerFactory.getLogger(NewFVDSolver.class);
    //public static long componentsMeasure = 0 ;

    //public static long connectivityMeasure =0;

    //public static long inducedGraphMeasure=0;

    //public static long callCount = 0;

    GraphNew globalGraph;

    //public static boolean similarInitialized =false;

    Hashtable<Integer, Set<Integer>> similarsTable = null;
    Hashtable<Integer, Set<Integer>> coveringTable = null;
    Hashtable<Integer, Set<Integer>> coveredTable = null;
    private int timeout;
    private long startTime;
    private int graphLb ;
    private int graphUb ;


    List<Integer> currentBranch ;
    List<Integer> bestAnswer ;
    private boolean stopFlag;
    private boolean similarPrune = true ;
    private boolean coverPrune = true;

    public SimpleFVDExperiment findFVD(GraphNew graph, int k, int c, int timeout) {

        similarsTable = new Hashtable<>();
        coveringTable = new Hashtable<>();
        coveredTable = new Hashtable<>();
        this.globalGraph = graph ;
        boolean[] globalForbidenVertices ;
        globalForbidenVertices = new boolean[graph.getN()];
        for(int i=0; i < graph.getN(); i++){
            globalForbidenVertices[i]=false;
        }
        initSimilars(graph);
        initCovered(graph);

        this.timeout = timeout ;

        graphLb = 0;
        graphUb = graph.getSize();

        currentBranch = new ArrayList<>();

        stopFlag = false ;

        new Thread(this).start();

        bestAnswer = null ;

        this.startTime = System.currentTimeMillis();

        List<Integer> fvd = getFVD(graph, k, c, graph.getSize(), globalForbidenVertices ,true);

        SimpleFVDExperiment experiment = new SimpleFVDExperiment();

        long currentTime = System.currentTimeMillis();
        experiment.setC(c);
        boolean finished = !stopFlag || (graphLb>=graphUb);
        experiment.setFinished(finished);
        experiment.setOperationDuration(currentTime - startTime);
        experiment.setTimeout(timeout);
        experiment.setLb(graphLb);
        experiment.setK(k);
        experiment.setUb(graphUb);
        if(fvd==null)
            fvd = bestAnswer ;
        if(fvd!=null) {
            experiment.setFvdSize(fvd.size());
            List<VertexEntity> entities = new ArrayList<>();
            for(Integer vertex : fvd){
                VertexEntity entity = new VertexEntity();
                entity.setExpriment(experiment);
                entity.setVertex(vertex);
                entities.add(entity);
            }
            experiment.setSelectedVertices(entities);
        }
        stopFlag = true ;
        return experiment;
    }

    private List<Integer> getFVD(GraphNew graph, int k, int c, int bestLB, boolean[] globalForbiddenVertices, boolean root) {
        //callCount++;
        if(stopFlag==true)
            return null ;
        if(currentBranch.size()>graphUb)
            return null ;

        List<Integer> deletionList = new ArrayList<>();

        //long tmp = System.currentTimeMillis();

        KernelizationResult preprocess = KernelizationNew.preprocess(graph, k, c, bestLB);


        //Check if kernelizeation return NO instance
        if(preprocess==null)
            return null;

        List<FVDInstance> fvdInstances = preprocess.getFvdInstanceList();
        deletionList.addAll(preprocess.getRemovedVertices());

        //Check if kernelization return YES instance
        if(fvdInstances.size()==0)
            return deletionList ;

        //componentsMeasure += (System.currentTimeMillis()-tmp);
        if (fvdInstances.size() > 1) {

            for (FVDInstance instance : fvdInstances) {
                //for (GraphNew h : components) {
                GraphNew h=instance.getGraph();
                int parameter = instance.getParameter();

                List<Integer> fvd = getFVD(h, parameter, c, instance.getGraphBestLB(),  globalForbiddenVertices,false);
                if (fvd == null)
                    return null;


                for (Integer vertex : fvd)
                    deletionList.add(vertex);
            }

            if (deletionList.size() > k){
                if(root) {
                    graphUb = deletionList.size();
                    logger.debug("new ub is "+ graphUb);
                    System.out.println("new ub is "+ graphUb);
                    bestAnswer = new ArrayList<>(deletionList);
                    bestAnswer.addAll(currentBranch);
                }
                return null;
            }

            return deletionList;
        } else {

            FVDInstance instance = fvdInstances.get(0);
            GraphNew instanceGraph = instance.getGraph();
            int parameter = instance.getParameter();

            //tmp = System.currentTimeMillis();

            List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(instanceGraph, c + 1);
            Collections.sort(connectedVertices, new Comparator<Integer>() {
                @Override
                public int compare(Integer o2, Integer o1) {
                    return Integer.compare(instanceGraph.getDegreeof(o2), instanceGraph.getDegreeof(o1));
                }
            });
            //connectivityMeasure += (System.currentTimeMillis()-tmp);
            Set<Integer> forbiddenSet = new HashSet<>();
            if(coverPrune){
                for(Integer vertex : connectedVertices){
                    Set<Integer> integers = coveringTable.get(vertex);
                    if(integers!=null)
                        forbiddenSet.addAll(integers);
                }

            }
            //boolean lbObtainable = false ;
            int min = Integer.MAX_VALUE;
            for (Integer removingVertex : connectedVertices) {
                if (globalForbiddenVertices[removingVertex])
                    continue;

                if (forbiddenSet.contains(removingVertex))
                    continue;


                if(similarPrune) {
                    Set<Integer> similars = similarsTable.get(removingVertex);
                    if (similars != null)
                        forbiddenSet.addAll(similars);
                }

                boolean[] newGlobalForbiddenVertices = Arrays.copyOf(globalForbiddenVertices, graph.getN());
                for(Integer vertex: connectedVertices){
                    if(removingVertex>=vertex)
                        newGlobalForbiddenVertices[vertex]=true;
                }

                //tmp = System.currentTimeMillis();
                currentBranch.add(removingVertex);
                //lbObtainable = true;
                GraphNew newGraph = instanceGraph.removeVertexCopy(removingVertex);//graph.getInducedGraph(vertexMark);
                //inducedGraphMeasure += (System.currentTimeMillis()-tmp);

                List<Integer> fvd = getFVD(newGraph, parameter - 1, c, instance.getGraphBestLB(), newGlobalForbiddenVertices, root);
                currentBranch.remove(currentBranch.size()-1);
                if ((fvd != null) && (fvd.size() < min - 1 )) {
                    fvd.add(removingVertex);
                    deletionList = fvd;
                    min = fvd.size();
                    if(graphUb> deletionList.size()+currentBranch.size())
                        if(root){
                            graphUb = deletionList.size() + currentBranch.size();
                            logger.debug("new ub is "+ graphUb);
                            System.out.println("new ub is "+ graphUb);
                            bestAnswer = new ArrayList<>(deletionList);
                            bestAnswer.addAll(currentBranch);
                        }

                }
            }

            if (min > parameter) {
                //if((root)&&(lbObtainable)){
                if((root)&&(!stopFlag)){
                    if(graphLb< k)
                        graphLb = k ;
                }
                return null;
            }

            if(graphUb<=graphLb){
                stopFlag= true ;
            }
        }

        deletionList.addAll(preprocess.getRemovedVertices());
        if(root){
            if(graphLb< deletionList.size())
                graphLb = deletionList.size();
        }
        return deletionList;
    }

    public void initSimilars(GraphNew graph){
        SimilarVerticesExperiment experiment = new SimilarVerticesExecutor().doSimpleExperiment(graph);
        List<SimilarVertexEntity> similarVertexEntities = experiment.getSimilarVertexEntities();
        Integer bagCount = experiment.getBagCount();

        List<Set<Integer>> similarVertices = new ArrayList<>(bagCount);
        for(int i=0; i< bagCount; i++)
            similarVertices.add(new HashSet<>());

        for(SimilarVertexEntity entity: similarVertexEntities){
            Integer index = entity.getBagNumber();
            Set<Integer> set = similarVertices.get(index);
            set.add(entity.getVertex());
        }

        Iterator<Set<Integer>> iterator = similarVertices.iterator();
        while(iterator.hasNext()){
            Set<Integer> set = iterator.next();
            if(set.size()==1)
                iterator.remove();
            for(Integer vertex : set){
                Set<Integer> newSet = new HashSet<>(set);
                newSet.remove(vertex);
                similarsTable.put(vertex, newSet);
            }
        }


    }

    public void initCovered(GraphNew graph){
        for(Integer vertex1 : graph.getVertices()){
            Set set = new HashSet();
            for(Integer vertex2 : graph.getVertices()){
                if (vertex2==vertex1)
                    continue;
                if(GraphNewHelper.isCovered(graph, vertex1, vertex2)){
                    if(!GraphNewHelper.isCovered(graph, vertex2, vertex1)) {
                        Set<Integer> integers = coveredTable.get(vertex2);
                        if (integers == null)
                            integers = new HashSet<>();
                        integers.add(vertex1);
                        set.add(vertex2);
                    }
                }
            }
            if(!set.isEmpty()) {
                coveringTable.put(vertex1, set);
            }
        }

    }



    @Override
    public void run() {
        while(!stopFlag){
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long currentTime = System.currentTimeMillis();
            long delta = (currentTime - startTime) / 1000;
            /*String msg = "";
            for(Integer vertex : currentBranch) {
                msg += globalGraph.getGraphInfo().getNameOf(vertex)+" ";
            }
            msg = delta + ": Current Lb is "+graphLb+" Current Branch "+ msg;*/
            String msg = delta + ": Current Lb is "+graphLb+" Current Branch "+ Arrays.toString(currentBranch.toArray());
            logger.debug(msg);
            System.out.println(msg);


            if(delta > timeout) {
                logger.debug("Current Time - start Time is "+ delta);
                stopFlag = true;
            }
        }

        logger.debug("Thread is finished");
        System.out.println("Thread is finished");
    }

    public boolean isSimilarPrune() {
        return similarPrune;
    }

    public void setSimilarPrune(boolean similarPrune) {
        this.similarPrune = similarPrune;
    }

    public boolean isCoverPrune() {
        return coverPrune;
    }

    public void setCoverPrune(boolean coverPrune) {
        this.coverPrune = coverPrune;
    }

    Set<Integer> getIntersctionOf(Set<Integer> set1, Set<Integer> set2){
        Set newSet = new HashSet();
        for(Integer v1:set1)
            if(set2.contains(v1))
                newSet.add(v1);
        return newSet ;
    }
}
