package com.face.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.face.Constant;
import face.R;
import com.face.activity.UserInfoActivity;
import com.face.http.model.vo.FriendApplyVo;
import com.face.util.CommonUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NewFriendsMsgAdapter extends ArrayAdapter<FriendApplyVo> {
    Context mContext;
    List<FriendApplyVo> ls;

    public NewFriendsMsgAdapter(Context context, List<FriendApplyVo> ls) {
        super(context, R.layout.item_new_friends_msg, ls);
        this.mContext = context;
        this.ls = ls;
    }

    public void setData(List<FriendApplyVo> FriendApplyVoList) {
        this.ls = FriendApplyVoList;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public FriendApplyVo getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FriendApplyVo p = ls.get(position);
        convertView = View.inflate(mContext, R.layout.item_new_friends_msg, null);

        TextView mNickNameTv = convertView.findViewById(R.id.tv_nick_name);
        TextView mApplyReasonTv = convertView.findViewById(R.id.tv_apply_remark);
        SimpleDraweeView mAvatarSdv = convertView.findViewById(R.id.sdv_avatar);
        Button mAddBtn = convertView.findViewById(R.id.btn_add);
        TextView mAddTv = convertView.findViewById(R.id.tv_added);

        mNickNameTv.setText(p.getSender().getNickName());
        mApplyReasonTv.setText(p.getReason());
        CommonUtil.loadAvatar(mContext, mAvatarSdv, p.getSender().getAvatar());
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
            intent.putExtra("ruid", p.getSender().getUid());
            mContext.startActivity(intent);
        });
        return convertView;
    }
}
