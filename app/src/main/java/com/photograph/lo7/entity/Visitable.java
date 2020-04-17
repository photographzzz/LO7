package com.photograph.lo7.entity;

import androidx.databinding.BaseObservable;

public class Visitable extends BaseObservable {
    private Integer id;

    private Integer authorId;

    private Integer visitCount;

    private Integer commentCount;

    private Integer stars;

    private Integer likes;

    private String relativeDate;

    private String absoluteDate;

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
}
