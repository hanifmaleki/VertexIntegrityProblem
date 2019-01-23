package at.tuwien.ac.graph.labeled;

import at.tuwien.ac.graph.utils.GFReader;

/**
 * Created by e1528895 on 8/22/17.
 */
public class LabeledGraphDao {

    public LabeledGraph getInstanceByName(String filename){
        return new GFReader().readFromFile(filename);
    }
}
