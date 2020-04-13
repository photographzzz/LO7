package com.photograph.lo7.service;

import com.photograph.lo7.entity.Section;

import java.util.List;

import io.reactivex.Observable;

public interface ISectionService {
    public Observable<List<Section>> getAllSection(int articleId);
}
