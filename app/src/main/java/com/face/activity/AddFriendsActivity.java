package com.face.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import face.R;
import com.face.dao.entity.User;
import com.face.http.model.QrCodeContent;
import com.face.util.PreferencesUtil;

public class AddFriendsActivity extends FragmentActivity implements View.OnClickListener {

    private static final int SCAN_REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION = 110;

    private RelativeLayout mSearchRl;

    private RelativeLayout mRadarRl;
    private RelativeLayout mScanRl;


    private LinearLayout mMyInfoLl;
    private TextView mWxIdTv;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        mUser = PreferencesUtil.getInstance().getUser();
        initView();
    }

    private void initView() {
        mSearchRl = findViewById(R.id.rl_search);
        mRadarRl = findViewById(R.id.rl_radar);
        mScanRl = findViewById(R.id.rl_scan);

        mMyInfoLl = findViewById(R.id.ll_my_info);

        mWxIdTv = findViewById(R.id.tv_wx_id);

        mWxIdTv.setText("我的微信号：" + mUser.getUserWxId());
        mSearchRl.setOnClickListener(this);
        mRadarRl.setOnClickListener(this);
        mScanRl.setOnClickListener(this);
        mMyInfoLl.setOnClickListener(this);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                startActivity(new Intent(this, AddFriendsBySearchActivity.class));
                break;

            case R.id.rl_scan:
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(AddFriendsActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddFriendsActivity.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                    } else {
                        startScanActivity();
                    }
                } else {
                    startScanActivity();
                }
                break;

            case R.id.ll_my_info:
                startActivity(new Intent(this, QrCodeActivity.class));
                break;
        }
    }

    private void startScanActivity() {
//        Intent intent = new Intent(AddFriendsActivity.this, CaptureActivity.class);
//        intent.putExtra(CaptureActivity.USE_DEFUALT_ISBN_ACTIVITY, true);
//        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startScanActivity();
                } else {
                    Toast.makeText(AddFriendsActivity.this, "请手动打开摄像头权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int checkSelfPermission(String permission) {
        return super.checkSelfPermission(permission);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SCAN_REQUEST_CODE) {
                String isbn = data.getStringExtra("CaptureIsbn");
                if (!TextUtils.isEmpty(isbn)) {
                    if (isbn.contains("http")) {
                        Intent intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra(WebViewActivity.RESULT, isbn);
                        startActivity(intent);
                    } else {
                        try {
                            QrCodeContent qrCodeContent = JSON.parseObject(isbn, QrCodeContent.class);
                            if (QrCodeContent.QR_CODE_TYPE_USER.equals(qrCodeContent.getType())) {
                                startActivity(new Intent(this, UserInfoActivity.class).
                                        putExtra("userId", String.valueOf(qrCodeContent.getContentMap().get("userId"))));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
