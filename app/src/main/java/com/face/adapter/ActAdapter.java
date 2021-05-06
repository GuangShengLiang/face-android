package com.face.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.face.activity.ActDetailActivity;
import com.face.activity.ActManageActivity;
import com.face.http.model.vo.AccountDetail;
import com.face.http.model.vo.ActivityFeedVo;
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
        ActivityFeedVo d = list.get(position);
        title.setText(d.getTitle());
        address.setText(d.getAddress());
        period.setText(d.getPeriod());
        holder.itemView.setOnClickListener(view -> {
            AccountDetail acc = PreferencesUtil.getAccount(mContext);
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
}
