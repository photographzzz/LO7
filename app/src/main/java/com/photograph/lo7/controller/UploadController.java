package com.photograph.lo7.controller;

import java.io.File;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum UploadController {
    INSTANCE;

    public Observable<String> upload(File file) {
        return RxHttp.postForm("upload")
                .addFile("file", file)
                .asResponse(String.class);
    }
}
