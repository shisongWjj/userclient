package com.ss.example.v2;

import com.ss.example.v1.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * RpcInvocationHandler
 *
 * @author shisong
 * @date 2020/6/24
 */
public class RpcInvocationHandler implements InvocationHandler {

    private String ip;

    private int port;

    public RpcInvocationHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setTypes(method.getParameterTypes());
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);

        RpcTransport rpcTransport = new RpcTransport();
        return rpcTransport.send(rpcRequest,ip,port);
    }
}
