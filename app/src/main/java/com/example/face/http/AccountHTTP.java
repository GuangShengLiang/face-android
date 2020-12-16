package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.AccountReq;
import com.example.face.model.Message;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AccountHTTP {

    @GET("login")
    String login(String mobike, String vcode);

    @POST("register")
    String register(String mobike, String vcode);

    @GET("mine/info")
    Observable<Account> myInfo();

    @GET("search_mobile")
    Observable<Account> searchMobile(@Query("mobile") String mobike);

    @GET("msg/list")
    Observable<List<Message>> listMessage();

    @GET("info")
    Observable<Account> info(@Query("uid") int uid);

    @PUT("info")
    Observable<Void> updateInfo(@Body AccountReq r);
}
