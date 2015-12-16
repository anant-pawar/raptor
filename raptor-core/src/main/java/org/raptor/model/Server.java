package org.raptor.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Anant on 24-11-2015.
 */
public class Server {
    private String serverName;
    private String serverIP;

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
