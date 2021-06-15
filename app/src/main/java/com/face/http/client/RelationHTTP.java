package com.face.http.client;


import com.face.http.model.JsonResponse;
import com.face.http.model.vo.RelationStatVO;
import com.face.http.model.vo.FriendshipVo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface RelationHTTP {

    /**
     * 朋友列表：互相关注的好友
     * @return
     */
    @GET("v1/relations/friends")
    Observable<JsonResponse<List<FriendshipVo>>> queryFriends();

    /**
     * 关注
     * @param targetUid 目标uid
     * @return
     */
    @POST("v1/relations/follow")
    Observable<JsonResponse> follow(@Query("targetUid") int targetUid);

    /**
     * 取消关注
     * @param targetUid 目标uid
     * @return
     */
    @POST("v1/relations/unfollow")
    Observable<JsonResponse> unfollow(@Query("targetUid") int targetUid);

    @GET("v1/relations/followers")
    Observable<JsonResponse<List<FriendshipVo>>> queryFollowers();

    @GET("v1/relations/fans")
    Observable<JsonResponse<List<FriendshipVo>>> queryFans();

    /**
     * 关注和粉丝统计
     * 关注列表
     * 粉丝列表
     * @return
     */
    @GET("v1/relations/stat")
    Observable<JsonResponse<RelationStatVO>> queryStat();

}
