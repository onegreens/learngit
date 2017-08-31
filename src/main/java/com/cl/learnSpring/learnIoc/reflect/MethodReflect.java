package com.cl.learnSpring.learnIoc.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cl on 2017/8/28.
 * 利用反射机制完成方法的加载
 */
public class MethodReflect {

    public void getTest1() {
        System.out.println("test1");
    }

    public void getTest2() {
        System.out.println("test2");
    }

    public void getTest3() {
        System.out.println("test3");
    }

    public static void main(String[] args) throws Exception {
        MethodReflect.getClassMethod("1");
    }

    static void getClassMethod(String method) throws Exception{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.cl.learnSpring.learnIoc.reflect.MethodReflect");
        Method setBrand = clazz.getMethod("getTest"+method);
        setBrand.invoke(new MethodReflect());
    }
}
