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
public class ModernFVDSolver implements Runnable{

    Logger logger = LoggerFactory.getLogger(ModernFVDSolver.class);
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

    private boolean symmetryBraking = true ;

    Integer[] indexedArray ;
    int indexCounter ;
    private int originalK;
    private boolean pauseFlag = false;


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

        indexedArray = new Integer[graph.getN()];
        indexCounter=0;

        originalK = k ;


        FVDAnswer answer = getFVD(graph, k, c, graph.getSize(), globalForbidenVertices, true, true);
        List<Integer>fvd = null ;
        if(answer!=null)
            fvd = answer.vertices;
        if(answer==null)
            graphLb=k+1;

        SimpleFVDExperiment experiment = new SimpleFVDExperiment();

        long currentTime = System.currentTimeMillis();
        experiment.setC(c);
        boolean finished = currentTime-startTime< (timeout*1000);//!stopFlag ;
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

    private FVDAnswer getFVD(GraphNew graph, int k, int c, int bestLB, boolean[] globalForbiddenVertices, boolean root, boolean mostLeftBranch) {
        //callCount++;
        if(stopFlag==true) {
            FVDAnswer answer = new FVDAnswer();
            answer.vertices=null;
            answer.lbUpdate = false;
            return answer;
        }
        if(currentBranch.size()>graphUb) {
            FVDAnswer answer = new FVDAnswer();
            answer.vertices=null;
            answer.lbUpdate = true;
            logger.debug("We should not be here");
            System.out.println("We should not be here");
            return answer;
        }

        if(graphUb<=graphLb){
            logger.debug("Lower and upper bound are equal in " +graphLb+ "  . stopping");
            System.out.println("Lower and upper bound are equal in " +graphLb+ "  . stopping");
            FVDAnswer answer = new FVDAnswer();
            answer.vertices=null;
            answer.lbUpdate = false;
            stopFlag= true ;
        }

        List<Integer> deletionList = new ArrayList<>();

        //long tmp = System.currentTimeMillis();

        KernelizationResult preprocess = KernelizationNew.preprocess(graph, k, c, bestLB);


        //Check if kernelizeation return NO instance
        if(preprocess==null)
            return null;

        List<FVDInstance> fvdInstances = preprocess.getFvdInstanceList();
        deletionList.addAll(preprocess.getRemovedVertices());

        //Check if kernelization return YES instance
        if(fvdInstances.size()==0) {
            FVDAnswer fvdAnswer = new FVDAnswer();
            fvdAnswer.vertices=deletionList;
            fvdAnswer.lbUpdate=true ;
            updateUpperBound(k, root, deletionList);
            return fvdAnswer;
        }

        //componentsMeasure += (System.currentTimeMillis()-tmp);
        if (fvdInstances.size() > 1) {

            /*System.out.println("Pausing current thread");
            logger.debug("Pausing current thread");
            this.pauseFlag = true ;*/

            int totalFoundLB=0;

            for (FVDInstance instance : fvdInstances) {
                GraphNew h=instance.getGraph();
                int parameter = instance.getParameter();

                FVDAnswer answer = getFVD(h, parameter, c, instance.getGraphBestLB(), globalForbiddenVertices, false, mostLeftBranch);
                /*ModernFVDCaller modernFVDCaller = new ModernFVDCaller();
                modernFVDCaller.setSymmetryBraking(symmetryBraking);
                modernFVDCaller.setCoverPrune(coverPrune);
                modernFVDCaller.setSimilarPrune(similarPrune);
                int newParameter = parameter - totalFoundLB;
                SimpleFVDExperiment myAns = modernFVDCaller.findFVD(h, newParameter, c, timeout);
                //if(root){
                    logger.debug("answer for graph with size: "+ h.getSize()+" is "+myAns);
                    System.out.println("answer for graph with size: "+ h.getSize()+" is "+myAns);
                //}*/


                /*if((totalFoundLB+deletionList.size()>k)||(myAns.getLb()>newParameter)) {
                    //TODO may be it is not needed to update lb
                    FVDAnswer ans =new FVDAnswer();
                    ans.vertices=null;
                    ans.lbUpdate = true;
                    return ans;
                }*/

                List<Integer> fvd = null;
                if(answer!=null)
                    fvd = answer.vertices;
                if (fvd == null) {
                    FVDAnswer ans =new FVDAnswer();
                    ans.vertices=null;
                    ans.lbUpdate = answer.lbUpdate;
                    return ans;
                } else
                    totalFoundLB+=fvd.size();

                /*List<Integer> fvd = new ArrayList<>();
                for(VertexEntity entity : myAns.getSelectedVertices())
                    fvd.add(entity.getVertex());*/

                for (Integer vertex : fvd)
                    deletionList.add(vertex);
            }

            updateUpperBound(k, root, deletionList);

            /*System.out.println("Resuming previous thread");
            logger.debug("Resuming previous thread");
            this.pauseFlag= false;*/

            if (deletionList.size() > k){
                FVDAnswer answer = new FVDAnswer();
                answer.lbUpdate=true;
                answer.vertices=null;
                return answer;
            }



            FVDAnswer answer = new FVDAnswer();
            answer.vertices=deletionList;
            answer.lbUpdate=true ;
            return answer;
        } else {

            FVDInstance instance = fvdInstances.get(0);
            GraphNew instanceGraph = instance.getGraph();
            int parameter = instance.getParameter();

            List<Integer> connectedVertices = GraphNewHelper.getConnectedVertices(instanceGraph, c + 1);
            //Maximum degree vertex selection policy
            Collections.sort(connectedVertices, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(instanceGraph.getDegreeof(o2), instanceGraph.getDegreeof(o1));
                }
            });

            if(symmetryBraking)
                for(Integer vertex : connectedVertices){
                    if(indexedArray[vertex]==null){
                        indexedArray[vertex]=indexCounter;
                        indexCounter++;
                    }
                }

            Set<Integer> forbiddenSet = new HashSet<>();
            if(coverPrune){
                for(Integer vertex : connectedVertices){
                    Set<Integer> integers = coveringTable.get(vertex);
                    if(integers!=null)
                        forbiddenSet.addAll(integers);
                }

            }
            int min = Integer.MAX_VALUE;
            //Specify whether results is valid for updating lower bound
            //boolean lbUpdate = true ;
            //specify whether any branching is done
            //boolean isBranchingDone = false;


            for (Integer removingVertex : connectedVertices) {
                if ((!mostLeftBranch)&&(symmetryBraking)&& (globalForbiddenVertices[removingVertex]))
                    continue;

                if (forbiddenSet.contains(removingVertex))
                    continue;


                if(similarPrune&&!mostLeftBranch) {
                    Set<Integer> similars = similarsTable.get(removingVertex);
                    if (similars != null)
                        forbiddenSet.addAll(similars);
                }

                //isBranchingDone = true ;


                boolean[] newGlobalForbiddenVertices = null;
                if(symmetryBraking) {
                    newGlobalForbiddenVertices = Arrays.copyOf(globalForbiddenVertices, graph.getN());
                    for (Integer vertex : connectedVertices) {
                        if (indexedArray[removingVertex] >= indexedArray[vertex])
                            newGlobalForbiddenVertices[vertex] = true;
                    }
                }

                //tmp = System.currentTimeMillis();
                currentBranch.add(removingVertex);
                //lbObtainable = true;
                GraphNew newGraph = instanceGraph.removeVertexCopy(removingVertex);//graph.getInducedGraph(vertexMark);
                //inducedGraphMeasure += (System.currentTimeMillis()-tmp);

                FVDAnswer answer = getFVD(newGraph, parameter - 1, c, instance.getGraphBestLB(), newGlobalForbiddenVertices, root, mostLeftBranch);
                List<Integer> fvd = null;
                if(answer!=null)
                    fvd = answer.vertices;
                currentBranch.remove(currentBranch.size()-1);
                if ((fvd != null) && (fvd.size() < min - 1 )) {
                    fvd.add(removingVertex);
                    deletionList = fvd;
                    min = fvd.size();
                    updateUpperBound(k,root,deletionList);

                }

                if((fvd==null)&&(mostLeftBranch)){
                    if((graphLb<= k)&&/*(lbUpdate==true)*/ stopFlag==false) {
                        String additionalData = "";
                        //String additionalData = " root is " + root + " component size is "+ graph.getSize() + " k is " + k
                        //        +" min is "+ min + " graph is "+ graph.getVertices();
                        logger.debug("lower bound is updated with negative answer " + k + additionalData);
                        System.out.println("lower bound is updated with negative answer " + k+ additionalData);
                        graphLb = k;
                    }
                }
                mostLeftBranch=false;
            }


            if (min > parameter) {

                if((root)&&(!stopFlag)){
                    if((graphLb<= k)&&/*(lbUpdate==true)*/ stopFlag==false) {
                        String additionalData = "";
                        //String additionalData = " root is " + root + " component size is "+ graph.getSize() + " k is " + k
                        //        +" min is "+ min + " graph is "+ graph.getVertices();
                        logger.debug("lower bound is updated with negative answer " + k + additionalData);
                        System.out.println("lower bound is updated with negative answer " + k+ additionalData);
                        graphLb = k+1;
                    }
                }

                FVDAnswer answer = new FVDAnswer();
                answer.vertices=null;
                //answer.lbUpdate=lbUpdate;
                answer.lbUpdate=true;
                return answer;
            }

        }

        deletionList.addAll(preprocess.getRemovedVertices());
        if(/*root&&*/!stopFlag){
            if(graphLb< deletionList.size()) {
                logger.debug("lower bound is updated with positive answer "+ deletionList.size() + " root is "+ root);
                System.out.println("lower bound is updated with positive answer "+ deletionList.size() + " root is "+ root);
                graphLb = deletionList.size();
            }
        }

        FVDAnswer answer = new FVDAnswer();
        answer.lbUpdate = true ;
        answer.vertices = deletionList ;
        return answer;
    }

    private void updateUpperBound(int k, boolean root, List<Integer> deletionList) {
        if(root) {
            int newSize = deletionList.size()+ currentBranch.size();

            if(newSize<graphUb) {
                graphUb = newSize;
                logger.debug("*new ub is " + graphUb);
                System.out.println("*new ub is " + graphUb);
                bestAnswer = new ArrayList<>(deletionList);
                bestAnswer.addAll(currentBranch);
                logger.debug("best answer " + bestAnswer.size());
                if((root)&&(graphUb<=originalK))
                    stopFlag=true;
            }


        }
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
            if(pauseFlag)
                continue;
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

    class FVDAnswer{
        List<Integer> vertices ;
        boolean lbUpdate ;

        @Override
        public String toString() {
            return "FVDAnswer{" +
                    "vertices=" + vertices.size() +
                    '}';
        }
    }

    public boolean isSymmetryBraking() {
        return symmetryBraking;
    }

    public void setSymmetryBraking(boolean symmetryBraking) {
        this.symmetryBraking = symmetryBraking;
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for(int i = 0 ; i < 10; i++){
            double random = Math.random();
            int item = (int) (random * 100);
            integers.add(item);
        }

        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        System.out.println(Arrays.toString(integers.toArray()));
    }
}
