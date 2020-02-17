package com.example.face.http;


import com.chaoliu.mock.annotation.MOCK;
import com.example.face.model.Account;
import com.example.face.model.AccountReq;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AccountHTTP {

    @GET("login")
    String login(String mobike, String vcode);

    @POST("register")
    String register(String mobike, String vcode);

    @MOCK(value = "mock/mine_info.json",enable = true)
    @GET("mine/info")
    Observable<Account> myInfo();

    @GET("info")
    Observable<Account> info(int uid);

    @PUT("info")
    Observable updateInfo(AccountReq r);
}
