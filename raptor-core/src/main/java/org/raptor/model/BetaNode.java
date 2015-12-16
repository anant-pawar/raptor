package org.raptor.model;

import org.raptor.model.marker.INode;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Anant on 05-11-2015.
 */
public class BetaNode implements INode {
    private String name;
    private List<WorkerNode> workerNodes;

    public BetaNode(String name, List<WorkerNode> workerNodes){
        this.name = name;
        this.workerNodes = workerNodes;
    }

    public String getName() {
        return name;
    }

    public List<WorkerNode> getWorkerNodes() {
        return workerNodes;
    }
}
