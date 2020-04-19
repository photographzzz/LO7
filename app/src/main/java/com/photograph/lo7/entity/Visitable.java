package com.photograph.lo7.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.photograph.lo7.BR;

public class Visitable extends BaseObservable {
    private Integer id;

    private Integer authorId;

    private String title;

    private Integer visitCount;

    private Integer commentCount;

    private Integer stars;

    private Integer likes;

    private String relativeDate;

    private String absoluteDate;

    private boolean hasStar;

    private boolean hasLike;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
        notifyPropertyChanged(BR.visitCount);
    }

    @Bindable
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        notifyPropertyChanged(BR.commentCount);
    }

    @Bindable
    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
        notifyPropertyChanged(BR.stars);
    }

    @Bindable
    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
        notifyPropertyChanged(BR.likes);
    }

    public String getRelativeDate() {
        return relativeDate;
    }

    public void setRelativeDate(String relativeDate) {
        this.relativeDate = relativeDate;
    }

    public String getAbsoluteDate() {
        return absoluteDate;
    }

    public void setAbsoluteDate(String absoluteDate) {
        this.absoluteDate = absoluteDate;
    }

    private String authorName;
    private String authorPic;
    private Integer authorFollowCount;
    private Integer authorFollowerCount;
    private Boolean authorHasBeenFollowed;

    @Bindable
    public boolean getHasStar() {
        return hasStar;
    }

    public void setHasStar(boolean hasStar) {
        this.hasStar = hasStar;
        notifyPropertyChanged(BR.hasStar);
    }

    @Bindable
    public boolean getHasLike() {
        return hasLike;
    }

    public void setHasLike(boolean hasLike) {
        this.hasLike = hasLike;
        notifyPropertyChanged(BR.hasLike);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    @Bindable
    public Integer getAuthorFollowCount() {
        return authorFollowCount;
    }

    public void setAuthorFollowCount(Integer authorFollowCount) {
        this.authorFollowCount = authorFollowCount;
        notifyPropertyChanged(BR.authorFollowCount);
    }

    @Bindable
    public Integer getAuthorFollowerCount() {
        return authorFollowerCount;
    }

    public void setAuthorFollowerCount(Integer authorFollowerCount) {
        this.authorFollowerCount = authorFollowerCount;
        notifyPropertyChanged(BR.authorFollowerCount);
    }

    @Bindable
    public Boolean getAuthorHasBeenFollowed() {
        return authorHasBeenFollowed;
    }

    public void setAuthorHasBeenFollowed(Boolean authorHasBeenFollowed) {
        this.authorHasBeenFollowed = authorHasBeenFollowed;
        notifyPropertyChanged(BR.authorHasBeenFollowed);
    }
}
