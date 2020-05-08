package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.entity.Comment;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum CommentMomentController implements ICommentController {
    INSTANCE;

    @Override
    public Observable<List<Comment>> getCommentsByVisitableId(Integer momentId) {
        return RxHttp.get("comment/moment")
                .add("momentId", momentId)
                .asResponseList(Comment.class);
    }

    @Override
    public Observable<Comment> commentVisitable(Integer momentId, Integer userId, String content) {
        return RxHttp.postForm("comment/moment")
                .add("momentId", momentId)
                .add("content", content)
                .asResponse(Comment.class);
    }
}
