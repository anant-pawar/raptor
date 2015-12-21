package org.raptor.model;

import org.raptor.model.marker.INode;

/**
 * Created by Anant on 21-07-2015.
 */
public class WorkerNode implements INode {
    private String name;
    private String verticle;
    private Integer instance;
    private Boolean worker;
    private Object setting;

    public WorkerNode(WorkerNode workerNode){
        this.name = workerNode.name;
        this.verticle = workerNode.verticle;
        this.instance = workerNode.instance;
        this.worker = workerNode.worker;
        this.setting = workerNode.setting;
    }

    public String getName() {
        return name;
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