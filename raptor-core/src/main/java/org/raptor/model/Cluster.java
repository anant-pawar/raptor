package org.raptor.model;

import java.util.List;

/**
 * Created by Anant on 17-08-2015.
 */
public class Cluster {
    private String clusterKey;
    private List<WorkerNode> workerNodes;

    public String getClusterKey() {
        return clusterKey;
    }

    public List<WorkerNode> getWorkerNodes() {
        return workerNodes;
    }
}
