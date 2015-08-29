package org.raptor.core.nodes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Cluster;
import org.raptor.model.Node;

/**
 * Created by Anant on 11-07-2015.
 */
public class AlphaNode extends AbstractVerticle {
    IJSON json;

    public AlphaNode() {
        json = new GsonJSONImpl();
    }

    public void start() {
        try {
            // get cluster config details
            Cluster cluster = json.getInstance(
                    vertx
                            .getOrCreateContext()
                            .config()
                            .toString()
                    , Cluster.class);

            for (Node node : cluster.getNodes()) {
                DeploymentOptions deploymentOptions = new DeploymentOptions();
                deploymentOptions.setInstances(node.getInstance());
                deploymentOptions.setWorker(node.getIsWorker());
                deploymentOptions.setConfig(
                        new JsonObject(
                                json.getJsonString(
                                        node.getSetting())));
                
                vertx.deployVerticle(node.getVerticle(), deploymentOptions);

                // temporary
                Thread.sleep(1000);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}