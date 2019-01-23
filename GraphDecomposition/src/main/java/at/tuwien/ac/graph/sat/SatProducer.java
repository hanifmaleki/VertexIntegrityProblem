package at.tuwien.ac.graph.sat;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.graph.Vertex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by root on 4/11/17.
 */
public class SatProducer {

    public static String filename = "sat.ps";

    static int x[];
    static int c[][];
    static int z[][][];
    static int y[][];
    static boolean addedString = false ;

    public static void produceConstraints(Graph graph, int k) {

        int n = graph.getSize();
        int varCount = n + (n * n) + (n * n * k) + (n * k);
        System.out.println(varCount);

        initializeArrays(n, k);

        //List<Constraint> constraintList = new ArrayList<>();

        //~X_v -> Comp_(v,v)
        for (Vertex vertex : graph.getVertices()) {
            int index = vertex.getIndex();
            Constraint constraint = new Constraint();
            constraint.addIndex(x[index]);
            constraint.addIndex(c[index][index]);
            //constraintList.add(constraint);
            System.out.print(constraint);
        }

        //~X_v ^ ~X_w -> C_(v,w)
        for (Vertex vertex : graph.getVertices()) {
            HashSet<Vertex> neighbors = graph.getNeighbors(vertex);
            for (Vertex neighbor : neighbors) {
                int vertexIndex = vertex.getIndex();
                int neighborIndex = neighbor.getIndex();
                Constraint constraint = new Constraint();
                constraint.addIndex(x[vertexIndex]);
                constraint.addIndex(x[neighborIndex]);
                constraint.addIndex(c[vertexIndex][neighborIndex]);
                if(addedString)
                    constraint.setAddString("\tx[" + vertexIndex + "] x[" + neighborIndex + "] c[" + vertexIndex + "][" + neighborIndex + "]");
                //constraintList.add(constraint);
                System.out.print(constraint);

            }
        }

        //C_(x, y) ^ C_(y, z) -> C_(x, z)
        for (Vertex v1 : graph.getVertices()) {
            for (Vertex v2 : graph.getVertices()) {
                for (Vertex v3 : graph.getVertices()) {
                    int index1 = v1.getIndex();
                    int index2 = v2.getIndex();
                    int index3 = v3.getIndex();
                    Constraint constraint = new Constraint();
                    constraint.addIndex(-c[index1][index2]);
                    constraint.addIndex(-c[index2][index3]);
                    constraint.addIndex(c[index1][index3]);
                    //constraintList.add(constraint);
                    System.out.print(constraint);

                }
            }
        }

        //Z_(v, w-1, j) -> Z_(v,w,j)
        for (Vertex v : graph.getVertices()) {
            for (Vertex w : graph.getVertices()) {
                for (int i = 0; i < k; i++) {
                    //================================Warning========================================
                    //Is it necessary to check w < v ????????????????????????????
                    int vIndex = v.getIndex();
                    int wIndex = w.getIndex();
                    //if (wIndex == 0)
                    //    continue;
                    if (wIndex <= vIndex)
                        continue;
                    Constraint constraint = new Constraint();
                    constraint.addIndex(-z[vIndex][wIndex - 1][i]);
                    constraint.addIndex(z[vIndex][wIndex][i]);
                    if(addedString)
                        constraint.setAddString("\tz[" + vIndex + "][" + (wIndex - 1) + "][" + i + "] -> z[" + vIndex + "][" + wIndex + "][" + i + "]");
                    //constraintList.add(constraint);
                    System.out.print(constraint);

                }
            }
        }


        //C_(v,w)-> Z_(v,w,1)
        for (Vertex v : graph.getVertices()) {
            for (Vertex w : graph.getVertices()) {
                int vIndex = v.getIndex();
                int wIndex = w.getIndex();
                if (wIndex <= vIndex)
                    continue;
                Constraint constraint = new Constraint();
                constraint.addIndex(-c[vIndex][wIndex]);
                constraint.addIndex(z[vIndex][wIndex][0]);
                if(addedString)
                    constraint.setAddString("\tc[" + vIndex + "][" + wIndex + "] -> z[" + vIndex + "][" + wIndex + "][0]");
                //constraintList.add(constraint);
                System.out.print(constraint);

            }
        }

        //C_(v,w) ^ Z_(v,w-1,j-1) -> Z_(v,w,j)
        for (Vertex v : graph.getVertices()) {
            for (Vertex w : graph.getVertices()) {
                int vIndex = v.getIndex();
                int wIndex = w.getIndex();
                //if (wIndex == 0)
                //    continue;
                if (wIndex <= vIndex)
                    continue;
                for (int i = 1; i < k; i++) {
                    Constraint constraint = new Constraint();
                    constraint.addIndex(-c[vIndex][wIndex]);
                    constraint.addIndex(-z[vIndex][wIndex - 1][i - 1]);
                    constraint.addIndex(z[vIndex][wIndex][i]);
                    if(addedString)
                        constraint.setAddString("\t\tc[" + vIndex + "][" + wIndex + "] ^ z[" + vIndex + "][" + (wIndex - 1) + "][" + (i - 1) + "] -> z[" + vIndex + "][" + wIndex + "][" + i + "]");
                    //constraintList.add(constraint);
                    System.out.print(constraint);

                }
            }
        }


        //C_(v,w)-> ~Z_(v,w-1,k)
        for (Vertex v : graph.getVertices()) {
            for (Vertex w : graph.getVertices()) {

                int vIndex = v.getIndex();
                int wIndex = w.getIndex();
                if(vIndex>=wIndex)
                    continue;
                Constraint constraint = new Constraint();
                constraint.addIndex(-c[vIndex][wIndex]);
                constraint.addIndex(-z[vIndex][wIndex][k - 1]);
                if(addedString)
                    constraint.setAddString("\t\tc[" + vIndex + "][" + wIndex + "] -> ~z[" + vIndex + "][" + wIndex + "][k-1]");
                //constraintList.add(constraint);
                System.out.print(constraint);
            }
        }


        //X_v -> Y_(v,1)
        for (Vertex v : graph.getVertices()) {
            int vIndex = v.getIndex();
            Constraint constraint = new Constraint();
            constraint.addIndex(-x[vIndex]);
            constraint.addIndex(y[vIndex][0]);
            if(addedString)
                constraint.setAddString("\tx["+vIndex+"] -> y["+vIndex+"][0]");
            //constraintList.add(constraint);
            System.out.print(constraint);

        }

        //X_v ^ Y_(v-1, i-1) -> Y_(v,i)
        for (Vertex v : graph.getVertices()) {
            for (int i = 1; i < k; i++) {
                int vIndex = v.getIndex();
                if (vIndex == 0)
                    continue;
                Constraint constraint = new Constraint();
                constraint.addIndex(-x[vIndex]);
                constraint.addIndex(-y[vIndex - 1][i - 1]);
                constraint.addIndex(y[vIndex][i]);
                if(addedString)
                    constraint.setAddString("\tx["+vIndex+"] ^ y["+(vIndex - 1)+"]["+(i - 1)+"] -> y["+vIndex+"]["+i+"]");
                //constraintList.add(constraint);
                System.out.print(constraint);

            }
        }

        //X_v -> ~Y_(v-1,k)
        for (Vertex v : graph.getVertices()) {
            int vIndex = v.getIndex();
            if (vIndex == 0)
                continue;
            Constraint constraint = new Constraint();
            constraint.addIndex(-x[vIndex]);
            constraint.addIndex(-y[vIndex - 1][k - 1]);
            if(addedString)
                constraint.setAddString("\tx["+vIndex+"] -> ~y["+(vIndex - 1)+"][k - 1]");
            //constraintList.add(constraint);
            System.out.print(constraint);

        }

        //Y_(v-1,j) -> Y_(v,j)
        for(Vertex vertex: graph.getVertices()){
            int vertexIndex = vertex.getIndex();
            if(vertexIndex==0)
                continue;
            for(int i=0; i< k; i++) {
                Constraint constraint = new Constraint();
                constraint.addIndex(-y[vertexIndex - 1][i]);
                constraint.addIndex(y[vertexIndex ][i]);
                if(addedString)
                    constraint.setAddString("\ty["+(vertexIndex - 1)+"]["+i+"] -> y["+vertexIndex +"]["+i+"]");
                //constraintList.add(constraint);
                System.out.print(constraint);

            }
        }
        System.out.println("Making Constraints was just finished");
        /*try {
            writeTofile(constraintList, varCount);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private static void initializeArrays(int n, int k) {
        x = new int[n];
        c = new int[n][n];
        z = new int[n][n][k];
        y = new int[n][k];

        int myCounter = 1;

        for (int i = 0; i < n; i++)
            x[i] = myCounter++;

        System.out.println("x[n] is finished at " + myCounter);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = myCounter++;

        System.out.println("c[n][n] is finished at " + myCounter);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int l = 0; l < k; l++)
                    z[i][j][l] = myCounter++;

        System.out.println("z[n][n][k] is finished at " + myCounter);

        for (int i = 0; i < n; i++)
            for (int l = 0; l < k; l++)
                y[i][l] = myCounter++;
        System.out.println("y[n][k] is finished at " + myCounter);


    }

    private static void writeTofile(List<Constraint> constraintList, int varCount) throws IOException {
        BufferedWriter writer = null;

        File file = new File(filename);

        int clauseCounter = constraintList.size();

        if (!file.exists())
            file.createNewFile();

        writer = new BufferedWriter(new FileWriter(file));
        writer.write("p cnf " + varCount + " " + clauseCounter + "\n");
        for (Constraint constraint : constraintList)
            writer.write(constraint.toString());
        System.out.println("Writing Constraints was just finished");

        writer.close();
    }
}
