package com.photograph.lo7.service;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;

import io.reactivex.Observable;

public interface IUserService {
    public Observable<User> login(String username, String password);

    public Observable<User> register(String username, String password, Integer gender, String email, String phone);

    public Observable updateUsername(String username);

    public Observable updateBio(String bio);

    public Observable updateEmail(String email);

    public Observable updatePhone(String phone);

    public Observable updateGender(int gender);

    public Observable resetPassword(String password);

    public Observable<Friend> getFriendProfile(int friendId);

    public void exit();
}
