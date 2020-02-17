package com.example.face.activity;

import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.face.R;

public class BirthdayActivity extends AppCompatActivity {

    @BindView(R.id.ev_nick_name)
    EditText nickName;
//    @BindView(R.id.titlebar)
//    CustomTitleBar titleBar;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nick_name);
        ButterKnife.bind(this);

        nickName.setText(getIntent().getStringExtra("nickName"));
//        titleBar.setLeftIconOnClickListener(v -> finish());
//        titleBar.setRightTextOnClickListener(v -> {
//                    finish();
//                }
//        );
    }
}
