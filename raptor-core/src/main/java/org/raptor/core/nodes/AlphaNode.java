package org.raptor.core.nodes;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.raptor.helpers.ClusterMapper;
import org.raptor.model.*;
import org.raptor.startup.RegisterCodes;
import org.raptor.ui.RaptorUI;

import java.util.HashMap;

/**
 * Created by Anant on 11-07-2015.
 */
public class AlphaNode extends Node {
    private RaptorUI raptorUI;
    private RegisterCodes registerCodes;
    private BetaNodeLive betaNodeLive;
    private ClusterMapper clusterMapper;

    public AlphaNode() {
        clusterMapper = new ClusterMapper();
        registerCodes = new RegisterCodes();
    }

    public void start() {
        try {
            // initialisation
            super.init();

            // register codec
            registerCodes.register(vertx);

            // start ui
            raptorUI = new RaptorUI(vertx, this.deploymentID());
            raptorUI.start();

            // get cluster config details
            Cluster cluster = json.getInstance(
                    vertx
                            .getOrCreateContext()
                            .config()
                            .toString()
                    , Cluster.class);

            // construct a beta node live
            BetaNodeLive betaNodeLive = new BetaNodeLive(cluster.getBetaNode());
            betaNodeLive.setDeploymentId(this.deploymentID());

            // set ping node
            ping.setNode(betaNodeLive);

            // set the ping consumer
            vertx.eventBus().consumer(PING_BUS, this::processPing);

            // deploy the workers
            for (WorkerNode workerNode : cluster.getBetaNode().getWorkerNodes()) {
                WorkerNodeLive workerNodeLive = new WorkerNodeLive(workerNode);
                workerNodeLive.setParentName(cluster.getBetaNode().getName());
                workerNodeLive.setParentDeploymentId(this.deploymentID());

                DeploymentOptions deploymentOptions = new DeploymentOptions();
                deploymentOptions.setInstances(workerNodeLive.getInstance());
                deploymentOptions.setWorker(workerNodeLive.getIsWorker());
                deploymentOptions.setConfig(
                        new JsonObject(json.getJsonString(workerNodeLive)));

                vertx.deployVerticle(workerNodeLive.getVerticle(), deploymentOptions);

                // temporary
                Thread.sleep(1000);
            }

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

    private void sendCluster(HashMap<String, ServerLive> clusterMap) {
        vertx.eventBus().send(this.deploymentID(), json.getJsonString(clusterMap));
    }

    private void processPing(Message message) {
        rx
                .Observable
                .just(message.body())
                .map(pingObject -> (Ping) pingObject)
                .map(clusterMapper::getUpdatedCluster)
                .subscribe(this::sendCluster);
    }

}