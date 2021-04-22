package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.Response;
import com.example.face.model.param.ActivityParam;
import com.example.face.model.param.AidParam;
import com.example.face.model.vo.ActivityDetailVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ActivityHTTP {

    @GET("v1/activities/list/friend-join/previous")
    Observable<List<ActivityDetailVo>> listActivitiesFriendJoinPrevious(@Query("startAid") long startAid);

    @GET("v1/activities/list/friend-join/next")
    Observable<List<ActivityDetailVo>> listActivitiesFriendJoinNext(@Query("startAid") long startAid);

    @GET("v1/activities/list/publish")
    Observable<List<ActivityDetailVo>> listPublish();

    @GET("v1/activities/list/waiting")
    Observable<List<ActivityDetailVo>> listWaiting();

    @GET("v1/activities/list/finish")
    Observable<List<ActivityDetailVo>> listFinish();

    @GET("v1/activities/list/join")
    Observable<List<ActivityDetailVo>> listJoin();

    @GET("v1/activities/detail")
    Observable<ActivityDetailVo> detail(@Query("aid") long aid);

    @POST("v1/activities/create")
    Observable<Response> create(@Body ActivityParam r);

    @POST("v1/activities/update")
    Observable<Response> modify(@Body ActivityParam req);

    @POST("v1/activities/apply-stop")
    Observable<Response> applyStop(@Body AidParam req);

    @POST("v1/activities/start")
    Observable<Response> start(@Body AidParam req);

    @POST("v1/activities/finish")
    Observable<Response> finish(@Body AidParam req);

    @GET("v1/activities/members/list")
    Observable<List<Account>> listMember(@Query("aid") long aid);
}
