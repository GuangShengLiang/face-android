package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.param.IdParam;
import com.face.http.model.param.AidParam;
import com.face.http.model.vo.ApplyVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ApplyHTTP {

    @GET("v1/applies/list/aid")
    Observable<JsonResponse<List<ApplyVo>>> listApplyByAid(@Query("aid") long aid);

    @GET("v1/applies/is-need-apply")
    Observable<JsonResponse<Boolean>> isNeedApply(@Query("aid") long aid);

    @GET("v1/applies/list")
    Observable<JsonResponse<List<ApplyVo>>> listApply();

    @POST("v1/applies/apply")
    Observable<JsonResponse> apply(@Body AidParam r);

    @POST("v1/applies/agree")
    Observable<JsonResponse> agree(@Body IdParam r);

    @POST("v1/applies/cancel")
    Observable<JsonResponse> cancel(@Body IdParam r);

    @POST("v1/applies/reject")
    Observable<JsonResponse> reject(@Body IdParam r);
}
