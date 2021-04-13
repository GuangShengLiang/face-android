package com.example.face.http;


import com.example.face.model.IdReq;
import com.example.face.model.Response;
import com.example.face.model.act.ActInviteResp;
import com.example.face.model.act.InviteReq;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface InviteHTTP {

    @POST("v1/invites/invite")
    Observable<Response> invite(@Body InviteReq req);

    @POST("v1/invites/cancel")
    Observable<Response> cancel(@Body IdReq id);

    @GET("v1/invites/list/aid")
    Observable<List<ActInviteResp>> listInviteByAid(@Query("aid") long aid);
}
