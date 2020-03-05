package com.example.face.http;


import com.example.face.model.ActApply;
import com.example.face.model.AidReq;
import com.example.face.model.IdReq;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.List;

public interface ApplyHTTP {

    @GET(value = "apply/aid/list")
    Observable<List<ActApply>> listApply(@Query("aid") long aid);

    @POST(value = "apply")
    Observable<Void> apply(@Body AidReq r);

    @PUT(value = "apply/agree")
    Observable<Void> agree(@Body() IdReq r);
}
