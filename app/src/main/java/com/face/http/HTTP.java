package com.face.http;

import android.util.Log;
import com.face.FLinkApplication;
import com.face.http.client.*;
import com.face.util.PreferencesUtil;
import com.google.gson.Gson;
import okhttp3.*;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class HTTP {

    //    private static final String base_url = "http://192.168.0.118:8080/api/";
    private static final String base_url = "http://39.101.138.75/api/";
//        private static final String base_url = "http://192.168.0.105/api/";
    //        private static final String base_url="http://172.19.231.191:8080/api/";
    public static final AccountHTTP account;
    public static final PassportHTTP passport;
    public static final LinkHTTP link;
    public static final ActivityHTTP activity;
    public static final RelationHTTP relation;
    public static final ApplyHTTP apply;
    public static final InviteHTTP invite;
    public static final InvitedHTTP invited;

    static {
        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(base_url+"account/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(slowHttpClient());
        passport = builder.baseUrl(base_url + "passport/").build().create(PassportHTTP.class);
        account = builder.baseUrl(base_url + "account/").build().create(AccountHTTP.class);
        relation = builder.baseUrl(base_url + "account/").build().create(RelationHTTP.class);
        activity = builder.baseUrl(base_url + "activity/").build().create(ActivityHTTP.class);
        apply = builder.baseUrl(base_url + "activity/").build().create(ApplyHTTP.class);
        invite = builder.baseUrl(base_url + "activity/").build().create(InviteHTTP.class);
        invited = builder.baseUrl(base_url + "activity/").build().create(InvitedHTTP.class);
        link = builder.baseUrl(base_url + "link/").build().create(LinkHTTP.class);
    }

    public static OkHttpClient slowMoreHttpClient() {
        // 添加公共参数拦截器
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(8L, TimeUnit.SECONDS)
                .writeTimeout(5L, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(10, 30L, TimeUnit.MINUTES))
                .connectTimeout(8L, TimeUnit.SECONDS).addInterceptor(commonInterceptor())
                .addInterceptor(chain -> logInterceptor(chain)).build();
        return client;
    }

    public static OkHttpClient slowHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(2L, TimeUnit.SECONDS)
                .writeTimeout(2L, TimeUnit.SECONDS)
                .connectTimeout(3L, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 10L, TimeUnit.MINUTES))
                .addInterceptor(commonInterceptor())
                .addInterceptor(chain -> logInterceptor(chain)).build();
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


    private static Interceptor commonInterceptor() {
        return chain -> {
            String token = PreferencesUtil.getToken(FLinkApplication.getContext());
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .addHeader("Accept-Encoding", "gzip")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .method(originalRequest.method(), originalRequest.body());
            requestBuilder.addHeader("token", token);//添加请求头信息，服务器进行token有效性验证
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private static Response logInterceptor(Interceptor.Chain chain) throws IOException {
        HttpUrl url = chain.request().url();
        long t1 = System.currentTimeMillis();
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
            Log.e("HTTP_LOG", "http error", e);
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - t1;
            Log.d("HTTP_LOG", String.format("|%s |%s |%s |cost %d|", url, rb, pb, cost));
        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(pb, mediaType))
                .build();
    }

    static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return (Converter<ResponseBody, Object>) body -> {
                if (body.contentLength() == 0) return null;
                return delegate.convert(body);
            };
        }
    }
}
