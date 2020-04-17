package com.photograph.lo7.service.impl;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;
import com.photograph.lo7.service.IUserService;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class UserServiceImpl implements IUserService {

    @Override
    public Observable<User> login(String username, String password) {
        return RxHttp.get("user/login")
                .add("username", username)
                .add("password", password)
                .asResponse(User.class);
    }

    @Override
    public Observable<User> register(String username, String password, Integer gender, String email, String phone) {
        return RxHttp.postForm("user/register")
                .add("username", username)
                .add("password", password)
                .add("gender", gender)
                .add("email", email)
                .add("phone", phone)
                .asResponse(User.class);
    }

    @Override
    public Observable resetPassword(String password) {
        return RxHttp.postForm("user/reset_password")
                .add("password", password)
                .asString();
    }



    @Override
    public Observable<String> updateUsername(String username) {
        return RxHttp.postForm("user/update_username")
                .add("username", username)
                .asResponse(String.class);
    }

    @Override
    public Observable<String> updateBio(String bio) {
        return RxHttp.postForm("user/update_bio")
                .add("bio", bio)
                .asResponse(String.class);
    }

    @Override
    public Observable<String> updateEmail(String email) {
        return RxHttp.postForm("user/update_email")
                .add("email", email)
                .asResponse(String.class);
    }

    @Override
    public Observable<String> updatePhone(String phone) {
        return RxHttp.postForm("user/update_phone")
                .add("phone", phone)
                .asResponse(String.class);
    }

    @Override
    public Observable<Integer> updateGender(int gender) {
        return RxHttp.postForm("user/update_gender")
                .add("gender", gender)
                .asResponse(Integer.class);
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
