package com.photograph.lo7.presenter;

import android.view.View;

import com.photograph.lo7.adapter.CommentAdapter;
import com.photograph.lo7.controller.ICommentController;
import com.rxjava.rxlife.RxLife;

public class CommentPresenter {
    public void onClickComment(View view, Integer userId) {

    }

    public void onClickComment(Integer visitableId, Integer userId, String content, CommentAdapter commentAdapter, View view) {
        ICommentController.getCommentArticleController().commentVisitable(visitableId, userId, content)
                .as(RxLife.asOnMain(view))
                .subscribe(comment -> {
                    commentAdapter.addCommentToEnd(comment);
                });
    }
}
