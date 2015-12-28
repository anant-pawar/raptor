package org.raptor.model;

import org.raptor.model.marker.INode;

/**
 * Created by Anant on 05-11-2015.
 */
public class Ping {
    private INode node;
    private Server server;

    public Ping() {
        this.setNode(null);
        this.server = null;
    }

    public Ping(Server server)
    {
        this.server = server;
    }

    public INode getNode() {
        return node;
    }

    public void setNode(INode node) {
        this.node = node;
    }

    public Server getServer() {
        return server;
    }
}
