package com.rq.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyBuyer implements InvocationHandler {

    // Object 引用，该引用指向被代理类
    private final Object obj;
    public DynamicProxyBuyer(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);
        if(method.getName().equals("buy")){
            System.out.println("购买……");
        }
        return result;
    }
}
