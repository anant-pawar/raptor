package com.worker;

import io.vertx.core.eventbus.Message;
import org.raptor.core.nodes.workers.AbstractReaderNode;

/**
 * Created by Anant on 16-07-2015.
 */
public class DataReader extends AbstractReaderNode {
    @Override
    public void read() {
        process("Hello");
    }
}
