﻿package com.heima.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 */
public class User implements Serializable{
    private Integer idrgregrg; // 主键你好啊啊
    private Date vsdvfdbdfbirthday; // 生日ntytyjnyt
    private String fbdfbfdbgender; // 性别童年投入
    private String fdbdfbfdbusername; // 用户名，唯一挺好挺好用
    private String fvfvfpasswordfffvrvrffffffv; // 密码
    private String ffffvfddgsfbgdfgtrsdddddddsfsddffffffffffff;  //cdcddcdbc
    private String bfbdfbfremark; // 备注
    private String bdfbfdbfdbtation; // 状态
    private String tfbfdbfdbfdbbdfbelephone; // 联系电话
    private Set<Role> roles = new HashSet<Role>(0);//对应角色集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
