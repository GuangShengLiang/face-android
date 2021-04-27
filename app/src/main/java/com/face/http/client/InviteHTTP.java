package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.param.IdParam;
import com.face.http.model.param.InviteParam;
import com.face.http.model.vo.InviteVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface InviteHTTP {

    @POST("v1/invites/invite")
    Observable<JsonResponse> invite(@Body InviteParam req);

    @POST("v1/invites/cancel")
    Observable<JsonResponse> cancel(@Body IdParam id);

    @GET("v1/invites/list/aid")
    Observable<JsonResponse<List<InviteVo>>> listInviteByAid(@Query("aid") long aid);
}
