package com.face.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.face.activitys.user.SetActivity;
import com.face.activitys.user.UserInfoActivity;
import com.face.dao.entity.User;
import com.face.fragment.my.PagerAdapter;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.vo.MyInfoVO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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

    @BindView(R.id.set)
    ImageView set;
    @BindView(R.id.fans)
    LinearLayout fans;
    @BindView(R.id.follow)
    LinearLayout follow;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ID)
    TextView ID;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.infoBtn)
    TextView infoBtn;
    @BindView(R.id.friendBtn)
    TextView friendBtn;
    @BindView(R.id.fanTotal)
    TextView fanTotal;
    @BindView(R.id.followTotal)
    TextView followTotal;
    @BindView(R.id.genderImg)
    ImageView genderImg;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.loveStatusName)
    TextView loveStatusName;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mData = new ArrayList<>();
    private boolean isGetData = false;

    User user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView(view);
        return view;
    }

    void initView() {
        HTTP.account.myInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<MyInfoVO>>() {
                    @Override
                    public void onNext(JsonResponse<MyInfoVO> a) {
                        name.setText(a.getData().getNickName());
                        ID.setText("ID:" + a.getData().getUid());
                        fanTotal.setText("" + a.getData().getFanTotal());
                        followTotal.setText("" + a.getData().getFollowTotal());
                        year.setText(a.getData().getYear());
                        if (a.getData().getGender() == 1) {
                            //男
                        }

                        if (a.getData().getLoveStatus() == 0){
                            //没有感情状态
                            loveStatusName.setVisibility(View.GONE);
                        } else {
                            loveStatusName.setVisibility(View.VISIBLE);
                        }
//                        CommonUtil.loadAvatar(getContext(), mAvatarSdv, a.getData().getAvatar());
                    }
                });
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter && !isGetData) {
            isGetData = true;
            getOrderList();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    private void initView(View rootView) {
        mTabLayout = rootView.findViewById(R.id.home_indicator);
        mViewPager = rootView.findViewById(R.id.home_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager(), mData);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        mData.add("待参加");
        mData.add("报名中");
        mData.add("我的发布");
        mData.add("邀请");
        mData.add("全部");
    }

    //获取的订单列表
    private void getOrderList() {

    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @OnClick({R.id.set, R.id.fans, R.id.follow, R.id.infoBtn, R.id.friendBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set:
                startActivity(new Intent(getContext(), SetActivity.class));
                break;
            case R.id.fans:
                break;
            case R.id.follow:
                break;
            case R.id.infoBtn:
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                break;
            case R.id.friendBtn:
                break;
        }
    }
}
