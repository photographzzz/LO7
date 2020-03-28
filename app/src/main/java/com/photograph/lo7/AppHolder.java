package com.photograph.lo7;

import android.app.Application;
import android.content.Context;

import com.photograph.lo7.httpsender.RxHttpManager;
import com.photograph.lo7.vo.UserVO;

public class AppHolder extends Application {

    private static AppHolder instance;
    public static UserVO currentUser;


    public static AppHolder getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxHttpManager.init(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       //TODO MultiDex.install(this);
    }

}
