package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Section;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum SectionController {
    INSTANCE;
    public Observable<List<Section>> getAllSection(int articleId) {
        return RxHttp.get("/section/article_id")
                .add("articleId", articleId)
                .asResponseList(Section.class);
    }
}
