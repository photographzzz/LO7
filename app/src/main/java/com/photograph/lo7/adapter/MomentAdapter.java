package com.photograph.lo7.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.MomentController;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.ItemMomentBinding;
import com.photograph.lo7.entity.Moment;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.presenter.FollowerPresenter;
import com.photograph.lo7.ui.activities.FriendActivity;
import com.photograph.lo7.ui.activities.MomentActivity;
import com.rxjava.rxlife.RxLife;

import java.util.List;

public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.ViewHolder> {
    private List<Moment> moments;
    private Context context;

    public MomentAdapter(Context context, List<Moment> moments) {
        this.moments = moments;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        ItemMomentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_moment, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemMomentBinding momentBinding = holder.binding;
        momentBinding.setMoment(moments.get(position));
        momentBinding.executePendingBindings();
        Moment moment = moments.get(position);

        momentBinding.getRoot().setOnClickListener(v -> {
            AppHolder.currentMoment = moment;
            MomentController.INSTANCE.visitMoment(moment.getId())
                    .as(RxLife.asOnMain(holder.itemView.getRootView()))
                    .subscribe(success -> {
                        moment.setVisitCount(moment.getVisitCount() + 1);
                        Intent intent = new Intent(context, MomentActivity.class);
                        context.startActivity(intent);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });

        momentBinding.momentHeadView.friendPicCirimg.setOnClickListener(v -> {
            UserController.INSTANCE.getFriendProfile(moment.getAuthorId())
                    .as(RxLife.asOnMain(holder.itemView.getRootView()))
                    .subscribe(friend -> {
                        Intent intent = new Intent(context, FriendActivity.class);
                        intent.putExtra("friend", friend);
                        context.startActivity(intent);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ItemMomentBinding binding;

        public ViewHolder(ItemMomentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setFollowerPresenter(new MomentFollowerPresenter());
        }

        class MomentFollowerPresenter extends FollowerPresenter {
            @Override
            public void onClickFollowButton(Visitable visitable) {
                super.onClickFollowButton(visitable, binding.getRoot());
            }
        }
    }

    @Override
    public int getItemCount() {
        return moments.size();

    }

    public void loadMore(List<Moment> moments) {
        this.moments.addAll(moments);
    }

    public void loadMore(Moment moment) {
        this.moments.add(0, moment);
        notifyItemInserted(0);
    }

    public void setMoments(List<Moment> moments) {
        this.moments = moments;
    }


}
