package com.example.face.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.face.R;
import com.example.face.adapter.MyJoinAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentA extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_join_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        MyJoinAdapter adapter = new MyJoinAdapter(this.getContext());

        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this.getContext());
        managerHorizontal.setOrientation(RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(managerHorizontal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


}
