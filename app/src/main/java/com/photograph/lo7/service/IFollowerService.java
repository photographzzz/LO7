package com.photograph.lo7.service;

import com.photograph.lo7.entity.Friend;

import java.util.List;

import io.reactivex.Observable;

public interface IFollowerService {

    public Observable<List<Friend>> getAllFollow(int userId);

    public Observable<List<Friend>> getAllFollower(int userId);

    public Observable follow(int friendId);

    public Observable unfollow( int friendId);

    public Observable checkIsFollow(int friendId);

    public Observable checkIsFollower(int friendId);
}
