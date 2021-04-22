package com.example.face.http;


import com.example.face.model.param.IdParam;
import com.example.face.model.Response;
import com.example.face.model.param.AidParam;
import com.example.face.model.vo.ApplyVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ApplyHTTP {

    @GET("v1/applies/list/aid")
    Observable<List<ApplyVo>> listApplyByAid(@Query("aid") long aid);

    @GET("v1/applies/is-need-apply")
    Observable<Boolean> isNeedApply(@Query("aid") long aid);

    @GET("v1/applies/list")
    Observable<List<ApplyVo>> listApply();

    @POST("v1/applies/apply")
    Observable<Response> apply(@Body AidParam r);

    @POST("v1/applies/agree")
    Observable<Response> agree(@Body IdParam r);

    @POST("v1/applies/cancel")
    Observable<Response> cancel(@Body IdParam r);

    @POST("v1/applies/reject")
    Observable<Response> reject(@Body IdParam r);
}
