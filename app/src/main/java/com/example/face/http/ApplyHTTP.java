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

    @GET(value = "apply/aid/list")
    Observable<List<ApplyResp>> listApplyByAid(@Query("aid") long aid);

    @GET(value = "apply/is_need_apply")
    Observable<Boolean> isNeedApply(@Query("aid") long aid);

    @GET(value = "apply/list")
    Observable<List<ApplyResp>> listApply();

    @POST(value = "apply")
    Observable<Void> apply(@Body AidReq r);

    @PUT(value = "apply/agree")
    Observable<Void> agree(@Body IdReq r);

    @PUT(value = "apply/cancel")
    Observable<Void> cancel(@Body IdReq r);

    @PUT(value = "apply/reject")
    Observable<Void> reject(@Body IdReq r);
}
