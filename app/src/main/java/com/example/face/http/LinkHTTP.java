package com.example.face.http;


import com.example.face.model.ActivityDetail;
import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface LinkHTTP {

    @GET("list_friend_activity")
    Observable<List<ActivityDetail>> listFriendAct();
}
