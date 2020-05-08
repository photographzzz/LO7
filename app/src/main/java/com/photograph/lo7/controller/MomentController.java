package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Moment;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum MomentController {
    INSTANCE;

    public Observable<List<Moment>> getAllMoments(Integer pageNum) {
        return RxHttp.get("/moment/all")
                .add("pageNum", pageNum)
                .asResponseList(Moment.class);
    }

    public Observable<Moment> getMomentById(int momentId) {
        return RxHttp.get("/moment/moment_id")
                .add("momentId", momentId)
                .asResponse(Moment.class);
    }

    public Observable<List<Moment>> getMomentsByAuthorId(Integer pageNum) {
        return RxHttp.get("/moment/author_id")
                .add("pageNum", pageNum)
                .asResponseList(Moment.class);
    }


    public Observable<Moment> postMoment(String content, String imagePath) {
        return RxHttp.postForm("moment")
                .add("content", content)
                .add("imagePath",imagePath)
                .asResponse(Moment.class);
    }

    public Observable<String> visitMoment(int momentId) {
        return RxHttp.postForm("/moment/visit")
                .add("momentId", momentId)
                .asString();
    }
}