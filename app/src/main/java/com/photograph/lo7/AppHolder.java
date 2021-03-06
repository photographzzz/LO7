package com.photograph.lo7;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.photograph.lo7.entity.Article;
import com.photograph.lo7.entity.Moment;
import com.photograph.lo7.entity.User;
import com.photograph.lo7.entity.Video;
import com.photograph.lo7.httpsender.RxHttpManager;

/**
 * 自定义Application
 */
public class AppHolder extends Application {

    private static AppHolder instance;
    public static User currentUser;
    public static Article currentArticle;
    public static Moment currentMoment;
    public static Video currentVideo;

    public final static String userPicPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lo7/user/pic";


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
