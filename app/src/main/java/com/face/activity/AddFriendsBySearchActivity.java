package com.face.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.face.http.model.JsonResponse;
import face.R;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.vo.Account;
import com.face.utils.ActivityUtils;
import com.face.utils.PreferencesUtil;
import com.face.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddFriendsBySearchActivity extends FragmentActivity {

    @BindView(R.id.et_search)
    EditText mSearchEt;
    @BindView(R.id.rl_search)
    RelativeLayout mSearchRl;
    @BindView(R.id.tv_search)
    TextView mSearchTv;

    Context context;
    LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends_by_search);
        context = this;
        ButterKnife.bind(this);

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean searchHasText = mSearchEt.getText().toString().length() > 0;
                if (searchHasText) {
                    mSearchRl.setVisibility(View.VISIBLE);
                    mSearchTv.setText(mSearchEt.getText().toString().trim());
                } else {
                    mSearchRl.setVisibility(View.GONE);
                    mSearchTv.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        PreferencesUtil.getInstance().init(this);

        mDialog = new LoadingDialog(context);

        mSearchRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setMessage(getString(R.string.searching_for_user));
                mDialog.show();
                String keyword = mSearchEt.getText().toString().trim();
                searchUser(keyword);
            }
        });

        // 初始化弹出软键盘
        showKeyboard(mSearchEt);
    }

    public void back(View view) {
        finish();
    }

    private void searchUser(String mobile) {
/*
        HTTP.account.se(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<Account>>() {
                    @Override
                    public void onNext(JsonResponse<Account> a) {
                        mDialog.dismiss();
                        if (a.getData() == null) {
                            mDialog.dismiss();
                            Toast.makeText(AddFriendsBySearchActivity.this, "未搜索到", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ActivityUtils.openUserInfoActivity(context, a.getData().getUid());
                    }
                });
*/
    }

    /**
     * 显示键盘
     *
     * @param editText 输入框
     */
    public void showKeyboard(final EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
}
