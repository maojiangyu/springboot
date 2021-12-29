package com.example.demo.Response;

public class AnnouncementResponse{
    private String name;
    private String describeA;
    private long CID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribeA() {
        return describeA;
    }

    public void setDescribeA(String describeA) {
        this.describeA = describeA;
    }

    public long getCID() {
        return CID;
    }

    public void setCID(long CID) {
        this.CID = CID;
    }
}
