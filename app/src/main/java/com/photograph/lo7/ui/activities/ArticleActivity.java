package com.photograph.lo7.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.adapter.SectionAdapter;
import com.photograph.lo7.controller.FollowerController;
import com.photograph.lo7.controller.ILikeController;
import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.controller.SectionController;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.ActivityArticleBinding;
import com.photograph.lo7.entity.Article;
import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.photograph.lo7.util.LikeUtils;
import com.photograph.lo7.util.SpaceItemDecoration;
import com.photograph.lo7.util.StarUtils;
import com.rxjava.rxlife.RxLife;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityArticleBinding articleBinding;
    private RecyclerView recyclerView;
    private Friend author;
    private Article article = AppHolder.currentArticle;
    private IStarController starArticleController = IStarController.getStarArticleController();
    private ILikeController likeArticleController = ILikeController.getLikeArticleController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);
        articleBinding.articleAuthorBasicProfile.setUser(AppHolder.currentUser);
        articleBinding.setArticle(article);
        initAuthorProfile();
        initSections();

        setSupportActionBar(articleBinding.articleToolbar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        recyclerView = articleBinding.articleSectionsRecyclerview;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));

        Presenter presenter = new Presenter();
        articleBinding.setPresenter(presenter);
    }

    private void initAuthorProfile() {
        UserController.getInstance().getFriendProfile(article.getAuthorId())
                .as(RxLife.asOnMain(this))
                .subscribe(author -> {
                    this.author = author;
                    articleBinding.setFriend(author);
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });
    }

    private void initSections() {
        SectionController.getInstance().getAllSection(article.getId())
                .as(RxLife.asOnMain(this))
                .subscribe(sections -> {
                    recyclerView.setAdapter(new SectionAdapter(this, sections));
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu_article, menu);
        MenuItem likeItem = menu.getItem(0);
        likeItem.setIcon(article.isHasLike() ? R.drawable.ic_like_24dp : R.drawable.ic_like_border_24dp);
        MenuItem starItem = menu.getItem(1);
        starItem.setIcon(article.isHasStar() ? R.drawable.ic_favorite_24dp : R.drawable.ic_favorite_border_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                article = null;
                finish();
                break;
            case R.id.item_like_article:
                LikeUtils.handle(likeArticleController, article, item, articleBinding.getRoot());
                break;
            case R.id.item_star_article:
                StarUtils.handle(starArticleController,article,item,articleBinding.getRoot());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }


    public class Presenter {
        public void onClickFollowButton(int friendId, boolean isFollow) {
            if (isFollow) {
                FollowerController.getInstance().unfollow(friendId)
                        .as(RxLife.asOnMain(articleBinding.getRoot()))
                        .subscribe(followCount -> {
                            author.setHasBeenFollowed(false);
                            author.setFollowerCount(author.getFollowerCount() - 1);
                            AppHolder.currentUser.setFollowCount(followCount);
                            Tip.show("取消关注成功！");
                        }, (OnError) error -> error.show(error.getErrorMsg()));
            } else {
                FollowerController.getInstance().follow(friendId)
                        .as(RxLife.asOnMain(articleBinding.getRoot()))
                        .subscribe(followCount -> {
                            author.setHasBeenFollowed(true);
                            author.setFollowerCount(author.getFollowerCount() + 1);
                            AppHolder.currentUser.setFollowCount(followCount);
                            Tip.show("关注成功！");
                        }, (OnError) error -> error.show(error.getErrorMsg()));

            }
        }
    }
}
