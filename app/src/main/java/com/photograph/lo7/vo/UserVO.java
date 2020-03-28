package com.photograph.lo7.vo;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.photograph.lo7.BR;

public class UserVO extends BaseObservable {
    private Integer id;
    private String username;
    @Bindable
    private Integer gender;
    @Bindable
    private String email;
    @Bindable
    private String phone;
    @Bindable
    private String pic;
    @Bindable
    private String bio;
    @Bindable
    private Integer level;
    @Bindable
    private Integer exp;
    @Bindable
    private Integer coins;
    @Bindable
    private Integer followCount;
    @Bindable
    private Integer followerCount;

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
        notifyPropertyChanged(BR.gender);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
        notifyPropertyChanged(BR.bio);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
        notifyPropertyChanged(BR.exp);
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
        notifyPropertyChanged(BR.coins);
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
        notifyPropertyChanged(BR.followCount);
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
        notifyPropertyChanged(BR.followerCount);
    }
}
