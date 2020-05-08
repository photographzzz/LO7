package com.photograph.lo7.presenter;

import android.view.View;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.controller.FollowerController;
import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;

public class FollowerPresenter {
    public void onClickFollowButton(Visitable visitable) {

    }

    public void onClickFollowButton(Visitable visitable, View view) {
        if (visitable.getAuthorHasBeenFollowed()) {
            FollowerController.INSTANCE.unfollow(visitable.getAuthorId())
                    .as(RxLife.asOnMain(view))
                    .subscribe(followCount -> {
                        visitable.setAuthorHasBeenFollowed(false);
                        visitable.setAuthorFollowerCount(visitable.getAuthorFollowerCount() - 1);
                        AppHolder.currentUser.setFollowCount(followCount);
                        Tip.show("取消关注成功！");
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        } else {
            FollowerController.INSTANCE.follow(visitable.getAuthorId())
                    .as(RxLife.asOnMain(view))
                    .subscribe(followCount -> {
                        visitable.setAuthorHasBeenFollowed(true);
                        visitable.setAuthorFollowerCount(visitable.getAuthorFollowerCount() + 1);
                        AppHolder.currentUser.setFollowCount(followCount);
                        Tip.show("关注成功！");
                    }, (OnError) error -> error.show(error.getErrorMsg()));

        }
    }

    public void onClickFollowButton(Friend friend, View view) {
        if (friend.getHasBeenFollowed()) {
            FollowerController.INSTANCE.unfollow(friend.getId())
                    .as(RxLife.asOnMain(view))
                    .subscribe(followCount -> {
                        friend.setHasBeenFollowed(false);
                        friend.setFollowerCount(friend.getFollowerCount() - 1);
                        AppHolder.currentUser.setFollowCount(followCount);
                        Tip.show("取消关注成功！");
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        } else {
            FollowerController.INSTANCE.follow(friend.getId())
                    .as(RxLife.asOnMain(view))
                    .subscribe(followCount -> {
                        friend.setHasBeenFollowed(true);
                        friend.setFollowerCount(friend.getFollowerCount() + 1);
                        AppHolder.currentUser.setFollowCount(followCount);
                        Tip.show("关注成功！");
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        }
    }
}
