package at.tuwien.ac.graph.ops.expriments;

import at.tuwien.ac.graph.ops.dao.BasicEntity;
import at.tuwien.ac.graph.ops.instance.Instance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 7/28/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SimpleExpriment extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long operationDuration;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Instance instance;

    private Date executionTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperationDuration() {
        return operationDuration;
    }

    public void setOperationDuration(Long operationDuration) {
        this.operationDuration = operationDuration;
    }

    public Instance getInstance() {
        return instance;

    }


    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {

        String fileName = "null";
        if(instance!=null)
            fileName = instance.getFileName();
        return "SimpleExpriment{" +
                "operationDuration=" + operationDuration +
                ", instance=" + fileName +
                ", executionTime=" + executionTime +
                '}';
    }
}
