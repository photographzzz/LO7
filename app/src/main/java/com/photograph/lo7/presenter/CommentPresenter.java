package com.photograph.lo7.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.adapter.CommentAdapter;
import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.controller.impl.CommentArticleController;
import com.photograph.lo7.controller.impl.CommentMomentController;
import com.photograph.lo7.controller.impl.CommentVideoController;
import com.photograph.lo7.databinding.MyCommentLayoutBinding;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;

public class CommentPresenter {
    public void onClickComment() {

    }

    public void onClickComment(Integer visitableId, ICommentController commentController, CommentAdapter commentAdapter, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("评论");
        MyCommentLayoutBinding commentLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout.my_comment_layout, null, false);
        builder.setView(commentLayoutBinding.getRoot());
        final EditText commentEdit = commentLayoutBinding.commentEdit;
        builder.setPositiveButton("发送", (dialog, which) -> {
            String content = commentEdit.getText().toString().trim();
            Integer userId = AppHolder.currentUser.getId();
            commentController.commentVisitable(visitableId, userId, content)
                    .as(RxLife.asOnMain(view))
                    .subscribe(comment -> {
                        commentAdapter.addCommentToEnd(comment);
                        Visitable visitable = null;
                        if (commentController instanceof CommentArticleController) {
                            visitable = AppHolder.currentArticle;
                        } else if (commentController instanceof CommentMomentController) {
                            visitable = AppHolder.currentMoment;
                        } else if (commentController instanceof CommentVideoController) {
                            visitable = AppHolder.currentVideo;
                        }
                        visitable.setCommentCount(visitable.getCommentCount() + 1);
                        Tip.show("评论成功");
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }
}
