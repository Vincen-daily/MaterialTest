package com.example.materialtest;

/**
 * Created by xiecy on 2018/01/24.
 */

public class Pic {

    private String Name;

    private int picId;

    public Pic(String name, int picId) {
        Name = name;
        this.picId = picId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
