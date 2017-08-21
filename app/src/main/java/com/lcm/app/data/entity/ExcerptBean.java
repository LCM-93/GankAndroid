package com.lcm.app.data.entity;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午3:53
 * Desc:
 * *****************************************************************
 */

public class ExcerptBean {

    private String img;
    private String title;

    private String updateTime;

    private String url;

    private String note;

    private String cat;


    @Override
    public String toString() {
        return "ExcerptBean{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", url='" + url + '\'' +
                ", note='" + note + '\'' +
                ", cat='" + cat + '\'' +
                '}';
    }

    public ExcerptBean() {
    }

    public ExcerptBean(String img, String title, String updateTime, String url, String note, String cat) {
        this.img = img;
        this.title = title;
        this.updateTime = updateTime;
        this.url = url;
        this.note = note;
        this.cat = cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
