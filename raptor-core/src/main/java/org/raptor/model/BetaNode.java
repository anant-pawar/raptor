package org.raptor.model;

import org.raptor.model.marker.INode;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Anant on 05-11-2015.
 */
public class BetaNode implements INode {
    private String name;
    private String deploymentId;
    private List<WorkerNode> workerNodes;
    private HashMap<String,WorkerNode> liveNodes = new HashMap<>();

    public String getName() {
        return name;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public List<WorkerNode> getWorkerNodes() {
        return workerNodes;
    }

    public HashMap<String, WorkerNode> getLiveNodes() {
        return liveNodes;
    }
}
