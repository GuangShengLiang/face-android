package com.example.face.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.face.R;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.AccountDetail;
import com.example.face.model.param.AccountParam;
import com.example.face.model.Response;
import com.example.face.util.PreferencesUtil;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NickNameActivity extends BaseActivity {

    @BindView(R.id.ev_nick_name)
    EditText nickName;
    @BindView(R.id.title_bar)
    TitleBar titleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nick_name);
        ButterKnife.bind(this);
        AccountDetail a = PreferencesUtil.getAccount(mContext);
        Optional.of(a).ifPresent(e -> nickName.setText(a.getNickName()));
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                AccountParam req = new AccountParam();
                req.setNickName(nickName.getText().toString());
                HTTP.account.updateInfo(req)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<Response>() {
                        });
                finish();
            }
        });
    }
}
