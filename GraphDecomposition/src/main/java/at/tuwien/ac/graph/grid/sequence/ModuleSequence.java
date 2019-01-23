package at.tuwien.ac.graph.grid.sequence;

/**
 * Created by e1528895 on 11/2/18.
 */
public class ModuleSequence implements Sequence {

  //private final int remainder;
  //private final int offset;
  private final int module;

  public ModuleSequence(int module) {
    //this(module, 0,0);
    this.module = module;
  }

  @Override
  public int getNext(int currentIndex) {
    return module;
  }
  /*public ModuleSequence(int module,int remainder, int offset) {
    this.remainder = remainder;
    this.offset = offset;
    this.module = module;
  }

  @Override
  public int getNext(int currentIndex) {
    return (currentIndex+offset)*module+remainder;
  }*/
}
