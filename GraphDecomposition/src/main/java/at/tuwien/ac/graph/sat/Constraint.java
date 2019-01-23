package at.tuwien.ac.graph.sat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/11/17.
 */
public class Constraint {
    List<Integer> indexes = new ArrayList<>();


    private String addString= "";

    public void setAddString(String addString) {
        this.addString = addString;
    }

    @Override
    public String toString() {
        String string = "";

        for(Integer index: indexes){
            string += index + " ";
        }
        string +="0";
        string +=addString;
        string +="\n";
        return string;
    }

    public void addIndex(int i) {
        indexes.add(new Integer(i));
    }
}
