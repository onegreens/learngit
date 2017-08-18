package com.cl.learnSpring.learnIoc.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by cl on 2017/8/18.
 * 演示使用XStream进行对象与XML之间的互换
 */
public class XStreamSample {
    private static XStream xStream;

    static {
        //创建XStream实例并指定一个xml解析器
        //如果不指定，则会采用默认的XML Pull Parser
        xStream = new XStream(new DomDriver());
    }


    /**
     * 将集合中的每个对象持久化到文件中
     *
     * 实现类：
     * XmlArrayList
     * XmlMap
     * XmlSet
     */
    public void persist(){
        //创建持久化策略
        File file = new File("out");
        PersistenceStrategy strategy = new FilePersistenceStrategy(file);
        //持久化集合对象
        List list = new XmlArrayList(strategy);
        //添加持久化对象
        list.add(getUser());
    }

    static User getUser() {
        LoginLog loginLog = new LoginLog();
        loginLog.setIp("170.16.0.103");
        loginLog.setLoginDate(new Date());
        User user = new User();
        user.setUserId(1);
        user.setUserName("stream");
        user.addLoginLog(loginLog);
        return user;
    }

    /**
     * java对象转换为xml
     * @throws FileNotFoundException
     */
    public static void objectToXML() throws FileNotFoundException {
        //1. 获取转换的User对象实例
        User user = getUser();

        //2. 实例化一个文件输出流
        //此时的文件路径是指当前项目下的 与src同级
        FileOutputStream outputStream = new FileOutputStream("out/XStreamSample.xml");

        //3. 将User对象转换为XML，并保存到指定文件
        xStream.toXML(user, outputStream);

    }

    /**
     * xml转换为java对象
     * @throws FileNotFoundException
     */
    public static void XMLToObject() throws FileNotFoundException {
        //实例化一个文件输入流
        FileInputStream fileInputStream = new FileInputStream("out/XStreamSample.xml");
        //将xml文件输入流转换为对象
        User user = (User) xStream.fromXML(fileInputStream);
        for (LoginLog loginLog : user.getLoginLogList()){
            System.out.println(loginLog.toString());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        XMLToObject();
    }


}
