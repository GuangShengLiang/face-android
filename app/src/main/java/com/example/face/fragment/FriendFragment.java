package com.example.face.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.face.R;
import com.example.face.activity.NewFriendsMsgActivity;
import com.example.face.adapter.FriendsAdapter;
import com.example.face.dao.UserDao;
import com.example.face.entity.User;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Friend;
import com.example.face.util.ActivityUtils;
import com.example.face.util.PinyinComparator;
import com.example.face.util.PreferencesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FriendFragment extends Fragment {
    //    @BindView(R.id.rcv_friend)
//    RecyclerView rcvFriend;
//    @BindView(R.id.rl_new_friends)
//    RelativeLayout newFriends;
    private FriendsAdapter mFriendsAdapter;

    private ListView mFriendsLv;
    private LayoutInflater mInflater;

    TextView mNewFriendsUnreadNumTv;

    // 好友列表
    private List<User> mFriendList;
    private List<Friend> flist = new ArrayList<>();

    private UserDao mUserDao;


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
        mUserDao = new UserDao();
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
                .subscribe(new BaseObserver<List<Friend>>() {
                    @Override
                    public void onNext(List<Friend> fl) {
                        flist.addAll(fl);
                        mFriendsAdapter.notifyDataSetChanged();
//                        Collections.sort(fl, new PinyinComparator() {
//                        });
                    }
                });
        // 对list进行排序
//        Collections.sort(mFriendList, new PinyinComparator() {
//        });

        mFriendsLv.setOnItemClickListener((parent, view, position, id) -> {
            Friend f = flist.get(position - 1);
            ActivityUtils.openUserInfoActivity(getContext(), f.getRuid());
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

    public void refreshFriendsList() {
        mFriendList = mUserDao.getAllFriendList();

        // 对list进行排序
        Collections.sort(mFriendList, new PinyinComparator() {
        });
        mFriendsAdapter.notifyDataSetChanged();
    }

}
