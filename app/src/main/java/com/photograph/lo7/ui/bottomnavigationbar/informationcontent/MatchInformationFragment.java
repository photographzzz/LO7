package com.photograph.lo7.ui.bottomnavigationbar.informationcontent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentInformationMatchBinding;
import com.photograph.lo7.util.SpaceItemDecoration;
import com.photograph.lo7.util.XRecyclerUtils;

public class MatchInformationFragment extends Fragment implements XRecyclerView.LoadingListener {
    private FragmentInformationMatchBinding matchBinding;
    private XRecyclerView recyclerView;

    private int pageNum = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        matchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_match, container, false);
        recyclerView = matchBinding.matchArticleRecyclerview;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLimitNumberToCallLoadMore(10);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        recyclerView.setLoadingListener(this);
        onRefresh();

        return matchBinding.getRoot();
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
        XRecyclerUtils.refresh(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerUtils.loadMoreArticles(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }


}
