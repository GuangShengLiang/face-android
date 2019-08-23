package com.example.face.weibo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.face.R;
import com.example.face.adapter.MomentAdapter;
import com.example.face.listener.EndlessRecyclerOnScrollListener;
import com.example.face.model.Moment;
import com.stfalcon.chatkit.commons.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends Fragment {
    protected ImageLoader imageLoader;
    private Context context;
    private List<Moment> dataList = new ArrayList<>();
    private SwipeRefreshLayout mView;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mine, container, false);

        mView = view.findViewById(R.id.refresh_layout);
        recyclerView =  mView.findViewById(R.id.rv);
        context = this.getContext();
        initData();
        final MomentAdapter momentAdapter = new MomentAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(momentAdapter);
        // 设置加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                momentAdapter.setLoadState(momentAdapter.LOADING);

                if (dataList.size() < 52) {
                    loadData();
                    momentAdapter.setLoadState(momentAdapter.LOADING_COMPLETE);
                } else {
                    // 显示加载到底的提示
                    momentAdapter.setLoadState(momentAdapter.LOADING_END);
                }
            }
        });
        mView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("zttjiangqq","invoke onRefresh...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <5; i++) {
                            int index = i + 1;
                            Moment m = new Moment();
                            m.setText("new item" + index);
                            dataList.add(m);
                        }
//                        momentAdapter.setLoadState();
                        mView.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        return view;
    }

    protected void initData() {
        for (int i = 0; i < 8; i++) {
            Moment m = new Moment();
            m.setText("item" + i);
            dataList.add(m);
        }
    }

    protected void loadData() {
        int j =dataList.size();
        for (int i = 0; i < 8; i++) {
            Moment m = new Moment();
            m.setText("item" + (j+i));
            dataList.add(m);
        }
    }
}
