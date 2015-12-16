package org.raptor.model;

import org.raptor.model.Server;

import java.util.HashMap;

/**
 * Created by Anant on 13-12-2015.
 */
public class ServerLive extends Server {
    private HashMap<String,BetaNodeLive> betaNodes;

    public ServerLive(Server server)
    {
        super(server.getServerIP(),server.getServerName());
        this.betaNodes = new HashMap<>();
    }

    public HashMap<String, BetaNodeLive> getBetaNodes() {
        return betaNodes;
    }
}
