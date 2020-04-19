package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Section;
import com.photograph.lo7.service.ISectionService;
import com.photograph.lo7.service.impl.SectionServiceImpl;

import java.util.List;

import io.reactivex.Observable;

public enum SectionController {
    INSTANCE;
    private ISectionService sectionService = new SectionServiceImpl();

    public Observable<List<Section>> getAllSection(int articleId) {
        return sectionService.getAllSection(articleId);
    }
}
