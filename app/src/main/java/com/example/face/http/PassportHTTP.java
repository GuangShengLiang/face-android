package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.AccountReq;
import com.example.face.model.LoginReq;
import com.example.face.model.Message;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface PassportHTTP {

    @POST("v1/account/login")
    Observable<String> login(@Body LoginReq r);

}
