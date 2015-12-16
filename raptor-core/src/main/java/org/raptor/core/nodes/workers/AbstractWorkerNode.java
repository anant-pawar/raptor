package org.raptor.core.nodes.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.codecs.PingCodec;
import org.raptor.core.nodes.Node;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.*;

import java.net.InetAddress;


/**
 * Created by Anant on 19-08-2015.
 */
public class AbstractWorkerNode extends Node {
    protected Setting setting;
    protected WorkerNode workerNode;

    public JsonObject config;

    public void init() {
        try {
            super.init();

            // read workerNode information
            workerNode = json.getInstance(
                    vertx
                            .getOrCreateContext()
                            .config()
                            .toString()
                    , WorkerNode.class);

            //set deployment id
            workerNode.setDeploymentId(this.deploymentID());

            // read setting information
            setting = json.getInstance(
                    workerNode.getSetting().toString(), Setting.class);

            // get configuration information
            config = new JsonObject(
                    json.getJsonString(
                            setting.getConfig()));

            ping.setNode(workerNode);

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

}
