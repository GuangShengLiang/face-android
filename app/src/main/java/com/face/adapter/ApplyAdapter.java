package com.face.adapter;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import face.R;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.IdParam;
import com.face.http.model.vo.ApplyVo;
import com.face.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.HorizontalViewHolder> {

    private Context mContext;

    private List<ApplyVo> mList = new ArrayList<>();

    public ApplyAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(long aid) {
        HTTP.apply.listApplyByAid(aid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<List<ApplyVo>>>() {
                    @Override
                    public void onNext(JsonResponse<List<ApplyVo>> l) {
                        mList.addAll(l.getData());
                        notifyDataSetChanged();
                    }
                });
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.apply_list_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        ApplyVo p = mList.get(position);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
                .load("http://img2.woyaogexing.com/2020/02/14/3d352b92e7df409bb2dd172d0b73ad4f!400x400.jpeg")    //myurl表示图片的url地址
                .apply(options)
                .into(holder.avatar);
        holder.statusName.setText(p.getStatusName());
        holder.name.setText(p.getApplicant().getNickName());

        if (p.getStatus() != 0) {
            holder.approval.setClickable(false);
            holder.approval.setBackgroundColor(Color.GRAY);
        }
        holder.avatar.setOnClickListener(view -> {
            ActivityUtils.openUserInfoActivity(mContext, p.getApplicant().getUid());
        });
        holder.approval.setOnClickListener(view -> {
            IdParam req = new IdParam();
            req.setId(p.getId());
            HTTP.apply.agree(req)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<JsonResponse>() {
                        @Override
                        public void onNext(JsonResponse v) {
                            holder.statusName.setText("同意");
                            holder.approval.setClickable(false);
                            holder.approval.setBackgroundColor(Color.GRAY);
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
        @BindView(R.id.apply_status)
        TextView statusName;
        @BindView(R.id.apply_avatar)
        ImageView avatar;
        @BindView(R.id.apply_approval)
        Button approval;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
