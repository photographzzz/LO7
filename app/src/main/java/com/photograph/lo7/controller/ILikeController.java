package com.photograph.lo7.controller;

import com.photograph.lo7.controller.impl.LikeArticleController;

import io.reactivex.Observable;

public interface ILikeController {
    public static ILikeController getLikeArticleController() {
        return LikeArticleController.INSTANCE;
    }

  /*  public static ILikeController getLikeMomentController() {
        return LikeMomentController.getInstance();
    }

    public static ILikeController getLikeVideoController() {
        return LikeVideoController.getInstance();
    }
*/
    public Observable<Integer> like(Integer objectId);

    public Observable<Integer> unlike(Integer objectId);

    // 判断是否收藏文章，返回收藏与否
    public Observable<Boolean> hasLike(Integer objectId);

    public Observable<Integer> getLikeCountOfObject(Integer objectId);

    public Observable<Integer> getLikeCountOfUser(Integer userId);

}
