package at.tuwien.ac.graph.ops.instance;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.dao.BasicEntity;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.expriments.Separation;
import at.tuwien.ac.graph.samples.SplitGraph;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;

import javax.persistence.*;
import java.util.Comparator;

/**
 * Created by root on 7/18/17.
 */
@Entity
public class Instance extends BasicEntity implements Comparable<Instance>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String fileName;

    private Integer size ;

    private Integer edgeSize ;

    private Integer maxDegree ;

    private Integer maxDegreeCount ;

    private String type ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getEdgeSize() {
        return edgeSize;
    }

    public void setEdgeSize(Integer edgeSize) {
        this.edgeSize = edgeSize;
    }

    public Integer getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(Integer maxDegree) {
        this.maxDegree = maxDegree;
    }

    public Integer getMaxDegreeCount() {
        return maxDegreeCount;
    }

    public void setMaxDegreeCount(Integer maxDegreeCount) {
        this.maxDegreeCount = maxDegreeCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instance instance = (Instance) o;

        //if (!id.equals(instance.id)) return false;
        return fileName.equals(instance.fileName);
    }

    @Override
    public int hashCode() {
        return fileName.hashCode();
    }

    @Override
    public String toString() {
        return "Instance{" +
                "fileName='" + fileName + '\'' +
                ", size=" + size +
                ", edgeSize=" + edgeSize +
                ", maxDegree=" + maxDegree +
                ", maxDegreeCount=" + maxDegreeCount +
                '}';
    }

    @Override
    public int compareTo(Instance o) {
        return Integer.compare(size, o.getSize());
    }

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Instance parent ;

    public Instance getParent() {
        return parent;
    }

    public void setParent(Instance parent) {
        this.parent = parent;
    }

    //@OneToOne(fetch=FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name="experiment_id")
    private Separation separation;

    public Separation getSeparation() {
        return separation;
    }

    public void setSeparation(Separation separation) {
        this.separation = separation;
    }
}
