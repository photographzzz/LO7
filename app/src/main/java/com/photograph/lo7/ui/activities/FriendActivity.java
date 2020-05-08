package com.photograph.lo7.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.ActivityFriendBinding;
import com.photograph.lo7.entity.Friend;
import com.photograph.lo7.presenter.FollowerPresenter;

public class FriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFriendBinding friendBinding = DataBindingUtil.setContentView(this, R.layout.activity_friend);
        Friend friend = (Friend) getIntent().getSerializableExtra("friend");
        friendBinding.setFriend(friend);

        friendBinding.friendFollowFloatingBtn.setOnClickListener(v ->
                new FollowerPresenter().onClickFollowButton(friend, friendBinding.getRoot()));

        setSupportActionBar(friendBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        friendBinding.collapsingToolbar.setTitle(friend.getUsername());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}