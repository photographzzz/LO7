package com.photograph.lo7.service;

import com.photograph.lo7.entity.Article;

import java.util.List;

import io.reactivex.Observable;

public interface IArticleServiceImpl {

    public Observable<Article> getArticleById(int articleId);

    public Observable<List<Article>> getAllArticles();

    public Observable<List<Article>> getVersionArticles();

    public Observable<List<Article>> getMatchArticles();

    public Observable<List<Article>> getStrategyArticles();

    public Observable<List<Article>> getArticlesByType(int type);
}
