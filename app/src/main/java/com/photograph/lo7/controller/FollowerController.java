package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.httpsender.entity.Response;
import com.photograph.lo7.service.IFollowerService;
import com.photograph.lo7.service.impl.FollowerServiceImpl;

import java.util.List;

import io.reactivex.Observable;

public class FollowerController {
    private IFollowerService followerService;

    private FollowerController() {
        followerService = new FollowerServiceImpl();
    }

    public static FollowerController getInstance() {
        return FollowerController.Inner.instance;
    }

    private static class Inner {
        private static final FollowerController instance = new FollowerController();
    }


    public Observable<List<Friend>> getAllFollow(int userId){
        return followerService.getAllFollow(userId);
    }

    public Observable<List<Friend>> getAllFollower(int userId){
        return followerService.getAllFollower(userId);
    }

    public Observable<Integer>  follow(int friendId){
        return followerService.follow(friendId);
    }

    public Observable<Integer>  unfollow(int friendId){
        return followerService.unfollow(friendId);
    }


    public Observable<Boolean> checkIsFollow(int friendId) {
        return followerService.checkIsFollow(friendId);
    }

    public Observable<Boolean> checkIsFollower(int friendId){
        return followerService.checkIsFollower(friendId);
    }
}
