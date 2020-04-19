package com.photograph.lo7.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.adapter.CommentAdapter;
import com.photograph.lo7.adapter.SectionAdapter;
import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.controller.ILikeController;
import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.controller.SectionController;
import com.photograph.lo7.databinding.ActivityArticleBinding;
import com.photograph.lo7.databinding.MyCommentLayoutBinding;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.presenter.CommentPresenter;
import com.photograph.lo7.presenter.FollowerPresenter;
import com.photograph.lo7.util.LikeUtils;
import com.photograph.lo7.util.SpaceItemDecoration;
import com.photograph.lo7.util.StarUtils;
import com.rxjava.rxlife.RxLife;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityArticleBinding articleBinding;
    private RecyclerView sectionsRecyclerView;
    private RecyclerView commentsRecyclerView;
    private Visitable article = AppHolder.currentArticle;
    private CommentAdapter commentAdapter;
    private IStarController starArticleController = IStarController.getStarArticleController();
    private ILikeController likeArticleController = ILikeController.getLikeArticleController();
    private SectionController sectionController = SectionController.INSTANCE;
    private ICommentController commentArticleController = ICommentController.getCommentArticleController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);
        articleBinding.setArticle(article);
        initSections();
        initComments();

        setSupportActionBar(articleBinding.articleToolbar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);

        sectionsRecyclerView = articleBinding.sectionsRecyclerview;
        sectionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sectionsRecyclerView.addItemDecoration(spaceItemDecoration);

        commentsRecyclerView = articleBinding.commentsRecyclerview;
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentsRecyclerView.addItemDecoration(spaceItemDecoration);
        FollowerPresenter followerPresenter = new ArticleFollowerPresenter();
        articleBinding.setFollowerPresenter(followerPresenter);
        CommentPresenter commentPresenter = new ArticleCommentPresenter();
        articleBinding.setCommentPresenter(commentPresenter);


        articleBinding.articleCommentFloatingBtn.commentFloatingBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(articleBinding.getRoot().getContext());
            builder.setTitle("评论");
            MyCommentLayoutBinding commentLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(articleBinding.getRoot().getContext()), R.layout.my_comment_layout, null,false);
            builder.setView(commentLayoutBinding.getRoot());
            final EditText commentEdit = commentLayoutBinding.commentEdit;
            builder.setPositiveButton("发送", (dialog, which) -> {
                String content = commentEdit.getText().toString().trim();
                int articleId = article.getId();
                new CommentPresenter().onClickComment(articleId,AppHolder.currentUser.getId(),content,commentAdapter,articleBinding.getRoot());
            });

            builder.setNegativeButton("取消", (dialog, which) -> {
                dialog.cancel();
            });
            builder.show();
        });
    }

    private void initSections() {
        sectionController.getAllSection(article.getId())
                .as(RxLife.asOnMain(this))
                .subscribe(sections -> {
                    sectionsRecyclerView.setAdapter(new SectionAdapter(this, sections));
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });
    }

    private void initComments() {
        commentArticleController.getCommentsByVisitableId(article.getId())
                .as(RxLife.asOnMain(this))
                .subscribe(comments -> {
                    commentAdapter = new CommentAdapter(this, comments);
                    commentsRecyclerView.setAdapter(commentAdapter);
                }, (OnError) error -> {
                    error.show(error.getErrorMsg());
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu_article, menu);
        MenuItem likeItem = menu.getItem(0);
        likeItem.setIcon(article.getHasLike() ? R.drawable.ic_like_24dp : R.drawable.ic_like_border_24dp);
        MenuItem starItem = menu.getItem(1);
        starItem.setIcon(article.getHasStar() ? R.drawable.ic_favorite_24dp : R.drawable.ic_favorite_border_24dp);
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
                StarUtils.handle(starArticleController, article, item, articleBinding.getRoot());
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


    public class ArticleFollowerPresenter extends FollowerPresenter {
        @Override
        public void onClickFollowButton(Visitable visitable) {
            super.onClickFollowButton(visitable, articleBinding.getRoot());
        }
    }

    public class ArticleCommentPresenter extends CommentPresenter{
        @Override
        public void onClickComment(View view ,Integer userId) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("评论");
            MyCommentLayoutBinding commentLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(articleBinding.getRoot().getContext()), R.layout.my_comment_layout, null,false);
            builder.setView(commentLayoutBinding.getRoot());
            final EditText commentEdit = commentLayoutBinding.commentEdit;
            builder.setPositiveButton("发送", (dialog, which) -> {
                String content = commentEdit.getText().toString().trim();
                int articleId = article.getId();
                ArticleCommentPresenter.super.onClickComment(articleId,userId,content,commentAdapter,articleBinding.getRoot());
            });

            builder.setNegativeButton("取消", (dialog, which) -> {
                dialog.cancel();
            });
            builder.show();
        }
    }
}
