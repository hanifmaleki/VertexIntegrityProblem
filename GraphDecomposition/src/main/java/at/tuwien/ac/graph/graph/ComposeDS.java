package at.tuwien.ac.graph.graph;

import java.util.BitSet;

/**
 * Created by root on 4/1/17.
 */
public class ComposeDS {

    private int k ;
    private BitSet bitSet;

    public ComposeDS(int k, BitSet bitSet) {
        this.k = k;
        this.bitSet = bitSet;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComposeDS that = (ComposeDS) o;

        if (k != that.k) return false;
        return bitSet.equals(that.bitSet);
    }

    @Override
    public int hashCode() {
        int result = k;
        result = 31 * result + bitSet.hashCode();
        return result;
    }

    /*public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        int count = 5000 ;
        BitSet[] bitSet = new BitSet[count];
        for(int i = 0 ; i < count; i++) {
            bitSet[i] = new BitSet(100000);
            bitSet[i].flip(100);
            System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        }
    }*/

}
