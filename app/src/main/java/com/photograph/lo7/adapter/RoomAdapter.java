package com.photograph.lo7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.R;
import com.photograph.lo7.controller.RoomController;
import com.photograph.lo7.databinding.ItemRoomBinding;
import com.photograph.lo7.entity.Room;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<Room> rooms;
    private Context context;
    private boolean enter;
    private ItemRoomBinding roomBinding;

    public RoomAdapter(Context context, List<Room> rooms, boolean enter) {
        this.rooms = rooms;
        this.context = context;
        this.enter = enter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        roomBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_room, parent, false);
        Button doRoomBtn = roomBinding.doRoomBtn;
        if (enter) {
            doRoomBtn.setText("进入房间");
        } else {
            doRoomBtn.setText("退出房间");
        }
        return new ViewHolder(roomBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        roomBinding.setRoom(rooms.get(position));
        roomBinding.executePendingBindings();

        roomBinding.doRoomBtn.setOnClickListener(v -> {
            int roomId = rooms.get(position).getId();
            if (enter) {
                RoomController.INSTANCE.enter(roomId)
                        .as(RxLife.asOnMain(holder.itemView))
                        .subscribe(room -> {
                            remove(position);
                            Tip.show("加入房间成功");
                        }, (OnError) error -> error.show(error.getErrorMsg()));
            } else {
                RoomController.INSTANCE.quit(roomId)
                        .as(RxLife.asOnMain(holder.itemView))
                        .subscribe(room -> {
                            remove(position);
                            Tip.show("退出房间成功");
                        }, (OnError) error -> error.show(error.getErrorMsg()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemRoomBinding binding;

        ViewHolder(ItemRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void loadMore(List<Room> rooms) {
        this.rooms.addAll(rooms);
    }

    public void loadMore(Room room) {
        this.rooms.add(0, room);
        notifyItemInserted(0);
    }

    private void remove(int position) {
        rooms.remove(position);
        notifyItemRangeRemoved(position + 1, 1);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
