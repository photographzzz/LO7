package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.entity.Comment;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum  CommentArticleController implements ICommentController {
    INSTANCE;

    @Override
    public Observable<List<Comment>> getCommentsByVisitableId(Integer articleId) {
        return RxHttp.get("comment/article")
                .add("articleId", articleId)
                .asResponseList(Comment.class);
    }

    @Override
    public Observable<Comment> commentVisitable(Integer articleId, Integer userId, String content) {
        return RxHttp.postForm("comment/article")
                .add("articleId", articleId)
                .add("content", content)
                .asResponse(Comment.class);
    }
}
