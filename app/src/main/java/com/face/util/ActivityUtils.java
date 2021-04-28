package com.face.util;

import android.content.Context;
import android.content.Intent;

import com.face.activity.UserInfoActivity;

public class ActivityUtils {

    public static void openUserInfoActivity(Context context, int uid) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra("ruid", uid);
        context.startActivity(intent);
    }
}
