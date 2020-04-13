package com.photograph.lo7.service.impl;

import com.photograph.lo7.common.ArticleConst;
import com.photograph.lo7.entity.Article;
import com.photograph.lo7.service.IArticleServiceImpl;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class ArticleServiceImpl implements IArticleServiceImpl {
    @Override
    public Observable<List<Article>> getAllArticles() {
        return RxHttp.get("/article/all")
                .asResponseList(Article.class);
    }

    @Override
    public Observable<List<Article>> getVersionArticles() {
        return getArticlesByType(ArticleConst.VERSION);
    }

    @Override
    public Observable<List<Article>> getMatchArticles() {
        return getArticlesByType(ArticleConst.MATCH);
    }

    @Override
    public Observable<List<Article>> getStrategyArticles() {
        return getArticlesByType(ArticleConst.STRATEGY);
    }

    @Override
    public Observable<List<Article>> getArticlesByType(int type) {
        return RxHttp.get("/article/type")
                .add("type",type)
                .asResponseList(Article.class);
    }
}
