package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.param.LoginParam;
import com.face.http.model.vo.LoginVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PassportHTTP {

    @POST("v1/account/login")
    Observable<JsonResponse<LoginVo>> login(@Body LoginParam r);

}
