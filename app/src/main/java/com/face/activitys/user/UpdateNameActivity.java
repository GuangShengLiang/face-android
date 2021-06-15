package com.face.activitys.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.face.base.BaseActivity;
import com.face.dialog.BackDialog;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.MyInfoUpdateParam;
import com.face.http.model.vo.MyInfoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import face.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.face.utils.TimeUtils.getDateToString;

/**
 * @author smlzhixing@163.com
 * @date 6/15/21
 * @describe TODO 修改称呼
 */

public class UpdateNameActivity extends BaseActivity {

    @BindView(R.id.head_text_title)
    TextView headTextTitle;
    @BindView(R.id.head_text_right)
    TextView headTextRight;
    @BindView(R.id.head_img_left)
    View headImgLeft;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.nums)
    TextView nums;
    BackDialog backDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upfate_name);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        headTextTitle.setText("修改称呼");
        headTextRight.setText("保存");
        headTextRight.setVisibility(View.VISIBLE);
        name.setText(getIntent().getStringExtra("name"));
        nums.setText(name.length() + "/20");
        headTextRight.setTextColor(getResources().getColor(R.color.nosave));
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nums.setText(s.length() + "/20");
                if (s.length() == 20) {
                    nums.setTextColor(getResources().getColor(R.color.save));
                }
                if (name.getText().toString().equals(s) || s.length() == 0) {
                    headTextRight.setTextColor(getResources().getColor(R.color.nosave));
                    headTextRight.setEnabled(false);
                } else {
                    headTextRight.setTextColor(getResources().getColor(R.color.save));
                    headTextRight.setEnabled(true);
                }

            }
        });
    }

    @OnClick({R.id.head_text_right, R.id.head_img_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_text_right:
                update();
                break;
            case R.id.head_img_left:
                String names = name.getText().toString();
                if (!names.equals(getIntent().getStringExtra("name")) && !names.equals("")) {
                    backDialog = new BackDialog(UpdateNameActivity.this, "是否保存修改", "保存", "放弃",
                            R.style.Theme_Dialog_Scale, new BackDialog.Dialog3ClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (view.getId()) {
                                case R.id.cancel:
                                    backDialog.dismiss();
                                    finish();
                                    break;
                                case R.id.ok:
                                    update();
                                    backDialog.dismiss();
                                    break;
                            }
                        }
                    });
                    backDialog.setCancelable(false);
                    backDialog.show();
                }
                break;
        }
    }

    //    修改称呼
    private void update() {
        MyInfoUpdateParam param = new MyInfoUpdateParam();
        param.setNickName(name.getText().toString());
        HTTP.account.updateMyInfo(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse>() {
                    @Override
                    public void onNext(JsonResponse a) {
                        if (a.getCode() == 0) {
                            Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                });
    }
}