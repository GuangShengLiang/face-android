package com.example.face.http;


import com.example.face.entity.FriendApply;
import com.example.face.model.*;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RelationHTTP {

    @GET("v1/relations/detail")
    Observable<Relation> relation(@Query("ruid") int ruid);

    @POST("v1/relations/black")
    Observable<Response> black(@Query("ruid") int ruid);

    @GET("v1/friends/list")
    Observable<List<Friend>> friendList();

    @POST("v1/friends/delete")
    Observable<Void> friendRemove(@Query("ruid") int ruid);

    @GET("v1/friends/applies/detail")
    Observable<Void> friendApply(@Query("uid") int ruid);

    @POST("v1/friends/applies/apply")
    Observable<Response> friendApply(@Body FriendReq r);

    @POST("v1/friends/applies/agree")
    Observable<Response> friendAgree(@Body RuidReq r);

    @GET("v1/friends/applies/list")
    Observable<List<FriendApply>> friendApplyList();

}
