package at.tuwien.ac.graph.grid;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.samples.Grid;
import at.tuwien.ac.graph.sat.Constraint;
import at.tuwien.ac.graph.sat.SatAnswer;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by root on 4/11/17.
 */
public class GridSymmetrySatSolver {

    //public static String filename = "sat.ps";

    static int x[];
    static int c[][];
    static int z[][][];
    static int y[][];
    static boolean addedString = true ;
    static boolean writeToFile = true ;


    public static void produceConstraints(int l, int componentSize, int k, String fileName) throws IOException {
        GraphNew graph = IncidentGraphHelper.getNewStructure(new Grid(l).getGraph());
        int n = graph.getN();
        int size = graph.getSize();
        //int varCount = size + (size * size) + (size * size * k) + (size * k);
        int varCount = n + (n * n) + (n * n * componentSize) + (n * k);
        int constraintCount = getCounstraintCount(l, componentSize, k, graph.getEdgeSize());
        //System.out.println(varCount);

        initializeArrays(n, componentSize, k);
        BufferedWriter writer = initializeFile(fileName, varCount, constraintCount);

        //List<Constraint> constraintList = new ArrayList<>();

        //~X_v -> Comp_(v,v)
        for (Integer vertex : graph.getVertices()) {
            //int index = vertex.getIndex();
            Constraint constraint = new Constraint();
            constraint.addIndex(x[vertex]);
            constraint.addIndex(c[vertex][vertex]);
            writeConstraintTofile(writer, constraint);
            /*if(writeToFile)
                constraintList.add(constraint);
            else
                System.out.print(constraint);*/
        }

        //~X_v ^ ~X_w -> C_(v,w)
        //TODO **** half of the following constraints are redundant
        for (Integer vertex : graph.getVertices()) {
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer neighbor : neighbors) {
                Constraint constraint = new Constraint();
                constraint.addIndex(x[vertex]);
                constraint.addIndex(x[neighbor]);
                constraint.addIndex(c[vertex][neighbor]);
                if(addedString)
                    constraint.setAddString("\tx[" + vertex + "] x[" + neighbor + "] c[" + vertex + "][" + neighbor + "]");
                /*if(writeToFile)
                    constraintList.add(constraint);
                else
                    System.out.print(constraint);*/
                writeConstraintTofile(writer, constraint);

            }
        }

        //C_(x, y) ^ C_(y, z) -> C_(x, z)
        for (Integer v1 : graph.getVertices()) {
            for (Integer v2 : graph.getVertices()) {
                for (Integer v3 : graph.getVertices()) {

                    Constraint constraint = new Constraint();
                    constraint.addIndex(-c[v1][v2]);
                    constraint.addIndex(-c[v2][v3]);
                    constraint.addIndex(c[v1][v3]);
                    /*if(writeToFile)
                        constraintList.add(constraint);
                    else
                        System.out.print(constraint);
                    */
                    writeConstraintTofile(writer, constraint);
                }
            }
        }

        //Z_(v, w-1, j) -> Z_(v,w,j)
        for (Integer v : graph.getVertices()) {
            for (Integer w : graph.getVertices()) {
                //for (int i = 0; i < k; i++) {
                if (w <= v)
                    continue;
                for (int i = 0; i < componentSize; i++) {
                    //================================Warning========================================
                    //Is it necessary to check w < v ????????????????????????????

                    //if (wIndex == 0)
                    //    continue;
                    Constraint constraint = new Constraint();
                    constraint.addIndex(-z[v][w - 1][i]);
                    constraint.addIndex(z[v][w][i]);
                    if(addedString)
                        constraint.setAddString("\tz[" + v + "][" + (w - 1) + "][" + i + "] -> z[" + v + "][" + w + "][" + i + "]");
                    /*if(writeToFile)
                        constraintList.add(constraint);
                    else
                        System.out.print(constraint);
                        */
                    writeConstraintTofile(writer, constraint);

                }
            }
        }

        //C_(v,w)-> Z_(v,w,1)
        for (Integer v : graph.getVertices()) {
            for (Integer w : graph.getVertices()) {
                if (w <= v)
                    continue;
                Constraint constraint = new Constraint();
                constraint.addIndex(-c[v][w]);
                constraint.addIndex(z[v][w][0]);
                if(addedString)
                    constraint.setAddString("\tc[" + v + "][" + w + "] -> z[" + v + "][" + w + "][0]");
                /*if(writeToFile)
                    constraintList.add(constraint);
                else
                    System.out.print(constraint);*/
                writeConstraintTofile(writer, constraint);
            }
        }

        //C_(v,w) ^ Z_(v,w-1,j-1) -> Z_(v,w,j)
        for (Integer v : graph.getVertices()) {
            for (Integer w : graph.getVertices()) {
                //if (wIndex == 0)
                //    continue;
                if (w <= v)
                    continue;
                //for (int i = 1; i < k; i++) {
                for (int i = 1; i < componentSize; i++) {
                    Constraint constraint = new Constraint();
                    constraint.addIndex(-c[v][w]);
                    constraint.addIndex(-z[v][w - 1][i - 1]);
                    constraint.addIndex(z[v][w][i]);
                    if(addedString)
                        constraint.setAddString("\t\tc[" + v + "][" + w + "] ^ z[" + v + "][" + (w - 1) + "][" + (i - 1) + "] -> z[" + v + "][" + w + "][" + i + "]");
                    /*if(writeToFile)
                        constraintList.add(constraint);
                    else
                        System.out.print(constraint);*/
                    writeConstraintTofile(writer, constraint);

                }
            }
        }


        //C_(v,w)-> ~Z_(v,w-1,c)
        for (Integer v : graph.getVertices()) {
            for (Integer w : graph.getVertices()) {

                if(v>=w)
                    continue;
                Constraint constraint = new Constraint();
                constraint.addIndex(-c[v][w]);
                constraint.addIndex(-z[v][w][componentSize - 1]);
                if(addedString)
                    constraint.setAddString("\t\tc[" + v + "][" + w + "] -> ~z[" + v + "][" + w + "][k-1]");
                /*if(writeToFile)
                    constraintList.add(constraint);
                else
                    System.out.print(constraint);*/
                writeConstraintTofile(writer, constraint);
            }
        }


        //X_v -> Y_(v,1)
        for (Integer v : graph.getVertices()) {
            Constraint constraint = new Constraint();
            constraint.addIndex(-x[v]);
            constraint.addIndex(y[v][0]);
            if(addedString)
                constraint.setAddString("\tx["+v+"] -> y["+v+"][0]");

            /*if(writeToFile)
                constraintList.add(constraint);
            else
                System.out.print(constraint);*/
            writeConstraintTofile(writer, constraint);

        }

        //X_v ^ Y_(v-1, i-1) -> Y_(v,i)
        for (Integer v : graph.getVertices()) {
            if (v == 0)
                continue;
            for (int i = 1; i < k; i++) {
                Constraint constraint = new Constraint();
                constraint.addIndex(-x[v]);
                constraint.addIndex(-y[v - 1][i - 1]);
                constraint.addIndex(y[v][i]);
                if(addedString)
                    constraint.setAddString("\tx["+v+"] ^ y["+(v - 1)+"]["+(i - 1)+"] -> y["+v+"]["+i+"]");
                /*if(writeToFile)
                    constraintList.add(constraint);
                else
                    System.out.print(constraint);*/
                writeConstraintTofile(writer, constraint);

            }
        }

        //X_v -> ~Y_(v-1,k)
        for (Integer v : graph.getVertices()) {
            if (v == 0)
                continue;
            Constraint constraint = new Constraint();
            constraint.addIndex(-x[v]);
            constraint.addIndex(-y[v - 1][k - 1]);
            if(addedString)
                constraint.setAddString("\tx["+v+"] -> ~y["+(v - 1)+"][k - 1]");
            /*if(writeToFile)
                constraintList.add(constraint);
            else
                System.out.print(constraint);*/
            writeConstraintTofile(writer, constraint);
        }

        //Y_(v-1,j) -> Y_(v,j)
        for(Integer vertex: graph.getVertices()){
            if(vertex==0)
                continue;
            for(int i=0; i< k; i++) {
                Constraint constraint = new Constraint();
                constraint.addIndex(-y[vertex - 1][i]);
                constraint.addIndex(y[vertex ][i]);
                if(addedString)
                    constraint.setAddString("\ty["+(vertex - 1)+"]["+i+"] -> y["+vertex +"]["+i+"]");
                /*if(writeToFile)
                    constraintList.add(constraint);
                else
                    System.out.print(constraint);*/
                writeConstraintTofile(writer, constraint);

            }
        }

        //Only for Grids
        //x[i,j]= x[l-i,j]= x[i,l-j]=x[l-i,l-j]
        int w = l/2;
        int h = (l/2)+(l%2);
        for(int i = 0 ; i < w; i++){
            for(int j = 0; j < h; j++){

                //x[i,j]->x[l-i-1,j]
                Constraint constraint = new Constraint();
                constraint.addIndex(-x[i*l+j]);
                constraint.addIndex(x[(l-i-1)*l+j]);
                if(addedString)
                    constraint.setAddString("\tx["+(i*l+j)+"] -> x["+((l-i-1)*l+j) +"]");
                writeConstraintTofile(writer, constraint);

                //x[l-i-1,j]->x[l-i-1,l-j-1]
                if((j<h-1)||(l%2==0)) {
                    constraint = new Constraint();
                    constraint.addIndex(-x[(l - i - 1) * l + j]);
                    constraint.addIndex(x[(l - i - 1) * l + l - j - 1]);
                    if (addedString)
                        constraint.setAddString(
                            "\tx[" + ((l - i - 1) * l + j) + "] -> x[" + ((l - i - 1) * l + l - j
                                - 1) + "]");
                    writeConstraintTofile(writer, constraint);
                }


                //x[l-i-1,l-j-1]->x[i,l-j-1]
                constraint = new Constraint();
                constraint.addIndex(-x[(l-i-1)*l+l-j-1]);
                constraint.addIndex(x[i*l+l-j-1]);
                if(addedString)
                    constraint.setAddString("\tx["+((l-i-1)*l+l-j-1)+"] -> x["+(i*l+l-j-1) +"]");
                writeConstraintTofile(writer, constraint);




                //x[i,l-j-1]->x[i,j]
                if((j<h-1)||(l%2==0)) {
                    constraint = new Constraint();
                    constraint.addIndex(-x[i * l + l - j - 1]);
                    constraint.addIndex(x[i * l + j]);
                    if (addedString)
                        constraint.setAddString(
                            "\tx[" + (i * l + l - j - 1) + "] -> x[" + (i * l + j) + "]");
                    writeConstraintTofile(writer, constraint);
                }

                /*
                constraint = new Constraint();
                constraint.addIndex(x[i*l+h-j]);
                constraint.addIndex(-x[i*l+j]);
                if(addedString)
                    constraint.setAddString("\tx["+(i*l+h-j)+"] -> -x["+(i*l+j) +"]");
                writeConstraintTofile(writer, constraint);
                */
            }
        }
        finilizeFile(writer);
    }

    private static int getCounstraintCount(int l,int c, int k, int edgeSize) {
        int n = l * l ;
        int w = l/2;
        int h = l/2+l%2;
        int constraintCounter = 0 ;
        constraintCounter += n ;
        constraintCounter += edgeSize;
        constraintCounter += n*n*n ;
        constraintCounter += n*(n-1)/2*c ;
        constraintCounter += n*(n-1)/2 ;
        constraintCounter += n*(n-1)/2 * (c-1) ;
        constraintCounter += n*(n-1)/2 ;
        constraintCounter += n ;
        constraintCounter += (n-1)*(k-1) ;
        constraintCounter += n-1 ;
        constraintCounter += (n-1)*k ;
        constraintCounter += 4*w*h;
        if((l%2)==1)
            constraintCounter -= -2*l;
        return constraintCounter ;
    }

    private static void initializeArrays(int n, int componentSize, int k) {
        x = new int[n];
        c = new int[n][n];
        z = new int[n][n][componentSize];
        y = new int[n][k];

        int myCounter = 1;

        for (int i = 0; i < n; i++)
            x[i] = myCounter++;

        //System.out.println("x[n] is finished at " + myCounter);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = myCounter++;

        //System.out.println("c[n][n] is finished at " + myCounter);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                //for (int l = 0; l < k; l++)
                for (int l = 0; l < componentSize; l++)
                    z[i][j][l] = myCounter++;

        //System.out.println("z[n][n][k] is finished at " + myCounter);

        for (int i = 0; i < n; i++)
            for (int l = 0; l < k; l++)
                y[i][l] = myCounter++;
        //System.out.println("y[n][k] is finished at " + myCounter);


    }

    private static BufferedWriter initializeFile(String filename, int varCount, int constraintCount) throws IOException {
        BufferedWriter writer = null;

        File file = new File(filename);

        if (!file.exists())
            file.createNewFile();

        //System.out.println("Start writing constraint list to the file "+filename);

        writer = new BufferedWriter(new FileWriter(file));
        writer.write("p cnf " + varCount + " " + constraintCount + "\n");

        return writer ;
    }

    private static void writeConstraintTofile(BufferedWriter writer, Constraint constraint) throws IOException {
        writer.write(constraint.toString());
    }

    private static void finilizeFile(BufferedWriter writer) throws IOException {
        writer.close();
    }

    public static SatAnswer getFVDBySAT(int l, int mycompsize, int k, int timout) {
        Set<Integer> myList = null;
        String cpuTime = "";

        String fileName = "sat.ps";
        //System.out.println("component size " + component.getSize()+ "\t in fileName: "+fileName );
        GraphNew shuffle = IncidentGraphHelper.getNewStructure(new Grid(l).getGraph()).shuffle();
        try {
            GridSymmetrySatSolver.produceConstraints(l, mycompsize, k  , fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String command = "/home/e1528895/Programs/glucose-syrup-4.1/simp/./glucose " + fileName + " -model";
            if(timout>0)
                command += " -cpu-lim="+timout;


            Process proc = Runtime.getRuntime().exec(command);//> /home/e1528895/temp.tmp
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                if(!s.startsWith("v"))
                    System.out.println(s);
                if(s.startsWith("c")){
                    String[] split = s.split("\\s+");
                    if((split.length>4)&&(split[1].equalsIgnoreCase("CPU"))){
                        cpuTime = split[4]+split[5];
                    }
                }
                if(s.startsWith("s")){
                    char c = s.charAt(2);
                    //System.out.println(c);
                    if(c =='U') {
                        SatAnswer answer = new SatAnswer(null, cpuTime, true);
                        return answer;
                    }
                    if(c =='I') {
                        SatAnswer answer = new SatAnswer(null, cpuTime, false);
                        return answer;
                    }
                    //s+='-';
                    if(c =='S')
                        s+='+';
                }

                /*if(s.equalsIgnoreCase("*** INTERRUPTED ***")) {
                    finished = false;
                }*/

                if(!s.startsWith("v")) {
                    //System.out.println(s);
                }else{
                    myList = new HashSet<>();
                    String[] split = s.split("\\s+");
                    for(int i=0; i < shuffle.getSize(); i++) {
                        int value = Integer.parseInt(split[i+1]);
                        if(value > 0) {
                            String nameOf = shuffle.getGraphInfo().getNameOf(i);
                            System.out.println(nameOf);
                            myList.add(Integer.parseInt(nameOf));
                        }
                    }
                }

            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SatAnswer answer = new SatAnswer(myList, cpuTime, true);
        return answer ;
    }
}
