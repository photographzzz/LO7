package com.photograph.lo7.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.photograph.lo7.BR;

public class Friend extends BaseObservable {
    private Integer id;
    private String username;
    private Integer gender;
    private String pic;
    private String bio;
    private Integer level;
    private Integer followCount;
    private Integer followerCount;
    private Boolean hasBeenFollowed;
    private Boolean beFollower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Bindable
    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
        notifyPropertyChanged(BR.followCount);
    }

    @Bindable
    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
        notifyPropertyChanged(BR.followerCount);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Bindable
    public Boolean getHasBeenFollowed() {
        return hasBeenFollowed;
    }

    public void setHasBeenFollowed(Boolean hasBeenFollowed) {
        this.hasBeenFollowed = hasBeenFollowed;
        notifyPropertyChanged(BR.hasBeenFollowed);
    }

    @Bindable
    public Boolean getBeFollower() {
        return beFollower;
    }

    public void setBeFollower(Boolean beFollower) {
        this.beFollower = beFollower;
        notifyPropertyChanged(BR.beFollower);
    }
}
