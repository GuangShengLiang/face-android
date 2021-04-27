package com.face.activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.face.Constant;
import face.R;
import com.face.dao.entity.User;
import com.face.util.PreferencesUtil;
import com.facebook.drawee.view.SimpleDraweeView;

public class QrCodeActivity extends BaseActivity {
    private SimpleDraweeView mAvatarSdv;
    private TextView mNickNameTv;
    private ImageView mSexIv;
    private SimpleDraweeView mQrCodeSdv;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        mUser = PreferencesUtil.getInstance().getUser();
        initView();
    }

    private void initView() {
        mAvatarSdv = findViewById(R.id.sdv_avatar);
        mNickNameTv = findViewById(R.id.tv_nick_name);
        mSexIv = findViewById(R.id.iv_sex);
        mQrCodeSdv = findViewById(R.id.sdv_qr_code);

        if (!TextUtils.isEmpty(mUser.getUserAvatar())) {
            mAvatarSdv.setImageURI(Uri.parse(mUser.getUserAvatar()));
        }
        mNickNameTv.setText(mUser.getUserNickName());

        if (Constant.USER_SEX_MALE.equals(mUser.getUserSex())) {
            mSexIv.setImageResource(R.mipmap.ic_sex_male);
        } else if (Constant.USER_SEX_FEMALE.equals(mUser.getUserSex())) {
            mSexIv.setImageResource(R.mipmap.ic_sex_female);
        } else {
            mSexIv.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(mUser.getUserQrCode())) {
            mQrCodeSdv.setImageURI(Uri.parse(mUser.getUserQrCode()));
        }
    }

    public void back(View view) {
        finish();
    }
}
