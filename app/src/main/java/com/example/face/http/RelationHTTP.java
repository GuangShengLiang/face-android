package com.example.face.http;


import com.example.face.entity.FriendApply;
import com.example.face.model.Friend;
import com.example.face.model.FriendReq;
import com.example.face.model.Relation;
import com.example.face.model.RuidReq;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RelationHTTP {

    @GET("relation")
    Observable<Relation> relation(@Query("ruid") int ruid);

    @GET("friend/list")
    Observable<List<Friend>> friendList();

    @GET("friend/apply/list")
    Observable<List<FriendApply>> friendApplyList();

    @GET("friend/apply")
    Observable<Void> friendApply(@Query("uid") int ruid);

    @POST(value = "friend/apply")
    Observable<Void> friendApply(@Body FriendReq r);

    @POST(value = "friend/apply/agree")
    Observable<Void> friendApplyAgree(@Body RuidReq r);

    @POST(value = "friend/delete")
    Observable<Void> friendRemove(@Query("ruid") int ruid);

    @POST(value = "black")
    Observable<Void> black(@Query("ruid") int ruid);

}
