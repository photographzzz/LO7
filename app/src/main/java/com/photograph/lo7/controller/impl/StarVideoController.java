package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.entity.Visitable;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum StarVideoController implements IStarController {
    INSTANCE;
    @Override
    public Observable<Integer> star( Integer videoId) {
        return RxHttp.postForm("sv")
                .add("videoId", videoId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unstar(Integer videoId) {
        return RxHttp.deleteForm("sv")
                .add("videoId", videoId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasStar(Integer videoId) {
        return RxHttp.get("sv")
                .add("videoId", videoId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getStarCountOfVisitable(Integer videoId) {
        return RxHttp.get("sv/video_count")
                .add("videoId", videoId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> getStarCountOfUser(Integer userId) {
        return RxHttp.get("sv/user_count")
                .add("userId", userId)
                .asResponse(Integer.class);
    }

    @Override
    public <E extends Visitable> Observable<List<E>> getAllStarVisitableOfUser(Integer userId, Class<E> clazz) {
        return RxHttp.get("/sv/all_star_video")
                .add("userId", userId)
                .asResponseList(clazz);
    }
}
