package com.xawl.ttvideo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue
    private Integer tid;
    private String tname;
    private String introduce;
    private String phone;

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", introduce='" + introduce + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Teacher() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }


    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

}
