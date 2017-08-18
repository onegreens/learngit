package com.cl.learnSpring.learnIoc.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

import java.io.*;
import java.util.Locale;
import java.util.Random;

/**
 * Created by cl on 2017/8/18.
 * <p>
 * XStream流化对象来处理XML序列化及反序列化
 */
public class ObjectXStreamSample {
    private static XStream xStream;

    static {
        //创建XStream实例并指定一个xml解析器
        xStream = new XStream(new DomDriver());
    }

    /**
     * java对象转换为xml
     *
     * @throws FileNotFoundException
     */
    public static void objectToXML() throws IOException {
        //1. 获取转换的User对象实例
        User user = XStreamSample.getUser();

        //2.创建一个PrintWriter对象用于输出
        PrintWriter pw = new PrintWriter("out/ObjectXStreamSample.xml");

        //3.选用一个HierarchicalStreamWriter的实现类来创建输出
        PrettyPrintWriter ppw = new PrettyPrintWriter(pw);

        //4.创建对象输出流
        ObjectOutputStream o = xStream.createObjectOutputStream(ppw);
        o.writeObject(user);
        o.close();

    }

    /**
     * xml转换为java对象
     *
     * @throws FileNotFoundException
     */
    public static void XMLToObject() throws IOException, ClassNotFoundException {
        //通过对象流进行输入操作
        FileReader reader = new FileReader("out/ObjectXStreamSample.xml");
        BufferedReader bufferedReader = new BufferedReader(reader);

        //创建对象输入流
        ObjectInputStream inputStream = xStream.createObjectInputStream(bufferedReader);

        //将xml文件输入流转换为对象
        User user = (User) inputStream.readObject();
        for (LoginLog loginLog : user.getLoginLogList()) {
            System.out.println(loginLog.toString());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        XMLToObject();
    }
}
