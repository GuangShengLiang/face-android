package com.example.face.http;


import com.example.face.model.IdReq;
import com.example.face.model.act.ActInviteResp;
import com.example.face.model.act.ActReq;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InviteHTTP {

    @POST("v1/invites/invite")
    Observable<Void> invite(@Body ActReq req);

    @POST("v1/invites/cancel")
    Observable<Void> cancel(@Body IdReq id);

    @GET("v1/invites/list/aid")
    Observable<List<ActInviteResp>> listInviteByAid(@Query("aid") long aid);
}
