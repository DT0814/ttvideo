package com.xawl.ttvideo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue
    private Integer cid;
    private String name;
    private Boolean del;

    public Catalog(Integer cid) {
        this.cid = cid;
    }

    public Catalog() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

}
