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
import com.photograph.lo7.databinding.MyCommentLayoutBinding;
import com.rxjava.rxlife.RxLife;

public class CommentPresenter {
    public void onClickComment() {

    }

    public void onClickComment(Integer visitableId,  CommentAdapter commentAdapter, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("评论");
        MyCommentLayoutBinding commentLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout.my_comment_layout, null,false);
        builder.setView(commentLayoutBinding.getRoot());
        final EditText commentEdit = commentLayoutBinding.commentEdit;
        builder.setPositiveButton("发送", (dialog, which) -> {
            String content = commentEdit.getText().toString().trim();
            Integer userId = AppHolder.currentUser.getId();
            ICommentController.getCommentArticleController().commentVisitable(visitableId, userId, content)
                    .as(RxLife.asOnMain(view))
                    .subscribe(commentAdapter::addCommentToEnd);
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }
}
