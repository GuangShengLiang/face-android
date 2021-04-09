package com.example.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.face.R;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Account;
import com.example.face.model.FriendReq;
import com.example.face.model.Relation;
import com.example.face.model.RuidReq;
import com.example.face.util.CommonUtil;
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
        HTTP.relation.relation(ruid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Relation>() {
                    @Override
                    public void onNext(Relation r) {
                        if (r != null) {
                            if (r.getType() == 1) {
                                addFriend.setVisibility(View.GONE);
                                bagree.setVisibility(View.GONE);
                            }
                        }
                    }
                });
        HTTP.account.info(ruid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Account>() {
                    @Override
                    public void onNext(Account a) {
                        name.setText(a.getNickName());
                        birthday.setText(a.getYear());
                        city.setText("北京");
                        CommonUtil.loadAvatar(mContext, avatar, a.getAvatar());

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
        FriendReq req = new FriendReq();
        req.setRuid(getIntent().getIntExtra("ruid", 0));
        HTTP.relation.friendApply(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>());
    }

    @OnClick(R.id.b_fd_agree)
    public void agree() {
        RuidReq r = new RuidReq();
        int ruid = getIntent().getExtras().getInt("ruid", 93);
        r.setRuid(ruid);
        HTTP.relation.friendAgree(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>());
    }
}
