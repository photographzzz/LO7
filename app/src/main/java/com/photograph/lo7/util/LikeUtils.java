package com.photograph.lo7.util;

import android.view.MenuItem;
import android.view.View;

import com.photograph.lo7.R;
import com.photograph.lo7.controller.ILikeController;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;

public class LikeUtils {
    public static void handle(ILikeController likeController, Visitable visitable, MenuItem likeItem, View view) {
        if (visitable.isHasLike()) {
            unlike(likeController, visitable, likeItem, view);
        }else {
            like(likeController, visitable, likeItem, view);
        }
    }
    private static void unlike(ILikeController likeController, Visitable visitable, MenuItem likeItem, View view) {
        likeController.unlike(visitable.getId())
                .as(RxLife.asOnMain(view))
                .subscribe(likeCount -> {
                    visitable.setLikes(visitable.getLikes() - 1);
                    likeItem.setIcon(R.drawable.ic_like_border_24dp);
                    visitable.setHasLike(false);
                    Tip.show("取消点赞成功");
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    private static void like(ILikeController likeController, Visitable visitable, MenuItem likeItem, View view) {
        likeController.like(visitable.getId())
                .as(RxLife.asOnMain(view))
                .subscribe(likeCount -> {
                    visitable.setLikes(visitable.getLikes() + 1);
                    likeItem.setIcon(R.drawable.ic_like_24dp);
                    visitable.setHasLike(true);
                    Tip.show("点赞成功");
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }
}
