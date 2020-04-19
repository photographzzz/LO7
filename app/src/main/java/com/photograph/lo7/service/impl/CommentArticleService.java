package com.photograph.lo7.service.impl;

import com.photograph.lo7.entity.Comment;
import com.photograph.lo7.service.ICommentService;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class CommentArticleService implements ICommentService {
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
