package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.entity.Comment;
import com.photograph.lo7.service.ICommentService;
import com.photograph.lo7.service.impl.CommentArticleService;

import java.util.List;

import io.reactivex.Observable;

public enum  CommentArticleController implements ICommentController {
    INSTANCE;


    private ICommentService commentArticleService = new CommentArticleService();
    @Override
    public Observable<List<Comment>> getCommentsByVisitableId(Integer articleId) {
        return commentArticleService.getCommentsByVisitableId(articleId);
    }

    @Override
    public Observable<Comment> commentVisitable(Integer articleId, Integer userId, String content) {
        return commentArticleService.commentVisitable(articleId, userId, content);
    }

}
