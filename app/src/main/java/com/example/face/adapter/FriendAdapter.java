package com.example.face.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.face.R;
import com.example.face.activity.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.HorizontalViewHolder> {

    private static final String TAG = FriendAdapter.class.getSimpleName();
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private List<String> mList = new ArrayList<>();

    public FriendAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(List<String> list) {
        Log.d(TAG, "setHorizontalDataList: " + list.size());

        mList = list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        holder.tvContent.setText(mList.get(position));
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(view -> {
                ComponentName cn = new ComponentName(mContext, UserInfoActivity.class);
                Intent intent = new Intent();
                intent.setComponent(cn);
                intent.putExtra("id", position);
                mContext.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_friend_name)
        TextView tvContent;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
