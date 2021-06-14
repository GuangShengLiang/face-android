package com.face.http.client;

import com.face.http.model.JsonResponse;
import com.face.http.model.param.MyInfoUpdateParam;
import com.face.http.model.vo.AccountInfoVO;
import com.face.http.model.vo.AccountVO;
import com.face.http.model.vo.FriendshipVo;
import com.face.http.model.vo.MyInfoVO;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {

    @GET("v1/myinfo")
    Observable<JsonResponse<MyInfoVO>> myInfo();

    @POST("v1/myinfo/update")
    Observable<JsonResponse> updateMyInfo(@Body MyInfoUpdateParam param);

    @GET("v1/myinfo/avatar/upload-token")
    Observable<JsonResponse<String>> getAvatarUploadToken();

    @POST("v1/account/search")
    Observable<JsonResponse<FriendshipVo>> search(@Query("key") String key);

    @GET("v1/account/info")
    Observable<JsonResponse<AccountInfoVO>> info(@Query("targetUid") int targetUid);

}
