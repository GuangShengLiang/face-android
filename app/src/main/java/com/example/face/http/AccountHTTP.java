package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.AccountReq;
import com.example.face.model.Message;

import java.util.List;

import com.example.face.model.Response;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {
    @GET("v1/mine/info/base")
    Observable<Account> baseInfo();

    @POST("v1/mine/info/base/update")
    Observable<Response> updateInfo(@Body AccountReq r);

    @GET("v1/account/search-mobile")
    Observable<Account> searchByMobile(@Query("mobile") String mobile);

    @GET("v1/msg/list")
    Observable<List<Message>> listMessage();

    @GET("v1/account/info/base")
    Observable<Account> info(@Query("uid") int uid);

}
