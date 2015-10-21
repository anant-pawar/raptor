package org.raptor.core.nodes.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Node;
import org.raptor.model.Setting;


/**
 * Created by Anant on 19-08-2015.
 */
public class AbstractWorkerNode extends AbstractVerticle {
    private final Integer PING_TIME = 1000;
    private final String PING_BUS = "PING_BUS";

    protected IJSON json;
    protected Node node;
    protected Setting setting;
    protected static Logger logger;

    public JsonObject config;

    public AbstractWorkerNode()
    {
        json = new GsonJSONImpl();
    }

    public void init()
    {
        // read settings
        node = json.getInstance(
                vertx
                        .getOrCreateContext()
                        .config()
                        .toString()
                , Node.class);

        setting = json.getInstance(
                node.getSetting().toString(), Setting.class);

        // get configuration information
        config = new JsonObject(
                json.getJsonString(
                        setting.getConfig()));

        // configure ping bus
        vertx.setPeriodic(PING_TIME, id -> {
            vertx.eventBus().publish(PING_BUS, json.getJsonString(node));
        });

        logger = LoggerFactory.getLogger(this.getClass().getName());
    }
}
