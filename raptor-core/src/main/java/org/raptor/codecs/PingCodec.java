package org.raptor.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.raptor.model.Ping;

import java.io.*;

/**
 * Created by Anant on 10-11-2015.
 */
public class PingCodec implements MessageCodec<Ping, Ping> {
    private final String NAME = "PING_ENCODER";
    private final static Logger logger = LoggerFactory.getLogger(PingCodec.class);

    @Override
    public void encodeToWire(Buffer buffer, Ping ping) {
        byte[] bytes;

        ObjectOutput objectOutput = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(ping);

            bytes = byteArrayOutputStream.toByteArray();
            buffer.appendInt(bytes.length);
            buffer.appendBytes(bytes);
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
        }
    }

    @Override
    public Ping decodeFromWire(int pos, Buffer buffer) {
        int length;
        byte[] bytes;

        Ping ping = null;
        ObjectInput objectInput = null;
        ByteArrayInputStream byteArrayInputStream = null;

        try {
            length = buffer.getInt(pos);
            pos += 4;
            bytes = buffer.getBytes(pos, pos + length);
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInput = new ObjectInputStream(byteArrayInputStream);
            ping = (Ping) objectInput.readObject();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            logger.error(classNotFoundException.getMessage());
        } finally {
            try {
                if (byteArrayInputStream != null)
                    byteArrayInputStream.close();
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
        }
        return ping;
    }

    @Override
    public Ping transform(Ping ping) {
        return ping;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}