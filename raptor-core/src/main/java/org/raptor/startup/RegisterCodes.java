package org.raptor.startup;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.codecs.PingCodec;
import org.raptor.model.Ping;

/**
 * Created by Anant on 22-12-2015.
 */
public class RegisterCodes {
    private final Logger logger  = LoggerFactory.getLogger(getClass());
    private MessageCodec<Ping, Ping> pingCodec = new PingCodec();

    public void register(Vertx vertx) {
        try {
            vertx.eventBus().registerDefaultCodec(Ping.class, pingCodec);
        }catch (Exception exception)
        {
            logger.error(exception.getMessage());
        }
    }
}
