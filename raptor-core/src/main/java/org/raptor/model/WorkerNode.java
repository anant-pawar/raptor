package org.raptor.model;

import org.raptor.model.marker.INode;

/**
 * Created by Anant on 21-07-2015.
 */
public class WorkerNode implements INode {
    private String name;
    private String deploymentId;
    private String verticle;
    private Integer instance;
    private Boolean worker;
    private Object setting;

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Boolean getIsWorker() {
        return worker;
    }

    public String getVerticle() {
        return verticle;
    }

    public Integer getInstance() {
        return instance;
    }

    public Object getSetting() {
        return setting;
    }
}