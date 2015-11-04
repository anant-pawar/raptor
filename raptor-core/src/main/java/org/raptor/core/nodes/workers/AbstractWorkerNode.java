package org.raptor.core.nodes.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Ping;
import org.raptor.model.WorkerNode;
import org.raptor.model.ServerDetails;
import org.raptor.model.Setting;

import java.net.InetAddress;


/**
 * Created by Anant on 19-08-2015.
 */
public class AbstractWorkerNode extends AbstractVerticle {
    protected static Logger logger;
    private final Integer PING_TIME = 1000;
    private final String PING_BUS = "PING_BUS";
    public JsonObject config;
    protected IJSON json;
    protected WorkerNode workerNode;
    protected Setting setting;
    protected ServerDetails serverDetails;

    public AbstractWorkerNode() {
        json = new GsonJSONImpl();
    }

    public void init() {
        try {
            // read workerNode information
            workerNode = json.getInstance(
                    vertx
                            .getOrCreateContext()
                            .config()
                            .toString()
                    , WorkerNode.class);

            //set deployment id
            workerNode.setDeploymentId(this.deploymentID());

            // get host name and host ip
            serverDetails = new ServerDetails(
                    InetAddress.getLocalHost().getHostName(),
                    InetAddress.getLocalHost().getHostAddress()
            );

            // read setting information
            setting = json.getInstance(
                    workerNode.getSetting().toString(), Setting.class);

            // get configuration information
            config = new JsonObject(
                    json.getJsonString(
                            setting.getConfig()));

            // configure ping bus
            vertx.setPeriodic(PING_TIME, id -> {
                vertx.eventBus().publish(PING_BUS, json.getJsonString(new Ping(workerNode, serverDetails)));
            });

            logger = LoggerFactory.getLogger(this.getClass().getName());
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
