package com.cl.learnSpring.learnIoc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by cl on 2017/8/16.
 * 通过java反射机制以一种简介的方式操控目标类
 *
 * 主要的几个反射类：ClassLoader/Class/Constructor/Method
 */
public class ReflectTest {
    public static Car initByDefaultConst() throws Exception{
        //1.通过类装载器获取car类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.cl.learnSpring.learnIoc.reflect.Car");

        //2.获取类的默认构造器对象并通过他实例化Car
        Constructor constructor = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) constructor.newInstance();

        //3.通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);
        return car;
    }

    public static void main(String[] args) throws Exception {
        Car car = initByDefaultConst();
        System.out.println(car.toString());
    }
}
