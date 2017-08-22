package com.lcm.app.data.entity;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:37
 * Desc:
 * *****************************************************************
 */

public class ZHNewsBean {

    private String date;

    private List<ZHStoriesBean> stories;

    private List<ZHTopStoriesBean> top_stories;

    @Override
    public String toString() {
        return "ZHNewsBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }

    public List<ZHTopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<ZHTopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZHStoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<ZHStoriesBean> stories) {
        this.stories = stories;
    }
}
