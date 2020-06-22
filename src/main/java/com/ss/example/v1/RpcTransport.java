package com.ss.example.v1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * RpcTransport
 *
 * @author shisong
 * @date 2020/6/22
 */
public class RpcTransport {

    private String ip;
    private int port;

    public RpcTransport(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private Socket newSocket(String ip, int port) throws IOException {
        return new Socket(ip,port);
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            socket = newSocket(ip, port);

            oos = new ObjectOutputStream( socket.getOutputStream());
            oos.writeObject(rpcRequest);
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
