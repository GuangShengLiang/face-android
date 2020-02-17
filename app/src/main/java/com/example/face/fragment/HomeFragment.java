package com.example.face.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.face.R;
import com.example.face.activity.ActDetailActivity;
import com.example.face.activity.ActManageActivity;
import com.example.face.model.ActivityDetail;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.view.View.OVER_SCROLL_NEVER;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private TabListAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, null);
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        if (mRefreshLayout == null) {
            refreshLayout.setRefreshHeader(new ClassicsHeader(inflater.getContext()));
            refreshLayout.setRefreshFooter(new ClassicsFooter(inflater.getContext()));
            refreshLayout.setRefreshContent(mRecyclerView = new RecyclerView(inflater.getContext()));
            refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            refreshLayout.setDragRate(1);
            refreshLayout.setHeaderMaxDragRate(1.2f);
            refreshLayout.setEnableNestedScroll(false);
            mRefreshLayout = refreshLayout;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
            mRecyclerView.setOverScrollMode(OVER_SCROLL_NEVER);
        }
        return mRefreshLayout.getLayout();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mAdapter.refresh(initData());
            refreshLayout.finishRefresh(2000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mAdapter.loadMore(initData());
            refreshLayout.finishLoadMore(2000);
        });
        if (mAdapter == null) {
            mRecyclerView.setAdapter(mAdapter = new TabListAdapter());
            mRefreshLayout.autoRefresh();
        }

    }
    private List<ActivityDetail> initData() {
        List<ActivityDetail> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            ActivityDetail d = new ActivityDetail();
            d.setName("name"+i);
            list.add(d);
        }
        return list;
    }

    class TabListAdapter extends RecyclerView.Adapter {

        private List<ActivityDetail> list = new ArrayList<>();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.act_list_item,parent,false)) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView1 =  holder.itemView.findViewById(R.id.text1);
            textView1.setText(String.format(Locale.CHINA, "第%d条数据的饼屋", position + 1));
            holder.itemView.setOnClickListener(view -> {
//                    Toast.makeText("这是条目"+textView1.getText(),Toast.LENGTH_LONG).show());
                ComponentName cn = new ComponentName(getContext(), ActDetailActivity.class);
//                ComponentName cn = new ComponentName(getContext(), ActManageActivity.class);
                Intent intent = new Intent();
                intent.setComponent(cn);
                intent.putExtra("id", position);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        void refresh(List<ActivityDetail> list) {
            this.list.clear();
            this.loadMore(list);
        }

        void loadMore(List<ActivityDetail> list) {
            this.list.addAll(list);
            this.notifyDataSetChanged();
        }
    }
}
