package com.suning.crowdfunding.domain;

import java.io.Serializable;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/1 19:26
 * @desc
 */
public class Role implements Serializable {
    private Integer id;
    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
