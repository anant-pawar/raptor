package com.worker;


import io.vertx.core.eventbus.Message;
import org.raptor.core.nodes.workers.AbstractWriterNode;

/**
 * Created by Anant on 20-08-2015.
 */
public class DataWriter extends AbstractWriterNode {

    @Override
    public void write(Message message) {
        System.out.println((String) message.body());
    }
}
