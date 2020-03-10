package com.example.face.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.face.Constant;
import com.example.face.R;
import com.example.face.activity.UserInfoActivity;
import com.example.face.entity.FriendApply;
import com.example.face.util.CommonUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NewFriendsMsgAdapter extends ArrayAdapter<FriendApply> {
    Context mContext;
    List<FriendApply> ls;

    public NewFriendsMsgAdapter(Context context, List<FriendApply> ls) {
        super(context, R.layout.item_new_friends_msg, ls);
        this.mContext = context;
        this.ls = ls;
    }

    public void setData(List<FriendApply> friendApplyList) {
        this.ls = friendApplyList;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public FriendApply getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FriendApply p = ls.get(position);
        convertView = View.inflate(mContext, R.layout.item_new_friends_msg, null);

        TextView mNickNameTv = convertView.findViewById(R.id.tv_nick_name);
        TextView mApplyReasonTv = convertView.findViewById(R.id.tv_apply_remark);
        SimpleDraweeView mAvatarSdv = convertView.findViewById(R.id.sdv_avatar);
        Button mAddBtn = convertView.findViewById(R.id.btn_add);
        TextView mAddTv = convertView.findViewById(R.id.tv_added);

        mNickNameTv.setText(p.getNickName());
        mApplyReasonTv.setText(p.getReason());
        CommonUtil.loadAvatar(mContext, mAvatarSdv, p.getAvatar());
        if (Constant.FRIEND_APPLY_STATUS_ACCEPT.equals(p.getStatus())) {
            mAddTv.setVisibility(View.VISIBLE);
            mAddBtn.setVisibility(View.GONE);
        } else {
            mAddTv.setVisibility(View.GONE);
            mAddBtn.setVisibility(View.VISIBLE);
        }

        convertView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, UserInfoActivity.class);
            intent.putExtra("applyId", p.getId());
            intent.putExtra("ruid", p.getRuid());
            mContext.startActivity(intent);
        });
        return convertView;
    }
}
