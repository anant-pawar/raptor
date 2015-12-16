package org.raptor.model;

import java.util.HashMap;

/**
 * Created by Anant on 13-12-2015.
 */
public class BetaNodeLive extends BetaNode {
    private String deploymentId;
    private HashMap<String,WorkerNode> liveNodes = new HashMap<>();

    public BetaNodeLive(BetaNode betaNode, String deploymentId)
    {
        super(betaNode.getName(), betaNode.getWorkerNodes());
        this.deploymentId = deploymentId;
        this.liveNodes = new HashMap<>();
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public HashMap<String, WorkerNode> getLiveNodes() {
        return liveNodes;
    }
}
