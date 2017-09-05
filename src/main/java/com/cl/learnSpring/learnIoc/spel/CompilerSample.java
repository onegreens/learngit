package com.cl.learnSpring.learnIoc.spel;

import com.cl.learnSpring.userManage.entity.UserPo;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by cl on 2017/9/1.
 * SpEL编译器
 *
 * 怎么感觉像是上下文设定对象 表达式设定方法 然后调用  和反射机制有点类似
 *
 * SpelCompilerMode.IMMEDIATE：立即启用编译
 * SpelCompilerMode.OFF：不启用编译
 * SpelCompilerMode.MIXED：混合模式
 *
 */
public class CompilerSample {
    public static void main(String[] args) {
        UserPo userPo = new UserPo();
        //1.创建解析配置；配置解析类，并指定编译模式和类加载器
        SpelParserConfiguration configuration = new SpelParserConfiguration(
                SpelCompilerMode.IMMEDIATE,
                CompilerSample.class.getClassLoader()
        );

        //2，创建解析器
        SpelExpressionParser parser = new SpelExpressionParser(configuration);

        //3.创建取值上下文
        EvaluationContext context = new StandardEvaluationContext(userPo);

        //4.表达式
        String expression = "isVipMember('tom') && isVipMember('jony')";

        //5.解析表达式
        SpelExpression spelExpression = parser.parseRaw(expression);
        System.out.println(spelExpression.getValue(context));
        System.out.println(spelExpression.getValue(context));
    }
}
