package com.example.face.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.face.Constant;
import com.example.face.R;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.Account;
import com.example.face.util.ActivityUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.HorizontalViewHolder> {

    private Context mContext;

    private List<Account> mList = new ArrayList<>();

    public PartnerAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(long aid) {
        HTTP.activity.listMember(aid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Account>>() {
                    @Override
                    public void onNext(List<Account> l) {
                        mList.addAll(l);
                        notifyDataSetChanged();
                    }
                });
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.partner_list_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        Account acc = mList.get(position);
        holder.tvContent.setText(acc.getNickName());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
                .load(Constant.BASE_URL_PICTURE + acc.getAvatar())
                .apply(options)
                .into(holder.imAvatar);
        holder.itemView.setOnClickListener(view -> {
            ActivityUtils.openUserInfoActivity(mContext,acc.getUid());
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
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