package com.photograph.lo7.controller;

import com.photograph.lo7.controller.impl.StarArticleController;
import com.photograph.lo7.controller.impl.StarMomentController;
import com.photograph.lo7.controller.impl.StarVideoController;
import com.photograph.lo7.entity.Visitable;

import java.util.List;

import io.reactivex.Observable;

public interface IStarController {
    static IStarController getStarArticleController() {
        return StarArticleController.INSTANCE;
    }

    static IStarController getStarMomentController() {
        return StarMomentController.INSTANCE;
    }

    static IStarController getStarVideoController() {
        return StarVideoController.INSTANCE;
    }

    Observable<Integer> star(Integer visitableId);

    Observable<Integer> unstar(Integer visitableId);

    // 判断是否收藏文章，返回收藏与否
    Observable<Boolean> hasStar(Integer visitableId);

    Observable<Integer> getStarCountOfVisitable(Integer visitableId);

    Observable<Integer> getStarCountOfUser(Integer userId);

    <E extends Visitable> Observable<List<E>> getAllStarVisitableOfUser(Integer userId, Class<E> clazz);

}
