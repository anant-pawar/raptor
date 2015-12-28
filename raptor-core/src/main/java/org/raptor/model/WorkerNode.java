package org.raptor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.raptor.model.marker.INode;

/**
 * Created by Anant on 21-07-2015.
 */
public class WorkerNode implements INode {
    private String name;
    private String verticle;
    private Integer instance;
    private Boolean isWorker;
    private Object setting;

    public WorkerNode() {
        this.name = null;
        this.verticle = null;
        this.instance = 1;
        this.isWorker = true;
        this.setting = null;
    }

    public WorkerNode(WorkerNode workerNode){
        this.name = workerNode.name;
        this.verticle = workerNode.verticle;
        this.instance = workerNode.instance;
        this.isWorker = workerNode.isWorker;
        this.setting = workerNode.setting;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsWorker() {
        return isWorker;
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