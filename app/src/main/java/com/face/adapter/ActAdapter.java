package com.face.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.face.activity.ActDetailActivity;
import com.face.activity.ActManageActivity;
import com.face.enums.account.FriendshipTypeEnum;
import com.face.http.model.vo.*;
import com.face.utils.PreferencesUtil;
import face.R;

import java.util.ArrayList;
import java.util.List;

public class ActAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public List<ActivityFeedVo> list = new ArrayList<>();

    //        @BindView(R.id.title)
//        TextView title;
//        @BindView(R.id.time)
//        TextView time;
//        @BindView(R.id.address)
//        TextView address;
//        @BindView(R.id.uname)
//        TextView uname;
    public ActAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.act_list_item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ButterKnife.bind(,holder.itemView);
        TextView title = holder.itemView.findViewById(R.id.title);
        TextView address = holder.itemView.findViewById(R.id.address);
        TextView period = holder.itemView.findViewById(R.id.period);
        LinearLayout friends = holder.itemView.findViewById(R.id.ll_friends);
        ActivityFeedVo d = list.get(position);
        title.setText(d.getTitle());
        address.setText(d.getAddress());
        period.setText(d.getPeriod());
        bindMemberLayout(friends,d.getMembers());
        holder.itemView.setOnClickListener(view -> {
            AccountVO acc = PreferencesUtil.getAccount(mContext);
            Intent intent;
            if (acc != null && acc.getUid() == d.getPublisher().getAccount().getUid()) {
                intent = new Intent(mContext, ActManageActivity.class);
            } else {
                intent = new Intent(mContext, ActDetailActivity.class);
            }
            intent.putExtra("aid", d.getAid());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(List<ActivityFeedVo> list) {
        this.list.clear();
        this.loadMore(list);
    }

    public void loadMore(List<ActivityFeedVo> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void bindMemberLayout(LinearLayout llFriend, List<MemberVo> memberVos) {
        llFriend.removeAllViews();
        memberVos.forEach(m -> {
            View layout = LayoutInflater.from(mContext).inflate(R.layout.item_layout_friends, null, false);
            ImageView ivFriend = layout.findViewById(R.id.iv_friend);
//            CommonUtil.loadAvatar(mContext, ivFriend, m.getAccount().getGender());
            TextView tvFriend = layout.findViewById(R.id.tv_friend);
            if (m.getRelationType() == FriendshipTypeEnum.friend.getCode()) {
                tvFriend.setTextColor(Color.RED);
            }
            tvFriend.setText(m.getDisplayName());
            llFriend.addView(layout);
        });

    }


}
