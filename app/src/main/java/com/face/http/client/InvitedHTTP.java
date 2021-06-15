package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.param.IdParam;
import com.face.http.model.vo.InviteVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface InvitedHTTP {

    @POST("v1/invited/agree")
    Observable<JsonResponse> agree(@Body IdParam id);

    @POST("v1/invited/reject")
    Observable<JsonResponse> reject(@Body IdParam id);

    @GET("v1/invited/list")
    Observable<JsonResponse<List<InviteVo>>> listInvited();
}
