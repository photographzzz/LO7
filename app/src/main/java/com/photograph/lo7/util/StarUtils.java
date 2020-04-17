package com.photograph.lo7.util;

import android.view.MenuItem;
import android.view.View;

import com.photograph.lo7.R;
import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;

public class StarUtils {
    public static void handle(IStarController starController, Visitable visitable, MenuItem starItem, View view) {
        if (visitable.isHasStar()) {
            unstar(starController, visitable, starItem, view);
        }else {
            star(starController, visitable, starItem, view);
        }
    }
    public static void unstar(IStarController starController, Visitable visitable, MenuItem starItem, View view) {
        starController.unstar(visitable.getId())
                .as(RxLife.asOnMain(view))
                .subscribe(starCount -> {
                    visitable.setStars(visitable.getStars() - 1);
                    starItem.setIcon(R.drawable.ic_favorite_border_24dp);
                    visitable.setHasStar(false);
                    Tip.show("取消收藏成功");
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void star(IStarController starController, Visitable visitable, MenuItem starItem, View view) {
        starController.star(visitable.getId())
                .as(RxLife.asOnMain(view))
                .subscribe(starCount -> {
                    visitable.setStars(visitable.getStars() + 1);
                    starItem.setIcon(R.drawable.ic_favorite_24dp);
                    visitable.setHasStar(true);
                    Tip.show("收藏成功");
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }
}
