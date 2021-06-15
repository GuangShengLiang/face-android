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
 * @author smlzhixing@163.com
 * @date 4/27/21 4:43 PM
 * @describe TODO
 */

public class Page5Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private Unbinder unbinder;

    public Page5Fragment() {
    }

    public static Page5Fragment newInstance(String param1) {
        Page5Fragment fragment = new Page5Fragment();
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
        View rootView = inflater.inflate(R.layout.fragment_page5, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
