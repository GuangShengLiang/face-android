package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.AccountReq;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.*;

public interface AccountHTTP {

    @GET("login")
    String login(String mobike, String vcode);

    @POST("register")
    String register(String mobike, String vcode);

    @GET("mine/info")
    Observable<Account> myInfo();

    @GET("search_mobile")
    Observable<Account> searchMobile(@Query("mobile") String mobike);

    @GET("info")
    Observable<Account> info(@Query("uid") int uid);

    @PUT("info")
    Observable<Void> updateInfo(@Body AccountReq r);
}
