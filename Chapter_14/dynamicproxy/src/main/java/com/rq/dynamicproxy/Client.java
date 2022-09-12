package com.rq.dynamicproxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args){

        IShop shop = new Buyer();
        // 创建动态代理
        DynamicProxyBuyer mDynamicProxyBuyer = new DynamicProxyBuyer(shop);
        // 创建 ClassLoader
        ClassLoader loader = shop.getClass().getClassLoader();
        // 动态创建代理类
        IShop buyerProxy = (IShop) Proxy.newProxyInstance(loader, new Class[]{IShop.class}, mDynamicProxyBuyer);
        buyerProxy.buy();
    }
}
