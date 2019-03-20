package com.xawl.ttvideo.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xawl.ttvideo.utils.CustomDateSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    private Integer aid;
    private String aname;
    private String account;
    private String phone;
    private String pass;
    private Integer type;
    private Date intime;

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", pass='" + pass + '\'' +
                ", type=" + type +
                ", intime=" + intime +
                '}';
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }


    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
