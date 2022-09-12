package com.rq.chapter12;

import android.telephony.mbms.MbmsErrors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        DiskClassLoader diskClassLoader = new DiskClassLoader("D:\\lib");

        try {
            Class c = diskClassLoader.loadClass("com.example.Jobs");
            if(c != null){
                try {
                    Object obj = c.newInstance();
                    System.out.println(obj.getClass().getClassLoader());
                    Method method = c.getDeclaredMethod("say", null);
                    method.invoke(obj, null);
                } catch ( InstantiationException
                        | IllegalAccessException
                        | NoSuchMethodException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
