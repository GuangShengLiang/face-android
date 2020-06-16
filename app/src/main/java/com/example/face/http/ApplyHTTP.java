package com.example.face.http;


import com.example.face.model.act.ApplyResp;
import com.example.face.model.act.AidReq;
import com.example.face.model.IdReq;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.List;

public interface ApplyHTTP {

    @GET(value = "apply/aid/list")
    Observable<List<ApplyResp>> listApplyByAid(@Query("aid") long aid);

    @GET(value = "apply/list")
    Observable<List<ApplyResp>> listApply();

    @POST(value = "apply")
    Observable<Void> apply(@Body AidReq r);

    @PUT(value = "apply/agree")
    Observable<Void> agree(@Body IdReq r);
}
