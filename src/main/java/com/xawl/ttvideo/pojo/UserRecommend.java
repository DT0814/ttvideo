package com.xawl.ttvideo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_recommend")
public class UserRecommend {
    @Id
    private Integer uid;
    private String cids;

    public UserRecommend() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

}
