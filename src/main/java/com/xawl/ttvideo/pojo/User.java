package com.xawl.ttvideo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer uid;
    private String account;
    private String pass;
    private String img;
    private Double balence;
    private Boolean isvip;
    private Integer age;
    private Integer gender;
    private Boolean firstlogin;
    private String phone;
    private Date intime;
    private String name;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", img='" + img + '\'' +
                ", balence=" + balence +
                ", isvip=" + isvip +
                ", age=" + age +
                ", gender=" + gender +
                ", firstlogin=" + firstlogin +
                ", phone='" + phone + '\'' +
                ", intime=" + intime +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public Double getBalence() {
        return balence;
    }

    public void setBalence(Double balence) {
        this.balence = balence;
    }


    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public Boolean getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(Boolean firstlogin) {
        this.firstlogin = firstlogin;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

}
