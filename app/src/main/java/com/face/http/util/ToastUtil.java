package com.face.http.util;

import android.view.Gravity;
import android.widget.Toast;
import com.face.FLinkApplication;
import es.dmoral.toasty.Toasty;

public class ToastUtil {

    public static void showShort(String content) {
        showToast(content, Toast.LENGTH_SHORT);
    }

    public static void showLong(String content) {
        showToast(content, Toast.LENGTH_LONG);
    }

    private static void showToast(String content, int duration) {
        Toast t = Toasty.success(FLinkApplication.getContext(), content, duration, true);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}
