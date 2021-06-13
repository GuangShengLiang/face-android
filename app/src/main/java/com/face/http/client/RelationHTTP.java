package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.vo.RelationStatVO;
import com.face.http.model.vo.RelationVO;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface RelationHTTP {

    @GET("v1/relations")
    Observable<JsonResponse<RelationVO>> relation(@Query("tuid") int ruid);

    @POST("v1/relations/black")
    Observable<JsonResponse> black(@Query("blackUid") int blackUid);

    @GET("v1/relations/friends")
    Observable<JsonResponse<List<RelationVO>>> queryFriends();

    @POST("v1/relations/follow")
    Observable<JsonResponse> follow(@Query("targetUid") int targetUid);

    @POST("v1/relations/unfollow")
    Observable<JsonResponse> unfollow(@Query("targetUid") int targetUid);

    @GET("v1/relations/followers")
    Observable<JsonResponse<List<RelationVO>>> queryFollowers();

    @GET("v1/relations/fans")
    Observable<JsonResponse<List<RelationVO>>> queryFans();

    @GET("v1/relations/stat")
    Observable<JsonResponse<RelationStatVO>> queryStat();
}
