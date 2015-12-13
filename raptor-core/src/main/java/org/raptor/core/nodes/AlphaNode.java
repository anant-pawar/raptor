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
import org.raptor.model.*;
import org.raptor.ui.RaptorUI;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * Created by Anant on 11-07-2015.
 */
public class AlphaNode extends AbstractVerticle {
    private final String PING_BUS = "PING_BUS";
    private final Integer PING_TIME = 1000;
    private final Logger logger  = LoggerFactory.getLogger(AlphaNode.class);

    private RaptorUI raptorUI;
    private IJSON json;
    private HashMap<String, Server> clusterMap = new HashMap<>();

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
                rx.Observable
                        .just(message.body())
                        .map(pingObject -> (Ping) pingObject)
                        .map(ping -> {

                            String serverIP = ping.getServerDetails().getServerIp();

                            if (!clusterMap.containsKey(serverIP))
                                clusterMap.put(serverIP
                                        , new Server(ping.getServerDetails().getServerIp()
                                        , ping.getServerDetails().getServerName()));

                            if (ping.getNode() instanceof BetaNode) {
                                BetaNode betaNode = (BetaNode) ping.getNode();
                                String betaId = betaNode.getDeploymentId();

                                if (!clusterMap.get(serverIP).getBetaNodes().containsKey(betaId))
                                    clusterMap.get(serverIP).getBetaNodes().put(betaId, betaNode);
                            } else {
                                WorkerNode workerNode = (WorkerNode) ping.getNode();
                                String betaId = workerNode.getParentDeploymentId();
                                String workerId = workerNode.getDeploymentId();

                                if(!clusterMap.get(serverIP).getBetaNodes().get(betaId).getLiveNodes().containsKey(workerId))
                                    clusterMap.get(serverIP).getBetaNodes().get(betaId).getLiveNodes().put(workerId, workerNode);
                            }

                            return clusterMap;
                        })
                        .subscribe(clustermap -> {
                            vertx.eventBus().send(this.deploymentID(), json.getJsonString(clustermap));
                        });
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