package org.raptor.core.nodes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.codecs.PingCodec;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Cluster;
import org.raptor.model.Ping;
import org.raptor.model.ServerDetails;
import org.raptor.model.WorkerNode;
import org.raptor.ui.RaptorUI;

import java.net.InetAddress;
import java.util.List;
import java.util.Observable;

/**
 * Created by Anant on 11-07-2015.
 */
public class AlphaNode extends AbstractVerticle {
    private final String PING_BUS = "PING_BUS";
    private final Integer PING_TIME = 1000;
    private final Logger logger  = LoggerFactory.getLogger(AlphaNode.class);

    private RaptorUI raptorUI;
    private IJSON json;

    protected ServerDetails serverDetails;

    public AlphaNode() {
        json = new GsonJSONImpl();
    }

    public void start() {
        try {

            raptorUI = new RaptorUI(vertx, this.deploymentID());
            raptorUI.start();

            MessageCodec<Ping, Ping> pingCodec = new PingCodec();
            vertx.eventBus().registerDefaultCodec(Ping.class, pingCodec);

            // get host name and host ip
            serverDetails = new ServerDetails(
                    InetAddress.getLocalHost().getHostName(),
                    InetAddress.getLocalHost().getHostAddress()
            );

            // get cluster config details
            Cluster cluster = json.getInstance(
                    vertx
                            .getOrCreateContext()
                            .config()
                            .toString()
                    , Cluster.class);

            cluster.getBetaNode().setDeploymentId(this.deploymentID());

            vertx.eventBus().consumer(PING_BUS, message -> {
                vertx.eventBus().send(this.deploymentID(), json.getJsonString(message.body()));
            });

            // configure ping bus
            vertx.setPeriodic(PING_TIME, id -> {
                vertx.eventBus().publish(PING_BUS, new Ping(cluster.getBetaNode(), serverDetails));
            });

            for (WorkerNode workerNode : cluster.getBetaNode().getWorkerNodes()) {
                workerNode.setParentName(cluster.getBetaNode().getName());
                workerNode.setParentDeploymentId(cluster.getBetaNode().getDeploymentId());

                DeploymentOptions deploymentOptions = new DeploymentOptions();
                deploymentOptions.setInstances(workerNode.getInstance());
                deploymentOptions.setWorker(workerNode.getIsWorker());
                deploymentOptions.setConfig(
                        new JsonObject(
                                json.getJsonString(
                                        workerNode)));

                vertx.deployVerticle(workerNode.getVerticle(), deploymentOptions);

                // temporary
                Thread.sleep(1000);
            }

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}