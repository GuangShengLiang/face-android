package com.example.face.http;


import com.example.face.model.LoginReq;
import com.example.face.model.LoginResp;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PassportHTTP {

    @POST("v1/account/login")
    Observable<LoginResp> login(@Body LoginReq r);

}
