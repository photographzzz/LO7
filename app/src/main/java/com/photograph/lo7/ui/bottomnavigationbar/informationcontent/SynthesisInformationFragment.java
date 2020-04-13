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
import com.photograph.lo7.databinding.FragmentInformationSynthesisBinding;
import com.photograph.lo7.entity.Article;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import rxhttp.wrapper.param.RxHttp;

public class SynthesisInformationFragment extends Fragment {
    private FragmentInformationSynthesisBinding synthesisBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        synthesisBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_synthesis, container, false);
        RecyclerView recyclerView = synthesisBinding.synthesisArticleRecyclerview;

        List<Article> data = new ArrayList<>();
        RxHttp.get("/article/all")
                .asResponseList(Article.class)
                .subscribe(articles -> {
                    for (Article article : articles) {
                        data.add(article);
                    }
                },(OnError) error ->{
                    error.show(error.getErrorMsg());
                });

        ArticleAdapter adapter = new ArticleAdapter(getContext(), data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));


        return synthesisBinding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
