package org.raptor.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Anant on 24-11-2015.
 */
public class Server {
    private String serverName;
    private String serverIP;
    private HashMap<String,BetaNode> betaNodes;

    public Server(String serverIP, String serverName)
    {
        this.serverIP = serverIP;
        this.serverName = serverName;
        this.betaNodes = new HashMap<>();
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerIP() {
        return serverIP;
    }

    public HashMap<String, BetaNode> getBetaNodes() {
        return betaNodes;
    }
}
