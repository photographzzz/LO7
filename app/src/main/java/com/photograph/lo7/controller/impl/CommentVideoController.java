package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.entity.Comment;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum CommentVideoController implements ICommentController {
    INSTANCE;

    @Override
    public Observable<List<Comment>> getCommentsByVisitableId(Integer videoId) {
        return RxHttp.get("comment/video")
                .add("videoId", videoId)
                .asResponseList(Comment.class);
    }

    @Override
    public Observable<Comment> commentVisitable(Integer videoId, Integer userId, String content) {
        return RxHttp.postForm("comment/video")
                .add("videoId", videoId)
                .add("content", content)
                .asResponse(Comment.class);
    }
}
