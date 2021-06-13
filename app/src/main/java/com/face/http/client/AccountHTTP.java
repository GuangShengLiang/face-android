package com.face.http.client;

import com.face.http.model.JsonResponse;
import com.face.http.model.param.MyInfoUpdateParam;
import com.face.http.model.vo.AccountDetailVO;
import com.face.http.model.vo.AccountVO;
import com.face.http.model.vo.RelationVO;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {

    @GET("v1/myinfo")
    Observable<JsonResponse<AccountVO>> myinfo();

    @POST("v1/myinfo/update")
    Observable<JsonResponse> updateBaseInfo(@Body MyInfoUpdateParam param);

    @POST("v1/account/search")
    Observable<JsonResponse<RelationVO>> search(@Query("key") String key);

    @GET("v1/account/info")
    Observable<JsonResponse<AccountDetailVO>> info(@Query("targetUid") int targetUid);

}
