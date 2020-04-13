package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;
import com.photograph.lo7.service.IUserService;
import com.photograph.lo7.service.impl.UserServiceImpl;

import io.reactivex.Observable;

public class UserController {
    private IUserService userService;

    private UserController() {
        userService = new UserServiceImpl();
    }

    public static UserController getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        private static final UserController instance = new UserController();
    }


    public Observable<User> login(String username, String password) {
        return userService.login(username, password);
    }


    public Observable<Friend> getFriendProfile(int friendId) {
        return userService.getFriendProfile(friendId);
    }
}
