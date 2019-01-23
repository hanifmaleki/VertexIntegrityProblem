package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimpleFVDDao;
import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.dao.SimpleSatDao;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.*;

/**
 * Created by e1528895 on 9/4/17.
 */
public class SATLPFVDReport extends Report {
    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "c", "S\\_FVD", "S\\_TIME", "LP\\_LB", "LB\\_UB", "LP\\_TIME", "S\\_FVD", "FVD\\_TIME"};
    SimpleSatDao satDao = new SimpleSatDao();
    SimpleLPDao lpDao = new SimpleLPDao();
    SimpleFVDDao fvdDao = new SimpleFVDDao();
    InstanceManager instanceManager = new InstanceManager();

    Hashtable<ProblemInstance, SimpleSatExperiment> satTable= new  Hashtable();
    Hashtable<ProblemInstance, SimpleLPExpriment> lpTable= new  Hashtable();
    Hashtable<ProblemInstance, SimpleFVDExperiment> fvdTable= new  Hashtable();
    List<ProblemInstance> list = null;
    private int rowCounter = 1;

    public SATLPFVDReport(boolean successful){
        Set<ProblemInstance> instanceSet = new HashSet<>();
        List<Instance> instances = instanceManager.getAllAscending();
        for(Instance instance: instances){
            List<SimpleSatExperiment> satExperiments = satDao.getAllExprimentsByInstance(instance);
            List<SimpleLPExpriment> lpExperiments = lpDao.getAllExprimentsByInstance(instance);
            List<SimpleFVDExperiment> fvdExperiments = fvdDao.getAllExprimentsByInstance(instance);
            for(SimpleSatExperiment experiment: satExperiments){
                ProblemInstance problemInstance = new ProblemInstance(instance, experiment.getC());
                satTable.put(problemInstance, experiment);
                instanceSet.add(problemInstance);
            }

            for(SimpleLPExpriment experiment: lpExperiments){
                ProblemInstance problemInstance = new ProblemInstance(instance, experiment.getC());
                lpTable.put(problemInstance, experiment);
                instanceSet.add(problemInstance);
            }

            for(SimpleFVDExperiment experiment: fvdExperiments){
                ProblemInstance problemInstance = new ProblemInstance(instance, experiment.getC());
                fvdTable.put(problemInstance, experiment);
                instanceSet.add(problemInstance);
            }

        }
        list = new ArrayList<ProblemInstance>(instanceSet);
        Collections.sort(list);
    }
    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return list.size();
    }

    Instance previousInstance = null ;
    @Override
    String getCellContent(int row, int column) {
        ProblemInstance problemInstance = list.get(row);
        Instance instance = problemInstance.instance;
        SimpleSatExperiment satExperiment = satTable.get(problemInstance);
        SimpleLPExpriment lpExperiment = lpTable.get(problemInstance);
        SimpleFVDExperiment fvdExperiment = fvdTable.get(problemInstance);
        boolean b = (previousInstance == null) || (!previousInstance.getFileName().equalsIgnoreCase(instance.getFileName()));

        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                if (b)
                    return instance.getFileName();
                return "";
            case 2:
                if (b)
                    return String.valueOf(instance.getSize());
                return "";
            case 3:
                if (b)
                    return String.valueOf(instance.getEdgeSize());
                return "";
            case 4:
                if (b)
                    return String.valueOf(instance.getMaxDegree());
                return "";
            case 5:
                if (b) {
                    previousInstance = instance;
                    return String.valueOf(instance.getMaxDegreeCount());
                }
                return "";
//"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "c", "S_FVD", "S_TIME", "LP_LB", "LB_UB", "LP_TIME", "S_FVD", "FVD_TIME"
            case 6:
                return String.valueOf(problemInstance.c);
            case 7:
                if(satExperiment==null)
                    return null ;
                return String.valueOf(satExperiment.getFvdSize());
            case 8 :
                if(satExperiment==null)
                    return null ;
                return String.valueOf(millisecondsToFormat(satExperiment.getOperationDuration()));
            case 9:
                if(lpExperiment==null)
                    return null ;
                return String.valueOf(lpExperiment.getLb());
            case 10 :
                if(lpExperiment==null)
                    return null ;
                return String.valueOf(lpExperiment.getK());
            case 11 :
                if(lpExperiment==null)
                    return null ;
                return String.valueOf(millisecondsToFormat(lpExperiment.getOperationDuration()));
            case 12:
                if(fvdExperiment==null)
                    return null ;
                return String.valueOf(fvdExperiment.getFvdSize());
            case 13 :
                if(fvdExperiment==null)
                    return null ;
                return String.valueOf(millisecondsToFormat(fvdExperiment.getOperationDuration()));
        }
            return null;
    }

    @Override
    String getDescription() {
        return "The following table is a comparison  between three methods SAT, LP, and FPT ";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Comparison of SAT, LP, and FPT Algorithm for Component Ordered Connectivity Problem";
    }
    class ProblemInstance implements Comparable<ProblemInstance>{
        Instance instance ;
        Integer c ;

        public ProblemInstance(Instance instance, Integer c) {
            this.c = c ;
            this.instance = instance ;


        }

        @Override
        public int compareTo(ProblemInstance o) {
            if(instance.getSize()!= o.instance.getSize())
                return Integer.compare(instance.getSize(), o.instance.getSize());
            return Integer.compare(c, o.c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProblemInstance that = (ProblemInstance) o;

            if (instance != null ? !instance.equals(that.instance) : that.instance != null) return false;
            return c != null ? c.equals(that.c) : that.c == null;
        }

        @Override
        public int hashCode() {
            int result = instance != null ? instance.hashCode() : 0;
            result = 31 * result + (c != null ? c.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) {
        new SATLPFVDReport(false).generateReportIntoFile("satlpfvd.tex");
        PersistenceManager.close();
    }
}
