package com.face.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.face.FLinkApplication;
import face.R;
import com.face.adapter.ApplyAdapter;
import com.face.adapter.InviteAdapter;
import com.face.adapter.PartnerAdapter;
import com.face.enums.ActivityStatusEnum;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.AidParam;
import com.face.http.model.vo.ActivityDetailVo;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import es.dmoral.toasty.Toasty;
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
    @BindView(R.id.btn_action)
    Button action;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    ActivityDetailVo d = null;
    ActivityStatusEnum status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_manage);
        ButterKnife.bind(this);
        initView();

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
                intent.putExtra("aid", d.getAid());
                intent.putExtra("uid", d.getPublisher().getUid());
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
        HTTP.activity.detail(getIntent().getExtras().getLong("aid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<ActivityDetailVo>>() {
                    @Override
                    public void onNext(JsonResponse<ActivityDetailVo> a) {
                        d = a.getData();
                        title.setText(d.getTitle());
                        time.setText(d.getStime());
                        address.setText(d.getAddress());
                        adapter.setHorizontalDataList(d.getAid());
                        status = ActivityStatusEnum.get(d.getStatus());
                        actionView();

                    }
                });
        initApply();
        initInvite();
    }

    private void actionView() {
        switch (status) {
            case 报名中:
                action.setText("停止报名");
                break;
            case 报名结束:
                action.setText("开始");
                break;
            case 活动进行中:
                action.setText("结束");
                break;
            case 活动结束:
                action.setText("已结束");
                action.setBackgroundColor(Color.GRAY);
                action.setClickable(false);
                break;
        }
    }

    @OnClick(R.id.btn_action)
    public void action() {
        AidParam r = new AidParam(d.getAid());
        switch (status) {
            case 报名中:
                HTTP.activity.applyStop(r).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse>() {
                            @Override
                            public void onNext(JsonResponse v) {
                                status = ActivityStatusEnum.报名结束;
                                Toast t = Toasty.success(FLinkApplication.getContext(), "OK",
                                        Toast.LENGTH_SHORT, true);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        });
                break;
            case 报名结束:
                HTTP.activity.start(r).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse>() {
                            @Override
                            public void onNext(JsonResponse v) {
                                status = ActivityStatusEnum.活动进行中;
                                Toast t = Toasty.success(FLinkApplication.getContext(), "OK",
                                        Toast.LENGTH_SHORT, true);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        });
                break;
            case 活动进行中:
                HTTP.activity.finish(r).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse>() {
                            @Override
                            public void onNext(JsonResponse v) {
                                status = ActivityStatusEnum.活动结束;
                                Toast t = Toasty.success(FLinkApplication.getContext(), "OK",
                                        Toast.LENGTH_SHORT, true);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        });
                break;
            case 活动结束:
                action.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        actionView();
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
