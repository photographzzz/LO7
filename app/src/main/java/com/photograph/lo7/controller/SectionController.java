package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Section;
import com.photograph.lo7.service.ISectionService;
import com.photograph.lo7.service.impl.SectionServiceImpl;

import java.util.List;

import io.reactivex.Observable;

public class SectionController {
    ISectionService sectionService;

    private SectionController() {
        sectionService = new SectionServiceImpl();
    }

    public static SectionController getInstance() {
        return SectionController.Inner.instance;
    }

    private static class Inner {
        private static final SectionController instance = new SectionController();
    }
    public Observable<List<Section>> getAllSection(int articleId) {
        return sectionService.getAllSection(articleId);
    }
}
