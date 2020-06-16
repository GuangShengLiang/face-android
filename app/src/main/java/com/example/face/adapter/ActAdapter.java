package com.example.face.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.face.R;
import com.example.face.activity.ActDetailActivity;
import com.example.face.model.act.ActivityDetail;

import java.util.ArrayList;
import java.util.List;

public class ActAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ActivityDetail> list = new ArrayList<>();

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
        TextView uname = holder.itemView.findViewById(R.id.uname);
        TextView address = holder.itemView.findViewById(R.id.address);
        TextView time = holder.itemView.findViewById(R.id.time);
        ImageView avatar = holder.itemView.findViewById(R.id.avatar);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
                .load("http://img2.woyaogexing.com/2020/02/14/3d352b92e7df409bb2dd172d0b73ad4f!400x400.jpeg")    //myurl表示图片的url地址
                .apply(options)
                .into(avatar);
        ActivityDetail d = list.get(position);
        title.setText(d.getTitle());
        address.setText(d.getAddress());
        uname.setText(d.getUname());
        time.setText(d.getStime());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, ActDetailActivity.class);
            intent.putExtra("aid", d.getAid());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(List<ActivityDetail> list) {
        this.list.clear();
        this.loadMore(list);
    }

    public void loadMore(List<ActivityDetail> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
}
