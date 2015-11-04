package org.raptor.model;

import org.raptor.model.marker.INode;

/**
 * Created by Anant on 05-11-2015.
 */
public class Ping {
    private INode node;
    private ServerDetails serverDetails;

    public Ping(INode node, ServerDetails serverDetails)
    {
        this.node = node;
        this.serverDetails = serverDetails;
    }
}
