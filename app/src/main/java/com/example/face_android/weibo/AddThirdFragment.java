package com.example.face_android.weibo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.face_android.R;
import com.example.face_android.features.demo.styled.StyledDialogsActivity;


/**
 * Created by Jue on 2018/6/2.
 */

public class AddThirdFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_styled_dialogs_bar, container,false);
//        StyledDialogsActivity.open(this.getContext());
        return view;
    }

}
