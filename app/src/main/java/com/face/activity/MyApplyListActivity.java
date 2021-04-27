package com.face.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import face.R;
import com.face.adapter.MyApplyAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyApplyListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.title_bar)
//    TitleBar titleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_join_list);

        ButterKnife.bind(this);

//        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
//            @Override
//            public void onLeftClick(View v) {
//                finish();
//            }
//
//            @Override
//            public void onTitleClick(View v) {
//
//            }
//
//            @Override
//            public void onRightClick(View v) {
//                Log.d("tt", "right");
//            }
//        });
        initView();
    }


    private void initView() {
        MyApplyAdapter adapter = new MyApplyAdapter(mContext);

        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this.getBaseContext());
        managerHorizontal.setOrientation(RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getBaseContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(managerHorizontal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}
