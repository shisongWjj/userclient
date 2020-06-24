package com.ss.example.v2;

import com.ss.example.v1.OrderService;

/**
 * RpcDemo
 *
 * @author shisong
 * @date 2020/6/24
 */
public class RpcDemo {

    public static void main(String[] args) {
        OrderService orderService = null;

        orderService = RpcProxyClient.proxy(OrderService.class,"localhost",8080);

        System.out.println(orderService.queryList());
        System.out.println(orderService.queryInfoById("888888"));
    }

}
