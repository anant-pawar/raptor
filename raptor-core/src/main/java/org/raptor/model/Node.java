package org.raptor.model;

/**
 * Created by Anant on 21-07-2015.
 */
public class Node {
    private String verticle;
    private Integer instance;
    private Boolean worker;
    private Object setting;

    private ServerInfo serverInfo;

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public Boolean getIsWorker() {
        return worker;
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