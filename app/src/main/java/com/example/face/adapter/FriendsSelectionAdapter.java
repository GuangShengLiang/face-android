package com.example.face.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.face.R;
import com.example.face.model.Friend;
import com.example.face.util.CommonUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class FriendsSelectionAdapter extends ArrayAdapter<Friend> {
    List<Friend> mFriendList;
    List<Integer> uids;
    int mResource;
    private LayoutInflater mLayoutInflater;

    public FriendsSelectionAdapter(Context context, int resource, List<Friend> friendList, List<Integer> uids) {
        super(context, resource, friendList);
        this.mResource = resource;
        this.mFriendList = friendList;
        this.uids = uids;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(mResource, null);
            viewHolder = new ViewHolder();
            viewHolder.mAvatarSdv = convertView.findViewById(R.id.sdv_avatar);
            viewHolder.mNameTv = convertView.findViewById(R.id.tv_name);
            viewHolder.checkBox = convertView.findViewById(R.id.tv_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Friend friend = getItem(position);
        if (TextUtils.isEmpty(friend.getRemark())) {
            viewHolder.mNameTv.setText(friend.getRname());
        } else {
            viewHolder.mNameTv.setText(friend.getRemark());
        }

        String avatar = friend.getAvatar();
        if (!TextUtils.isEmpty(avatar)) {
            CommonUtil.loadAvatar(getContext(), viewHolder.mAvatarSdv, avatar);
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.checkBox.isChecked()) {
                    uids.add(mFriendList.get(position).getRuid());
                } else {
                    uids.remove(mFriendList.get(position).getRuid());
                }
            }
        });
        return convertView;
    }

    @Override
    public Friend getItem(int position) {
        return mFriendList.get(position);
    }

    @Override
    public int getCount() {
        return mFriendList.size();
    }

    public void setData(List<Friend> friendList) {
        this.mFriendList = friendList;
    }

    class ViewHolder {
        SimpleDraweeView mAvatarSdv;
        TextView mNameTv;
        CheckBox checkBox;
    }
}
