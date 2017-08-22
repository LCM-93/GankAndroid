package com.lcm.app.data.entity;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/22 下午2:39
 * Desc:
 * *****************************************************************
 */

public class ZHInfoBean {


    /**
     * body : aaaaa
     * image_source : Yestone.com 版权图片库
     * title : 能撑得起简单 T 恤的好身材，胸肌毫无疑问是重点
     * image : https://pic2.zhimg.com/v2-d1f55ce7378ef0e5d19b6d68d6a5cdf5.jpg
     * share_url : http://daily.zhihu.com/story/9582205
     * js : ["aaa"]
     * ga_prefix : 082213
     * images : ["https://pic4.zhimg.com/v2-444a094a8e191bbde66285749e449b93.jpg"]
     * type : 0
     * id : 9582205
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    @Override
    public String toString() {
        return "ZHInfoBean{" +
                "body='" + body + '\'' +
                ", image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
