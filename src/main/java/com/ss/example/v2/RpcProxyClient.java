package com.ss.example.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RpcProxyClient
 *
 * @author shisong
 * @date 2020/6/24
 */
public class RpcProxyClient {

    public static <T> T proxy(Class<T> interfaceCls , String ip,int port){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RpcInvocationHandler(ip,port));
    }


}
