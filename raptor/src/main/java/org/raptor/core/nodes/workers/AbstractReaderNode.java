package org.raptor.core.nodes.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.raptor.json.GsonJSONImpl;
import org.raptor.json.IJSON;
import org.raptor.model.Setting;

/**
 * Created by Anant on 11-07-2015.
 */
public abstract class AbstractReaderNode extends AbstractWorkerNode{

    public void start()
    {
        System.out.println("Started : " + this.getClass().toString());

        this.init();
        this.read();
    }

    public abstract void read();

    public <T> void process(T message)
    {
        for(String receiverId: setting.getSendTo()) {
            vertx.eventBus().send(setting.getId() + receiverId, message);
        }
    }
}
