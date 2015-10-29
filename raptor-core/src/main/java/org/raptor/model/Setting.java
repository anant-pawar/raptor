package org.raptor.model;

/**
 * Created by Anant on 17-07-2015.
 */
public class Setting {
    private String id;
    private Object config;
    private Routes routes;

    public String getId() {
        return id;
    }

    public Routes getRoutes() {
        return routes;
    }

    public Object getConfig() {
        return config;
    }
}
