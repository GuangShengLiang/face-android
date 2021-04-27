package com.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import face.R;

public class AboutAddressActivity extends AppCompatActivity {

    @BindView(R.id.tv_a_about)
    EditText title;
    @BindView(R.id.title_bar)
    TitleBar titleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_address_about);
        ButterKnife.bind(this);
        title.setText("title");
//        ah.myInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<Account>() {
//                    @Override
//                    public void onComplete() {
//
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException){
//                            HttpException he = (HttpException)e;
//                            if (he.code()==600){
//                                try {
//                                    Log.d("biz error {}",he.response().errorBody().string());
//                                } catch (IOException e1) {
//                                    e1.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                    @Override
//                    public void onNext(Account a) {
//                        title.setText("title");
//                        time.setText("time");
//                        address.setText("address");
//                    }
//                });
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
    }

}
