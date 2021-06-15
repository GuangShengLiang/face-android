package com.face.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import face.R;

/**
 * @author  smlzhixing@163.com
 * @date  6/15/21
 * @describe TODO  我的-待参加容器
 */


public class Page1Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private Unbinder unbinder;

    public Page1Fragment() {
    }

    public static Page1Fragment newInstance(String param1) {
        Page1Fragment fragment = new Page1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page1, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
