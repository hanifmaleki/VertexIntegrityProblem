package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.labeled.LabeledGraph;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.Separation;
import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.ops.instance.MyMPSReader;
import at.tuwien.ac.graph.samples.BinaryTree;
import at.tuwien.ac.graph.samples.Clique;
import at.tuwien.ac.graph.samples.CliqueCycle;
import at.tuwien.ac.graph.samples.Grid;
import at.tuwien.ac.graph.samples.SplitGraph;
import at.tuwien.ac.graph.utils.GFReader;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import at.tuwien.ac.graph.utils.PersistenceManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 7/27/17.
 */
public class InstanceManager extends BasicDao<Instance> {

  public static final String LP = "LP";
  public static final String ARTIFICIAL = "Artificial";
  public static final String PLANNING = "Planning";
  public static final String SUB_INSTANCE = "SubInstance";
  Logger logger = LoggerFactory.getLogger(InstanceManager.class);
  String binaryPath = "samples/bin";
  String samplePath = "samples/instances";
  private String planningPath = "samples/planning";


  public GraphNew getGraph(Instance instance) {
    if (instance.getType().equalsIgnoreCase(LP)) {
      return getGraphForLPInstance(instance);
    }
    if (instance.getType().equalsIgnoreCase(PLANNING)) {
      return getGraphFromBinaryFile(binaryPath + "/" + instance.getFileName());
    }
    if (instance.getType().equalsIgnoreCase(ARTIFICIAL)) {
      return getGraphFromBinaryFile(binaryPath + "/" + instance.getFileName());
    }
    if (instance.getType().equalsIgnoreCase(SUB_INSTANCE)) {
      return getGraphFromBinaryFile(binaryPath + "/" + instance.getFileName());
    }
    return null;
  }

  public GraphNew getGraphForLPInstance(Instance instance) {
    String name = instance.getFileName();
    String fileName = name.substring(0, name.lastIndexOf("."));
    //TODO add binary file address into database ...
    String binaryFileName = binaryPath + "/" + fileName + ".mps";
    File file = new File(binaryFileName);
    if (!file.exists()) {
      logger.debug("The graph does not exist in binary format");
      Graph inducedGraph = new MyMPSReader()
          .getInducedGraph("samples/instances/" + instance.getFileName());
      GraphNew graph = (GraphNew) IncidentGraphHelper.getNewStructure(inducedGraph);
      writeGraphToBinaryFile(graph, fileName);
      return graph;
    }

    GraphNew graph = getGraphFromBinaryFile(binaryFileName);
    return graph;
  }

  private GraphNew getGraphFromBinaryFile(String binaryFileName) {
    FileInputStream fis = null;
    GraphNew graph = null;
    try {
      fis = new FileInputStream(binaryFileName);
      ObjectInputStream ois = new ObjectInputStream(fis);
      graph = (GraphNew) ois.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return graph;
  }

  public void extractAllLPGraphs() {

    File folder = new File(samplePath);

    for (File fileEntry : folder.listFiles()) {
      String filename = fileEntry.getName();
      //if (filename.equals("mspp16.mps"))
      //    continue;
      Instance available = getInstanceByName(filename);
      if (available != null) {
        logger.debug("Instance " + filename + " is already exist.");
        continue;
      }
      Graph inducedGraph = new MyMPSReader().getInducedGraph(fileEntry.getAbsolutePath());
      logger.debug("Induced file extracted. Converting to new structure");
      GraphNew graphNew = IncidentGraphHelper.getNewStructure(inducedGraph);
      String name = filename;

      Instance instance = getRawInstanceForGraph(graphNew, name, "LP");
      logger.debug("New structure extracted " + instance);

      save(instance);
      String binFileName = name.substring(0, name.lastIndexOf("."));
      logger.debug("Writing to file" + binFileName);
      writeGraphToBinaryFile(graphNew, binFileName);
      logger.debug(name + " successfully wrote to the file" + binFileName);
    }
  }

  private Instance getRawInstanceForGraph(GraphNew graphNew, String name, String type) {
    int edgeSize = graphNew.getEdgeSize();
    int maxDegree = graphNew.getMaxDegree();
    int size = graphNew.getSize();
    int maxDegreeCount = graphNew.getMaxDegreeList().size();

    Instance instance = new Instance();
    instance.setEdgeSize(edgeSize);
    instance.setFileName(name);
    instance.setMaxDegree(maxDegree);
    instance.setSize(size);
    instance.setMaxDegreeCount(maxDegreeCount);
    instance.setType(type);
    return instance;
  }

  public Instance getInstanceByName(String filename) {
    EntityManager em = PersistenceManager.getEntityManager();
    List<Instance> instances = em.createQuery("from Instance i where i.fileName LIKE :name")
        .setParameter("name", filename).getResultList();
    em.close();
    if (instances.isEmpty()) {
      return null;
    }
    return instances.get(0);
  }

  private void writeGraphToBinaryFile(GraphNew graph, String fileName) {
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream(binaryPath + "//" + fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fout);
      oos.writeObject(graph);
      oos.close();
      fout.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Instance> getAllAscending() {
    EntityManager em = PersistenceManager.getEntityManager();
    List<Instance> instances = null;
    instances = em.createQuery("from Instance i where i.type != :type ORDER BY i.size")
        .setParameter("type", SUB_INSTANCE)
        .getResultList();
    em.close();
    return instances;
  }

  public void readAndStoreCliqueInstance(int n) {
    Graph gf = new Clique(n).getGraph();
    String name = "clique-" + n;
    readAndStoreArtificialInstance(gf, name);
  }

  public void readAndStoreGridInstance(int n) {
    Graph graph = new Grid(n).getGraph();
    String name = "Grid-" + n;
    readAndStoreArtificialInstance(graph, name);
  }

  public void readAndStoreCliqueCycleInstance(int cliqueCount, int connectorCount) {
    Graph graph = new CliqueCycle(cliqueCount, connectorCount).getGraph();
    String name = "ccycle" + cliqueCount + "x" + connectorCount;
    readAndStoreArtificialInstance(graph, name);
  }

  private void readAndStoreBinaryTreeInstance(int height) {
    Graph graph = new BinaryTree(height).getGraph();
    String name = "btree-" + height;
    readAndStoreArtificialInstance(graph, name);
  }

  public void readAndStoreSplitGraph(int n) {
    Graph graph = new SplitGraph(n).getGraph();
    String name = "Split-" + n;
    readAndStoreArtificialInstance(graph, name);
  }

  private void readAndStoreArtificialInstance(Graph gf, String name) {
    GraphNew graph = IncidentGraphHelper.getNewStructure(gf);
    Instance instance = getRawInstanceForGraph(graph, name, ARTIFICIAL);
    save(instance);
    writeGraphToBinaryFile(graph, name);
  }

  private void readAndStorePlanningInstance(GraphNew graph, String name) {
    Instance instance = getRawInstanceForGraph(graph, name, PLANNING);
    save(instance);
    writeGraphToBinaryFile(graph, name);
  }

  void readAndStoreAllPlanningInstances() {
    File folder = new File(planningPath);

    for (File fileEntry : folder.listFiles()) {
      String filename = fileEntry.getName();
      if (!filename.endsWith(".gf")) {
        continue;
      }
      logger.debug("Try to read instance from" + filename);
      LabeledGraph labeledGraph = new GFReader().readFromFile(planningPath + "/" + filename);
      GraphNew graphNew = labeledGraph.getGraphNew();

      readAndStorePlanningInstance(graphNew, filename);
    }
  }

  public void removeAllPlanningInstances() {
    entityManager = PersistenceManager.getEntityManager();
    List<Instance> resultList = entityManager
        .createQuery("from Instance i where i.type like 'Planning'")
        .getResultList();
    entityManager.close();
    for (Instance instance : resultList) {
      removeInstanceWithAllExperince(instance);
    }
  }

  private void removeInstanceWithAllExperince(Instance instance) {
    VertexDao vertexDao = new VertexDao();
    entityManager = PersistenceManager.getEntityManager();
    entityManager.getTransaction().begin();
    Instance attachedInstance = entityManager.find(Instance.class, instance.getId());
    List<SimpleExpriment> experiments = entityManager
        .createQuery("from SimpleExpriment experiment where instance = :instance")
        .setParameter("instance", attachedInstance).getResultList();
    for (SimpleExpriment experiment : experiments) {
      entityManager.createQuery("delete from VertexEntity ve where ve.expriment = :experiment")
          .setParameter("experiment", experiment).executeUpdate();
      entityManager.remove(experiment);
    }
    entityManager.remove(attachedInstance);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public List<Instance> getSubinstances(Instance parent, Separation separation) {
    entityManager = PersistenceManager.getEntityManager();
    String queryString = "from Instance i where i.parent = :parent and i.separation = :separation";
    List<Instance> resultList = entityManager.createQuery(queryString)
        .setParameter("parent", parent)
        .setParameter("separation", separation)
        .getResultList();
    entityManager.close();
    if (resultList.isEmpty()) {
      return null;
    }

    return resultList;
  }

  void myMethod() {
    List<Instance> all = getAllAscending();
    int i = 0;
    for (Instance instance : all) {
      String type = instance.getType();
      String fileName = instance.getFileName();
      System.out.println(i + " " + fileName + " " + type);
      GraphNew graph = getGraph(instance);
      System.out.println(graph.getEdgeSize());
      System.out.println(graph.getVertices().size());
      System.out.println(graph.getMaxDegree());
      //GraphNew graphNew = ZahreMar.convert(graph);
      //writeGraphToBinaryFile2(graphNew, fileName);
      i++;
      //System.out.println(i + " " + graph.hashCode() + " " );

    }
  }

    /*    private void writeGraphToBinaryFile2(GraphNew graph, String fileName) {
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(binaryPath + "//new//" + fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(graph);
                oos.close();
                fout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    public GraphNew2 getGraph2(Instance instance) {
        if(instance.getType().equalsIgnoreCase(LP))
            return getGraphForLPInstance2(instance);
        if(instance.getType().equalsIgnoreCase(PLANNING))
            return getGraphFromBinaryFile2(binaryPath+"/"+instance.getFileName());
        if(instance.getType().equalsIgnoreCase(ARTIFICIAL))
            return getGraphFromBinaryFile2(binaryPath+"/"+instance.getFileName());
        return null ;
    }

    public GraphNew2 getGraphForLPInstance2(Instance instance) {
        String name = instance.getFileName();
        String fileName = name.substring(0, name.lastIndexOf("."));
        //TODO add binary file address into database ...
        String binaryFileName = binaryPath+"/" + fileName+".mps";
        File file = new File(binaryFileName);

        GraphNew2 graph = getGraphFromBinaryFile2(binaryFileName);
        return graph;
    }

    private GraphNew2 getGraphFromBinaryFile2(String binaryFileName) {
        FileInputStream fis = null;
        GraphNew2 graph = null;
        try {
            fis = new FileInputStream(binaryFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            graph = (GraphNew2) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }*/


  public static void main(String[] args) {

    new InstanceManager().correctEdgeCounts();

    PersistenceManager.close();

  }

  public List<Instance> storeSubinstances(Instance instance, List<GraphNew> list,
      Separation separation) {
    List<Instance> instances = new ArrayList<>();
    String id = String.valueOf(separation.getId());
    int counter = 1;
    for (GraphNew graph : list) {
      //boolean isParentSubistance = (instance.getType()==SUB_INSTANCE);
      graph = graph.shuffle();
      String name = instance.getFileName() + "-" + id + "-" + counter;
      Instance subinstance = new Instance();
      subinstance.setType(SUB_INSTANCE);
      subinstance.setSize(graph.getSize());
      subinstance.setMaxDegree(graph.getMaxDegree());
      subinstance.setMaxDegreeCount(graph.getMaxDegreeList().size());
      subinstance.setEdgeSize(graph.getEdgeSize());
      subinstance.setFileName(name);
      subinstance.setParent(instance);
      subinstance.setSeparation(separation);
      writeGraphToBinaryFile(graph, name);
      save(subinstance);
      instances.add(subinstance);
      counter++;

    }

    return instances;
  }

  private void correctEdgeCounts() {
    List<Instance> allAscending = getAllAscending();
    EntityManager entityManager = PersistenceManager.getEntityManager();
    entityManager.getTransaction().begin();
    for (Instance instance : allAscending) {
      GraphNew graph = getGraph(instance);
      //System.out.println(instance);
      int edgeSize = graph.getEdgeSize();
      int size = graph.getSize();
      if (edgeSize != instance.getEdgeSize()) {
        System.out.println(instance + "\t" + edgeSize);
        instance.setSize(size);
        instance.setEdgeSize(edgeSize);
        entityManager.merge(instance);
      }
    }
    entityManager.getTransaction().commit();
    entityManager.close();

  }
}
