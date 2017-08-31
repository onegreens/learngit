package com.cl.catchUrl;

import org.jsoup.nodes.Document;

/**
 * Created by cl on 2017/8/21.
 */
public class DocumentEntity {
    private Document document;
    private boolean isCatch;
    private String url;

    public boolean isCatch() {
        return isCatch;
    }

    public void setCatch(boolean aCatch) {
        isCatch = aCatch;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
