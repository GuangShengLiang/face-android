package com.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import face.R;
import com.face.fragment.FragmentA;
import com.google.android.material.tabs.TabLayout;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActTabsActivity extends BaseActivity {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] tabs = new String[]{"待参加", "申请中", " 被邀请", "发布中", "已结束"};
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tabs);
        ButterKnife.bind(this);

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
                Log.d("tt", "right");
            }
        });
        fragmentList.add(new FragmentA("waiting"));
        fragmentList.add(new FragmentA("apply"));
        fragmentList.add(new FragmentA("invited"));
        fragmentList.add(new FragmentA("publish"));
        fragmentList.add(new FragmentA("finish"));
        initView();


    }

    private void initView() {

        TabLayout tab_layout = findViewById(R.id.act_tabs);

        ViewPager viewPager = findViewById(R.id.viewPager);

        MyAdapter fragmentAdater = new MyAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdater);

        tab_layout.setupWithViewPager(viewPager);

    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {

            super(fm);

        }

        @Override

        public int getCount() {

            return tabs.length;

        }

        @Override

        public Fragment getItem(int position) {

            return fragmentList.get(position);

        }


        @Override

        public CharSequence getPageTitle(int position) {

            return tabs[position];

        }
    }
}
