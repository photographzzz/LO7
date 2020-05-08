package com.photograph.lo7.controller;

import com.photograph.lo7.entity.Room;

import java.util.List;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

public enum RoomController {
    INSTANCE;

    public Observable<Room> create(String startTime, String endTime) {
        return RxHttp.postForm("room")
                .add("startTime", startTime)
                .add("endTime", endTime)
                .asResponse(Room.class);
    }

    public Observable<Room> enter(Integer roomId) {
        return RxHttp.putForm("room")
                .add("roomId", roomId)
                .add("type", "enter")
                .asResponse(Room.class);
    }

    public Observable<Room> quit(Integer roomId) {
        return RxHttp.putForm("room")
                .add("roomId", roomId)
                .add("type", "quit")
                .asResponse(Room.class);
    }

    public Observable<List<Room>> findFreeRoom(){
        return RxHttp.get("room")
                .add("type","free")
                .asResponseList(Room.class);
    }

    public Observable<List<Room>> findMyRoom(){
        return RxHttp.get("room")
                .add("type","my")
                .asResponseList(Room.class);
    }
}
