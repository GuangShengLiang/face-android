package com.example.face.http;


import com.example.face.model.param.IdParam;
import com.example.face.model.Response;
import com.example.face.model.param.InviteParam;
import com.example.face.model.vo.InviteVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface InviteHTTP {

    @POST("v1/invites/invite")
    Observable<Response> invite(@Body InviteParam req);

    @POST("v1/invites/cancel")
    Observable<Response> cancel(@Body IdParam id);

    @GET("v1/invites/list/aid")
    Observable<List<InviteVo>> listInviteByAid(@Query("aid") long aid);
}
