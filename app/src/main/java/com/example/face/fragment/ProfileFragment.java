package com.example.face.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.face.Constant;
import com.example.face.R;
import com.example.face.activity.*;
import com.example.face.entity.User;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Account;
import com.example.face.util.CommonUtil;
import com.example.face.util.PreferencesUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * tab - "我"
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.sdv_avatar)
    SimpleDraweeView mAvatarSdv;
    @BindView(R.id.tv_name)
    TextView mNickNameTv;
    @BindView(R.id.tv_wxid)
    TextView mWxIdTv;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PreferencesUtil.getInstance().init(getActivity());
        user = PreferencesUtil.getInstance().getUser();
        HTTP.account.myInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Account>() {
                    @Override
                    public void onNext(Account a) {
                        mNickNameTv.setText(a.getNickName());
                        mWxIdTv.setText("ID:" + a.getUid());
                        CommonUtil.loadAvatar(getContext(), mAvatarSdv, a.getAvatar());
                    }
                });

    }

    @OnClick(R.id.rl_myinfo)
    void showMyinfo() {
        startActivity(new Intent(getActivity(), MyUserInfoActivity.class));
    }

    @OnClick(R.id.rl_setting)
    void showSetting() {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    @OnClick(R.id.sdv_avatar)
    void showAvatar() {
        Intent intent = new Intent(getActivity(), BigImageActivity.class);
        intent.putExtra("imgUrl", user.getUserAvatar());
        startActivity(intent);
    }

    @OnClick(R.id.rl_my_join)
    void myJoinList() {
        Intent intent = new Intent(getActivity(), MyJoinListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_my_publish)
    void myPublishList() {
        Intent intent = new Intent(getActivity(), MyPublishListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_my_apply)
    void myapplyList() {
        Intent intent = new Intent(getActivity(), MyApplyListActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.rl_my_invited)
    void myInvitedList() {
        Intent intent = new Intent(getActivity(), MyInvitedListActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        Account acc = PreferencesUtil.getAccount(this.getContext());
        mNickNameTv.setText(acc.getNickName());
        mWxIdTv.setText("ID:" + acc.getUid());
        CommonUtil.loadAvatar(this.getContext(), mAvatarSdv, acc.getAvatar());
    }
}
