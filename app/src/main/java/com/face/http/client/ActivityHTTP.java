package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.vo.Account;
import com.face.http.model.param.ActivityParam;
import com.face.http.model.param.AidParam;
import com.face.http.model.vo.ActivityDetailVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ActivityHTTP {

    @GET("v1/activities/list/friend-join/previous")
    Observable<JsonResponse<List<ActivityDetailVo>>> listActivitiesFriendJoinPrevious(@Query("startAid") long startAid);

    @GET("v1/activities/list/friend-join/next")
    Observable<JsonResponse<List<ActivityDetailVo>>> listActivitiesFriendJoinNext(@Query("startAid") long startAid);

    @GET("v1/activities/list/publish")
    Observable<JsonResponse<List<ActivityDetailVo>>> listPublish();

    @GET("v1/activities/list/waiting")
    Observable<JsonResponse<List<ActivityDetailVo>>> listWaiting();

    @GET("v1/activities/list/finish")
    Observable<JsonResponse<List<ActivityDetailVo>>> listFinish();

    @GET("v1/activities/list/join")
    Observable<JsonResponse<List<ActivityDetailVo>>> listJoin();

    @GET("v1/activities/detail")
    Observable<JsonResponse<ActivityDetailVo>> detail(@Query("aid") long aid);

    @POST("v1/activities/create")
    Observable<JsonResponse> create(@Body ActivityParam r);

    @POST("v1/activities/update")
    Observable<JsonResponse> modify(@Body ActivityParam req);

    @POST("v1/activities/apply-stop")
    Observable<JsonResponse> applyStop(@Body AidParam req);

    @POST("v1/activities/start")
    Observable<JsonResponse> start(@Body AidParam req);

    @POST("v1/activities/finish")
    Observable<JsonResponse> finish(@Body AidParam req);

    @GET("v1/activities/members/list")
    Observable<JsonResponse<List<Account>>> listMember(@Query("aid") long aid);
}
