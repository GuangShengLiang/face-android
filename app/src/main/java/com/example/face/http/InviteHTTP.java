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

    @POST("invite")
    Observable<Void> invite(@Body ActReq req);

    @POST(value = "invite/cancel")
    Observable<Void> cancel(@Body IdReq id);

    @GET(value = "invite/aid/list")
    Observable<List<ActInviteResp>> listInviteByAid(@Query("aid") long aid);
}
