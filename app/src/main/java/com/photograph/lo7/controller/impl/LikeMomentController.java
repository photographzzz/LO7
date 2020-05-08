package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ILikeController;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum LikeMomentController implements ILikeController {
    INSTANCE;

    @Override
    public Observable<Integer> like(Integer momentId) {
        return RxHttp.postForm("lm")
                .add("momentId", momentId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unlike(Integer momentId) {
        return RxHttp.deleteForm("lm")
                .add("momentId", momentId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasLike(Integer momentId) {
        return RxHttp.get("lm")
                .add("momentId", momentId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getLikeCountOfVisitable(Integer momentId) {
        return RxHttp.get("lm/count")
                .add("momentId", momentId)
                .asResponse(Integer.class);
    }
}
