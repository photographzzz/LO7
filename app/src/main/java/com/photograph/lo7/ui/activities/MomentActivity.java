package com.photograph.lo7.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.adapter.CommentAdapter;
import com.photograph.lo7.controller.ICommentController;
import com.photograph.lo7.controller.ILikeController;
import com.photograph.lo7.controller.IStarController;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.ActivityMomentBinding;
import com.photograph.lo7.entity.Visitable;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.presenter.CommentPresenter;
import com.photograph.lo7.presenter.FollowerPresenter;
import com.photograph.lo7.util.LikeUtils;
import com.photograph.lo7.util.StarUtils;
import com.photograph.lo7.view.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;

public class MomentActivity extends AppCompatActivity  {
    private ActivityMomentBinding momentBinding;
    private RecyclerView commentsRecyclerView;
    private Visitable moment = AppHolder.currentMoment;
    private CommentAdapter commentAdapter;
    private IStarController starMomentController = IStarController.getStarMomentController();
    private ILikeController likeMomentController = ILikeController.getLikeMomentController();
    private ICommentController commentMomentController = ICommentController.getCommentMomentController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        momentBinding = DataBindingUtil.setContentView(this, R.layout.activity_moment);
        initComments();

        setSupportActionBar(momentBinding.momentToolbar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        commentsRecyclerView = momentBinding.commentsRecyclerview;
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentsRecyclerView.addItemDecoration(spaceItemDecoration);
        FollowerPresenter followerPresenter = new MomentFollowerPresenter();
        momentBinding.setFollowerPresenter(followerPresenter);
        CommentPresenter commentPresenter = new MomentCommentPresenter();
        momentBinding.setCommentPresenter(commentPresenter);


        momentBinding.momentHeadView.friendPicCirimg.setOnClickListener(v -> {
            UserController.INSTANCE.getFriendProfile(moment.getAuthorId())
                    .as(RxLife.asOnMain(this))
                    .subscribe(friend -> {
                        Intent intent = new Intent(this, FriendActivity.class);
                        intent.putExtra("friend", friend);
                        startActivity(intent);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
        });
    }

    private void initComments() {
        commentMomentController.getCommentsByVisitableId(moment.getId())
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
        getMenuInflater().inflate(R.menu.tool_menu_visitable, menu);
        MenuItem likeItem = menu.getItem(0);
        likeItem.setIcon(moment.getHasLike() ? R.drawable.ic_like_24dp : R.drawable.ic_like_border_24dp);
        MenuItem starItem = menu.getItem(1);
        starItem.setIcon(moment.getHasStar() ? R.drawable.ic_favorite_24dp : R.drawable.ic_favorite_border_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                moment = null;
                finish();
                break;
            case R.id.item_like_visitable:
                LikeUtils.handle(likeMomentController, moment, item, momentBinding.getRoot());
                break;
            case R.id.item_star_visitable:
                StarUtils.handle(starMomentController, moment, item, momentBinding.getRoot());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public class MomentFollowerPresenter extends FollowerPresenter {
        @Override
        public void onClickFollowButton(Visitable visitable) {
            super.onClickFollowButton(visitable, momentBinding.getRoot());
        }
    }

    public class MomentCommentPresenter extends CommentPresenter{
        @Override
        public void onClickComment() {
            super.onClickComment(moment.getId(),commentMomentController,commentAdapter,momentBinding.getRoot());
        }
    }
}
