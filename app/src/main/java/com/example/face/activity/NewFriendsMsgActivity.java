package com.example.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.face.R;
import com.example.face.adapter.NewFriendsMsgAdapter;
import com.example.face.entity.FriendApply;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class NewFriendsMsgActivity extends BaseActivity {
    @BindView(R.id.lv_new_friends_msg)
    ListView mNewFriendsMsgLv;
    NewFriendsMsgAdapter newFriendsMsgAdapter;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    private List<FriendApply> friendApplyList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends_msg);
        ButterKnife.bind(this);

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
                startActivity(new Intent(NewFriendsMsgActivity.this, AddFriendsActivity.class));
            }
        });

        newFriendsMsgAdapter = new NewFriendsMsgAdapter(this, friendApplyList);
        mNewFriendsMsgLv.setAdapter(newFriendsMsgAdapter);

        HTTP.relation.friendApplyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FriendApply>>() {
                    @Override
                    public void onNext(List<FriendApply> fls) {
                        friendApplyList.addAll(fls);
                        newFriendsMsgAdapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick(R.id.tv_search)
    void openSearch() {
        startActivity(new Intent(mContext, AddFriendsBySearchActivity.class));
    }
}
