package org.raptor.model;

import org.raptor.model.marker.INode;

/**
 * Created by Anant on 05-11-2015.
 */
public class BetaNode implements INode {
    private String name;
    private String deploymentId;
    private WorkerNode workerNode;

    public String getName() {
        return name;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public WorkerNode getWorkerNode() {
        return workerNode;
    }
}
