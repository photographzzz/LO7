package com.photograph.lo7.controller;

import com.photograph.lo7.controller.impl.LikeArticleController;
import com.photograph.lo7.controller.impl.LikeMomentController;
import com.photograph.lo7.controller.impl.LikeVideoController;

import io.reactivex.Observable;

public interface ILikeController {
    static ILikeController getLikeArticleController() {
        return LikeArticleController.INSTANCE;
    }

    static ILikeController getLikeMomentController() {
        return LikeMomentController.INSTANCE;
    }

    static ILikeController getLikeVideoController() {
        return LikeVideoController.INSTANCE;
    }

    Observable<Integer> like(Integer visitableId);

    Observable<Integer> unlike(Integer visitableId);

    // 判断是否收藏文章，返回收藏与否
    Observable<Boolean> hasLike(Integer visitableId);

    Observable<Integer> getLikeCountOfVisitable(Integer visitableId);

}
