package com.example.demo.entity;

import java.util.Date;

public class showMember extends User{
    private long id;

    private long UCID;

    public long getUCID() {
        return UCID;
    }

    public void setUCID(long UCID) {
        this.UCID = UCID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
