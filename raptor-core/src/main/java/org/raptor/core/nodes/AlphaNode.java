package org.raptor.core.nodes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Cluster;
import org.raptor.model.Node;
import org.raptor.ui.RaptorUI;

import java.util.List;

/**
 * Created by Anant on 11-07-2015.
 */
public class AlphaNode extends AbstractVerticle {
    private final String PING_BUS = "PING_BUS";
    private List<Node> nodes;
    private IJSON json;
    private RaptorUI raptorUI;

    public AlphaNode() {
        json = new GsonJSONImpl();
    }

    public void start() {
        try {

            raptorUI = new RaptorUI(vertx, this.deploymentID());
            raptorUI.start();

            vertx.eventBus().consumer(PING_BUS, message -> {
                vertx.eventBus().send(this.deploymentID(), message.body());
            });

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
                                        node)));

                vertx.deployVerticle(node.getVerticle(), deploymentOptions);

                // temporary
                Thread.sleep(1000);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}