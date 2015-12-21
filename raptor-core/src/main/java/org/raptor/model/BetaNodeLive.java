package org.raptor.model;

import java.util.HashMap;

/**
 * Created by Anant on 13-12-2015.
 */
public class BetaNodeLive extends BetaNode {
    private String deploymentId;
    private HashMap<String,WorkerNodeLive> liveNodes = new HashMap<>();

    public BetaNodeLive(BetaNode betaNode)
    {
        super(betaNode.getName(), betaNode.getWorkerNodes());
        this.liveNodes = new HashMap<>();
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public HashMap<String, WorkerNodeLive> getLiveNodes() {
        return liveNodes;
    }
}
