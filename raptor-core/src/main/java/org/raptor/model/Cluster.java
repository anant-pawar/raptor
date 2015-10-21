package org.raptor.model;

import java.util.List;

/**
 * Created by Anant on 17-08-2015.
 */
public class Cluster {
    private String clusterKey;
    private List<Node> nodes;

    public String getClusterKey() {
        return clusterKey;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
