package com.photograph.lo7.controller;

import com.photograph.lo7.common.ArticleConst;
import com.photograph.lo7.entity.Article;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum ArticleController {
    INSTANCE;

    public Observable<List<Article>> getAllArticles(Integer pageNum) {
        return RxHttp.get("/article/all")
                .add("pageNum",pageNum)
                .asResponseList(Article.class);
    }

    public Observable<Article> getArticleById(int articleId) {
        return RxHttp.get("/article/article_id")
                .add("articleId", articleId)
                .asResponse(Article.class);
    }

    public Observable<List<Article>> getVersionArticles() {
        return getArticlesByType(ArticleConst.VERSION);
    }

    public Observable<List<Article>> getMatchArticles() {
        return getArticlesByType(ArticleConst.MATCH);
    }
    public Observable<List<Article>> getStrategyArticles() {
        return getArticlesByType(ArticleConst.STRATEGY);
    }

    public Observable<List<Article>> getArticlesByType(int type) {
        return RxHttp.get("/article/type")
                .add("type",type)
                .add("pageNum",1)
                .asResponseList(Article.class);
    }

    public Observable<String> visitArticle(int articleId) {
        return RxHttp.postForm("/article/visit")
                .add("articleId", articleId)
                .asString();
    }
}
