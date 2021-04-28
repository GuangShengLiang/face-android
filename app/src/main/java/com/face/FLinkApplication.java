package com.face;

import android.app.Application;

import android.content.Context;
import com.face.util.PreferencesUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orm.SugarContext;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class FLinkApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fresco.initialize(this);
        SugarContext.init(this);
        PreferencesUtil.getInstance().init(this);

//        RetrofitMock.setEnabled( true );

//        // 极光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//
//        // 极光IM
//        JMessageClient.setDebugMode(true);
//        JMessageClient.init(this);

        // 上拉下拉控件
        ClassicsHeader.REFRESH_HEADER_PULLING = ""; //" 下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_RELEASE = ""; // "释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = ""; // "正在刷新...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = ""; // "释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = ""; // "刷新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = ""; // "刷新失败";
        ClassicsHeader.REFRESH_HEADER_UPDATE = ""; // "上次更新 M-d HH:mm";

        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在加载"; // "正在加载..."
        ClassicsFooter.REFRESH_FOOTER_FINISH = ""; // "加载完成"
        ClassicsFooter.REFRESH_FOOTER_NOTHING = ""; // "全部加载完成"

        // 百度地图
//        SDKInitializer.initialize(this);
//        locationService = new LocationService(getApplicationContext());
    }

    public static Context getContext() {
        return context;
    }
}
