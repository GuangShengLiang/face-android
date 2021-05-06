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
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import face.R;
import com.face.activity.ActDetailActivity;
import com.face.activity.ActManageActivity;
import com.face.http.model.vo.AccountDetail;
import com.face.http.model.vo.ActivityDetailVo;
import com.face.utils.PreferencesUtil;

import java.util.ArrayList;
import java.util.List;


public class MyJoinAdapter extends RecyclerView.Adapter<MyJoinAdapter.HorizontalViewHolder> {
    private Context mContext;

    public List<ActivityDetailVo> mList = new ArrayList<>();

    public MyJoinAdapter(Context context) {
        mContext = context;
//        HTTP.activity.listJoin()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<List<ActivityDetail>>() {
//                    @Override
//                    public void onNext(List<ActivityDetail> ls) {
//                        mList.addAll(ls);
//                        notifyDataSetChanged();
//                    }
//                });
    }

    @NonNull
    @Override
    public MyJoinAdapter.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_list_item, parent, false);
        return new MyJoinAdapter.HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyJoinAdapter.HorizontalViewHolder holder, int position) {
        ActivityDetailVo d = mList.get(position);
        holder.title.setText(d.getTitle());
        holder.address.setText(d.getAddress());
        holder.time.setText(d.getStime());
        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(mContext, ActDetailActivity.class);
//            intent.putExtra("aid", mList.get(position).getAid());
//            mContext.startActivity(intent);
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
        return mList == null ? 0 : mList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.period)
        TextView time;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
