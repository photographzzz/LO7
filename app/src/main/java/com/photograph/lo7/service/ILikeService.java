package com.photograph.lo7.service;

import io.reactivex.Observable;

public interface ILikeService {
    // 收藏文章，返回文章收藏数
    public Observable<Integer> like(Integer objectId);

    public Observable<Integer> unlike(Integer objectId);

    // 判断是否收藏文章，返回收藏与否
    public Observable<Boolean> hasLike(Integer objectId);

    public Observable<Integer> getLikeCountOfObject(Integer objectId);

    public Observable<Integer> getLikeCountOfUser(Integer userId);
}
