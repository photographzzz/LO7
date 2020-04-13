package com.photograph.lo7.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.R;
import com.photograph.lo7.adapter.SectionAdapter;
import com.photograph.lo7.controller.SectionController;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.ActivityArticleBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.util.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityArticleBinding articleBinding;
    private int authorId;
    private int articleId;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);

        Intent intent = getIntent();
        articleId = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        String preview = intent.getStringExtra("preview");
        int likes = intent.getIntExtra("likes", 0);
        int stars = intent.getIntExtra("stars", 0);
        authorId = intent.getIntExtra("authorId", 0);

        initAuthorProfile();

        setSupportActionBar(articleBinding.articleToolbar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        recyclerView = articleBinding.articleSectionsRecyclerview;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        initSection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu_article, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }


    private void initAuthorProfile() {
        UserController.getInstance().getFriendProfile(authorId)
                .as(RxLife.asOnMain(this))
                .subscribe(author -> {
                    articleBinding.setFriend(author);
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });

    }
    private void initSection(){
        SectionController.getInstance().getAllSection(articleId)
                .as(RxLife.asOnMain(this))
                .subscribe(sections -> {
                    recyclerView.setAdapter(new SectionAdapter(this, sections));
                }, (OnError) error -> {
                    error.show();
                });
    }
}
