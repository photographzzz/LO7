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
import com.photograph.lo7.adapter.MomentAdapter;
import com.photograph.lo7.databinding.FragmentSquareMomentBinding;
import com.photograph.lo7.entity.Moment;
import com.photograph.lo7.ui.activities.PostMomentActivity;
import com.photograph.lo7.util.XRecyclerViewUtils;

public class MomentSquareFragment extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView recyclerView;

    private int pageNum = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSquareMomentBinding momentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_square_moment, container, false);
        recyclerView = momentBinding.momentSquareRecyclerview;
        XRecyclerViewUtils.initXRecyclerUtils(recyclerView, this);
        recyclerView.setLoadingListener(this);
        onRefresh();

        momentBinding.momentPostFloatingBtn.postFloatingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(momentBinding.getRoot().getContext(), PostMomentActivity.class);
            startActivityForResult(intent,0);
        });
        return momentBinding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if (resultCode == 200) {
                Moment moment = (Moment) data.getSerializableExtra("moment");
                MomentAdapter momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                momentAdapter.loadMore(moment);
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
        XRecyclerViewUtils.refreshMoments(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerViewUtils.loadMoreMoments(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
