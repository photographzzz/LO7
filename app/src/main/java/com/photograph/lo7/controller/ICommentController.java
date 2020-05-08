package com.photograph.lo7.controller;

import com.photograph.lo7.controller.impl.CommentArticleController;
import com.photograph.lo7.controller.impl.CommentMomentController;
import com.photograph.lo7.controller.impl.CommentVideoController;
import com.photograph.lo7.entity.Comment;

import java.util.List;

import io.reactivex.Observable;

public interface ICommentController {
    public static CommentArticleController getCommentArticleController(){
        return CommentArticleController.INSTANCE;
    }

    public static CommentMomentController getCommentMomentController(){
        return CommentMomentController.INSTANCE;
    }

    public static CommentVideoController getCommentVideoController(){
        return CommentVideoController.INSTANCE;
    }

    Observable<List<Comment>> getCommentsByVisitableId(Integer visitableId);

    Observable<Comment> commentVisitable(Integer visitableId, Integer userId, String content);
}
