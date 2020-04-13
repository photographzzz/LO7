package com.photograph.lo7.service.impl;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.httpsender.entity.Response;
import com.photograph.lo7.service.IFollowerService;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class FollowerServiceImpl implements IFollowerService {
    @Override
    public Observable<List<Friend>> getAllFollow(int userId) {
        return RxHttp.get("/follower/all_follow")
                .add("userId", userId)
                .asResponseList(Friend.class);
    }

    @Override
    public Observable<List<Friend>> getAllFollower(int userId) {
        return RxHttp.get("/follower/all_follower")
                .add("userId", userId)
                .asResponseList(Friend.class);
    }

    @Override
    public Observable follow(int friendId) {
        return RxHttp.postForm("/follower/follow_friend")
                .add("friendId", friendId)
                .asResponse(Response.class);
    }

    @Override
    public Observable unfollow(int friendId) {
        return RxHttp.postForm("/follower/unfollow_friend")
                .add("friendId", friendId)
                .asResponse(Response.class);
    }

    // 检查是否已关注另一用户
    @Override
    public Observable checkIsFollow(int friendId) {
        return RxHttp.get("/follow/is_follow")
                .add("friendId", friendId)
                .asResponse(Response.class);
    }

    @Override
    public Observable checkIsFollower(int friendId) {
        return RxHttp.get("/follow/is_follower")
                .add("friendId", friendId)
                .asResponse(Response.class);
    }
}
