package com.photograph.lo7.ui.bottomnavigationbar;

import android.content.Intent;
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
import com.photograph.lo7.adapter.RoomAdapter;
import com.photograph.lo7.databinding.FragmentBottomGameBinding;
import com.photograph.lo7.entity.Room;
import com.photograph.lo7.ui.activities.CreateRoomActivity;
import com.photograph.lo7.util.XRecyclerViewUtils;

public class BottomNavigationBarGameFragment extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView recyclerView;
    private int pageNum = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBottomGameBinding gameBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_bottom_game, container, false);

        recyclerView = gameBinding.roomsRecyclerview;

        gameBinding.createRoomBtn.setOnClickListener(v -> {
            Intent intent = new Intent(gameBinding.getRoot().getContext(), CreateRoomActivity.class);
            startActivityForResult(intent, 0);
        });

        return gameBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        XRecyclerViewUtils.initXRecyclerUtils(recyclerView,this);
        recyclerView.setLoadingListener(this);
        onRefresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if (resultCode == 200) {
                RoomAdapter roomAdapter = (RoomAdapter) recyclerView.getAdapter();
                Room room = (Room) data.getSerializableExtra("room");
                roomAdapter.loadMore(room);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        XRecyclerViewUtils.refreshFreeRooms(recyclerView, pageNum++, this);
    }

    @Override
    public void onLoadMore() {
        XRecyclerViewUtils.loadMoreFreeRooms(recyclerView, pageNum++, this);
        recyclerView.loadMoreComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
