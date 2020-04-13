package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Article;
import com.photograph.lo7.service.IArticleServiceImpl;
import com.photograph.lo7.service.impl.ArticleServiceImpl;

import java.util.List;

import io.reactivex.Observable;

public class ArticleController {
    IArticleServiceImpl articleService;
    private ArticleController() {
        articleService = new ArticleServiceImpl();
    }

    public static ArticleController getInstance() {
        return ArticleController.Inner.instance;
    }

    private static class Inner {
        private static final ArticleController instance = new ArticleController();
    }


    public Observable<List<Article>> getAllArticles() {
        return articleService.getAllArticles();
    }

    public Observable<List<Article>> getVersionArticles(){
        return articleService.getVersionArticles();
    }
    public Observable<List<Article>> getMatchArticles(){
        return articleService.getMatchArticles();
    }

    public Observable<List<Article>> getStrategyArticles(){
        return articleService.getStrategyArticles();
    }
}
