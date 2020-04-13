package com.photograph.lo7.entity;

public class ArticleModel {
    public static class InstanceHolder {
        static final ArticleModel INSTANCE = new ArticleModel();
    }

    private ArticleModel() {

    }

    public static ArticleModel getInstance() {
        return InstanceHolder.INSTANCE;
    }


}
