package com.face.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import face.R;
import com.face.http.model.vo.FriendVo;
import com.face.util.CommonUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class FriendsAdapter extends ArrayAdapter<FriendVo> {

    List<FriendVo> mFriendList;
    int mResource;
    private LayoutInflater mLayoutInflater;

    public FriendsAdapter(Context context, int resource, List<FriendVo> friendList) {
        super(context, resource, friendList);
        this.mResource = resource;
        this.mFriendList = friendList;
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
            viewHolder.mHeaderTv = convertView.findViewById(R.id.tv_header);
            viewHolder.mTempView = convertView.findViewById(R.id.view_header);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FriendVo friend = getItem(position);
        String header;
        if (TextUtils.isEmpty(friend.getRemark())) {
            header = CommonUtil.setUserHeader(friend.getFriend().getNickName());
        } else {
            header = CommonUtil.setUserHeader(friend.getRemark());
        }

        if (TextUtils.isEmpty(friend.getRemark())) {
            viewHolder.mNameTv.setText(friend.getFriend().getNickName());
        } else {
            viewHolder.mNameTv.setText(friend.getRemark());
        }

        String avatar = friend.getFriend().getAvatar();
        if (0 == position || null != header && !header.equals(getItem(position - 1).getHeader())) {
            if (TextUtils.isEmpty(header)) {
                viewHolder.mHeaderTv.setVisibility(View.GONE);
                viewHolder.mTempView.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mHeaderTv.setVisibility(View.VISIBLE);
                viewHolder.mHeaderTv.setText(header);
                viewHolder.mTempView.setVisibility(View.GONE);
            }
        } else {
            viewHolder.mHeaderTv.setVisibility(View.GONE);
            viewHolder.mTempView.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(avatar)) {
            CommonUtil.loadAvatar(getContext(), viewHolder.mAvatarSdv, avatar);
        }

        return convertView;
    }

    @Override
    public FriendVo getItem(int position) {
        return mFriendList.get(position);
    }

    @Override
    public int getCount() {
        return mFriendList.size();
    }

    public void setData(List<FriendVo> friendList) {
        this.mFriendList = friendList;
    }

    class ViewHolder {
        SimpleDraweeView mAvatarSdv;
        TextView mNameTv;
        TextView mHeaderTv;
        View mTempView;
    }
}
