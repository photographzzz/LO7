package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Video;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum VideoController {
    INSTANCE;

    public Observable<List<Video>> getAllVideos(Integer pageNum) {
        return RxHttp.get("/video/all")
                .add("pageNum",pageNum)
                .asResponseList(Video.class);
    }

    public Observable<Video> getVideoById(int videoId) {
        return RxHttp.get("/video/video_id")
                .add("videoId", videoId)
                .asResponse(Video.class);
    }

    public Observable<List<Video>> getVideosByAuthorId(Integer pageNum) {
        return RxHttp.get("/video/author_id")
                .add("pageNum",pageNum)
                .asResponseList(Video.class);
    }

    public Observable<Video> postVideo(String title, String videoPath) {
        return RxHttp.postForm("video")
                .add("title", title)
                .add("videoPath",videoPath)
                .asResponse(Video.class);
    }


    public Observable<String> visitVideo(int videoId) {
        return RxHttp.postForm("/video/visit")
                .add("videoId", videoId)
                .asString();
    }
}