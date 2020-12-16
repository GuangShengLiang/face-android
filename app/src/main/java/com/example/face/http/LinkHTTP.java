package com.example.face.http;


import com.example.face.model.act.ActivityDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface LinkHTTP {

    @GET("list_friend_activity")
    Observable<List<ActivityDetail>> listFriendAct();
}
