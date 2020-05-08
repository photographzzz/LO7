package com.photograph.lo7.ui.bottomnavigationbar.informationcontent;

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
import com.photograph.lo7.databinding.FragmentInformationStrategyBinding;
import com.photograph.lo7.util.XRecyclerViewUtils;

public class StrategyInformationFragment extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView recyclerView;

    private int pageNum = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentInformationStrategyBinding strategyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_strategy, container, false);
        recyclerView = strategyBinding.strategyArticleRecyclerview;
        XRecyclerViewUtils.initXRecyclerUtils(recyclerView,this);
        recyclerView.setLoadingListener(this);
        onRefresh();

        return strategyBinding.getRoot();
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
        XRecyclerViewUtils.refreshArticles(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerViewUtils.loadMoreArticles(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }


}
