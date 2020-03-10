package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.ActReq;
import com.example.face.model.ActivityDetail;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.List;

public interface ActivityHTTP {

    @GET("list_friend_activity")
    Observable<List<ActivityDetail>> listFriendAct();

    @GET(value = "search")
    Observable<Object> search(int fromId);

    @GET(value = "publish/list")
    Observable<List<ActivityDetail>> listPublish();

    @GET(value = "waiting/list")
    Observable<Object> listWaiting();

    @GET(value = "finish/list")
    Observable<Object> finish();

    @GET(value = "join/list")
    Observable<List<ActivityDetail>> listJoin();

    @GET(value = "activity")
    Observable<ActivityDetail> detail(@Query("aid") long aid);

    @POST(value = "activity")
    Observable<Void> add(@Body ActReq r);

    @PUT(value = "activity")
    Observable<Void> modify(@Body ActReq req);

    @GET(value = "activity/member/list")
    Observable<List<Account>> listMember(@Query("aid") long aid);
}
