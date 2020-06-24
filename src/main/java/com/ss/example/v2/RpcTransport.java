package com.ss.example.v2;

import com.ss.example.v1.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * RpcTransport
 *
 * @author shisong
 * @date 2020/6/24
 */
public class RpcTransport {



    private Socket newSocket(String ip,int port) throws IOException {
        return new Socket(ip,port);
    }


    public Object send(RpcRequest rpcRequest,String ip,int port){
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            socket = this.newSocket(ip,port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rpcRequest);
            oos.flush();
            //传输 数据结束
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
            }
        }
        return null;
    }

}
