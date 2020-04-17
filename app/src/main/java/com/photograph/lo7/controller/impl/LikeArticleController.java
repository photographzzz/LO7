package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.ILikeController;
import com.photograph.lo7.service.ILikeService;
import com.photograph.lo7.service.impl.LikeArticleServiceImpl;

import io.reactivex.Observable;

public class LikeArticleController implements ILikeController {
    private ILikeService likeArticleService;

    private LikeArticleController() {
        likeArticleService = new LikeArticleServiceImpl();
    }

    public static LikeArticleController getInstance() {
        return LikeArticleController.Inner.instance;
    }

    private static class Inner {
        private static final LikeArticleController instance = new LikeArticleController();
    }

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
