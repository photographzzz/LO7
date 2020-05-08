package com.photograph.lo7.ui.bottomnavigationbar.squarecontent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.photograph.lo7.R;
import com.photograph.lo7.adapter.VideoAdapter;
import com.photograph.lo7.databinding.FragmentSquareVideoBinding;
import com.photograph.lo7.entity.Video;
import com.photograph.lo7.ui.activities.PostVideoActivity;
import com.photograph.lo7.util.XRecyclerViewUtils;

public class VideoSquareFragment extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView recyclerView;

    private int pageNum = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSquareVideoBinding videoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_square_video, container, false);
        recyclerView = videoBinding.videoSquareRecyclerview;
        XRecyclerViewUtils.initXRecyclerUtils(recyclerView, this);
        recyclerView.setLoadingListener(this);
        onRefresh();

        videoBinding.videoPostFloatingBtn.postFloatingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(videoBinding.getRoot().getContext(), PostVideoActivity.class);
            startActivityForResult(intent,0);
        });
        return videoBinding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if (resultCode == 200) {
                Video video = (Video) data.getSerializableExtra("video");
                VideoAdapter videoAdapter = (VideoAdapter) recyclerView.getAdapter();
                videoAdapter.loadMore(video);
            }
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (recyclerView != null) {
            recyclerView.destroy();
            recyclerView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        XRecyclerViewUtils.refreshVideos(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerViewUtils.loadMoreVideos(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
