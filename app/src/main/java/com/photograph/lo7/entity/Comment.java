package com.photograph.lo7.entity;

import androidx.databinding.BaseObservable;

public class Comment extends BaseObservable {
    private Integer id;

    private Integer visitableId;

    private Integer authorId;

    private String content;

    private String authorPic;

    private String authorName;

    private String relativeTime;

    private String absoluteDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisitableId() {
        return visitableId;
    }

    public void setVisitableId(Integer visitableId) {
        this.visitableId = visitableId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setRelativeTime(String relativeTime) {
        this.relativeTime = relativeTime;
    }

    public String getRelativeTime() {
        return relativeTime;
    }

    public void setRelativeDate(String relativeTime) {
        this.relativeTime = relativeTime;
    }

    public String getAbsoluteDate() {
        return absoluteDate;
    }

    public void setAbsoluteDate(String absoluteDate) {
        this.absoluteDate = absoluteDate;
    }
}