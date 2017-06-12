package com.lcm.app.data.entity;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午2:14
 * Desc:
 * *****************************************************************
 */

public class DailyBean {

    private String _id;
    private String content;
    private String created_at;
    private String pulishedAt;
    private String rand_id;
    private String title;
    private String updated_at;

    @Override
    public String toString() {
        return "DailyBean{" +
                "_id='" + _id + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", pulishedAt='" + pulishedAt + '\'' +
                ", rand_id='" + rand_id + '\'' +
                ", title='" + title + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPulishedAt() {
        return pulishedAt;
    }

    public void setPulishedAt(String pulishedAt) {
        this.pulishedAt = pulishedAt;
    }

    public String getRand_id() {
        return rand_id;
    }

    public void setRand_id(String rand_id) {
        this.rand_id = rand_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
