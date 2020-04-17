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
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.R;
import com.photograph.lo7.adapter.ArticleAdapter;
import com.photograph.lo7.controller.ArticleController;
import com.photograph.lo7.databinding.FragmentInformationVersionBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.util.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;

public class VersionInformationFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentInformationVersionBinding versionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_version, container, false);
        RecyclerView recyclerView = versionBinding.versionArticleRecyclerview;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        ArticleController.getInstance().getVersionArticles()
                .as(RxLife.asOnMain(this))
                .subscribe(articles -> {
                    ArticleAdapter adapter = new ArticleAdapter(getContext(), articles);
                    recyclerView.setAdapter(adapter);
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });
        return versionBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
