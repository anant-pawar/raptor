package org.raptor.core.nodes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Ping;
import org.raptor.model.Server;

import java.net.InetAddress;

/**
 * Created by Anant on 13-12-2015.
 */
public class Node extends AbstractVerticle {
    private final Integer PING_TIME = 1000;

    protected final Logger logger  = LoggerFactory.getLogger(getClass());
    protected final String PING_BUS = "PING_BUS";

    protected IJSON json;
    protected Ping ping;
    protected Server server;

    protected Node()
    {
        json = new GsonJSONImpl();
    }

    protected void init()
    {
        // get host name and host ip
        try {
            server = new Server(
                    InetAddress.getLocalHost().getHostName(),
                    InetAddress.getLocalHost().getHostAddress()
            );
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        ping = new Ping(server);

        // register pinger
        vertx.setPeriodic(PING_TIME, this::ping);

    }

    private void ping(Long id) {
        vertx.eventBus().publish(PING_BUS, ping);
    }
}
