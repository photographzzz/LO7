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
    public Observable<Integer> like(Integer visitableId);

    public Observable<Integer> unlike(Integer visitableId);

    // 判断是否收藏文章，返回收藏与否
    public Observable<Boolean> hasLike(Integer visitableId);

    public Observable<Integer> getLikeCountOfVisitable(Integer visitableId);

    public Observable<Integer> getLikeCountOfUser(Integer userId);

}
