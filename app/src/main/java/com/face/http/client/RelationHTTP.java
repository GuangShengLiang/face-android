package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.vo.FriendVo;
import com.face.http.model.vo.RelationStatVO;
import com.face.http.model.vo.RelationVo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface RelationHTTP {

    @GET("v1/relations/detail")
    Observable<JsonResponse<RelationVo>> relation(@Query("tuid") int ruid);

    @POST("v1/relations/black")
    Observable<JsonResponse> black(@Query("blackUid") int blackUid);

    @GET("v1/relations/friends")
    Observable<JsonResponse<List<RelationVo>>> queryFriends();

    @POST("v1/relations/follow")
    Observable<JsonResponse> follow(@Query("followUid") int followUid);

    @POST("v1/relations/unfollow")
    Observable<JsonResponse> unfollow(@Query("followUid") int followUid);

    @GET("v1/relations/followers")
    Observable<JsonResponse<List<RelationVo>>> queryFollowers();

    @GET("v1/relations/fans")
    Observable<JsonResponse<List<RelationVo>>> queryFans();

    @GET("v1/relations/stat")
    Observable<JsonResponse<RelationStatVO>> queryStat();
}
