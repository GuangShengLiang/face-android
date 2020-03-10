package com.example.face.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.face.Constant;
import com.example.face.R;
import com.example.face.entity.User;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Account;
import com.example.face.model.AccountReq;
import com.example.face.util.CommonUtil;
import com.example.face.util.FileUtil;
import com.example.face.util.PreferencesUtil;
import com.example.face.util.VolleyUtil;
import com.example.face.widget.LoadingDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUserInfoActivity extends BaseActivity {

    private static final int WRITE_PERMISSION = 0x01;

    @BindView(R.id.tv_nick_name)
    TextView mNickNameTv;
    @BindView(R.id.tv_wx_id)
    TextView mWxIdTv;
    @BindView(R.id.tv_sex)
    TextView mSexTv;
    @BindView(R.id.tv_sign)
    TextView mSignTv;
    @BindView(R.id.sdv_avatar)
    SimpleDraweeView mAvatarSdv;
    Context context;

    private VolleyUtil volleyUtil;

    private static final int UPDATE_AVATAR_BY_ALBUM = 2;
    private static final int UPDATE_USER_NICK_NAME = 3;
    private static final int UPDATE_USER_WX_ID = 4;
    private static final int UPDATE_USER_SIGN = 5;

    LoadingDialog dialog;
    User user;
    Account acc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        context = this;
        ButterKnife.bind(this);
        volleyUtil = VolleyUtil.getInstance(this);
        PreferencesUtil.getInstance().init(this);
        user = PreferencesUtil.getInstance().getUser();
        dialog = new LoadingDialog(MyUserInfoActivity.this);

        HTTP.account.myInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Account>() {
                    @Override
                    public void onNext(Account a) {
                        acc = a;
                        PreferencesUtil.saveAccount(context, a);
                        mNickNameTv.setText(a.getNickName());
                        mWxIdTv.setText(String.valueOf(a.getUid()));
                        CommonUtil.loadAvatar(mContext, mAvatarSdv, a.getAvatar());
                        mSexTv.setText(a.getGenderName());
                    }
                });
    }

    public void back(View view) {
        finish();
    }

    @OnClick(R.id.rl_nick_name)
    void showNickName() {
        startActivityForResult(new Intent(this, NickNameActivity.class), UPDATE_USER_NICK_NAME);
    }

    @OnClick(R.id.rl_qr_code)
    void showQrCode() {
        startActivity(new Intent(this, QrCodeActivity.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            final User user = PreferencesUtil.getInstance().getUser();
            switch (requestCode) {
                case UPDATE_AVATAR_BY_ALBUM:
                    if (data != null) {
                        Uri uri = data.getData();
                        final String filePath = FileUtil.getFilePathByUri(this, uri);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                List<String> imageList = FileUtil.uploadFile(Constant.FILE_UPLOAD_URL, filePath);
                                if (null != imageList && imageList.size() > 0) {
                                    String newAvatar = Constant.FILE_BASE_URL + imageList.get(0);
                                    updateUserAvatar(user.getUserId(), newAvatar);
                                }
                            }
                        }).start();

                        mAvatarSdv.setImageURI(uri);
                    }
                    break;
                case UPDATE_USER_NICK_NAME:
                    // 昵称
                    mNickNameTv.setText(user.getUserNickName());
                    break;
                case UPDATE_USER_WX_ID:
                    // 微信号
                    mWxIdTv.setText(user.getUserWxId());
                    break;
                case UPDATE_USER_SIGN:
                    // 个性签名
                    mSignTv.setText(user.getUserSign());
                    break;
            }
        }
    }

    @OnClick(R.id.rl_sex)
    void showSexDialog() {
        final AlertDialog sexDialog = new AlertDialog.Builder(this).create();
        sexDialog.show();
        Window window = sexDialog.getWindow();
        window.setContentView(R.layout.dialog_alert);
        LinearLayout mTitleLl = window.findViewById(R.id.ll_title);
        mTitleLl.setVisibility(View.VISIBLE);
        TextView mTitleTv = window.findViewById(R.id.tv_title);
        mTitleTv.setText(getString(R.string.sex));
        TextView mMaleTv = window.findViewById(R.id.tv_content1);
        mMaleTv.setText(getString(R.string.sex_male));
        mMaleTv.setOnClickListener(view -> {
            dialog.setMessage(getString(R.string.saving));
            dialog.show();
            AccountReq req = new AccountReq();
            req.setGender(1);
            HTTP.account.updateInfo(req)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<Void>() {
                        @Override
                        public void onNext(Void v) {
                            dialog.dismiss();
                            sexDialog.dismiss();
                        }
                    });
        });
        TextView mFemaleTv = window.findViewById(R.id.tv_content2);
        mFemaleTv.setText(getString(R.string.sex_female));
        mFemaleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage(getString(R.string.saving));
                dialog.show();
                AccountReq req = new AccountReq();
                req.setGender(2);
                HTTP.account.updateInfo(req)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<Void>() {
                            @Override
                            public void onNext(Void v) {
                                dialog.dismiss();
                                sexDialog.dismiss();
                            }
                        });
            }
        });
    }

    @OnClick(R.id.rl_avatar)
    void showPhotoDialog() {
        final AlertDialog photoDialog = new AlertDialog.Builder(this).create();
        photoDialog.show();
        Window window = photoDialog.getWindow();
        window.setContentView(R.layout.dialog_alert);
        TextView mTakePicTv = window.findViewById(R.id.tv_content1);
        TextView mAlbumTv = window.findViewById(R.id.tv_content2);
        mTakePicTv.setText("拍照");
        mTakePicTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mAlbumTv.setText("相册");
        mAlbumTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, UPDATE_AVATAR_BY_ALBUM);
                photoDialog.dismiss();
            }
        });

    }


    private void updateUserAvatar(String userId, final String userAvatar) {
        String url = Constant.BASE_URL + "users/" + userId + "/userAvatar";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userAvatar", userAvatar);

        volleyUtil.httpPutRequest(url, paramMap, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                user.setUserAvatar(userAvatar);
                PreferencesUtil.getInstance().setUser(user);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof NetworkError) {
                    Toast.makeText(MyUserInfoActivity.this, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
                    return;
                } else if (volleyError instanceof TimeoutError) {
                    Toast.makeText(MyUserInfoActivity.this, R.string.network_time_out, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    // 运行时添加权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void requestWritePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
        }
    }
}
