package com.photograph.lo7.ui.drawerlayout;

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
import com.photograph.lo7.databinding.FragmentDrawerArticleBinding;
import com.photograph.lo7.util.XRecyclerViewUtils;

public class DrawerLayoutArticleFragment extends Fragment implements XRecyclerView.LoadingListener{
    private XRecyclerView recyclerView;

    private int pageNum = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentDrawerArticleBinding articleBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_drawer_article, container, false);
        recyclerView = articleBinding.drawerArticleRecyclerView;
        XRecyclerViewUtils.initXRecyclerUtils(recyclerView, this);
        recyclerView.setLoadingListener(this);
        onRefresh();

        return articleBinding.getRoot();
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
        XRecyclerViewUtils.refreshMyArticles(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerViewUtils.loadMoreMyArticles(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
