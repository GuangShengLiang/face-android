package com.face.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.face.http.model.JsonResponse;
import face.R;
import com.face.adapter.MyJoinAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.vo.ActivityDetailVo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class FragmentA extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String action;

    public FragmentA(String action) {
        super();
        this.action = action;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_join_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        MyJoinAdapter adapter = new MyJoinAdapter(this.getContext());
        switch (action) {
            case "waiting":
                HTTP.activity.listWaiting()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse<List<ActivityDetailVo>>>() {
                            @Override
                            public void onNext(JsonResponse<List<ActivityDetailVo>> ls) {
                                adapter.mList.addAll(ls.getData());
                                adapter.notifyDataSetChanged();
                            }
                        });
                break;
            case "invited":
       /*         HTTP.invited.listInvited()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<List<ApplyVo>>() {
                            @Override
                            public void onNext(List<ApplyVo> ls) {
                                ls.stream().map(e->{
                                    ActivityDetailVo v =new ActivityDetailVo();
                                    v.setp
                                    return v;
                                });
                                adapter.mList.addAll(ls);
                                adapter.notifyDataSetChanged();
                            }
                        });*/
                break;
            case "apply":
               /* HTTP.apply.listApply()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<List<ApplyVo>>() {
                            @Override
                            public void onNext(List<ApplyVo> ls) {
                                adapter.mList.addAll(ls);
                                adapter.notifyDataSetChanged();
                            }
                        });*/
                break;
            case "publish":
                HTTP.activity.listPublish()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse<List<ActivityDetailVo>>>() {
                            @Override
                            public void onNext(JsonResponse<List<ActivityDetailVo>> ls) {
                                adapter.mList.addAll(ls.getData());
                                adapter.notifyDataSetChanged();
                            }
                        });
                break;
            case "finish":
                HTTP.activity.listFinish()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<JsonResponse<List<ActivityDetailVo>>>() {
                            @Override
                            public void onNext(JsonResponse<List<ActivityDetailVo>> ls) {
                                adapter.mList.addAll(ls.getData());
                                adapter.notifyDataSetChanged();
                            }
                        });
                break;
            default:
                break;
        }

        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this.getContext());
        managerHorizontal.setOrientation(RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(managerHorizontal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


}
