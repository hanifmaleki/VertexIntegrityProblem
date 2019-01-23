package at.tuwien.ac.graph.dynamicProgramming;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.samples.Grid;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 2/9/18.
 */
public class GridTreeGenerator {

  private final int n;
  private final GraphNew originalGraph ;

  public GridTreeGenerator(int n) {
    this.n = n;
    Graph graph = new Grid(n).getGraph();
    originalGraph = IncidentGraphHelper.getNewStructure(graph);
  }

  public TreeDecomposition getTree() {
    Root root = new Root();
    TreeDecomposition decomposition = new TreeDecomposition(root);
    GraphNew correspondingGraph = new GraphNew(originalGraph);
    root.setCorrespondingGraph(new GraphNew(correspondingGraph));
    decomposition.setRoot(root);
    int introducing = 0;
    Set<Integer> bag = new HashSet<>();
    TreeNode parent = root;
    Set<Integer> newBag = null;
    Introduce introduce = null;
    Forget forget = null;
    do{
      bag.add(introducing);
      forget = new Forget();
      newBag = new HashSet<>();
      newBag.addAll(bag);
      forget.setBag(newBag);
      forget.setCorrespondingGraph(new GraphNew(correspondingGraph));
      forget.setForgetting(introducing);
      parent.setChild(forget);
      introducing = getNextRight(introducing);
      parent = forget ;
    }while (introducing!=1);

    introduce = new Introduce();
    introduce.setCorrespondingGraph(new GraphNew(correspondingGraph));
    bag.add(introducing);
    introducing = getNextRight(introducing);
    newBag = new HashSet<>();
    newBag.addAll(bag);
    introduce.setBag(newBag);
    introduce.setIntroducing(introducing);
    parent.setChild(introduce);
    parent = introduce ;

    int removing = 0 ;
    do{
      correspondingGraph = correspondingGraph.removeVertexCopy(removing);
      bag.remove(removing);
      forget = new Forget();
      newBag = new HashSet<>();
      newBag.addAll(bag);
      forget.setBag(newBag);
      forget.setCorrespondingGraph(new GraphNew(correspondingGraph));
      forget.setForgetting(removing);
      parent.setChild(forget);

      introduce = new Introduce();
      bag.add(introducing);
      newBag = new HashSet<>();
      newBag.addAll(bag);
      introduce.setBag(newBag);
      introduce.setIntroducing(introducing);
      introduce.setCorrespondingGraph(new GraphNew(correspondingGraph));
      forget.setChild(introduce);
      parent=introduce;
      introducing = getNextRight(introducing);
      removing = getNextRight(removing);
    }while(removing!=n*n-2);

    do{
      introduce = new Introduce();
      bag.remove(removing);
      newBag = new HashSet<>();
      newBag.addAll(bag);
      introduce.setBag(newBag);
      introduce.setIntroducing(removing);
      correspondingGraph=correspondingGraph.removeVertexCopy(removing);
      introduce.setCorrespondingGraph(new GraphNew(correspondingGraph));
      parent.setChild(introduce);
      parent=introduce;
      introducing = getNextRight(introducing);
      removing = getNextRight(removing);
    }while(removing!=n*n-n -1);

    Leaf leaf = new Leaf();
    bag.remove(removing);
    newBag = new HashSet<>();
    newBag.addAll(bag);
    leaf.setBag(newBag);
    correspondingGraph = correspondingGraph.removeVertexCopy(removing);
    leaf.setCorrespondingGraph(new GraphNew(correspondingGraph));
    leaf.setLeaf(removing);
    parent.setChild(leaf);

    return decomposition;
  }


  private Introduce constructLeftSide() {
    Introduce introduce = new Introduce();
    Set<Integer> bag = new HashSet<>();
    int m = n / 2;
    for (int i = 0; i < n; i++) {
      bag.add(m);
      m += n;
    }
    m--;
    bag.add(m);
    introduce.setBag(bag);
    TreeNode parent = introduce;
    int introducing = m - 1 - n;
    int forgetting = n / 2;
    while (m > n + 2) {
      Set<Integer> newBag = new HashSet<>(bag);
      bag.remove(forgetting);
      Forget forget = new Forget();
      forget.setBag(newBag);
      introduce.setChild(forget);

      Introduce introduce1 = new Introduce();
      newBag = new HashSet<>(newBag);
      bag.add(introducing);
      introduce1.setBag(newBag);
      forget.setChild(introduce1);

      introducing = getNextLeft(introducing);

      forgetting = getNextLeft(forgetting);

    }

    return introduce;
  }

  private int getNextLeft(int current) {
    if (current <= n) {
      current = current - 1 + (n - 1) * n;
    } else {
      current = current - n;
    }
    return current;
  }

  private int getNextRight(int current) {
    if (current >= n*n-n) {
      current = current + 1 - (n - 1) * n;
    } else {
      current = current + n;
    }
    return current;
  }

  public static void main(String[] args) {
    int n=3;
    /*int nextLeft = new GridTreeGenerator(5).getNextLeft(25);
    do {
      nextLeft = new GridTreeGenerator(5).getNextLeft(nextLeft);
      System.out.println(nextLeft);
    } while (nextLeft > 1);*/

    /*int nextRight = 0;
    System.out.println(nextRight);
    do{
      nextRight = new GridTreeGenerator(n).getNextRight(nextRight);
      System.out.println(nextRight);
    } while(nextRight!=n*n-1);*/
    TreeDecomposition tree = new GridTreeGenerator(3).getTree();
    System.out.println(tree);
  }



  void myDynamicProgramming(TreeDecomposition decomposition, int c, int k){

    calculateRecordsOf(decomposition.getRoot(), c, k);

  }

  private Set<Record> calculateRecordsOf(Root root, int c, int k) {
    TreeNode child = root.getChild();
    Set<Record> recordSet = null;
    if(child instanceof Forget){
      Forget forget = (Forget) child;
      recordSet = calculateRecordsOf(forget, c, k);
      for (Record record : recordSet) {
        System.out.println(record);
      }
    }else{
      //TODO fill it
    }

    return recordSet ;
  }

  private Set<Record> calculateRecordsOf(Forget forget, int c, int k) {
    TreeNode child = forget.getChild();
    Set<Record> records = null;
    if(child instanceof Introduce){
      Introduce introduce = (Introduce) child;
      records = calculateRecordsOf(introduce, c, k);
    }
    if(child instanceof  Leaf){
      Leaf leaf = (Leaf) child;
      records = calculateRecordsOf(leaf);
    }
    int forgetting = forget.getForgetting();

    Hashtable<Set<Integer>, Record> hashtable = new Hashtable<>();

    for(Record record : records){
      Set<Integer> removing = record.removing;
      Set<Integer> newRemoving = new HashSet<>();
      newRemoving.addAll(removing);
      Set<Integer> newOld = new HashSet<>();
      newOld.addAll(record.old);
      if(removing.contains(forgetting)) {
        newRemoving.remove(forgetting);
        newOld.add(forgetting);
      }

      Record newRecord = new Record();
      newRecord.removing = newRemoving;
      newRecord.old = newOld ;
      newRecord.i = record.i ;
      Record hashTableRecord = hashtable.get(newRemoving);
      if((hashTableRecord ==null)||(hashTableRecord.i > newRecord.i))
        hashtable.put(newRemoving, newRecord);
    }

    Collection<Record> values = hashtable.values();
    Set<Record>  recordSet = new HashSet<>();
    recordSet.addAll(values);
    forget.setRecords(recordSet);
    return recordSet ;
  }

  private Set<Record> calculateRecordsOf(Introduce introduce, int c, int k) {
    Set<Record> recordSet = new HashSet<>();
    //TODO consider other cases
    Forget forget = (Forget) introduce.getChild();
    Set<Record> records = calculateRecordsOf(forget, c, k);
    GraphNew graph = introduce.getCorrespondingGraph();

    for(Record record : records){
      Set<Integer> removings = new HashSet<>();
      removings.addAll(record.removing);
      removings.addAll(record.old);
      GraphNew newGraph = new GraphNew(graph);
      for(Integer vertex : removings)
        graph= graph.removeVertexCopy(vertex);
      List<GraphNew> components = GraphNewHelper.getConnectedComponentsHS(graph);
      Collections.sort(components, new Comparator<GraphNew>() {
        @Override
        public int compare(GraphNew o1, GraphNew o2) {
          return Integer.compare(o2.getSize(), o1.getSize());
        }
      });
      if(components.get(0).getSize()<=c){
        Record newRecord = new Record();
        Set<Integer> newRemoving = new HashSet<>();
        newRemoving.addAll(record.removing);
        Set<Integer> newOld = new HashSet<>();
        newOld.addAll(record.old);
        newRecord.i = record.i ;
        recordSet.add(newRecord);
      }

      if(record.i<k){
        Record newRecord = new Record();
        Set<Integer> newRemoving = new HashSet<>();
        newRemoving.addAll(record.removing);
        newRemoving.add(introduce.getIntroducing());
        Set<Integer> newOld = new HashSet<>();
        newOld.addAll(record.old);
        newRecord.i = record.i + 1;
        recordSet.add(newRecord);
      }
    }
    introduce.setRecords(recordSet);
    return recordSet;
  }

  private Set<Record> calculateRecordsOf(Leaf leaf) {
    int vertex = leaf.getLeaf();
    Set<Record> recordSet = new HashSet<>();
    Record record = new Record();
    record.i = 0;
    record.removing = new HashSet<>();
    record.old = new HashSet<>();
    recordSet.add(record);

    record = new Record();
    record.i = 1;
    record.removing = new HashSet<>();
    record.removing.add(vertex);
    record.old = new HashSet<>();
    recordSet.add(record);

    leaf.setRecords(recordSet);
    return recordSet;
  }


}
