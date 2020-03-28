package com.photograph.lo7.httpsender;

import com.photograph.lo7.httpsender.entity.ErrorInfo;

import io.reactivex.functions.Consumer;


public interface OnError extends Consumer<Throwable> {
    @Override
    default void accept(Throwable throwable) throws Exception {
        onError(new ErrorInfo(throwable));
    }

    void onError(ErrorInfo error) throws Exception;
}
