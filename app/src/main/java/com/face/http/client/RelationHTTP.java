package com.face.http.client;


import java.util.List;

import com.face.http.model.param.FriendParam;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.UidParam;
import com.face.http.model.vo.FriendVo;
import com.face.http.model.vo.FriendApplyVo;
import com.face.http.model.vo.RelationVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RelationHTTP {

    @GET("v1/relations/detail")
    Observable<JsonResponse<RelationVo>> relation(@Query("ruid") int ruid);

    @POST("v1/relations/black")
    Observable<JsonResponse> black(@Query("ruid") int ruid);

    @GET("v1/friends/list")
    Observable<JsonResponse<List<FriendVo>>> friendList();

    @POST("v1/friends/delete")
    Observable<Void> friendRemove(@Query("ruid") int ruid);

    @GET("v1/friend/applies/detail")
    Observable<JsonResponse> applyDetail(@Query("uid") int ruid);

    @POST("v1/friend/applies/apply")
    Observable<JsonResponse> friendApply(@Body FriendParam r);

    @POST("v1/friend/applies/agree")
    Observable<JsonResponse> friendAgree(@Body UidParam r);

    @GET("v1/friend/applies/list")
    Observable<JsonResponse<List<FriendApplyVo>>> friendApplyList();

}
