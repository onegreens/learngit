package com.cl.learnSpring.learnIoc.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Created by cl on 2017/8/30.
 * java对象和JSON的相互转换工作
 * 需要添加依赖jar包
 *
 * 关键点：创建实例时，传递一个xml到json映射转换的驱动器
 */
public class XStreamJSONSample {
    private static XStream xStream;

    static {
        //创建XStream实例并指定一个xml解析器
        //如果不指定，则会采用默认的XML Pull Parser
        xStream = new XStream(new DomDriver());
    }

    /**
     * 连续的没有分割的JSON串
     * @throws FileNotFoundException
     */
    public static void toJsonByJettisonMapperdXmlDriver() throws FileNotFoundException {
        User user = XStreamSample.getUser();
        FileOutputStream outputStream = new FileOutputStream("out/JettisonMapperdSample");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
        //创建实例时，传递一个xml到json映射转换的驱动器
        xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("user",User.class);
        xStream.toXML(user,writer);
    }

    /**
     * 格式化良好的JSON串
     * @throws FileNotFoundException
     */
    public static void toJsonByJsonHierarchicalStreamDriver() throws FileNotFoundException {
        User user = XStreamSample.getUser();
        FileOutputStream outputStream = new FileOutputStream("out/JsonByJsonHierarchicalSample");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
        //创建实例时，传递一个xml到json映射转换的驱动器
        xStream = new XStream(new JsonHierarchicalStreamDriver());
        xStream.alias("user", User.class);
        xStream.toXML(user, writer);
    }

    public static void main(String[] args) throws FileNotFoundException {
        XStreamJSONSample.toJsonByJettisonMapperdXmlDriver();
        XStreamJSONSample.toJsonByJsonHierarchicalStreamDriver();
    }
}
