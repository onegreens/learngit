package com.cl.learnSpring.learnIoc.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Locale;

/**
 * Created by cl on 2017/8/18.
 *
 * 使用XStream别名
 *
 * 三种情况：
 * 类别名
 * 类成员别名
 * 类成员作为属性别名
 */
public class XStreamAliasSample {
    private static XStream xStream;

    static {
        //创建XStream实例并指定一个xml解析器
        //如果不指定，则会采用默认的XML Pull Parser
        xStream = new XStream(new DomDriver());

        //1，设置类别名，默认当前类名称加上包名
        xStream.alias("LoginLog",LoginLog.class);
        xStream.alias("User",User.class);

        //2.设置类成员别名
        xStream.aliasField("id",User.class,"userId");

        //3.把LoginLog的userId属性视为xml属性，默认为xml的元素
        xStream.aliasAttribute(LoginLog.class,"userId","id");
        xStream.useAttributeFor(LoginLog.class,"userId");

        //4. 去掉集合类型生成xml的父节点，即忽略xml中<logs></logs>标记
        xStream.addImplicitCollection(User.class,"logs");

        //注册自定义转换器
        xStream.registerConverter(new DateConverter(Locale.getDefault()));
    }

    /**
     * java对象转换为xml
     * @throws FileNotFoundException
     */
    public static void objectToXML() throws FileNotFoundException {
        //1. 获取转换的User对象实例
        User user = XStreamSample.getUser();

        //2. 实例化一个文件输出流
        //此时的文件路径是指当前项目下的 与src同级
        FileOutputStream outputStream = new FileOutputStream("out/XStreamAliasSample.xml");

        //3. 将User对象转换为XML，并保存到指定文件
        xStream.toXML(user, outputStream);

    }

    /**
     * xml转换为java对象
     * @throws FileNotFoundException
     */
    public static void XMLToObject() throws FileNotFoundException {
        //实例化一个文件输入流
        FileInputStream fileInputStream = new FileInputStream("out/XStreamAliasSample.xml");
        //将xml文件输入流转换为对象
        User user = (User) xStream.fromXML(fileInputStream);
        for (LoginLog loginLog : user.getLoginLogList()){
            System.out.println(loginLog.toString());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        objectToXML();
    }
}
