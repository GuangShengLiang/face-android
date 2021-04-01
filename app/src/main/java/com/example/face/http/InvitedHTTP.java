package com.example.face.http;


import com.example.face.model.IdReq;
import com.example.face.model.act.ActInvitedResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InvitedHTTP {

    @POST("v1/invited/agree")
    Observable<Void> agree(@Body IdReq id);

    @POST("v1/invited/reject")
    Observable<Void> reject(@Body IdReq id);

    @GET("v1/invited/list")
    Observable<List<ActInvitedResp>> listInvited();
}
