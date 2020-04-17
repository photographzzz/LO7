package com.photograph.lo7.service;

import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.User;

import io.reactivex.Observable;

public interface IUserService {
    public Observable<User> login(String username, String password);

    public Observable<User> register(String username, String password, Integer gender, String email, String phone);

    public Observable<String> updateUsername(String username);

    public Observable<String> updateBio(String bio);

    public Observable<String> updateEmail(String email);

    public Observable<String> updatePhone(String phone);

    public Observable<Integer> updateGender(int gender);

    public Observable resetPassword(String password);

    public Observable<Friend> getFriendProfile(int friendId);

    public void exit();
}
