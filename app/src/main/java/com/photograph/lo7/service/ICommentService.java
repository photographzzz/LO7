package com.photograph.lo7.service;

import com.photograph.lo7.entity.Comment;

import java.util.List;

import io.reactivex.Observable;

public interface ICommentService {
    public Observable<List<Comment>> getCommentsByVisitableId(Integer visitableId);

    public Observable<Comment> commentVisitable(Integer visitableId,Integer userId,String content);
}
