package com.cl.catchUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by cl on 2017/8/21.
 */

public class Catch360m {
    static Set set = new TreeSet();

    public static void main(String[] args) {
        String url = null;
        for (int i = 1; i < 3; i++) {
            url = "http://www.360kan.com/dianying/list?rank=rankhot&cat=all&area=all&act=all&year=all&pageno=" + i;
            new Catch360m().handlerUrl(url);
        }

    }

    String adjustUrl(String url) {
        if (url.startsWith("/m"))
            return "http://www.360kan.com" + url;
        if (url.contains("http://www.360kan.com/dianying/list"))
            return url;
        if (url.contains("www.360kan.com/m/")) {
            if (url.startsWith("//")) {
                return "http:" + url;
            } else if (url.startsWith("http://")) {
                return url;
            }
        }

        return null;
    }

    void handlerUrl(String url) {
        String url_ = adjustUrl(url);
        if (url_ == null)
            return;
        int begin = set.size();
        set.add(url);
        if (begin == set.size())
            return;
        System.out.println("");
        System.out.println("===================================");
        System.out.println("正在搜索：" + url_);
        System.out.println("===================================");
        System.out.println("");
        begin(url_);

    }

    void begin(String url) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setUrl(url);
        isCatch(documentEntity);
        getDocument(documentEntity);
        handlerMessage(documentEntity);
    }

    void handlerMessage(DocumentEntity documentEntity) {
        //判断是否为360影视
        if (isNeedCatch(documentEntity.getUrl())) {
            if (documentEntity.isCatch()) {
                catchMessage(documentEntity);
            }
            //对其他链接进行捕获
            catchOtherHref(documentEntity.getDocument());

        }
    }

    void catchOtherHref(Document document) {
        Elements elements1 = document.select("a.js-tongjic");
        findHref(elements1);
    }

    void findHref(Elements elements) {
        if (elements.size() == 0)
            return;
        for (Element element : elements) {
            String href = element.attr("href");
            handlerUrl(href);
        }
    }

    void catchMessage(DocumentEntity documentEntity) {
        Document document = documentEntity.getDocument();

        String title = getTitle(document.title());
        System.out.println(" title： " + title);
        Elements imgs = document.getElementsByAttributeValue("monitor-desc", "大海报");

        Elements zd = document.select("div.top-list-zd");
        Elements bofangyuan = zd.select("a");
        for (Element element : bofangyuan) {
            System.out.println("播放源："+element.attr("data-daochu"));
            String href = element.attr("href");
            if (href.contains("http"))
            System.out.println("播放链接："+href );
        }
        System.out.println("海报url : " + imgs.select("img[src]").first().attr("src"));

        Elements divs = document.getElementsByAttributeValue("monitor-desc", "详细信息");
        System.out.println("电影名称：" + divs.select("h1").first().text());
        System.out.println("类型： " + divs.select("p.tag").first().text());
        Elements items = divs.select("div.item-wrap");
        Elements pitems = items.select("p.item");
        for (Element element : pitems) {
            System.out.println(element.text());
        }
        System.out.println("短简介：" + divs.select("p.js-open-wrap").first().text());
        System.out.println("长简介：" + divs.select("p.js-close-wrap").first().text());

    }

    String getTitle(String title) {
        return title.split("-")[0];
    }

    /**
     * 对url进行分析 判断其是否需要进行信息捕捉
     *
     * @param documentEntity
     */
    void isCatch(DocumentEntity documentEntity) {
        String url = documentEntity.getUrl();
        if (url.startsWith("http://www.360kan.com/m")) {
            documentEntity.setCatch(true);
        } else {
            documentEntity.setCatch(false);
        }
    }

    boolean isNeedCatch(String url) {
        if (url.contains("www.360kan")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取document
     *
     * @param documentEntity
     */
    void getDocument(DocumentEntity documentEntity) {
        Document document = null;
        try {
            document = Jsoup.connect(documentEntity.getUrl()).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        documentEntity.setDocument(document);
    }


}
