package com.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.face.http.model.JsonResponse;
import face.R;
import com.face.adapter.NewFriendsMsgAdapter;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.vo.FriendApplyVo;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewFriendsMsgActivity extends BaseActivity {
    @BindView(R.id.lv_new_friends_msg)
    ListView mNewFriendsMsgLv;
    NewFriendsMsgAdapter newFriendsMsgAdapter;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    private List<FriendApplyVo> friendApplyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                .subscribe(new BaseObserver<JsonResponse<List<FriendApplyVo>>>() {
                    @Override
                    public void onNext(JsonResponse<List<FriendApplyVo>> fls) {
                        friendApplyList.addAll(fls.getData());
                        newFriendsMsgAdapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick(R.id.tv_search)
    void openSearch() {
        startActivity(new Intent(mContext, AddFriendsBySearchActivity.class));
    }
}
