package com.example.face.http;

import android.util.Log;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import java.io.IOException;

public class BaseObserver<T> implements Observer<T> {

    protected String errMsg = "";
    protected Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(T t) {}

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException){
            HttpException he = (HttpException)e;
            if (he.code()==600){
                try {
                    Log.d("biz error {}",he.response().errorBody().string());
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
