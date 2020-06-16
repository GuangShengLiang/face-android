package com.example.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.face.R;
import com.example.face.adapter.MyInvitedAdapter;
import com.example.face.adapter.MyJoinAdapter;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class MyInvitedListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_join_list);

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
                Log.d("tt","right");
            }
        });
        initView();
    }


    private void initView() {
        MyInvitedAdapter adapter = new MyInvitedAdapter(mContext);

        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this.getBaseContext());
        managerHorizontal.setOrientation(RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getBaseContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(managerHorizontal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}
