package com.face.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.face.dao.MessageDao;
import com.face.dao.entity.User;
import com.face.util.PreferencesUtil;

public class BaseActivity extends FragmentActivity {
    private MessageDao mMessageDao;
    private User mUser;
    protected Context mContext;
//    @BindView(R.id.title_bar)
//    protected TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
        mContext = this;
        mMessageDao = new MessageDao();
        mUser = PreferencesUtil.getInstance().getUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
