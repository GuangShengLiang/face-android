package com.example.face.http;

import android.util.Log;
import okhttp3.*;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HTTPFactory {

    private static final String base_url="http://localhost:8080/api";
    private static final AccountHTTP accHttp;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(slowHttpClient())
                .build();

        accHttp = retrofit.create(AccountHTTP.class);
    }

    public static OkHttpClient slowMoreHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(8L, TimeUnit.SECONDS)
                .writeTimeout(5L, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(10, 30L, TimeUnit.MINUTES))
                .connectTimeout(8L, TimeUnit.SECONDS).addInterceptor(chain -> interceptor(chain)).build();
        return client;
    }

    public static OkHttpClient slowHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(2L, TimeUnit.SECONDS)
                .writeTimeout(2L, TimeUnit.SECONDS)
                .connectTimeout(5L, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(20, 30L, TimeUnit.MINUTES))
                .addInterceptor(chain -> interceptor(chain)).build();
        return client;
    }
//
//    @Bean
//    public OkHttpClient okHttpClient() {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(1L, TimeUnit.SECONDS)
//                .writeTimeout(1L, TimeUnit.SECONDS)
//                .connectionPool(new ConnectionPool(10, 30L, TimeUnit.MINUTES))
//                .connectTimeout(3L, TimeUnit.SECONDS).addInterceptor(chain -> interceptor(chain)).build();
//        return client;
//    }


    private static Response interceptor(Interceptor.Chain chain) throws IOException {
        HttpUrl url = chain.request().url();
        long t1 = System.nanoTime();
        Request request = chain.request();
        String rb = "";
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.newBuilder().build().body().writeTo(buffer);
            rb = buffer.readUtf8();
        }
        Response response;
        String pb = "null";
        okhttp3.MediaType mediaType;
        try {
            response = chain.proceed(chain.request());
            mediaType = response.body().contentType();
            pb = response.body() != null ? response.body().string() : "null";
        } catch (Exception e) {
            throw e;
        } finally {
            long cost = System.nanoTime()-t1;
            Log.d("HTTP_LOG",String.format("|%s|reqBody %s|respBody %s|cost %d|", url, rb, pb, cost));
        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(pb,mediaType))
                .header("token","6589affbcb794b97a4ca477c458bbff9")
                .build();
    }

    public static AccountHTTP getAccountHTTP(){
        return accHttp;
    }
}
