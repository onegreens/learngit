package com.cl.learnSpring.learnIoc.spel;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by cl on 2017/9/1.
 * 实例：脚本实现动态函数
 *
 * 使用Rhino快速实现一个动态求和函数sum，只需要在脚本引擎中注册一个标准的JavaScript
 * 就可以在Java应用上下文中调用注册的JavaScript函数
 *
 */
public class ScriptSample {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
       //1.创建脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
       //2.创建脚本引擎
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String scriptText = "function sum(a,b){return a+b}";
       //3.注册脚本
        engine.eval(scriptText);
       //4.调用引擎
        Invocable invocable = (Invocable) engine;
       //5.设置参数；函数名称，参数列表
        Object result = invocable.invokeFunction("sum",100,99);
        System.out.println(result);
    }
}
