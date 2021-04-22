package com.example.face.http;


import com.example.face.model.param.LoginParam;
import com.example.face.model.vo.LoginVo;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PassportHTTP {

    @POST("v1/account/login")
    Observable<LoginVo> login(@Body LoginParam r);

}
