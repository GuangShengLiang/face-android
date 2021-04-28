package com.face.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.face.activity.ActTabsActivity;
import com.face.activity.BigImageActivity;
import com.face.activity.MyApplyListActivity;
import com.face.activity.MyInvitedListActivity;
import com.face.activity.MyPublishListActivity;
import com.face.activity.MyUserInfoActivity;
import com.face.activity.SettingActivity;
import com.face.dao.entity.User;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.vo.AccountDetail;
import com.face.util.CommonUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import face.R;
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    void initView(){
        HTTP.account.baseInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<AccountDetail>>() {
                    @Override
                    public void onNext(JsonResponse<AccountDetail> a) {
                        mNickNameTv.setText(a.getData().getNickName());
                        mWxIdTv.setText("ID:" + a.getData().getUid());
                        CommonUtil.loadAvatar(getContext(), mAvatarSdv, a.getData().getAvatar());
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

    @OnClick(R.id.act_list)
    void myActList() {
        Intent intent = new Intent(getActivity(), ActTabsActivity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.rl_my_join)
    void myJoinList() {
        Intent intent = new Intent(getActivity(), ActTabsActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.rl_my_publish)
    void myPublishList() {
        Intent intent = new Intent(getActivity(), MyPublishListActivity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.rl_my_apply)
    void myApplyList() {
        Intent intent = new Intent(getActivity(), MyApplyListActivity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.rl_my_invited)
    void myInvitedList() {
        Intent intent = new Intent(getActivity(), MyInvitedListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
}
