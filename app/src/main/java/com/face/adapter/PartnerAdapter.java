package com.face.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.face.activity.FriendSelectionActivity;
import com.face.http.model.vo.ActivityDetailVo;
import com.face.http.model.vo.MemberVo;
import com.face.utils.ActivityUtils;
import com.face.utils.CommonUtil;
import com.face.utils.PreferencesUtil;
import face.R;

import java.util.ArrayList;
import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<MemberVo> mList = new ArrayList<>();
    private long aid;

    public PartnerAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(ActivityDetailVo detailVo) {
        this.aid = detailVo.getAid();
        mList.add(detailVo.getPublisher());
        mList.addAll(detailVo.getMembers());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.partner_list_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        //最后一个，+邀请链接
        if (position == mList.size()) {
            int uid = PreferencesUtil.getAccount(mContext).getUid();
            if (mList.stream().noneMatch(e -> e.getAccount().getUid() == uid)) {
                return;
            }
            holder.imAvatar.setImageResource(R.mipmap.icon_add_user_to_group);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, FriendSelectionActivity.class);
                intent.putExtra("aid", aid);
                mContext.startActivity(intent);
            });
            return;
        }
        MemberVo acc = mList.get(position);
        holder.tvContent.setText(acc.getDisplayName());
        CommonUtil.loadAvatar(mContext, holder.imAvatar, acc.getAccount().getAvatar());
        holder.itemView.setOnClickListener(view -> {
            ActivityUtils.openUserInfoActivity(mContext, acc.getAccount().getUid());
        });
    }

    @Override
    public int getItemCount() {
        int add=0;
        int uid = PreferencesUtil.getAccount(mContext).getUid();
        if (mList.stream().anyMatch(e -> e.getAccount().getUid() == uid)) {
            add++;
        }
        return mList == null ? 0 : mList.size()+add;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        ImageView imAvatar;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            imAvatar = itemView.findViewById(R.id.im_avatar);

        }
    }
}
