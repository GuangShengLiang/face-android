package com.example.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.face.R;
import com.example.face.adapter.ApplyAdapter;
import com.example.face.adapter.InviteAdapter;
import com.example.face.adapter.PartnerAdapter;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.act.ActivityDetail;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActManageActivity extends BaseActivity {

    @BindView(R.id.tv_a_title)
    TextView title;
    @BindView(R.id.tv_a_time)
    TextView time;
    @BindView(R.id.tv_a_address)
    TextView address;
    @BindView(R.id.rcv_partner)
    RecyclerView partnerView;
    @BindView(R.id.rcv_apply)
    RecyclerView applyView;
    @BindView(R.id.rcv_invite)
    RecyclerView inviteView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    long aid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_manage);
        ButterKnife.bind(this);
        initView();
        HTTP.activity.detail(getIntent().getExtras().getLong("aid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ActivityDetail>() {
                    @Override
                    public void onNext(ActivityDetail a) {
                        title.setText(a.getTitle());
                        time.setText(a.getStime());
                        address.setText(a.getAddress());
                        aid=a.getAid();
                    }
                });
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("aid", aid);
                mContext.startActivity(intent);
            }
        });
    }

    private void initView() {
        PartnerAdapter adapter = new PartnerAdapter(this);
        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this);
        managerHorizontal.setOrientation(RecyclerView.HORIZONTAL);

        partnerView.setLayoutManager(managerHorizontal);
        partnerView.setHasFixedSize(true);
        partnerView.setAdapter(adapter);
        adapter.setHorizontalDataList(getIntent().getExtras().getLong("aid"));
        initApply();
        initInvite();
    }

    void initApply() {
        ApplyAdapter ad = new ApplyAdapter(this);
        LinearLayoutManager horizontal = new LinearLayoutManager(this);
        horizontal.setOrientation(RecyclerView.VERTICAL);
        applyView.setLayoutManager(horizontal);
        applyView.setHasFixedSize(true);
        applyView.setAdapter(ad);
        ad.setHorizontalDataList(getIntent().getExtras().getLong("aid"));
    }

    void initInvite() {
        InviteAdapter ad = new InviteAdapter(this);
        LinearLayoutManager horizontal = new LinearLayoutManager(this);
        horizontal.setOrientation(RecyclerView.VERTICAL);
        inviteView.setLayoutManager(horizontal);
        inviteView.setHasFixedSize(true);
        inviteView.setAdapter(ad);
        ad.setHorizontalDataList(getIntent().getExtras().getLong("aid"));
    }
}
