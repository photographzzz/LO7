package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ILikeController;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum LikeVideoController implements ILikeController {
    INSTANCE;

    @Override
    public Observable<Integer> like(Integer videoId) {
        return RxHttp.postForm("lv")
                .add("videoId", videoId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unlike(Integer videoId) {
        return RxHttp.deleteForm("lv")
                .add("videoId", videoId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasLike(Integer videoId) {
        return RxHttp.get("lv")
                .add("videoId", videoId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getLikeCountOfVisitable(Integer videoId) {
        return RxHttp.get("lv/count")
                .add("videoId", videoId)
                .asResponse(Integer.class);
    }
}
