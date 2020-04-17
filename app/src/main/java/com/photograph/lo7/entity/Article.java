package com.photograph.lo7.entity;


public class Article extends Visitable {

    private String preview;

    private Integer type;

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}