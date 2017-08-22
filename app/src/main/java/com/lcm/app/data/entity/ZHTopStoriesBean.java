package com.lcm.app.data.entity;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:39
 * Desc:
 * *****************************************************************
 */

public class ZHTopStoriesBean {

    /**
     * image : https://pic3.zhimg.com/v2-794a75e8350c53e36b758247d2dde3ce.jpg
     * type : 0
     * id : 9580831
     * ga_prefix : 082117
     * title : 被猥亵女童被养父领回家，惩罚在「家事」前只得草草收场了？
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public ZHTopStoriesBean(String image, int type, int id, String ga_prefix, String title) {
        this.image = image;
        this.type = type;
        this.id = id;
        this.ga_prefix = ga_prefix;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
