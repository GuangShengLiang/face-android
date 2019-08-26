package com.example.face.weibo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.face.R;
import com.example.face.activity.user.NickNameActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.aigestudio.datepicker.views.DatePicker;


public class MyInfoFragment extends Fragment {
    private Context context;
    @BindView(R.id.tv_nick_name)
    TextView nickName;
    @BindView(R.id.tv_sign)
    TextView sign;
    @BindView(R.id.et_gander)
    TextView gander;
    @BindView(R.id.et_birthday)
    TextView birthday;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_info, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        nickName.setText("lgs");
        sign.setText("山有木兮");
        gander.setText("男");
        birthday.setText("2000-01-01");
    }

    @OnClick(R.id.ll_nick_name)
    void editNickName() {
        Intent intent = new Intent(getActivity(), NickNameActivity.class);
//        intent.putExtra("userInfo", userInfo);
        intent.putExtra("nickName", nickName.getText());
        startActivity(intent);
    }

    @OnClick(R.id.ll_gander)
    void editGander() {
//        DialogPlus dialog = DialogPlus.newDialog(getActivity())
//                .setAdapter(new SimpleAdapter(, false))
//                .setContentHolder(new ListHolder())
//                .setCancelable(true)
//                .setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
//                    }
//                })
//                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
//                .create();
//        dialog.show();
        String[] g=new String[]{"保密","男","女"};
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this.getActivity());// 自定义对话框
        builder3.setSingleChoiceItems(g, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
//                changesex_textview.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }

    @OnClick(R.id.ll_birthday)
    void editBirthday() {
//        DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
//        picker.setDate(2015, 7);
//        picker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(List<String> date) {
//                String result = "";
//                Iterator iterator = date.iterator();
//                while (iterator.hasNext()) {
//                    result += iterator.next();
//                    if (iterator.hasNext()) {
//                        result += "\n";
//                    }
//                }
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
