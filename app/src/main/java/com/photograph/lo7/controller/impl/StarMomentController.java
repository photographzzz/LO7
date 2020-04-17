package com.photograph.lo7.controller.impl;

import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.service.IStarService;
import com.photograph.lo7.service.impl.StarArticleService;

import java.util.List;

import io.reactivex.Observable;

public class StarMomentController implements IStarController {
    private IStarService starArticleService;

    private StarMomentController() {
        starArticleService = new StarArticleService();
    }

    public static StarMomentController getInstance() {
        return StarMomentController.Inner.instance;
    }

    private static class Inner {
        private static final StarMomentController instance = new StarMomentController();
    }

    @Override
    public Observable<Integer> star(Integer articleId) {
        return starArticleService.star(articleId);
    }

    @Override
    public Observable<Integer> unstar(Integer articleId) {
        return starArticleService.unstar(articleId);
    }

    @Override
    public Observable<Boolean> hasStar(Integer articleId) {
        return starArticleService.hasStar(articleId);
    }

    @Override
    public Observable<Integer> getStarCountOfObject(Integer articleId) {
        return starArticleService.getStarCountOfObject(articleId);
    }

    @Override
    public Observable<Integer> getStarCountOfUser(Integer userId) {
        return starArticleService.getStarCountOfUser(userId);
    }

    @Override
    public <E extends Visitable> Observable<List<E>> getAllStarObjectOfUser(Integer userId, Class<E> clazz) {
        return starArticleService.getAllStarObjectOfUser(userId, clazz);
    }
}