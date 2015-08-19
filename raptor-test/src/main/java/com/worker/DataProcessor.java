package com.worker;

import io.vertx.core.eventbus.Message;
import org.raptor.core.nodes.workers.AbstractProcessorNode;

/**
 * Created by Anant on 20-08-2015.
 */
public class DataProcessor extends AbstractProcessorNode {
    @Override
    public void process(Message message) {

        String data = (String) message.body() + "World";

        this.write(data);
    }
}
