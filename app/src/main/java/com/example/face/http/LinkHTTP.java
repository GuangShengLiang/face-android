package com.example.face.http;


import com.example.face.model.vo.ActivityDetailVo;
import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface LinkHTTP {

    @GET("v1/acitivities/list/friend")
    Observable<List<ActivityDetailVo>> listFriendAct();
}
