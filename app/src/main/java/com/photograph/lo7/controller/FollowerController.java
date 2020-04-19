package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.service.IFollowerService;
import com.photograph.lo7.service.impl.FollowerServiceImpl;

import java.util.List;

import io.reactivex.Observable;

public enum FollowerController {
    INSTANCE;
    private IFollowerService followerService = new FollowerServiceImpl();

    public Observable<List<Friend>> getAllFollow(int userId) {
        return followerService.getAllFollow(userId);
    }

    public Observable<List<Friend>> getAllFollower(int userId) {
        return followerService.getAllFollower(userId);
    }

    public Observable<Integer> follow(int friendId) {
        return followerService.follow(friendId);
    }

    public Observable<Integer> unfollow(int friendId) {
        return followerService.unfollow(friendId);
    }


    public Observable<Boolean> checkIsFollow(int friendId) {
        return followerService.checkIsFollow(friendId);
    }

    public Observable<Boolean> checkIsFollower(int friendId) {
        return followerService.checkIsFollower(friendId);
    }
}
