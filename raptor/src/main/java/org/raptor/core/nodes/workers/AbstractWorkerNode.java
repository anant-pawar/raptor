package org.raptor.core.nodes.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Setting;

/**
 * Created by Anant on 19-08-2015.
 */
public class AbstractWorkerNode extends AbstractVerticle {
    protected IJSON json;
    protected Setting setting;

    public JsonObject config;

    public AbstractWorkerNode()
    {
        json = new GsonJSONImpl();
    }

    public void init()
    {
        // read settings
        setting = json.getInstance(
                vertx
                        .getOrCreateContext()
                        .config()
                        .toString()
                , Setting.class);

        // get configuration information
        config = new JsonObject(
                json.getJsonString(
                        setting.getConfig()));
    }
}
