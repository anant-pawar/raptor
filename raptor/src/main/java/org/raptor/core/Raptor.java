package org.raptor.core;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * Created by Anant on 12-07-2015.
 */
public class Raptor {
    private Vertx vertx;
    private DeploymentOptions deploymentOptions;

    public Raptor() {
        vertx = Vertx.vertx();
        deploymentOptions = new DeploymentOptions();
    }

    public void run(String jsonString) {
        try {
            // set config
            deploymentOptions
                    .setConfig(new JsonObject(jsonString));

            // spawn the alpha verticle
            vertx.deployVerticle("org.raptor.core.nodes.AlphaNode", deploymentOptions);
        }catch (Exception exception)
        {

        }
    }
}
