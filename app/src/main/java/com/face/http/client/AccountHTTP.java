package com.face.http.client;

import com.face.http.model.JsonResponse;
import com.face.http.model.param.MyInfoUpdateParam;
import com.face.http.model.vo.*;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountHTTP {

    /**
     * 我的个人资料
     *
     * @return
     */
    @GET("v1/myinfo")
    Observable<JsonResponse<MyInfoVO>> myInfo();

    /**
     * 更新个人资料
     *
     * @param param
     * @return
     */
    @POST("v1/myinfo/update")
    Observable<JsonResponse> updateMyInfo(@Body MyInfoUpdateParam param);

    /**
     * 获取上传头像的token
     *
     * @return
     */
    @GET("v1/myinfo/avatar/upload-token")
    Observable<JsonResponse<String>> getAvatarUploadToken();

    /**
     * 搜索用户
     *
     * @param key 用户称呼或者uid
     * @return
     */
    @POST("v1/account/search")
    Observable<JsonResponse<FriendshipVo>> search(@Query("key") String key);


    /**
     * 查询用户信息
     *
     * @param targetUid 目标uid
     * @return
     */
    @GET("v1/account/info")
    Observable<JsonResponse<AccountInfoVO>> info(@Query("targetUid") int targetUid);

}
