package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Friend;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum FollowerController {
    INSTANCE;
    public Observable<List<Friend>> getAllFollow(int userId) {
        return RxHttp.get("/follower/all_follow")
                .add("userId", userId)
                .asResponseList(Friend.class);
    }

    public Observable<List<Friend>> getAllFollower(int userId) {
        return RxHttp.get("/follower/all_follower")
                .add("userId", userId)
                .asResponseList(Friend.class);
    }

    public Observable<Integer> follow(int friendId) {
        return RxHttp.postForm("/follower/follow_friend")
                .add("friendId", friendId)
                .asResponse(Integer.class);
    }

    public Observable<Integer>  unfollow(int friendId) {
        return RxHttp.deleteForm("/follower/unfollow_friend")
                .add("friendId", friendId)
                .asResponse(Integer.class);
    }

    // 检查是否已关注另一用户
    public Observable<Boolean> checkIsFollow(int friendId) {
        return RxHttp.get("/follower/is_follow")
                .add("friendId", friendId)
                .asResponse(Boolean.class);
    }

    public Observable<Boolean> checkIsFollower(int friendId) {
        return RxHttp.get("/follower/is_follower")
                .add("friendId", friendId)
                .asResponse(Boolean.class);
    }
}
