package com.example.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.face.MainActivity;
import com.example.face.R;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Account;
import com.example.face.model.LoginReq;
import com.example.face.model.LoginResp;
import com.example.face.util.CommonUtil;
import com.example.face.util.PreferencesUtil;
import com.example.face.widget.LoadingDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    EditText mPhoneEt;
    EditText mPasswordEt;
    @BindView(R.id.btn_login)
    Button mLoginBtn;
    //    TextView mRegisterTv;
    LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mDialog = new LoadingDialog(LoginActivity.this);
        initView();
    }

    private void initView() {
        mPhoneEt = findViewById(R.id.et_user_phone);
        mPasswordEt = findViewById(R.id.et_password);
        mPhoneEt.addTextChangedListener(new TextChange());
        mPasswordEt.addTextChangedListener(new TextChange());
    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        mDialog.setMessage(getString(R.string.logging_in));
        mDialog.show();
        final String phone = mPhoneEt.getText().toString().trim();
        final String password = mPasswordEt.getText().toString().trim();
        LoginReq r = new LoginReq(phone, password);
        HTTP.passport.login(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginResp>() {
                    @Override
                    public void onNext(LoginResp resp) {
                        mDialog.dismiss();
                        PreferencesUtil.saveToken(LoginActivity.this, resp.getToken());
                        loadUserInfo();
                        PreferencesUtil.getInstance().setLogin(true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                });
    }

    private void loadUserInfo() {
        HTTP.account.baseInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Account>() {
                    @Override
                    public void onNext(Account a) {
                        PreferencesUtil.saveAccount(mContext, a);
                    }
                });
    }

    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean phoneEtHasText = mPhoneEt.getText().length() > 0;
            boolean passwordEtHasText = mPasswordEt.getText().length() > 0;
            if (phoneEtHasText && passwordEtHasText) {
                mLoginBtn.setTextColor(0xFFFFFFFF);
                mLoginBtn.setEnabled(true);
            } else {
                mLoginBtn.setTextColor(0xFFD0EFC6);
                mLoginBtn.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
