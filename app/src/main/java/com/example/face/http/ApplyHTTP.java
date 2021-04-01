package com.example.face.http;


import com.example.face.model.IdReq;
import com.example.face.model.act.AidReq;
import com.example.face.model.act.ApplyResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApplyHTTP {

    @GET("v1/activity/applies/list/aid")
    Observable<List<ApplyResp>> listApplyByAid(@Query("aid") long aid);

    @GET("v1/activity/applies/is-need-apply")
    Observable<Boolean> isNeedApply(@Query("aid") long aid);

    @GET("v1/activity/applies/list")
    Observable<List<ApplyResp>> listApply();

    @POST("v1/activity/applies/create")
    Observable<Void> apply(@Body AidReq r);

    @PUT("v1/activity/applies/agree")
    Observable<Void> agree(@Body IdReq r);

    @PUT("v1/activity/applies/cancel")
    Observable<Void> cancel(@Body IdReq r);

    @PUT("v1/activity/applies/reject")
    Observable<Void> reject(@Body IdReq r);
}
