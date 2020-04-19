package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ILikeController;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum LikeArticleController implements ILikeController {
    INSTANCE;

    @Override
    public Observable<Integer> like(Integer articleId) {
        return RxHttp.postForm("la/like_article")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unlike(Integer articleId) {
        return RxHttp.postForm("la/unlike_article")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasLike(Integer articleId) {
        return RxHttp.get("la/has_like_article")
                .add("articleId", articleId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getLikeCountOfVisitable(Integer articleId) {
        return RxHttp.get("la/article_like_count")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> getLikeCountOfUser(Integer userId) {
        return RxHttp.get("la/user_like_count")
                .add("userId", userId)
                .asResponse(Integer.class);
    }
}
