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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face.http.model.JsonResponse;
import face.R;
import com.face.activity.ActDetailActivity;
import com.face.http.BaseObserver;
import com.face.http.HTTP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.face.http.model.vo.ApplyVo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MyApplyAdapter extends RecyclerView.Adapter<MyApplyAdapter.HorizontalViewHolder> {
    private Context mContext;

    private List<ApplyVo> mList = new ArrayList<>();

    public MyApplyAdapter(Context context) {
        mContext = context;
        HTTP.apply.listApply()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<List<ApplyVo>>>() {
                    @Override
                    public void onNext(JsonResponse<List<ApplyVo>> ls) {
                        mList.addAll(ls.getData());
                        notifyDataSetChanged();
                    }
                });
    }

    @NonNull
    @Override
    public MyApplyAdapter.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_list_item, parent, false);
        return new MyApplyAdapter.HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyApplyAdapter.HorizontalViewHolder holder, int position) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.boy) //这里设置占位图
                .error(R.drawable.boy);
        Glide.with(mContext)
                .load("http://img2.woyaogexing.com/2020/02/14/3d352b92e7df409bb2dd172d0b73ad4f!400x400.jpeg")    //myurl表示图片的url地址
                .apply(options)
                .into(holder.avatar);
        ApplyVo d = mList.get(position);
        holder.title.setText(d.getActivity().getTitle());
        holder.address.setText(d.getActivity().getAddress());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, ActDetailActivity.class);
            intent.putExtra("aid", mList.get(position).getActivity().getAid());
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
