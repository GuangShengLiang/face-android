package com.example.face.http;


import com.example.face.model.*;

import java.util.List;

import com.example.face.model.param.FriendParam;
import com.example.face.model.param.UidParam;
import com.example.face.model.vo.FriendVo;
import com.example.face.model.vo.FriendApplyVo;
import com.example.face.model.vo.RelationVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RelationHTTP {

    @GET("v1/relations/detail")
    Observable<RelationVo> relation(@Query("ruid") int ruid);

    @POST("v1/relations/black")
    Observable<Response> black(@Query("ruid") int ruid);

    @GET("v1/friends/list")
    Observable<List<FriendVo>> friendList();

    @POST("v1/friends/delete")
    Observable<Void> friendRemove(@Query("ruid") int ruid);

    @GET("v1/friend/applies/detail")
    Observable<Void> applyDetail(@Query("uid") int ruid);

    @POST("v1/friend/applies/apply")
    Observable<Response> friendApply(@Body FriendParam r);

    @POST("v1/friend/applies/agree")
    Observable<Response> friendAgree(@Body UidParam r);

    @GET("v1/friend/applies/list")
    Observable<List<FriendApplyVo>> friendApplyList();

}
