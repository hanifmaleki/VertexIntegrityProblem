package at.tuwien.ac.graph.bounds.ub;

import at.tuwien.ac.graph.ops.executors.SimpleExecutor;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.expriments.OneMaxDegreeUB;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/27/17.
 */

//TODO refactor this class with new dao structure
public class OneMaxDegreeCalculator extends SimpleExecutor<OneMaxDegreeUB>{

    Logger logger = LoggerFactory.getLogger(OneMaxDegreeUB.class);
    private InstanceManager instanceManager = new InstanceManager();

    /*
    public OneMaxDegreeUB calculatrUpperBound(Instance instance){

        logger.debug(instance.toString());
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();
        List<Integer> upperBoundList = getUpperBound(graph);
        Long period = System.currentTimeMillis() - currentTime ;

        OneMaxDegreeUB oneMaxDegreeUB = new OneMaxDegreeUB();
        List<VertexEntity> vertexEntityList = new ArrayList<>();
        for(Integer item: upperBoundList){
            VertexEntity entity = new VertexEntity();
            entity.setVertex(item);
            entity.setExpriment(oneMaxDegreeUB);
            vertexEntityList.add(entity);
        }

        oneMaxDegreeUB.setSimilarVertexEntities(vertexEntityList);
        oneMaxDegreeUB.setExecutionTime(date);
        oneMaxDegreeUB.setInstance(instance);
        oneMaxDegreeUB.setOperationDuration(period);
        oneMaxDegreeUB.setSize(upperBoundList.size());
        return oneMaxDegreeUB ;
    }
    */


    public List<Integer> getUpperBound(GraphNew graph) {
        List<Integer> vertexList = new ArrayList<>();

        while(true){
            //long time = System.currentTimeMillis();
            List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
            //System.out.println((System.currentTimeMillis()-time));

            GraphNew maxComponent = connectedComponents.get(0);

            logger.debug("The graph has "+ maxComponent.getSize()+ " number of components");

            for (GraphNew component : connectedComponents) {
                if (component.getSize() > maxComponent.getSize())
                    maxComponent = component;
            }

            if (maxComponent.getSize() <= vertexList.size())
                return vertexList;

            List<Integer> maxDegreesList = maxComponent.getMaxDegreeList();

            logger.debug("Maximum Component Size: " + maxComponent.getSize()
                    +"\tVertexList Size: " + vertexList.size()
                    +"\tmax Degree: "+maxComponent.getMaxDegree()
                    +"\tmaxDegreeCount: " + maxDegreesList.size());

            //time = System.currentTimeMillis();
            logger.debug("Vertices are selecting from "+maxDegreesList.size());

            //TODO correct it
            List<Integer> selectedVertex = getSelectedVertex(maxComponent, maxDegreesList, 1);

            for(Integer index: selectedVertex) {
                graph = graph.removeVertexCopy(index);
                logger.debug("vertex "+ index +" has been removed");
            }


            vertexList.addAll(selectedVertex);
        }

    }

    private List<Integer> getSelectedVertex(GraphNew graph, List<Integer> maxDegreesList, int count) {
        Integer selected = maxDegreesList.get(0);
        List<Integer> list = new ArrayList<>();
        list.add(selected);
        return list;
        /*
        if((count==1)||(maxDegreesList.size()==1)) {
            Integer selectedRemovingVertex = getSelectedRemovingVertex(graph, maxDegreesList);
            List<Integer> removingList = new ArrayList<>();
            removingList.add(selectedRemovingVertex);
            return removingList;
        }
        return  getTowSelectedRemovingVertex(graph, maxDegreesList);
        */
    }

    /*public void executeUBs(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("backdoor");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        List<Instance> resultList = em.createQuery("from Instance i ORDER BY i.size").getResultList();
        em.getTransaction().commit();
        for(Instance instance : resultList) {
            //System.out.println(instance);
            OneMaxDegreeUB oneMaxDegreeUB = calculatrUpperBound(instance);
            em.getTransaction().begin();
            //for(VertexEntity vertexEntity : oneMaxDegreeUB.getSimilarVertexEntities())
            //    em.persist(vertexEntity);
            em.persist(oneMaxDegreeUB);
            em.getTransaction().commit();
        }

        em.close();
        factory.close();
    }*/

    @Override
    public OneMaxDegreeUB doSimpleExperiment(GraphNew graph) {
        List<Integer> upperBound = getUpperBound(graph);
        OneMaxDegreeUB maxDegreeUB = new OneMaxDegreeUB();
        maxDegreeUB.setSize(upperBound.size());
        List<VertexEntity> entities = new ArrayList<>();
        for(Integer vertex : upperBound){
            VertexEntity entity = new VertexEntity();
            entity.setExpriment(maxDegreeUB);
            entity.setVertex(vertex);
            entities.add(entity);
        }
        maxDegreeUB.setSelectedVertices(entities);
        return maxDegreeUB;
    }

}
