package com.lcm.app.data.entity;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:36
 * Desc:
 * *****************************************************************
 */

public class ZHStoriesBean {


    /**
     * title : 你去香港购物，我去香港看海
     * ga_prefix : 082119
     * images : ["https://pic4.zhimg.com/v2-0c374b9af000170902ad852f3fe68dff.jpg"]
     * multipic : true
     * type : 0
     * id : 9581544
     */

    private String title;
    private String ga_prefix;
    private boolean multipic;
    private int type;
    private int id;
    private List<String> images;

    @Override
    public String toString() {
        return "ZHStoriesBean{" +
                "title='" + title + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", multipic=" + multipic +
                ", type=" + type +
                ", id=" + id +
                ", images=" + images +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
