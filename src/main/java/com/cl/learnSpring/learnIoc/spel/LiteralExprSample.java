package com.cl.learnSpring.learnIoc.spel;

import com.cl.learnSpring.userManage.entity.UserPo;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by cl on 2017/9/1.
 */
public class LiteralExprSample {

    public static void main(String[] args) {
        UserPo userPo = new UserPo();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(userPo);
        //设置变量
        context.setVariable("newName", "tome===");
        //取变量值
        parser.parseExpression("username=#newName").getValue(context);
        System.out.println(userPo.getUsername());
    }

    public static void main7(String[] args) {
        //实例化构造方法
        ExpressionParser parser = new SpelExpressionParser();
        UserPo userPo = parser.parseExpression("new com.cl.learnSpring.userManage.entity.UserPo(1,'tom')").getValue(UserPo.class);
        System.out.println(userPo);
    }

    public static void main6(String[] args) {
        //T：类型操作符，从类路径加载指定类名称（全限定名）的Class对象
        //操作表达式："T(全限定类名)"
        ExpressionParser parser = new SpelExpressionParser();
        Class str = parser.parseExpression("T(java.lang.String)").getValue(Class.class);
        System.out.println(str == String.class);
    }

    /**
     * 复制
     *
     * @param args
     */
    public static void main5(String[] args) {
        UserPo userPo = new UserPo();
        userPo.setNickname("tom");
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(userPo);
        parser.parseExpression("nickname").setValue(context, "jcke");
        System.out.println(userPo.getNickname());
        parser.parseExpression("nickname='anunksf'").getValue(context);
        System.out.println(userPo.getNickname());


    }

    public static void main4(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        //调用string方法
        String str = parser.parseExpression("'spring spel'.substring(7)").getValue(String.class);

        //涉及实例的需要context
        //调用私有方法则会发生错误
        //静态方法无差别
        LiteralExprSample sample = new LiteralExprSample();
        EvaluationContext context = new StandardEvaluationContext(sample);
        boolean value = parser.parseExpression("getValue(5)").getValue(context, Boolean.class);

    }

    public boolean getValue(int i) {
        return i > 0;
    }

    /**
     * 数组、集合类型解析
     *
     * @param args
     */
    public static void main3(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();

        int[] array1 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue(context);
        int[] array2 = (int[]) parser.parseExpression("new int[2][3]").getValue(context);
//不支持多为数组初始化
//        int[] array3 = (int[]) parser.parseExpression("new int[2][3]{1,2,3}{4,5,6}").getValue(context);

        List list1 = (List) parser.parseExpression("{1,2,3}").getValue(context);
        List list2 = (List) parser.parseExpression("{'a','s'},{'c','d'}").getValue(context);

        Map map1 = (Map) parser.parseExpression("{userName:'tom',credits:100}").getValue(context);

        String i1 = (String) parser.parseExpression("array[0]").getValue(context);
        String i2 = (String) parser.parseExpression("list[0]").getValue(context);
        String i3 = (String) parser.parseExpression("map['i']").getValue(context);


    }

    /**
     * 对象属性解析
     *
     * @param args
     */
    public static void main2(String[] args) {
        UserPo userPo = new UserPo();
        userPo.setNickname("zhangsan");

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(userPo);

        String nickName = (String) parser.parseExpression("nickname").getValue(context);
        System.out.println(nickName);

        //嵌套属性 示例
        String nickNames = (String) parser.parseExpression("nickname.name").getValue(context);

    }

    /**
     * 文本字符解析
     *
     * @param args
     */
    public static void main1(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        //1.解析字符串
        String str = (String) parser.parseExpression("\"helloWorld\"").getValue();

        //2。解析双精度浮点型
        double num = (Double) parser.parseExpression("3.01113e+12").getValue();

        //解析整型
        int max = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();

        //解析整型布尔值
        boolean aTrue = (Boolean) parser.parseExpression("true").getValue();


        System.out.println(str + num + max + aTrue);

    }
}
