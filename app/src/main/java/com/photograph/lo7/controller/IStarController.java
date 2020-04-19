package com.photograph.lo7.controller;

import com.photograph.lo7.controller.impl.StarArticleController;
import com.photograph.lo7.controller.impl.StarMomentController;
import com.photograph.lo7.controller.impl.StarVideoController;
import com.photograph.lo7.entity.Visitable;

import java.util.List;

import io.reactivex.Observable;

public interface IStarController {
    public static IStarController getStarArticleController() {
        return StarArticleController.INSTANCE;
    }

    public static IStarController getStarMomentController() {
        return StarMomentController.INSTANCE;
    }

    public static IStarController getStarVideoController() {
        return StarVideoController.INSTANCE;
    }

    public Observable<Integer> star(Integer objectId);

    public Observable<Integer> unstar(Integer objectId);

    // 判断是否收藏文章，返回收藏与否
    public Observable<Boolean> hasStar( Integer objectId);

    public Observable<Integer> getStarCountOfObject(Integer objectId);

    public Observable<Integer> getStarCountOfUser(Integer userId);

    public <E extends Visitable> Observable<List<E>> getAllStarObjectOfUser(Integer userId, Class<E> clazz);



}
