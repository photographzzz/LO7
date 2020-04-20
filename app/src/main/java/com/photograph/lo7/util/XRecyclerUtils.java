package com.photograph.lo7.util;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.photograph.lo7.adapter.ArticleAdapter;
import com.photograph.lo7.controller.ArticleController;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;

public class XRecyclerUtils {
    public static void initXRecyclerUtils(XRecyclerView recyclerView,Fragment fragment) {
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        recyclerView.setLimitNumberToCallLoadMore(10);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    }

    public static void loadMoreArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        ArticleController.INSTANCE.getAllArticles(pageNum)
                .as(RxLife.asOnMain(fragment))
                .subscribe(articles -> {
                    if (articles.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        ArticleAdapter articleAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                articleAdapter = new ArticleAdapter(fragment.getContext(), articles);
                                recyclerView.setAdapter(articleAdapter);
                            } else {
                                articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                                articleAdapter.setArticles(articles);
                                articleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                            articleAdapter.loadMore(articles);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refresh(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreArticles(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
