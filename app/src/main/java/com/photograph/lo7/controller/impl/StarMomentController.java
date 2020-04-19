package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.entity.Visitable;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum StarMomentController implements IStarController {
    INSTANCE;
    @Override
    public Observable<Integer> star( Integer momentId) {
        return RxHttp.postForm("sm/star_moment")
                .add("momentId", momentId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> unstar(Integer momentId) {
        return RxHttp.postForm("sm/unstar_moment")
                .add("momentId", momentId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Boolean> hasStar(Integer momentId) {
        return RxHttp.get("sm/has_star_moment")
                .add("momentId", momentId)
                .asResponse(Boolean.class);
    }

    @Override
    public Observable<Integer> getStarCountOfVisitable(Integer momentId) {
        return RxHttp.get("sm/moment_star_count")
                .add("momentId", momentId)
                .asResponse(Integer.class);
    }

    @Override
    public Observable<Integer> getStarCountOfUser(Integer userId) {
        return RxHttp.get("sm/user_star_count")
                .add("userId", userId)
                .asResponse(Integer.class);
    }

    @Override
    public <E extends Visitable> Observable<List<E>> getAllStarVisitableOfUser(Integer userId, Class<E> clazz) {
        return RxHttp.get("/sm/all_star_moment")
                .add("userId", userId)
                .asResponseList(clazz);
    }
}
