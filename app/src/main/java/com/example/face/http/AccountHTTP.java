package com.example.face.http;



import com.example.face.model.Account;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountHTTP {

    @GET("login")
    String login(String mobike, String vcode);

    @POST("register")
    String register(String mobike, String vcode);

    @GET("my_info")
    Observable<Account> myInfo();
}
