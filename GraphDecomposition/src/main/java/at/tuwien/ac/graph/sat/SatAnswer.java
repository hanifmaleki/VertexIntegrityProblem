package at.tuwien.ac.graph.sat;

import java.util.Set;

/**
 * Created by e1528895 on 8/31/17.
 */
public class SatAnswer {
    private Set<Integer> fvd;
    private String cpuTime ;
    private Boolean finished ;

    public SatAnswer(Set<Integer> fvd, String cpuTime, Boolean finished) {
        this.fvd = fvd;
        this.cpuTime = cpuTime;
        this.finished = finished;
    }

    public Set<Integer> getFvd() {
        return fvd;
    }

    public void setFvd(Set<Integer> fvd) {
        this.fvd = fvd;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        String s = "";
        if(fvd!=null)
            s = "fvd-size=" + fvd.size();
        else
            s = "fvd is null";
        return "SatAnswer{" +
                s +
                ", cpuTime='" + cpuTime + '\'' +
                ", finished=" + finished +
                '}';
    }
}
