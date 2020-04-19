package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Article;
import com.photograph.lo7.service.IArticleService;
import com.photograph.lo7.service.impl.ArticleService;

import java.util.List;

import io.reactivex.Observable;

public enum ArticleController {
    INSTANCE;

    private IArticleService articleService = new ArticleService();

    public Observable<List<Article>> getAllArticles() {
        return articleService.getAllArticles();
    }

    public Observable<Article> getArticleById(int articleId) {
        return articleService.getArticleById(articleId);
    }

    public Observable<List<Article>> getVersionArticles() {
        return articleService.getVersionArticles();
    }

    public Observable<List<Article>> getMatchArticles() {
        return articleService.getMatchArticles();
    }

    public Observable<List<Article>> getStrategyArticles() {
        return articleService.getStrategyArticles();
    }

    public Observable<String> visitArticle(int articleId) {
        return articleService.visitArticle(articleId);
    }
}
