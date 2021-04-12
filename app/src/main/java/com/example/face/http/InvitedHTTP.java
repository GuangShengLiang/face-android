package com.example.face.http;


import com.example.face.model.IdReq;
import com.example.face.model.Response;
import com.example.face.model.act.ActInvitedResp;

import java.util.List;

import com.example.face.model.act.ActivityDetail;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InvitedHTTP {

    @POST("v1/invited/agree")
    Observable<Response> agree(@Body IdReq id);

    @POST("v1/invited/reject")
    Observable<Response> reject(@Body IdReq id);

    @GET("v1/invited/list")
    Observable<List<ActivityDetail>> listInvited();
}
