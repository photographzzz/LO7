package com.photograph.lo7.controller;

import com.google.gson.Gson;
import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum UserController {
    INSTANCE;

    public Observable<User> login(String username, String password) {
        return RxHttp.get("user/login")
                .add("username", username)
                .add("password", password)
                .asResponse(User.class);
    }

    public Observable<User> register(String username, String password, Integer gender, String email, String phone) {
        return RxHttp.postForm("user/register")
                .add("username", username)
                .add("password", password)
                .add("gender", gender)
                .add("email", email)
                .add("phone", phone)
                .asResponse(User.class);
    }

    public Observable resetPassword(String password) {
        return RxHttp.postForm("user/reset_password")
                .add("password", password)
                .asString();
    }



    public Observable<String> updateUsername(String username) {
        return RxHttp.postForm("user/update_username")
                .add("username", username)
                .asResponse(String.class);
    }

    public Observable<String> updateBio(String bio) {
        return RxHttp.postForm("user/update_bio")
                .add("bio", bio)
                .asResponse(String.class);
    }

    public Observable<String> updateEmail(String email) {
        return RxHttp.postForm("user/update_email")
                .add("email", email)
                .asResponse(String.class);
    }

    public Observable<String> updatePhone(String phone) {
        return RxHttp.postForm("user/update_phone")
                .add("phone", phone)
                .asResponse(String.class);
    }

    public Observable<Integer> updateGender(int gender) {
        return RxHttp.postForm("user/update_gender")
                .add("gender", gender)
                .asResponse(Integer.class);
    }

    public Observable<Friend> getFriendProfile(int friendId) {
        return RxHttp.get("/user/friend")
                .add("friendId", friendId)
                .asResponse(Friend.class);

    }

    public Observable<List<Friend>> getMultiFriendProfile(List<Integer> friendIds) {
        return RxHttp.postJson("user/multi_friend")
                .addAll(new Gson().toJson(friendIds))
                .asResponseList(Friend.class);
    }

    public void exit() {

    }}
