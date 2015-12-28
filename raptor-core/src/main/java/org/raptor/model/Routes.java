package org.raptor.model;

import java.util.ArrayList;

/**
 * Created by Anant on 29-08-2015.
 */
public class Routes {
    private ArrayList<String> send;
    private ArrayList<String> publish;

    public Routes() {
        this.send = new ArrayList<>();
        this.publish = new ArrayList<>();
    }

    public ArrayList<String> getSend() {
        return send;
    }

    public ArrayList<String> getPublish() {
        return publish;
    }
}
