package com.photograph.lo7.service.impl;

import android.content.Context;
import android.content.Intent;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.entity.Response;
import com.photograph.lo7.service.IUserService;
import com.photograph.lo7.ui.activities.HomeActivity;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class UserServiceImpl implements IUserService {


    @Override
    public Observable resetPassword(String password) {
        return RxHttp.get("user/reset_password")
                .add("password", password)
                .asResponse(Response.class);
    }

    @Override
    public Observable<User> login(String username, String password) {
        return RxHttp.get("/user/login?")
                .add("username", username)
                .add("password", password)
                .asResponse(User.class);
    }

    @Override
    public Observable<User> register(String username, String password, Integer gender, String email, String phone) {
        return RxHttp.get("user/register")
                .add("username", username)
                .add("password", password)
                .add("gender", gender)
                .add("email", email)
                .add("phone", phone)
                .asResponse(User.class);
    }

    @Override
    public Observable updateUsername(String username) {
        return RxHttp.get("user/update_username")
                .add("username", username)
                .asResponse(Response.class);
    }

    @Override
    public Observable updateBio(String bio) {
        return RxHttp.get("user/update_bio")
                .add("bio", bio)
                .asResponse(Response.class);
    }

    @Override
    public Observable updateEmail(String email) {
        return RxHttp.get("user/update_email")
                .add("email", email)
                .asResponse(Response.class);
    }

    @Override
    public Observable updatePhone(String phone) {
        return RxHttp.get("user/update_phone")
                .add("phone", phone)
                .asResponse(Response.class);
    }

    @Override
    public Observable updateGender(int gender) {
        return RxHttp.get("user/update_gender")
                .add("gender", gender)
                .asResponse(Response.class);
    }

    @Override
    public Observable<Friend> getFriendProfile(int friendId) {
        return RxHttp.get("/user/friend_profile")
                .add("friendId", friendId)
                .asResponse(Friend.class);

    }

    @Override
    public void exit() {

    }

}
