package com.face.activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import face.R;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.ActivityParam;
import com.face.http.model.vo.ActivityDetailVo;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.text.SimpleDateFormat;
import java.util.*;

public class EditActivity extends BaseActivity {

    @BindView(R.id.tv_pd_title)
    EditText title;
    @BindView(R.id.tv_st_time)
    TextView stime;
    @BindView(R.id.tv_end_time)
    TextView etime;
    @BindView(R.id.tv_pd_address)
    EditText address;
    @BindView(R.id.tv_pd_content)
    EditText detail;
    //    @BindView(R.id.wheelview)
//    WheelView wheelView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    TimePickerView pvTime;
    ActivityDetailVo d;
    //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
    //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
    Calendar selectedDate = Calendar.getInstance();
    Calendar startDate = Calendar.getInstance();
//        startDate.Builder().s(2013, 0, 23);
//    Calendar endDate = Calendar.getInstance();
//        endDate.set(2029, 11, 28);
    //时间选择器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit);
        ButterKnife.bind(this);
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                TextView btn = (TextView) v;
                btn.setText(getTimes(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setCancelText("取消")
                .setSubmitText("确认")
                .setDate(selectedDate)
//                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

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
                ActivityParam r = ActivityParam.builder().title(title.getText().toString())
                        .startTime(stime.getText().toString())
                        .endTime(etime.getText().toString())
                        .address(address.getText().toString())
                        .pubType(0)
                        .detail(detail.getText().toString()).build();
                HTTP.activity.create(r).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse>(){
                            @Override
                            public void onNext(JsonResponse v) {
                                finish();
                            }
                        });
            }
        });
        HTTP.activity.detail(getIntent().getExtras().getLong("aid", 1214775584295485519L))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<ActivityDetailVo>>() {
                    @Override
                    public void onNext(JsonResponse<ActivityDetailVo> a) {
                        d = a.getData();
                        title.setText(d.getTitle());
                        address.setText(d.getAddress());
                        detail.setText(d.getDetail());
//                        stime.setText(getTimes(a.getStartTime()));
//                        etime.setText(getTimes(a.getEndTime()));

                    }
                });
    }

    @OnClick(R.id.tv_st_time)
    public void showStimeDatePicker() {
        pvTime.show(stime);
    }

    @OnClick(R.id.tv_end_time)
    public void showEtimeDatePicker() {
        pvTime.show(etime);
    }

    //    @OnClick(R.id.tv_pd_address)
    public void showAddressChoice() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_choice, null);
        GridView gv = view.findViewById(R.id.gv_choice);
        //GridView的数据源，直接从strings.xml中加载过来
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("txt", "具体地点");
        data.add(map);
        Map<String, String> map1 = new HashMap<>();
        map1.put("txt", "大概地点");
        data.add(map1);
        //自定义适配器
        SimpleAdapter simpAdapter = new SimpleAdapter(EditActivity.this, data, R.layout.dialog_choice_item,
                new String[]{"txt"}, new int[]{R.id.tv_choice});
        gv.setAdapter(simpAdapter); // step3

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        //监听点击事件，点击以后，之前的选中应该变为未选中
        gv.setOnItemClickListener((parent, view1, position, id) -> {
            alertDialog.dismiss();
            ComponentName cn;
            if (0 == position) {
                cn = new ComponentName(EditActivity.this, SearchAddressActivity.class);
            } else {
                cn = new ComponentName(EditActivity.this, AboutAddressActivity.class);
            }
            Intent intent = new Intent();
            intent.setComponent(cn);
            intent.putExtra("id", position);
            startActivity(intent);
        });
    }

    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
