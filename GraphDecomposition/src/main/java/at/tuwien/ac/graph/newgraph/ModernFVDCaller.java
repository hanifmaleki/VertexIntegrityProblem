package at.tuwien.ac.graph.newgraph;

import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;


import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by e1528895 on 12/10/17.
 */
public class ModernFVDCaller {
    Logger logger = LoggerFactory.getLogger(ModernFVDCaller.class) ;
    private boolean coverPrune;
    private boolean similarPrune;
    private boolean symmetryBraking=true;

    public SimpleFVDExperiment findFVD(GraphNew graph, int k, int c, int timeout) {
        int newTimeout = timeout ;
        SimpleFVDExperiment bestFVD = null ;
        int kk = k;
        System.out.println("First k in caller is "+ k+ " graph size "+ graph.getSize());
        logger.debug("First k in caller is "+ k + " graph size "+ graph.getSize());
        while(true) {
            ModernFVDSolver modernFVDSolver = new ModernFVDSolver();
            modernFVDSolver.setCoverPrune(coverPrune);
            modernFVDSolver.setSimilarPrune(similarPrune);
            modernFVDSolver.setSymmetryBraking(symmetryBraking);
            SimpleFVDExperiment fvd = modernFVDSolver.findFVD(graph, kk, c, timeout);
            System.out.println(fvd);
            logger.debug(fvd.toString());
            boolean exitCondition = !(fvd.getOperationDuration()< timeout*1000) || (fvd.getLb() >= kk);
            if(exitCondition) {
                fvd.setK(k);
                if(bestFVD==null)
                    return fvd ;
                if(bestFVD.getLb()>fvd.getLb())
                    fvd.setLb(fvd.getLb());
                if((bestFVD.getFvdSize()!=null)&&((fvd.getFvdSize()==null)||(fvd.getFvdSize()>bestFVD.getFvdSize()))){
                    System.out.println("Updating upper bound");
                    logger.debug("Updating upper bound");
                    fvd.setUb(bestFVD.getUb());
                    List<VertexEntity> selectedVertices = bestFVD.getSelectedVertices();
                    fvd.setSelectedVertices(selectedVertices);
                    for(VertexEntity entity: selectedVertices)
                        entity.setExpriment(fvd);
                    fvd.setFvdSize(bestFVD.getFvdSize());

                }
                System.out.println("Answer in caller :" +fvd);
                logger.debug("Answer in caller :" + fvd);
                return fvd ;
            }
            else {
                bestFVD = fvd;
                kk = bestFVD.getUb() - 1;
                try {
                    Thread.sleep(20*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.debug("reexecution with new k = " + kk);
                System.out.println("reexecution with new k =" + kk);
            }
        }

    }

    public void setCoverPrune(boolean coverPrune) {
        this.coverPrune = coverPrune;
    }

    public boolean isCoverPrune() {
        return coverPrune;
    }

    public void setSimilarPrune(boolean similarPrune) {
        this.similarPrune = similarPrune;
    }

    public boolean isSimilarPrune() {
        return similarPrune;
    }

    public boolean isSymmetryBraking() {
        return symmetryBraking;
    }

    public void setSymmetryBraking(boolean symmetryBraking) {
        this.symmetryBraking = symmetryBraking;
    }
}
