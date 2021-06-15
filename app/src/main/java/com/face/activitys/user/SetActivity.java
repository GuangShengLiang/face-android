package com.face.activitys.user;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.face.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import face.R;

/**
 * @author smlzhixing@163.com
 * @date 6/15/21
 * @describe TODO 设置
 */

public class SetActivity extends BaseActivity {


    @BindView(R.id.head_text_title)
    TextView headTextTitle;
    @BindView(R.id.head_text_right)
    TextView headTextRight;
    @BindView(R.id.head_img_left)
    View headImgLeft;
    @BindView(R.id.set_about)
    RelativeLayout setAbout;
    @BindView(R.id.set_xy)
    RelativeLayout setXy;
    @BindView(R.id.set_zc)
    RelativeLayout setZc;
    @BindView(R.id.loginOut)
    LinearLayout loginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        headTextTitle.setText("个人资料");
    }

    @OnClick({R.id.head_img_left, R.id.set_about, R.id.set_xy, R.id.set_zc, R.id.loginOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_img_left:
                finish();
                break;
            case R.id.set_about:
                break;
            case R.id.set_xy:
                break;
            case R.id.set_zc:
                break;
            case R.id.loginOut:
                break;
        }
    }
}