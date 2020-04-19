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

    // 相对时间，如"一分钟前"、"昨天"等
    private String relativeDate;

    // 绝对时间
    private String absoluteDate;

    private Boolean hasStar;

    private Boolean hasLike;

    private String authorName;
    private String authorPic;
    private Integer authorFollowCount;
    private Integer authorFollowerCount;
    private Boolean authorHasBeenFollowed;

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

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
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

    public Boolean getHasStar() {
        return hasStar;
    }

    public void setHasStar(Boolean hasStar) {
        this.hasStar = hasStar;
    }

    public Boolean getHasLike() {
        return hasLike;
    }

    public void setHasLike(Boolean hasLike) {
        this.hasLike = hasLike;
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

    public Integer getAuthorFollowCount() {
        return authorFollowCount;
    }

    public void setAuthorFollowCount(Integer authorFollowCount) {
        this.authorFollowCount = authorFollowCount;
    }

    public Integer getAuthorFollowerCount() {
        return authorFollowerCount;
    }

    public void setAuthorFollowerCount(Integer authorFollowerCount) {
        this.authorFollowerCount = authorFollowerCount;
    }

    public Boolean getAuthorHasBeenFollowed() {
        return authorHasBeenFollowed;
    }

    public void setAuthorHasBeenFollowed(Boolean authorHasBeenFollowed) {
        this.authorHasBeenFollowed = authorHasBeenFollowed;
    }
}
