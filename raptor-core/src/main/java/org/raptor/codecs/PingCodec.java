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
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(ping);
            bytes = bos.toByteArray();
        }catch (IOException ex) {
            // ignore close exception
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        buffer.appendInt(bytes.length);
        buffer.appendBytes(bytes);

    }

    @Override
    public Ping decodeFromWire(int pos, Buffer buffer) {
        Ping ping = null;
        int length = buffer.getInt(pos);
        pos += 4;
        byte[] bytes = buffer.getBytes(pos, pos + length);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            ping = (Ping)in.readObject();
        }catch (IOException ex) {
            // ignore close exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return  ping;
    }

    @Override
    public Ping transform(Ping ping) {
        return ping;
    }

    @Override
    public String name() {
        return "mypojoencoder2";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}