package com.example.face.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.face.R;
import com.example.face.adapter.ActAdapter;
import com.example.face.http.ActivityHTTP;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.ActivityDetail;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

import static android.view.View.OVER_SCROLL_NEVER;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private ActAdapter mAdapter;
    private ActivityHTTP actHTTP = HTTP.activity;

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
        if (mAdapter == null) {
            mRecyclerView.setAdapter(mAdapter = new ActAdapter(this.getContext()));
            actHTTP.listFriendRefresh(0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<List<ActivityDetail>>() {
                        @Override
                        public void onNext(List<ActivityDetail> ls) {
                            mAdapter.loadMore(ls);
                        }
                    });
            mRefreshLayout.autoRefresh();
        }
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
//            mAdapter.refresh(initData());
            refreshLayout.finishRefresh(2000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            actHTTP.listFriendNext(0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<List<ActivityDetail>>() {
                        @Override
                        public void onNext(List<ActivityDetail> ls) {
                            mAdapter.loadMore(ls);
                        }
                    });
            refreshLayout.finishLoadMore(2000);
        });
    }
}
