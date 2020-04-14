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
import com.photograph.lo7.databinding.FragmentInformationSynthesisBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.util.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;

public class SynthesisInformationFragment extends Fragment {
    private FragmentInformationSynthesisBinding synthesisBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        synthesisBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_synthesis, container, false);
        RecyclerView recyclerView = synthesisBinding.synthesisArticleRecyclerview;


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        ArticleController.getInstance().getAllArticles()
                .as(RxLife.asOnMain(this))
                .subscribe(articles -> {
                    ArticleAdapter adapter = new ArticleAdapter(getContext(), articles);
                    recyclerView.setAdapter(adapter);
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });


        return synthesisBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
