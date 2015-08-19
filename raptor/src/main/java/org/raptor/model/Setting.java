package org.raptor.model;

import io.vertx.core.json.JsonObject;

import java.util.ArrayList;

/**
 * Created by Anant on 17-07-2015.
 */
public class Setting {
    private String id;
    private ArrayList<String> sendTo;
    private ArrayList<String> receiveFrom;
    private Object config;

    public String getId() {
        return id;
    }

    public ArrayList<String> getSendTo() {
        return sendTo;
    }

    public ArrayList<String> getReceiveFrom() {
        return receiveFrom;
    }

    public Object getConfig() {
        return config;
    }
}
