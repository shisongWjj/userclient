package com.ss.example.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * RpcInvocationHandler
 *
 * @author shisong
 * @date 2020/6/22
 */
public class RpcInvocationHandler implements InvocationHandler {

    private String ip;
    private int port;

    public RpcInvocationHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcTransport rpcTransport = new RpcTransport(ip,port);

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);
        rpcRequest.setTypes(method.getParameterTypes());

        return rpcTransport.send(rpcRequest);
    }
}
