package com.photograph.lo7.ui.drawerlayout.starcontent;

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
import com.photograph.lo7.databinding.FragmentStarArticleBinding;
import com.photograph.lo7.util.XRecyclerViewUtils;

public class StarActivityFragment extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView recyclerView;

    private int pageNum = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentStarArticleBinding starArticleBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_star_article, container, false);
        recyclerView = starArticleBinding.starArticleRecyclerview;
        XRecyclerViewUtils.initXRecyclerUtils(recyclerView, this);
        recyclerView.setLoadingListener(this);
        onRefresh();

        return starArticleBinding.getRoot();
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
        XRecyclerViewUtils.refreshStarArticles(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerViewUtils.loadMoreStarArticles(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
