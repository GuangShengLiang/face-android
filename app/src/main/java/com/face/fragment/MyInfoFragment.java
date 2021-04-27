package com.face.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import face.R;
import com.face.activity.NickNameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyInfoFragment extends Fragment {
    @BindView(R.id.tv_nick_name)
    TextView nickName;
    @BindView(R.id.tv_sign)
    TextView sign;
    @BindView(R.id.tv_gander)
    TextView gander;
    @BindView(R.id.et_birthday)
    TextView birthday;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_info, container, false);
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
        String[] g = new String[]{"男", "女"};
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this.getActivity());// 自定义对话框
        // 2默认的选中
        builder3.setSingleChoiceItems(g, 0, (dialog, which) -> {// which是被选中的位置
            dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
        });
        builder3.show();// 让弹出框显示
    }

    @OnClick(R.id.ll_birthday)
    void editBirthday() {
    }
}
