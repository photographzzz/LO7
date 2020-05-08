package com.photograph.lo7.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.controller.VideoController;
import com.photograph.lo7.databinding.ItemVideoBinding;
import com.photograph.lo7.entity.Video;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.presenter.FollowerPresenter;
import com.photograph.lo7.ui.activities.FriendActivity;
import com.photograph.lo7.ui.activities.VideoActivity;
import com.rxjava.rxlife.RxLife;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<Video> videos;
    private Context context;

    public VideoAdapter(Context context, List<Video> videos) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        ItemVideoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_video, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemVideoBinding videoBinding = holder.binding;
        videoBinding.setVideo(videos.get(position));
        videoBinding.executePendingBindings();
        Video video = videos.get(position);
        videoBinding.getRoot().setOnClickListener(v -> {
            AppHolder.currentVideo = video;
            VideoController.INSTANCE.visitVideo(video.getId())
                    .as(RxLife.asOnMain(holder.itemView.getRootView()))
                    .subscribe(success -> {
                        video.setVisitCount(video.getVisitCount() + 1);
                        Intent intent = new Intent(context, VideoActivity.class);
                        context.startActivity(intent);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });


        videoBinding.videoHeadView.friendPicCirimg.setOnClickListener(v -> {
            UserController.INSTANCE.getFriendProfile(video.getAuthorId())
                    .as(RxLife.asOnMain(holder.itemView.getRootView()))
                    .subscribe(friend -> {
                        Intent intent = new Intent(context, FriendActivity.class);
                        intent.putExtra("friend", friend);
                        context.startActivity(intent);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ItemVideoBinding binding;

        public ViewHolder(ItemVideoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setFollowerPresenter(new VideoFollowerPresenter());
        }

        class VideoFollowerPresenter extends FollowerPresenter {
            @Override
            public void onClickFollowButton(Visitable visitable) {
                super.onClickFollowButton(visitable, binding.getRoot());
            }
        }
    }

    @Override
    public int getItemCount() {
        return videos.size();

    }

    public void loadMore(List<Video> videos) {
        this.videos.addAll(videos);
    }

    public void loadMore(Video video) {
        this.videos.add(0, video);
        notifyItemInserted(0);
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


}
