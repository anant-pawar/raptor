package org.raptor.model;

import org.raptor.model.marker.INode;

import java.util.List;

/**
 * Created by Anant on 05-11-2015.
 */
public class BetaNode implements INode {
    private String name;
    private String deploymentId;
    private String clusterKey;
    private List<WorkerNode> workerNodes;

    public String getName() {
        return name;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getClusterKey() {
        return clusterKey;
    }

    public List<WorkerNode> getWorkerNodes() {
        return workerNodes;
    }
}
