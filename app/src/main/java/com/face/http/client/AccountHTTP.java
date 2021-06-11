package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.param.AccountBaseParam;
import com.face.http.model.param.AccountDetailParam;
import com.face.http.model.vo.Account;
import com.face.http.model.vo.AccountDetail;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {
    @GET("v1/mine/info/base")
    Observable<JsonResponse<AccountDetail>> getMineBaseInfo();

    @GET("v1/mine/info/detail")
    Observable<JsonResponse<AccountDetail>> getMineDetail();

    @POST("v1/mine/info/base/update")
    Observable<JsonResponse> updateBaseInfo(@Body AccountBaseParam param);

    @POST("v1/mine/info/detail/update")
    Observable<JsonResponse> updateDetail(@Body AccountDetailParam param);

    @GET("v1/account/search-mobile")
    Observable<JsonResponse<Account>> searchByMobile(@Query("mobile") String mobile);

    @GET("v1/account/info/detail")
    Observable<JsonResponse<AccountDetail>> getDetail(@Query("uid") int uid);

}
