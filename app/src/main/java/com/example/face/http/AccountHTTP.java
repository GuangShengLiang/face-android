package com.example.face.http;


import com.example.face.model.Account;
import com.example.face.model.AccountDetail;

import com.example.face.model.Response;
import com.example.face.model.param.AccountParam;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {
    @GET("v1/mine/info/base")
    Observable<AccountDetail> baseInfo();

    @POST("v1/mine/info/base/update")
    Observable<Response> updateInfo(@Body AccountParam r);

    @GET("v1/account/search-mobile")
    Observable<Account> searchByMobile(@Query("mobile") String mobile);

    @GET("v1/account/info/base")
    Observable<AccountDetail> info(@Query("uid") int uid);

}
