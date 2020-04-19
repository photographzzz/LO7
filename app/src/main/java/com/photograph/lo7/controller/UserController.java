package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;
import com.photograph.lo7.service.IUserService;
import com.photograph.lo7.service.impl.UserServiceImpl;

import java.util.List;

import io.reactivex.Observable;

public enum UserController {
    INSTANCE;
    private IUserService userService = new UserServiceImpl();


    public Observable<User> login(String username, String password) {
        return userService.login(username, password);
    }

    public Observable<Friend> getFriendProfile(int friendId) {
        return userService.getFriendProfile(friendId);
    }

    public Observable<List<Friend>> getMultiFriendProfile(List<Integer> friendId) {
        return userService.getMultiFriendProfile(friendId);
    }

    public Observable<User> register(String username, String password, Integer gender, String email, String phone) {
        return userService.register(username, password, gender, email, phone);
    }

    public Observable<String> updateUsername(String username) {
        return userService.updateUsername(username);
    }

    public Observable<String> updateBio(String bio) {
        return userService.updateBio(bio);
    }

    public Observable<String> updateEmail(String email) {
        return userService.updateEmail(email);
    }

    public Observable<String> updatePhone(String phone) {
        return userService.updatePhone(phone);
    }

    public Observable<Integer> updateGender(int gender) {
        return userService.updateGender(gender);
    }

    public Observable resetPassword(String password) {
        return userService.resetPassword(password);
    }
}
