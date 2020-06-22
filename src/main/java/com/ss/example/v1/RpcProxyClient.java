package com.ss.example.v1;

import java.lang.reflect.Proxy;

/**
 * RpcProxyClient
 *
 * @author shisong
 * @date 2020/6/22
 */
public class RpcProxyClient {


    public static <T> T proxy(Class<T> interfaceCls, String ip, int port){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls},new RpcInvocationHandler(ip,port));
    }

}
