package com.photograph.lo7.service;

import com.photograph.lo7.entity.Friend;

import java.util.List;

import io.reactivex.Observable;

public interface IFollowerService {

    public Observable<List<Friend>> getAllFollow(int userId);

    public Observable<List<Friend>> getAllFollower(int userId);

    public Observable<Integer>  follow(int friendId);

    public Observable<Integer>  unfollow( int friendId);

    public Observable<Boolean> checkIsFollow(int friendId);

    public Observable<Boolean> checkIsFollower(int friendId);
}
