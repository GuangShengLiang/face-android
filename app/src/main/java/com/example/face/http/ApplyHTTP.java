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

    @GET("v1/applies/list/aid")
    Observable<List<ApplyResp>> listApplyByAid(@Query("aid") long aid);

    @GET("v1/applies/is-need-apply")
    Observable<Boolean> isNeedApply(@Query("aid") long aid);

    @GET("v1/applies/list")
    Observable<List<ApplyResp>> listApply();

    @POST("v1/applies/apply")
    Observable<Void> apply(@Body AidReq r);

    @POST("v1/applies/agree")
    Observable<Void> agree(@Body IdReq r);

    @POST("v1/applies/cancel")
    Observable<Void> cancel(@Body IdReq r);

    @POST("v1/applies/reject")
    Observable<Void> reject(@Body IdReq r);
}
