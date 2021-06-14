package com.face.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face.enums.account.GenderEnum;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import face.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.json.JSONObject;

public class CommonUtil {
    /**
     * 获取用户header
     * 如: "张三"的header就是"Z"，用于用户默认分组
     *
     * @param userNickName 用户昵称
     * @return 用户header
     */
    public static String setUserHeader(String userNickName) {
        StringBuffer stringBuffer = new StringBuffer();
        // 将汉字拆分成一个个的char
        char[] chars = userNickName.toCharArray();
        // 遍历汉字的每一个char
        for (int i = 0; i < chars.length; i++) {

            try {
                HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

                // UPPERCASE：大写  (ZHONG)
                format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//输出大写

                // WITHOUT_TONE：无音标  (zhong)
                format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                // 汉字的所有读音放在一个pinyins数组
                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                if (pinyins == null) {
                    stringBuffer.append(chars[i]);
                } else {
                    stringBuffer.append(pinyins[0]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
        }
        char firstChar = stringBuffer.toString().toUpperCase().charAt(0);
        // 不是A-Z字母
        if (firstChar > 90 || firstChar < 65) {
            return "#";
        } else { // 代表是A-Z
            return String.valueOf(firstChar);
        }
    }

    public static final String PATH_HEAD = "file:///android_asset/";


    public static void loadAvatar(Context mContext, ImageView avatar, String path) {
        RequestOptions options = new RequestOptions();
//        File file = new File(  "app/src/main/assets/header/boy_01.jpeg");

        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
//                .load(Constant.BASE_URL_PICTURE + path)
                .load(PATH_HEAD + "header/boy_01.jpeg")
                .apply(options)
                .into(avatar);
    }

    public static void loadAvatar(Context mContext, ImageView avatar, int gender) {
        String avatarStr = PATH_HEAD + "header/";
        if (GenderEnum.man.getCode() == gender) {
            avatarStr += "boy_01.jpeg";
        } else {
            avatarStr += "girl_01.jpeg";
        }
        Glide.with(mContext)
                .load(avatarStr)
                .into(avatar);
    }

    public void uploadAvatar() {
        HTTP.account.getAvatarUploadToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<String>>() {
                    @Override
                    public void onNext(JsonResponse<String> token) {

                    }
                });
    }

    public void upload(String token) {
        Configuration config = new Configuration.Builder()
//                .zone(FixedZone.zone1)
                .build();
        UploadManager uploadManager = new UploadManager(config);
//        data = <File 对象 或 文件路径 或 字节数组 或 数据流 或 Uri 资源>
//                String key = <指定七牛服务上的文件名，或 null>;
        String data="";
        uploadManager.put(data, null, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res 包含 hash、key 等信息，具体字段取决于上传策略的设置
                        if (info.isOK()) {
                            Log.i("qiniu", "Upload Success");
                        } else {
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把 info 信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);
    }
}
