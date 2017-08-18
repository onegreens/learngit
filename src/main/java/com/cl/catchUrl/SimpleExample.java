package com.cl.catchUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by cl on 2017/8/18.
 */
public class SimpleExample {

    public static void main(String[] args) {
//        printHref(getUrl("https://www.baidu.com/"));
        Save_Html("http://v.youku.com/v_show/id_XMjgxMTY4NTE1Ng==.html","id_XMjgxMTY4NTE1Ng");
//        Get_Localhtml("out/catch");
    }

    public static void main1(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new CatchThread());
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdown();
    }

    public static class CatchThread implements Runnable{

        @Override
        public void run() {
            printHref(getUrl("https://www.baidu.com/"));
        }
    }

    /**
     * 解析本地的html
     *
     * @param path
     */
    public static void Get_Localhtml(String path) {
        File file = new File(path);
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            try {
                if (array[i].isFile()) {
                    //文件名字
                    System.out.println("正在解析网址：" + array[i].getName());

                    //下面开始解析本地的html
                    Document doc = Jsoup.parse(array[i], "UTF-8");
                    printHref(doc);
                }
            } catch (Exception e) {
                System.out.println("网址：" + array[i].getName() + "解析出错");
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 根据url抓取信息
     *
     * @param url
     */
    public static Document getUrl(String url) {
        Document documnt = null;
        try {
            documnt = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documnt;
    }

    /**
     * 处理document内容
     *
     * @param document
     */
    public static void printHref(Document document) {
        if (document==null)
            return;
        Elements elements = document.select("a");
        for (Element element : elements) {
            String href = element.attr("href");
            System.out.println(href);
            if (isHref(href)){
                Save_Html(href,getFileName());
            }
        }
    }

    public static boolean isHref(String href){
        return href.matches("http.*");
    }

    public static String getFileName(){
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * 通过url获取对应页面，并讲其信息存储为本地文件
     *
     * @param url
     */
    public static void Save_Html(String url,String fileName) {
        try {
            File dest = new File("out/catch/" + fileName+".html");
            //接收字节输入流
            InputStream is;
            //字节输出流
            FileOutputStream fos = new FileOutputStream(dest);

            URL temp = new URL(url);
            is = temp.openStream();

            //为字节输入流加缓冲
            BufferedInputStream bis = new BufferedInputStream(is);
            //为字节输出流加缓冲
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int length;

            byte[] bytes = new byte[1024 * 20];
            while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
            }

            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
