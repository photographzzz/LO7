package com.photograph.lo7.vo;


import android.net.Uri;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;

import com.bumptech.glide.Glide;
import com.photograph.lo7.BR;
import com.photograph.lo7.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserVO extends BaseObservable {
    private Integer id;
    private String username;
    private Integer gender;
    private String email;
    private String phone;
    private String pic;
    private String bio;
    private Integer level;
    private Integer exp;
    private Integer coins;
    private Integer followCount;
    private Integer followerCount;
    private Integer role;


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

    @Bindable
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Bindable
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
        notifyPropertyChanged(BR.bio);
    }

    @Bindable
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }

    @Bindable
    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
        notifyPropertyChanged(BR.exp);
    }

    @Bindable
    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
        notifyPropertyChanged(BR.coins);
        addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
            }
        });
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

    @Bindable
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
        notifyPropertyChanged(BR.role);
    }

    // 与HomeActivity的侧拉导航栏头部CircleImageView保持绑定，当用户pic字段变化时，自动刷新头像
    @BindingAdapter({"url"})
    public static void loadImage(CircleImageView view, String src) {
       Glide.with(view.getContext()).load(Uri.parse(src.replace("image.LO7.com", "192.168.0.102")))
                .placeholder(R.mipmap.ic_launcher).into(view);
    }

}
