package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ILikeController;
import com.photograph.lo7.service.ILikeService;
import com.photograph.lo7.service.impl.LikeArticleServiceImpl;

import io.reactivex.Observable;

public enum LikeArticleController implements ILikeController {
    INSTANCE;

    private ILikeService likeArticleService = new LikeArticleServiceImpl();

    @Override
    public Observable<Integer> like(Integer articleId) {
        return likeArticleService.like(articleId);
    }

    @Override
    public Observable<Integer> unlike(Integer articleId) {
        return likeArticleService.unlike(articleId);
    }

    @Override
    public Observable<Boolean> hasLike(Integer articleId) {
        return likeArticleService.hasLike(articleId);
    }

    @Override
    public Observable<Integer> getLikeCountOfObject(Integer articleId) {
        return likeArticleService.getLikeCountOfObject(articleId);
    }

    @Override
    public Observable<Integer> getLikeCountOfUser(Integer userId) {
        return likeArticleService.getLikeCountOfUser(userId);
    }

}
