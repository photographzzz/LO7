package com.photograph.lo7.service;

import com.photograph.lo7.entity.Visitable;

import java.util.List;

import io.reactivex.Observable;

public interface IStarService {
    // 收藏文章，返回文章收藏数
    public Observable<Integer> star( Integer objectId);

    public Observable<Integer> unstar(Integer objectId);

    // 判断是否收藏文章，返回收藏与否
    public Observable<Boolean> hasStar( Integer objectId);

    public Observable<Integer> getStarCountOfObject(Integer objectId);

    public Observable<Integer> getStarCountOfUser(Integer userId);

    public <E extends Visitable> Observable<List<E>> getAllStarObjectOfUser(Integer userId, Class<E> clazz);
}
