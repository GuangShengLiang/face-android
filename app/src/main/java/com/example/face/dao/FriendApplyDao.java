package com.example.face.dao;

import com.example.face.Constant;
import com.example.face.entity.FriendApply;

import java.util.ArrayList;
import java.util.List;

public class FriendApplyDao {

    public List<FriendApply> listFriend() {
        List<FriendApply> friendApplyList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FriendApply f = new FriendApply();
//            f.setApplyId("1"+i);
//            f.setApplyRemark("mark"+i);
//            f.setFromUserId("u");
//            f.setFromUserNickName("a"+i);
//            f.setId(i);
            f.setUid(i);
            f.setNickName("a" + i);
            if (i == 2) {
                f.setStatus(Constant.FRIEND_APPLY_STATUS_ACCEPT);
            }
            friendApplyList.add(f);
        }
        return friendApplyList;
    }
}
