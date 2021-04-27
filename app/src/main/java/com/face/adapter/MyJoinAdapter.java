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
import com.face.util.PreferencesUtil;

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
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
                .load("http://img2.woyaogexing.com/2020/02/14/3d352b92e7df409bb2dd172d0b73ad4f!400x400.jpeg")    //myurl表示图片的url地址
                .apply(options)
                .into(holder.avatar);
        ActivityDetailVo d = mList.get(position);
        holder.title.setText(d.getTitle());
        holder.address.setText(d.getAddress());
        holder.uname.setText(d.getPublisher().getNickName());
        holder.time.setText(d.getStime());
        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(mContext, ActDetailActivity.class);
//            intent.putExtra("aid", mList.get(position).getAid());
//            mContext.startActivity(intent);
            AccountDetail acc = PreferencesUtil.getAccount(mContext);
            Intent intent;
            if (acc != null && acc.getUid() == d.getPublisher().getUid()) {
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
        @BindView(R.id.uname)
        TextView uname;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.avatar)
        ImageView avatar;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
