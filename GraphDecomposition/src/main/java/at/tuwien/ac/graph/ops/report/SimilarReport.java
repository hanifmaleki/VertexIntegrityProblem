package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimilarVerticesDao;
import at.tuwien.ac.graph.ops.expriments.SimilarVerticesExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.*;

/**
 * Created by e1528895 on 9/8/17.
 */
public class SimilarReport extends Report {
    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "Time","S\\#Bag", "Bags"};
    SimilarVerticesDao dao = new SimilarVerticesDao();
    InstanceManager instanceManager = new InstanceManager();
    List<SimilarVerticesExperiment> all = new ArrayList<>();
    private int rowCounter=1;

    public SimilarReport(){
        List<Instance> allAscending = instanceManager.getAllAscending();
        for(Instance instance: allAscending){
            SimilarVerticesExperiment expriment = dao.getExprimentByInstance(instance);
            if(expriment!=null){
                expriment.setInstance(instance);
                all.add(expriment);
            }
        }
    }

    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return all.size();
    }

    @Override
    String getCellContent(int row, int column) {
        SimilarVerticesExperiment experiment = all.get(row);
        Instance instance = experiment.getInstance();
        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                return instance.getFileName();
            case 2:
                return String.valueOf(instance.getSize());

            case 3:
                return String.valueOf(instance.getEdgeSize());

            case 4:
                return String.valueOf(instance.getMaxDegree());
            case 5:
                return String.valueOf(instance.getMaxDegreeCount());
            case 6:
                return  millisecondsToFormat(experiment.getOperationDuration());
            case 7:
                return String.valueOf(experiment.getBagCount());
            case 8:
                return getSetsStringOf(experiment);
        }
        return null;
    }

    @Override
    String getDescription() {
        return "Similar Vertices";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Similar Vertices";
    }

    private String getSetsStringOf(SimilarVerticesExperiment experiment) {
        List<Set<Integer>> similarVertices = dao.getSimilarVertices(experiment);
        Collections.sort(similarVertices, new Comparator<Set<Integer>>() {
            @Override
            public int compare(Set<Integer> o1, Set<Integer> o2) {
                return Integer.compare(o2.size(), o1.size());
            }
        });

        String string = "";
        Integer previousSetSize = null ;
        int counter = 1 ;
        for(Set<Integer> set : similarVertices) {
            if((previousSetSize==null)||(previousSetSize!=set.size())){
                if(previousSetSize!=null) {
                    if (counter > 1)
                        string += "(" + counter + "), ";
                    else
                        string += ", ";
                }
                string += set.size() ;
                counter = 1 ;
                previousSetSize = set.size();
            }else{
                counter++;
            }
        }
        if (counter > 1)
            string += "(" + counter + "), ";
        return string;
    }

    public static void main(String[] args) {
        new SimilarReport().generateReportIntoFile("similar.tex");
        PersistenceManager.close();
    }
}
