package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.Response;
import com.example.face.model.act.ActReq;
import com.example.face.model.act.ActivityDetail;

import java.util.List;

import com.example.face.model.act.AidReq;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ActivityHTTP {

    @GET("v1/activities/list/friend")
    Observable<List<ActivityDetail>> listFriendRefresh(@Query("aid") long aid);

    @GET("v1/activities/list/friend-next-page")
    Observable<List<ActivityDetail>> listFriendNext(@Query("aid") long aid);

    @GET("v1/activities/search")
    Observable<Object> search(int fromId);

    @GET("v1/activities/list/publish")
    Observable<List<ActivityDetail>> listPublish();

    @GET("v1/activities/list/waiting")
    Observable<List<ActivityDetail>> listWaiting();

    @GET("v1/activities/list/finish")
    Observable<List<ActivityDetail>> listFinish();

    @GET("v1/activities/list/join")
    Observable<List<ActivityDetail>> listJoin();

    @GET("v1/activities/detail")
    Observable<ActivityDetail> detail(@Query("aid") long aid);

    @POST("v1/activities/create")
    Observable<Response> create(@Body ActReq r);

    @POST("v1/activities/update")
    Observable<Response> modify(@Body ActReq req);

    @POST("v1/activities/apply-stop")
    Observable<Response> applyStop(@Body AidReq req);

    @POST("v1/activities/start")
    Observable<Response> start(@Body AidReq req);

    @POST("v1/activities/finish")
    Observable<Response> finish(@Body AidReq req);

    @GET("v1/activities/members/list")
    Observable<List<Account>> listMember(@Query("aid") long aid);
}
