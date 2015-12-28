package org.raptor.model;

/**
 * Created by Anant on 24-11-2015.
 */
public class Server {
    private String serverName;
    private String serverIP;

    public Server() {
        this.serverName = null;
        this.serverIP = null;
    }

    public Server(String serverIP, String serverName) {
        this.serverIP = serverIP;
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerIP() {
        return serverIP;
    }
}
