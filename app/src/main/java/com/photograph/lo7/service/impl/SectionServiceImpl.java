package com.photograph.lo7.service.impl;

import com.photograph.lo7.entity.Section;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.service.ISectionService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public class SectionServiceImpl implements ISectionService {

    @Override
    public Observable<List<Section>> getAllSection(int articleId) {
        return RxHttp.get("/section/")
                .add("articleId", articleId)
                .asResponseList(Section.class);
    }
}
