package org.raptor.model;

/**
 * Created by Anant on 17-12-2015.
 */
public class WorkerNodeLive extends WorkerNode {
    private String deploymentId;
    private String parentName;
    private String parentDeploymentId;

    public WorkerNodeLive() {
        super();
        this.setDeploymentId(null);
        this.setParentName(null);
        this.setParentDeploymentId(null);
    }

    public WorkerNodeLive(WorkerNode workerNode) {
        super(workerNode);
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentDeploymentId() {
        return parentDeploymentId;
    }

    public void setParentDeploymentId(String parentDeploymentId) {
        this.parentDeploymentId = parentDeploymentId;
    }

}
