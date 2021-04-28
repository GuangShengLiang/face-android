package com.face.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.face.Constant;
import face.R;
import com.face.dao.entity.User;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.vo.AccountDetail;
import com.face.http.model.param.AccountParam;
import com.face.util.CommonUtil;
import com.face.util.FileUtil;
import com.face.util.PreferencesUtil;
import com.face.widget.LoadingDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MyUserInfoActivity extends BaseActivity {
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

    private static final int UPDATE_AVATAR_BY_ALBUM = 2;
    private static final int UPDATE_USER_NICK_NAME = 3;
    private static final int UPDATE_USER_WX_ID = 4;
    private static final int UPDATE_USER_SIGN = 5;

    LoadingDialog dialog;
    User user;
    AccountDetail acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        context = this;
        ButterKnife.bind(this);
        PreferencesUtil.getInstance().init(this);
        user = PreferencesUtil.getInstance().getUser();
        dialog = new LoadingDialog(MyUserInfoActivity.this);
    }

    void initView() {
        HTTP.account.baseInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<AccountDetail>>() {
                    @Override
                    public void onNext(JsonResponse<AccountDetail> a) {
                        acc = a.getData();
                        PreferencesUtil.saveAccount(context, acc);
                        mNickNameTv.setText(acc.getNickName());
                        mWxIdTv.setText(String.valueOf(acc.getUid()));
                        CommonUtil.loadAvatar(mContext, mAvatarSdv, acc.getAvatar());
                        mSexTv.setText(acc.getGenderName());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
//                                    updateUserAvatar(user.getUserId(), newAvatar);
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
            AccountParam req = new AccountParam();
            req.setGender(1);
            HTTP.account.updateInfo(req)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<JsonResponse>() {
                        @Override
                        public void onNext(JsonResponse v) {
                            mSexTv.setText(mMaleTv.getText());
                            sexDialog.dismiss();
                        }
                    });
        });
        TextView mFemaleTv = window.findViewById(R.id.tv_content2);
        mFemaleTv.setText(getString(R.string.sex_female));
        mFemaleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountParam req = new AccountParam();
                req.setGender(2);
                HTTP.account.updateInfo(req)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse>() {
                            @Override
                            public void onNext(JsonResponse v) {
                                mSexTv.setText(mFemaleTv.getText());
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

    // 运行时添加权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
