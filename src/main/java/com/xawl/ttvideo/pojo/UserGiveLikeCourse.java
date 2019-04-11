package com.xawl.ttvideo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_give_like_course")
public class UserGiveLikeCourse {
    @Id
    @GeneratedValue
    private Integer ugcid;
    private Integer uid;
    private Integer cid;
    private Date intime;


    public Integer getUgcid() {
        return ugcid;
    }

    public void setUgcid(Integer ugcid) {
        this.ugcid = ugcid;
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }


    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

}
