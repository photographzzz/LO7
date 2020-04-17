package com.photograph.lo7.service.impl;

import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.service.IStarService;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class StarArticleService implements IStarService {
    @Override
    public Observable<Integer> star(Integer userId, Integer articleId) {
        return RxHttp.postForm("/sa/star_article")
                .add("userId", userId)
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unstar(Integer userId, Integer articleId) {
        return RxHttp.deleteForm("/sa/unstar_article")
                .add("userId", userId)
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasStar(Integer userId, Integer articleId) {
        return RxHttp.get("/sa/has_star_article")
                .add("userId", userId)
                .add("articleId", articleId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getStarCountOfObject(Integer articleId) {
        return RxHttp.get("/sa/article_star_count")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> getStarCountOfUser(Integer userId) {
        return RxHttp.get("/sa/user_star_count")
                .add("userId", userId)
                .asResponse(Integer.class);
    }

    @Override
    public <E extends Visitable> Observable<List<E>> getAllStarObjectOfUser(Integer userId, Class<E> clazz) {
        return RxHttp.get("/sa/all_star_article")
                .add("userId", userId)
                .asResponseList(clazz);
    }


}
