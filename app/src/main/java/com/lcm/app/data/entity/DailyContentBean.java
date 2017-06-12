package com.lcm.app.data.entity;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午4:44
 * Desc:
 * *****************************************************************
 */

public class DailyContentBean {

    private String type;
    private String url;
    private String src;
    private String text;


    @Override
    public String toString() {
        return "DailyContentBean{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", src='" + src + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
