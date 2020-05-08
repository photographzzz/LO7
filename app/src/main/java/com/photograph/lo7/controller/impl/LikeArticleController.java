package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ILikeController;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum LikeArticleController implements ILikeController {
    INSTANCE;

    @Override
    public Observable<Integer> like(Integer articleId) {
        return RxHttp.postForm("la")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unlike(Integer articleId) {
        return RxHttp.deleteForm("la")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasLike(Integer articleId) {
        return RxHttp.get("la")
                .add("articleId", articleId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getLikeCountOfVisitable(Integer articleId) {
        return RxHttp.get("la/count")
                .add("articleId", articleId)
                .asResponse(Integer.class);
    }
}
