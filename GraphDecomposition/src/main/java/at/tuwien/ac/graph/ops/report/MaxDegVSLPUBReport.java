package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.expriments.LBMinorWidthExpriment;
import at.tuwien.ac.graph.ops.expriments.OneMaxDegreeUB;
import at.tuwien.ac.graph.ops.instance.Instance;

import at.tuwien.ac.graph.utils.PersistenceManager;
import javax.persistence.EntityManager;

import java.util.*;

/**
 * Created by root on 7/31/17.
 */
public class MaxDegVSLPUBReport extends Report {

    Hashtable<Instance, OneMaxDegreeUB> ubHashTable = new Hashtable<>();
    Hashtable<Instance, LBMinorWidthExpriment> hashtable = new Hashtable<>();
    List<Instance> instances = null;

    //String columns[] = {"Row", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\#\\Delta$", "MAX\\_Deg\\_UB", "LP\\_UB"};
    String columns[] = {"Name", "$|V|$", "$|E|$", "MAX\\_Deg\\_UB", "ILP\\_UB"};
    private int rowCounter=0;

    public MaxDegVSLPUBReport(){
        EntityManager em = PersistenceManager.getEntityManager();

        List<OneMaxDegreeUB> resultList = em.createQuery("SELECT u FROM OneMaxDegreeUB u").getResultList();


        for(OneMaxDegreeUB maxDegreeUB: resultList)
            ubHashTable.put(maxDegreeUB.getInstance(), maxDegreeUB);


        Set<Instance> instanceSet = new HashSet<>(ubHashTable.keySet());
        Set<Instance> instanceSet2 = hashtable.keySet();
        instanceSet.addAll(instanceSet2);
        instances = new ArrayList<>(instanceSet);
        Collections.sort(instances);

        em.close();

    }

    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return 45;
    }

    @Override
    String getColumnName(int column){
        return columns[column];

    }

    @Override
    String getTitle() {
        return "The Simplest Bounds";
    }

    private RowString rowString;
    @Override
    String getCellContent(int row, int column) {
        Instance instance = instances.get(row);
        if(column==3)
            rowString = fillRowString(instance);
        switch(column){
            //case 0: return String.valueOf(rowCounter++);
            case 0: return instance.getFileName();
            case 1: return String.valueOf(instance.getSize());
            case 2: return String.valueOf(instance.getEdgeSize());
            //case 4: return String.valueOf(instance.getMaxDegree());
            //case 5: return String.valueOf(instance.getMaxDegreeCount());
            //"MAX\\_Deg\\_UB", "LP\\_UB"
            case 3:
            return rowString.maxDegUb;
            case 4:
                return rowString.lpUb;
        }
        return "";
    }

  private RowString fillRowString(Instance instance) {
    RowString rowString = new RowString();
    int maxUB = ubHashTable.get(instance).getSize();
    String lpubStr = AllBoundsReport.getLPUB(instance);
    int lpUb = Integer.valueOf(lpubStr);
    rowString.lpUb=lpubStr;
    rowString.maxDegUb=String.valueOf(maxUB);
    if(maxUB<=lpUb)
      rowString.maxDegUb = emphasise(rowString.maxDegUb);

    if(lpUb<=maxUB)
      rowString.lpUb = emphasise(rowString.lpUb);


    return rowString;
  }

  @Override
    String getDescription() {
        return "This document compare max degree upperbound and ILP upperbound";
    }

  public static void main(String[] args) {
    new MaxDegVSLPUBReport().generateReportIntoFile("maxvslp.tex");
    PersistenceManager.close();
  }

    class RowString{
      String maxDegUb ;
      String lpUb;
    }


}
