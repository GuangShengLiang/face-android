package com.face.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import com.face.http.model.JsonResponse;
import face.R;
import com.face.activity.NewFriendsMsgActivity;
import com.face.adapter.FriendsAdapter;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.vo.FriendVo;
import com.face.util.ActivityUtils;
import com.face.util.PreferencesUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;


public class FriendFragment extends Fragment {
    private FriendsAdapter mFriendsAdapter;
    private ListView mFriendsLv;
    private LayoutInflater mInflater;

    TextView mNewFriendsUnreadNumTv;

    private List<FriendVo> flist = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PreferencesUtil.getInstance().init(getActivity());
        mFriendsLv = getView().findViewById(R.id.lv_friends);
        mInflater = LayoutInflater.from(getActivity());
        View headerView = mInflater.inflate(R.layout.item_friends_header, null);
        mFriendsLv.addHeaderView(headerView);


        RelativeLayout mNewFriendsRl = headerView.findViewById(R.id.rl_new_friends);
        mNewFriendsRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewFriendsMsgActivity.class));
                PreferencesUtil.getInstance().setNewFriendsUnreadNumber(0);
            }
        });

        mNewFriendsUnreadNumTv = headerView.findViewById(R.id.tv_new_friends_unread);

//        mFriendList = mUserDao.getAllFriendList();
        mFriendsAdapter = new FriendsAdapter(getActivity(), R.layout.item_friends, flist);
        mFriendsLv.setAdapter(mFriendsAdapter);
        HTTP.relation.friendList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<List<FriendVo>>>() {
                    @Override
                    public void onNext(JsonResponse<List<FriendVo>> fl) {
                        flist.addAll(fl.getData());
                        mFriendsAdapter.notifyDataSetChanged();
//                        Collections.sort(fl, new PinyinComparator() {
//                        });
                    }
                });
        // 对list进行排序
//        Collections.sort(mFriendList, new PinyinComparator() {
//        });

        mFriendsLv.setOnItemClickListener((parent, view, position, id) -> {
            FriendVo f = flist.get(position - 1);
            ActivityUtils.openUserInfoActivity(getContext(), f.getFriend().getUid());
        });
    }

    public void refreshNewFriendsUnreadNum() {
        int newFriendsUnreadNum = PreferencesUtil.getInstance().getNewFriendsUnreadNumber();
        if (newFriendsUnreadNum > 0) {
            mNewFriendsUnreadNumTv.setVisibility(View.VISIBLE);
            mNewFriendsUnreadNumTv.setText(String.valueOf(newFriendsUnreadNum));
        } else {
            mNewFriendsUnreadNumTv.setVisibility(View.GONE);
        }
    }

}
