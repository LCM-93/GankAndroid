package com.lcm.app.data.entity;

/**
 * Created by qeeniao on 16/10/21.
 */

public class BannerBean {

    /**
     * autokid : 4
     * id : 0
     * bak : http://www.10100000.com/m/Market/2015/customize/indexB.html?WT.mc_id=CXX-DiERXice-355
     * createtime :
     * imagepath : http://oeb52ee0x.bkt.clouddn.com/%E5%B9%B3%E5%AE%89-640-320-2.jpg
     * iostarget :
     * reamrk :
     * sort : 1
     * type :
     * version :
     * is_deleted : 0
     * row_created_at : 2016-10-12 10:58:18
     * row_updated_at : 2016-10-12 20:00:42
     */

    private int autokid;
    private int id;
    private String bak;
    private String createtime;
    private String imagepath;
    private String iostarget;
    private String reamrk;
    private String sort;
    private String type;
    private String version;
    private int is_deleted;
    private String row_created_at;
    private String row_updated_at;

    public int getAutokid() {
        return autokid;
    }

    public void setAutokid(int autokid) {
        this.autokid = autokid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getIostarget() {
        return iostarget;
    }

    public void setIostarget(String iostarget) {
        this.iostarget = iostarget;
    }

    public String getReamrk() {
        return reamrk;
    }

    public void setReamrk(String reamrk) {
        this.reamrk = reamrk;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getRow_created_at() {
        return row_created_at;
    }

    public void setRow_created_at(String row_created_at) {
        this.row_created_at = row_created_at;
    }

    public String getRow_updated_at() {
        return row_updated_at;
    }

    public void setRow_updated_at(String row_updated_at) {

        this.row_updated_at = row_updated_at;
    }


    @Override
    public String toString() {
        return "BannerBean{" +
                "autokid=" + autokid +
                ", id=" + id +
                ", bak='" + bak + '\'' +
                ", createtime='" + createtime + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", iostarget='" + iostarget + '\'' +
                ", reamrk='" + reamrk + '\'' +
                ", sort='" + sort + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", is_deleted=" + is_deleted +
                ", row_created_at='" + row_created_at + '\'' +
                ", row_updated_at='" + row_updated_at + '\'' +
                '}';
    }
}
