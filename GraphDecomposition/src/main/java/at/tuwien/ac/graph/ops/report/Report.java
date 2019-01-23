package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.VertexDao;
import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;

import java.io.*;
import java.util.*;

/**
 * Created by root on 7/31/17.
 */
public abstract class Report {
    public static final String REPORT_PATH = "report/";
    public static final String CVS_SEPARATOR = ",";
    public static final String TABLE_SPARATOR = "&";
    private static final String EMPHASIS = "$\\mathbf{";
    private VertexDao vertexDao = new VertexDao();
    private InstanceManager instanceManager = new InstanceManager();

    //Logger logger = LoggerFactory.getLogger(Report.class);
    abstract int getColumnCount();
    abstract int getRowCount();
    abstract String getCellContent(int row, int column);
    abstract String getDescription();
    abstract String getColumnName(int column);
    abstract String getTitle();


    public void generateReportIntoFile(String fileName){
        try {
            //logger.debug("Creating file "+ fileName);
            FileWriter fileWriter = new FileWriter(REPORT_PATH +fileName);
            PrintWriter writer = new PrintWriter(fileWriter);
            String beginDocument = "\\documentclass{article}\n" +
                    "\\usepackage[utf8]{inputenc}\n" +
                    "\\usepackage{longtable}\n" +
                    "\\title{"+getTitle()+"}\n"+
                    "\\begin{document}\n" +
                    "\\maketitle\n";
            //logger.debug("Writing begin Document "+ beginDocument);
            writer.print(beginDocument);
            writer.print(getDescription()+"\n");
            int columnCount = getColumnCount();
            int rowCount = getRowCount();
            //logger.debug("The report has "+ rowCount + " rows and "+ columnCount + " columns." );
            String TABULAR_TAG="longtable";
            String tableDeclaration = "\\begin{"+TABULAR_TAG+"}{|";

            for(int i = 0; i < columnCount; i++)
                tableDeclaration += "l |";
            tableDeclaration += "}\n";
            //logger.debug("Writeng table declaration "+ tableDeclaration);
            writer.print(tableDeclaration);

            String header = "\\hline\n";
            for(int i = 0; i < columnCount; i++) {
                header += getColumnName(i);
                if(i<columnCount-1)
                    header += TABLE_SPARATOR;
            }
            header+="\\\\\n\\hline\n";
            //logger.debug("writing header "+ header);
            writer.print(header);

            for(int i = 0; i < rowCount; i++) {
                String rowContent ="";
                for (int j = 0; j < columnCount; j++) {
                    String cellContent = getCellContent(i, j);
                    if(cellContent=="null")
                        cellContent="--";
                    rowContent+= cellContent;
                    if(j< columnCount -1)
                           rowContent+= TABLE_SPARATOR;
                }
                rowContent+="\\\\\n";
                writer.print(rowContent);
            }
            String endTable = "\\hline\n" + "\\end{"+TABULAR_TAG+"}\n";
            //logger.debug("writing end tabular "+ endTable);
            writer.print(endTable);
            String endDoc = "\\end{document}\n";
            //logger.debug("Writing End Doc "+ endDoc);
            writer.print(endDoc);
            writer.close();
            fileWriter.close();
            //logger.debug("file "+ fileName + " writing is complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateCSVFile(String fileName){
        try {
            FileWriter fileWriter = new FileWriter(REPORT_PATH + fileName);
            PrintWriter writer = new PrintWriter(fileWriter);

            int columnCount = getColumnCount();
            int rowCount = getRowCount();

            String header = "";
            for(int i = 0; i < columnCount; i++) {
                header += getColumnName(i);
                if(i<columnCount-1)
                    header += CVS_SEPARATOR;
            }
            header+="\n";
            writer.print(header);

            for(int i = 0; i < rowCount; i++) {
                String rowContent ="";
                for (int j = 0; j < columnCount; j++) {
                    String cellContent = getCellContent(i, j);
                    if(cellContent=="null")
                        cellContent="--";
                    rowContent+= cellContent;
                    if(j< columnCount -1)
                        rowContent+= CVS_SEPARATOR;
                }
                rowContent+="\n";
                writer.print(rowContent);
            }
            writer.close();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getComponentsStringOf(SimpleExpriment expriment, Instance instance) {
        List<VertexEntity> separation = vertexDao.getVerticesOf(expriment);
        if(separation.isEmpty())
            return "";
        GraphNew graph = instanceManager.getGraph(instance);
        String string ="";
        for(VertexEntity entity: separation) {
            Integer vertex = entity.getVertex();
            graph = graph.removeVertexCopy(vertex);
        }
        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);

        Hashtable<Integer, Integer> componentTable = new Hashtable<>();
        for(GraphNew component: connectedComponentsHS){
            Integer key = component.getSize();
            Integer value = componentTable.get(key);
            if(value ==null)
                componentTable.put(key, 1);
            else
                componentTable.put(key, (value+1));
        }

        Set<Integer> keys = componentTable.keySet();
        List<Integer> sortedKeys = new ArrayList<>(keys);
        Collections.sort(sortedKeys, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        for(Integer key : sortedKeys){
            Integer value = componentTable.get(key);
            string += key ;
            if(value > 1)
                string += "("+value+")";
            string += ",";
        }

        return string;
    }



    public static String millisecondsToFormat(Long millis){
        if(millis==null)
            return null;
        if(millis<10000)
            return millis+"ms";
        millis /=1000;
        if(millis <1000)
            return millis+"s";
        millis /= 60 ;
        return millis + "m";
    }

    public static void main(String[] args) {

    }

    public String emphasise(String string) {
        //$\mathbf{5}$
        return EMPHASIS + string + "}$";
    }

}
