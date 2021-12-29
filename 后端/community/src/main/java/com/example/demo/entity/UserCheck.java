package com.example.demo.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class UserCheck {
    private String SName;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    private long ID;

    private boolean isUser;

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }


    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

}
