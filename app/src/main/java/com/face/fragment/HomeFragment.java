package com.face.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import face.R;
import com.face.adapter.ActAdapter;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.client.ActivityHTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.vo.ActivityDetailVo;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mAdapter == null) {
            mRecyclerView.setAdapter(mAdapter = new ActAdapter(this.getContext()));
            actHTTP.listActivitiesFriendJoinNext(0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<JsonResponse<List<ActivityDetailVo>>>() {
                        @Override
                        public void onNext(JsonResponse<List<ActivityDetailVo>> resp) {
                            mAdapter.refresh(resp.getData());
                        }

                    });
            mRefreshLayout.autoRefresh();
        }
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            actHTTP.listActivitiesFriendJoinPrevious(0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<JsonResponse<List<ActivityDetailVo>>>() {
                        @Override
                        public void onNext(JsonResponse<List<ActivityDetailVo>> ls) {
                            mAdapter.refresh(ls.getData());
                        }

                    });
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            long sid = 0;
            if (!mAdapter.list.isEmpty()) {
                sid = mAdapter.list.get(mAdapter.list.size() - 1).getAid();
            }
            actHTTP.listActivitiesFriendJoinNext(sid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<JsonResponse<List<ActivityDetailVo>>>() {
                        @Override
                        public void onNext(JsonResponse<List<ActivityDetailVo>> ls) {
                            mAdapter.loadMore(ls.getData());
                        }
                    });
            refreshLayout.finishLoadMore(2000);
        });
    }
}
