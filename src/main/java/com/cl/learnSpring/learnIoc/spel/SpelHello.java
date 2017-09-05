package com.cl.learnSpring.learnIoc.spel;

import com.cl.learnSpring.userManage.entity.UserPo;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by cl on 2017/9/1.
 * spel简易实例
 *
 * 在单独使用SpEL时，需要创建一个ExpressionParser解析器，并提供一个EvaluationContext求值上下文
 */
public class SpelHello {
    public static void main(String[] args) {
        new SpelHello().method4();
    }

    /**
     * 基础用法
     */
    void method1(){
        //1.创建spel表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        //2.对表达式进行解析
        Expression expression = parser.parseExpression("'hello'+'world'");
        String message = (String) expression.getValue();
        System.out.println(message);
    }

    /**
     * 调用字符串的concat()方法
     */
    void method2(){
        //1.创建spel表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        //2.对表达式进行解析
        //**修改点
        Expression expression = parser.parseExpression("'helloWorld'.concat('')");
        String message = (String) expression.getValue();
        System.out.println(message);
    }

    /**
     * 修改显示类型转换
     */
    void method3(){
        //1.创建spel表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        //2.对表达式进行解析
        Expression expression = parser.parseExpression("'helloWorld'.concat('')");
        //**修改点
        String message = expression.getValue(String.class);
        System.out.println(message);
    }

    /**
     * 针对特定实例对象进行求值
     */
    void method4(){
        UserPo userPo = new UserPo();
        userPo.setUsername("tom");
        ExpressionParser parser = new SpelExpressionParser();
        //指定一个根对象为求值目标对象
        EvaluationContext context = new StandardEvaluationContext(userPo);
        //在求值内部使用反射机制从注册对象中获取相应的属性值
        String userName = (String) parser.parseExpression("username").getValue(context);
        System.out.println(userName);

    }
}
