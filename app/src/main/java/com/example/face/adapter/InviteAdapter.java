package com.example.face.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.face.R;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.act.ActInviteResp;
import com.example.face.model.IdReq;
import com.example.face.util.ActivityUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.HorizontalViewHolder> {

    private Context mContext;

    private List<ActInviteResp> mList = new ArrayList<>();

    public InviteAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(long aid) {
        HTTP.invite.listInviteByAid(aid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<ActInviteResp>>() {
                    @Override
                    public void onNext(List<ActInviteResp> l) {
                        mList.addAll(l);
                        notifyDataSetChanged();
                    }
                });
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.invite_list_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        ActInviteResp p = mList.get(position);
        holder.name.setText(p.getNickName());
        holder.status.setText(p.getStatusName());
        if (p.getStatus() != 0) {
            holder.cancel.setClickable(false);
            holder.cancel.setBackgroundColor(Color.GRAY);
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
                .load("http://img2.woyaogexing.com/2020/02/14/3d352b92e7df409bb2dd172d0b73ad4f!400x400.jpeg")    //myurl表示图片的url地址
                .apply(options)
                .into(holder.avatar);
        holder.avatar.setOnClickListener(view -> {
            ActivityUtils.openUserInfoActivity(mContext, p.getUid());
        });
        holder.cancel.setOnClickListener(view -> {
            IdReq req = new IdReq();
            req.setId(p.getId());
            HTTP.invite.cancel(req)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<Void>() {
                        @Override
                        public void onNext(Void v) {
                            holder.status.setText("已取消");
                            holder.cancel.setClickable(false);
                            holder.cancel.setBackgroundColor(Color.GRAY);
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.apply_name)
        TextView name;
        @BindView(R.id.apply_avatar)
        ImageView avatar;
        @BindView(R.id.invite_cancel)
        Button cancel;
        @BindView(R.id.invite_status)
        TextView status;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
