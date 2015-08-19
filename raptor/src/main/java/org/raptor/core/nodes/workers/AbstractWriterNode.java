package org.raptor.core.nodes.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.raptor.json.IJSON;
import org.raptor.model.Setting;

/**
 * Created by Anant on 11-07-2015.
 */
public abstract class AbstractWriterNode extends AbstractWorkerNode {
    public void start()
    {
        System.out.println("Started : " + this.getClass().toString());

        this.init();
        this.read();
    }

    public void read()
    {
        for (String senderNode: setting.getReceiveFrom())
            vertx.eventBus().consumer(senderNode + setting.getId(), message -> {
                this.write(message);
            });
    }

    public abstract void write(Message message);
}