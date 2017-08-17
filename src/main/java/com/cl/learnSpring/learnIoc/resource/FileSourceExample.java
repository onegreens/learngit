package com.cl.learnSpring.learnIoc.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by cl on 2017/8/17.
 * 分别通过FileSystemResource和ClassPathResource访问同一个文件资源
 */
public class FileSourceExample {
    public static void main(String[] args) {
        try {
            String filePath = "D:/mySource/123.txt";

            //1. 使用系统文件的方式加载文件
            WritableResource resource1 = new PathResource(filePath);

            //2. 通过类路径的方式加载文件
            Resource resource2 = new ClassPathResource("conf/file1.txt");

            //3.使用writableResource接口写资源文件
            OutputStream stream1 = resource1.getOutputStream();
            stream1.write("使用writableResource接口写资源文件".getBytes());
            stream1.close();

            //4. 使用resource接口读取文件
            InputStream inputStream1 = resource1.getInputStream();
            InputStream inputStream2 = resource2.getInputStream();

            ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream1.read()) != -1)
                bs1.write(i);
            System.out.println(bs1.toString());

            ByteArrayOutputStream bs2 = new ByteArrayOutputStream();
            while ((i = inputStream2.read()) != -1)
                bs2.write(i);
            System.out.println(bs2.toString());

            System.out.println("res1 : " + resource1.getFilename());
            System.out.println("res2 : " + resource2.getFilename());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
