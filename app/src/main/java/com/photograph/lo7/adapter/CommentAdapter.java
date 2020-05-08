package com.photograph.lo7.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.R;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.ItemCommentBinding;
import com.photograph.lo7.entity.Comment;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.ui.activities.FriendActivity;
import com.rxjava.rxlife.RxLife;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> comments;
    private Context context;
    private ItemCommentBinding commentBinding;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        commentBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_comment, parent, false);
        return new ViewHolder(commentBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        commentBinding.setComment(comments.get(position));
        commentBinding.executePendingBindings();
        Comment comment = comments.get(position);
        commentBinding.friendPicCirimg.setOnClickListener(v -> {
            UserController.INSTANCE.getFriendProfile(comment.getAuthorId())
                    .as(RxLife.asOnMain(commentBinding.getRoot()))
                    .subscribe(friend -> {
                        Intent intent = new Intent(context, FriendActivity.class);
                        intent.putExtra("friend", friend);
                        context.startActivity(intent);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemCommentBinding binding;

        ViewHolder(ItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void addCommentToEnd(Comment comment) {
        comments.add(comment);
        notifyItemInserted(comments.size());
    }
}
