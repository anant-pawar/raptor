package org.raptor.model;

/**
 * Created by anant on 1/11/15.
 */
public class ServerDetails {
    private String serverName;
    private String serverIp;


    public ServerDetails(String serverName, String serverIp)
    {
        this.serverName = serverName;
        this.serverIp = serverIp;
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

}
