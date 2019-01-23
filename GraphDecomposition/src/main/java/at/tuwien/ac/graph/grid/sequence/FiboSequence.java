package at.tuwien.ac.graph.grid.sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e1528895 on 11/2/18.
 */
public class FiboSequence implements Sequence{

  //List<Integer> list = new ArrayList<>(100);
  Integer[] list = new Integer[100];

  @Override
  public int getNext(int currentIndex) {
    return fibo(currentIndex);
  }

  private int fibo(int currentIndex) {
    if(/*(currentIndex<list.size())&&*/(list[currentIndex]!=null))
      return list[currentIndex];
    int value = 0;
    if(currentIndex>0)
      value = 1;
    if(currentIndex>1)
      value = fibo(currentIndex - 1) + fibo(currentIndex - 2);
    list[currentIndex]= value;
    return value;
  }
}
