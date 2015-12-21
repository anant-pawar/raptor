package org.raptor.core.nodes.workers;

import io.vertx.core.json.JsonObject;
import org.raptor.core.nodes.Node;
import org.raptor.model.Setting;
import org.raptor.model.WorkerNodeLive;


/**
 * Created by Anant on 19-08-2015.
 */
public class AbstractWorkerNode extends Node {
    protected Setting setting;
    protected WorkerNodeLive workerNodeLive;

    public JsonObject config;

    public void init() {
        try {
            // initialisation
            super.init();

            // read workerNode information
            workerNodeLive = json.getInstance(
                    vertx
                            .getOrCreateContext()
                            .config()
                            .toString()
                    , WorkerNodeLive.class);

            //set workers deployment id
            workerNodeLive.setDeploymentId(this.deploymentID());

            // read setting information
            setting = json.getInstance(
                    workerNodeLive.getSetting().toString(), Setting.class);

            // get configuration information
            config = new JsonObject(
                    json.getJsonString(
                            setting.getConfig()));

            // set ping node
            ping.setNode(workerNodeLive);

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

}
