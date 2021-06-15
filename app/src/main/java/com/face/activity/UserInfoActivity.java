package com.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.face.http.model.vo.AccountInfoVO;
import face.R;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.FriendParam;
import com.face.http.model.param.UidParam;
import com.face.utils.CommonUtil;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.tv_fd_name)
    TextView name;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.tv_fd_birthday)
    TextView birthday;
    @BindView(R.id.tv_fd_city)
    TextView city;
    @BindView(R.id.b_fd_add)
    Button addFriend;
    @BindView(R.id.b_fd_agree)
    Button bagree;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        ButterKnife.bind(this);
        int ruid = getIntent().getExtras().getInt("ruid", 93);
        HTTP.account.info(ruid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<AccountInfoVO>>() {
                    @Override
                    public void onNext(JsonResponse<AccountInfoVO> a) {
                        name.setText(a.getData().getAccount().getNickName());
                        birthday.setText(a.getData().getAccount().getYear());
                        city.setText("北京");
                        CommonUtil.loadAvatar(mContext, avatar, a.getData().getAccount().getAvatar());

                    }
                });
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
                Log.d("tt", "right");
            }
        });
    }

    @OnClick(R.id.b_fd_add)
    public void addFriend() {
        FriendParam req = new FriendParam();
        req.setUid(getIntent().getIntExtra("ruid", 0));
    }

    @OnClick(R.id.b_fd_agree)
    public void agree() {
        UidParam r = new UidParam();
        int ruid = getIntent().getExtras().getInt("ruid", 93);
        r.setUid(ruid);
    }
}
