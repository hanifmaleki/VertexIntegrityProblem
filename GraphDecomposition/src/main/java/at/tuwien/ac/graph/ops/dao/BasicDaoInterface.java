package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;

import java.util.List;

/**
 * Created by e1528895 on 8/3/17.
 */
public interface BasicDaoInterface<T extends BasicEntity> {
    List<T> getAll();

    T getById(Long id);

    void removeAll();

    void remove(BasicEntity entity);
}
