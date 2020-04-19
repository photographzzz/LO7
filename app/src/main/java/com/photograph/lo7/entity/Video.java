package com.photograph.lo7.entity;

public class Video extends Visitable {
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public static void main(String[] args) {
        Visitable visitable = new Video();
    }
}
