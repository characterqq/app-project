package com.wuxin.appservice.model;

import java.io.Serializable;

import com.wuxin.appservice.util.PageUtil;

public class Role extends PageUtil implements Serializable{
    private Integer rid;

    private String roleName;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}