package com.face.http.client;


import com.face.http.model.vo.Account;
import com.face.http.model.vo.AccountDetail;

import com.face.http.model.JsonResponse;
import com.face.http.model.param.AccountParam;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {
    @GET("v1/mine/info/base")
    Observable<JsonResponse<AccountDetail>> baseInfo();

    @POST("v1/mine/info/base/update")
    Observable<JsonResponse> updateInfo(@Body AccountParam r);

    @GET("v1/account/search-mobile")
    Observable<JsonResponse<Account>> searchByMobile(@Query("mobile") String mobile);

    @GET("v1/account/info/base")
    Observable<JsonResponse<AccountDetail>> info(@Query("uid") int uid);

}
