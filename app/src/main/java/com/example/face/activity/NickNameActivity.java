package com.example.face.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.face.R;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Account;
import com.example.face.model.AccountReq;
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
        Account a = PreferencesUtil.getAccount(mContext);
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
                AccountReq req = new AccountReq();
                req.setNickName(nickName.getText().toString());
                HTTP.account.updateInfo(req)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<Void>() {
                            @Override
                            public void onNext(Void v) {

                            }
                        });
                Toast.makeText(mContext, "更新成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
