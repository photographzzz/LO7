package com.photograph.lo7.controller;

import com.photograph.lo7.controller.impl.CommentArticleController;
import com.photograph.lo7.entity.Comment;

import java.util.List;

import io.reactivex.Observable;

public interface ICommentController {
    public static CommentArticleController getCommentArticleController(){
        return CommentArticleController.INSTANCE;
    }

    public Observable<List<Comment>> getCommentsByVisitableId(Integer visitableId);

    public Observable<Comment> commentVisitable(Integer visitableId,Integer userId,String content);
}
