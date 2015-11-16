package org.raptor.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import org.raptor.model.Ping;

import java.io.*;

/**
 * Created by Anant on 10-11-2015.
 */
public class PingCodec implements MessageCodec<Ping, Ping> {

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
        } catch (IOException ex) {
            // ignore close exception
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException ex) {
                // ignore close exception
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
        } catch (IOException ex) {
            // ignore close exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayInputStream != null)
                    byteArrayInputStream.close();
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
            } catch (IOException ex) {
                // ignore close exception
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
        return "PING_ENCODER";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}