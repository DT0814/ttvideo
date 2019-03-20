package com.xawl.ttvideo.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xawl.ttvideo.utils.CustomDateSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "resources")
public class Resources {
    @Id
    @GeneratedValue
    private Integer rid;
    private Integer cid;
    private String url;
    private String rname;
    private Integer rank;
    private Date intime;
    private Integer haveCourseware;
    private String coursewareUrl;

    public Resources() {
    }

    public Resources(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "rid=" + rid +
                ", cid=" + cid +
                ", url='" + url + '\'' +
                ", rname='" + rname + '\'' +
                ", rank=" + rank +
                ", intime=" + intime +
                ", haveCourseware=" + haveCourseware +
                ", coursewareUrl='" + coursewareUrl + '\'' +
                '}';
    }

    public String getCoursewareUrl() {
        return coursewareUrl;
    }

    public void setCoursewareUrl(String coursewareUrl) {
        this.coursewareUrl = coursewareUrl;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }


    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }


    public Integer getHaveCourseware() {
        return haveCourseware;
    }

    public void setHaveCourseware(Integer haveCourseware) {
        this.haveCourseware = haveCourseware;
    }

}
