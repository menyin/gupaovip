package com.gupaoedu.vip.distributed.myrmi.client;

import com.gupaoedu.vip.distributed.myrmi.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpTransport {
    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getByName(host), port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
               /* objectInputStream.close();
                objectOutputStream.close();*/
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
