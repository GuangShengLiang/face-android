package com.example.face.http;


import com.example.face.model.param.IdParam;
import com.example.face.model.Response;
import com.example.face.model.vo.InviteVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface InvitedHTTP {

    @POST("v1/invited/agree")
    Observable<Response> agree(@Body IdParam id);

    @POST("v1/invited/reject")
    Observable<Response> reject(@Body IdParam id);

    @GET("v1/invited/list")
    Observable<List<InviteVo>> listInvited();
}
