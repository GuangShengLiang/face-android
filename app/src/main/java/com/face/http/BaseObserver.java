package com.face.http;

import android.util.Log;

import java.io.IOException;

import android.view.Gravity;
import android.widget.Toast;
import com.face.FLinkApplication;
import com.face.http.model.JsonResponse;
import com.google.gson.Gson;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class BaseObserver<T> implements Observer<T> {

    protected String errMsg = "";
    protected Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException he = (HttpException) e;
            if (he.code() == 600) {
                try {
                    String body = he.response().errorBody().string();
                    JsonResponse err = new Gson().fromJson(body, JsonResponse.class);
//                    Toast t = Toast.makeText(FLinkApplication.getContext(),err.getMsg(),Toast.LENGTH_LONG);
//                    t.setGravity(Gravity.CENTER,0,0);
//                    t.show();
                    Toast t = Toasty.warning(FLinkApplication.getContext(), err.getMsg(),
                            Toast.LENGTH_LONG, true);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();

                    Log.e("biz error {}", body);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
