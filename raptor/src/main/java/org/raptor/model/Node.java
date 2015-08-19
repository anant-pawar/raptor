package org.raptor.model;

import io.vertx.core.json.JsonObject;

/**
 * Created by Anant on 21-07-2015.
 */
public class Node {
    private String verticle;
    private Integer instance;
    private Object setting;

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