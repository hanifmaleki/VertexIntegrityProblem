package at.tuwien.ac.graph.grid;

import at.tuwien.ac.graph.grid.sequence.FiboSequence;
import at.tuwien.ac.graph.grid.sequence.ModuleSequence;
import at.tuwien.ac.graph.grid.sequence.MutiplySequence;
import at.tuwien.ac.graph.grid.sequence.OddMultiplySequence;
import at.tuwien.ac.graph.grid.sequence.PowerSequence;
import at.tuwien.ac.graph.grid.sequence.Sequence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 11/2/18.
 */
public class SequenceProcessor {

  public static void main(String[] args) {
    int l = 11;
    //Sequence sequence = new FiboSequence();
    Sequence sequence = new OddMultiplySequence(12);
    List<Integer> list = new SequenceProcessor().findCloseSubset(l, sequence);
    new GridFrame(l,l,1,1,list);
  }

  private List<Integer> findCloseSubset(int l,  Sequence sequence) {
    int index = 1;
    Set<Integer> set = new HashSet<>();
    int offset = 3 ;
    while (true) {
      int next = sequence.getNext(index);
      index++;
      offset = (offset + next) % (l * l) ;
      if(set.contains(offset))
        break;
      set.add(offset);
    }
    System.out.println(index);
    List<Integer> list = new ArrayList<>();
    list.addAll(set);
    return list;
  }

}

