package at.tuwien.ac.graph.grid.sequence;

/**
 * Created by e1528895 on 11/2/18.
 */
public class MutiplySequence implements Sequence{

  private final int base ;

  public MutiplySequence(int base) {
    this.base = base;
  }

  @Override
  public int getNext(int currentIndex) {
    return currentIndex*base;
  }
}
