package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.param.ActivityCreateParam;
import com.face.http.model.param.ApplySwitchParam;
import com.face.http.model.vo.Account;
import com.face.http.model.vo.ActivityDetailVo;
import com.face.http.model.vo.ActivityFeedVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ActivityHTTP {

    @GET("v1/activities/list/friend-join")
    Observable<JsonResponse<List<ActivityFeedVo>>> listActivitiesFriendJoin();

    @GET("v1/activities/list/follower-join")
    Observable<JsonResponse<List<ActivityFeedVo>>> listActivitiesFollowerJoin(@Query("startTime") long startTime);

    @GET("v1/activities/list/publish")
    Observable<JsonResponse<List<ActivityDetailVo>>> listPublish();

    @GET("v1/activities/list/waiting")
    Observable<JsonResponse<List<ActivityDetailVo>>> listWaiting();

    @GET("v1/activities/list/join")
    Observable<JsonResponse<List<ActivityDetailVo>>> listJoin();

    @GET("v1/activities/detail")
    Observable<JsonResponse<ActivityDetailVo>> detail(@Query("aid") long aid);

    @POST("v1/activities/create")
    Observable<JsonResponse> create(@Body ActivityCreateParam r);

    @POST("v1/activities/create-publish")
    Observable<JsonResponse> createAndPublish(@Body ActivityCreateParam r);

    @POST("v1/activities/update")
    Observable<JsonResponse> modify(@Body ActivityCreateParam req);

    @POST("v1/activities/apply-switch")
    Observable<JsonResponse> applySwitch(@Body ApplySwitchParam req);

    @GET("v1/activities/members/list")
    Observable<JsonResponse<List<Account>>> listMember(@Query("aid") long aid);
}
