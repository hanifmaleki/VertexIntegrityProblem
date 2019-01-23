package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by e1528895 on 8/11/17.
 */
public class RoboticProblemProducer {
    int n ;

    public RoboticProblemProducer(int n){
        this.n = n ;
    }
    public void produceProblem(String filename){
        FileWriter fileWriter = null ;
        BufferedWriter bufferedWriter = null ;
        try {
            File file = new File(filename);
            System.out.println(file.getAbsolutePath());
            fileWriter = new FileWriter(file);

            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("(define (problem grid-"+n+")\n");
            bufferedWriter.write("(:domain grid-visit-all)\n");
            bufferedWriter.write("(:objects \n");
            for(int i=0; i < n; i++){
                for(int j=0; j < n; j++){
                    bufferedWriter.write("\tloc-x"+i+"-y"+j+"\n");
                }
            }
            bufferedWriter.write("- place \n" +
                    "        \n" +
                    ")\n" );
            bufferedWriter.write("(:init\n");
            bufferedWriter.write("\t(at-robot loc-x"+(n/2)+"-y"+(n/2)+")\n" );
            bufferedWriter.write("\t(visited loc-x"+(n/2)+"-y"+(n/2)+")\n");
            for(int i=0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(i>0)
                        bufferedWriter.write(" \t(connected loc-x"+i+"-y"+j+" loc-x"+(i-1)+"-y"+j+")\n");
                    if(i<n-1)
                        bufferedWriter.write(" \t(connected loc-x"+i+"-y"+j+" loc-x"+(i+1)+"-y"+j+")\n");
                    if(j>0)
                        bufferedWriter.write(" \t(connected loc-x"+i+"-y"+j+" loc-x"+i+"-y"+(j-1)+")\n");
                    if(j<n-1)
                        bufferedWriter.write(" \t(connected loc-x"+i+"-y"+j+" loc-x"+i+"-y"+(j+1)+")\n");

                }
            }
            bufferedWriter.write(" \n)\n" +
                    "(:goal\n" +
                    "(and \n");

            for(int i=0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bufferedWriter.write("\t(visited loc-x"+i+"-y"+j+")\n");
                }
            }

            bufferedWriter.write(")\n" +
                    ")\n" +
                    ")");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                // Close the writer regardless of what happens...
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        new RoboticProblemProducer(5).produceProblem("samples/grid-5.pddl");
    }
}
