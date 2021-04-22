package com.example.face.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.face.FLinkApplication;
import com.example.face.R;
import com.example.face.adapter.FriendsSelectionAdapter;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.vo.FriendVo;
import com.example.face.model.Response;
import com.example.face.model.param.InviteParam;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendSelectionActivity extends BaseActivity {
    //    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.lv_friends)
    ListView mFriendsLv;
    private long aid = 0;
    private Set<Integer> uids = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_selection_list);
        ButterKnife.bind(this);
        aid = getIntent().getExtras().getLong("aid");

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
                if (!uids.isEmpty()) {
                    InviteParam r = new InviteParam(aid, uids);
                    HTTP.invite.invite(r)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BaseObserver<Response>() {
                                @Override
                                public void onNext(@NotNull Response v) {
                                    Toast t = Toasty.success(FLinkApplication.getContext(), "添加成功",
                                            Toast.LENGTH_SHORT, true);
                                    t.setGravity(Gravity.CENTER, 0, 0);
                                    t.show();
                                }
                            });
                }
                finish();
            }
        });
        initView();
    }


    private void initView() {
        List<FriendVo> flist = new ArrayList<>();
        FriendsSelectionAdapter adapter = new FriendsSelectionAdapter(mContext, R.layout.friend_selection_list_item,
                flist, uids);
        mFriendsLv.setAdapter(adapter);
        HTTP.relation.friendList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FriendVo>>() {
                    @Override
                    public void onNext(@NotNull List<FriendVo> fl) {
                        flist.addAll(fl);
                        adapter.notifyDataSetChanged();
                    }
                });
     /*   mFriendsLv.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("tag", position + "");
            int uid = flist.get(position).getUid();
            if (uids.contains(uid)) {
                uids.remove(uid);
            } else {
                uids.add(uid);
            }
        });*/
    }
}
