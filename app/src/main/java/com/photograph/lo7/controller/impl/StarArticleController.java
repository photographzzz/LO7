package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.entity.Visitable;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum StarArticleController implements IStarController {
    INSTANCE;
    @Override
    public Observable<Integer> star( Integer articleId) {
        return RxHttp.postForm("sa")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unstar(Integer articleId) {
        return RxHttp.deleteForm("sa")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasStar(Integer articleId) {
        return RxHttp.get("sa")
                .add("articleId", articleId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getStarCountOfVisitable(Integer articleId) {
        return RxHttp.get("sa/article_count")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> getStarCountOfUser(Integer userId) {
        return RxHttp.get("sa/user_count")
                .add("userId", userId)
                .asResponse(Integer.class);
    }

    @Override
    public <E extends Visitable> Observable<List<E>> getAllStarVisitableOfUser(Integer userId, Class<E> clazz) {
        return RxHttp.get("/sa/all_star_article")
                .add("userId", userId)
                .asResponseList(clazz);
    }
}
