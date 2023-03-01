package com.example.tencenter.androiddemo;

import java.io.IOException;

import com.google.gson.Gson;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user 2016/8/3.
 */
public class RxDemoActivity extends AppCompatActivity {
    private Context mContext;
    //View
    private ImageView pic;
    private TextView title, id;
    private Button getInfo;
    //Http
    private static final String BaseUrl = "http://bubbler.labs.douban.com/j/user/ahbei";
    private OkHttpClient client;
    private Request request;

    private Wrapper.MyReceiver myReceiver;

    private WEEK mWeek;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_rxdemo);
        Init();
    }

    public void setWeek(WEEK week) {
        mWeek = week;
    }

    public WEEK getWeek() {
        return mWeek;
    }

    private void Init() {
        pic = (ImageView) findViewById(R.id.pic);
        title = (TextView) findViewById(R.id.tile);
        id = (TextView) findViewById(R.id.id);
        //
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(BaseUrl)
                .build();

        getInfo = (Button) findViewById(R.id.getInfo);
        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }


    private Observable<Response> HttpService() {
        Observable myObserve;


        myObserve = Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    subscriber.onNext(response);
                    subscriber.onCompleted();

                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }


        });


//        myObserve = Observable.just(BaseUrl)
//
//                .flatMap(new Func1<String, Observable<Request>>() {
//                    @Override
//                    public Observable<Request> call(String s) {
//                        Request request;
//                        request = new Request.Builder()
//                                .url(s)
//                                .build();
//                        return Observable.just(request);
//                    }
//                }).flatMap(new Func1<Request, Observable<Response>>() {
//                    @Override
//                    public Observable<Response> call(Request request) {
//                        Response response=null;
//                        try {
//                            response=client.newCall(request).execute();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        return Observable.just(response);
//                    }
//                });


        return myObserve;
    }


    private void getData() {
        HttpService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        if (response.isSuccessful()) {
                            try {
                                String json = response.body().string();
                                Log.e("onNext", json);
                                setView(json);
                                WEEK week = getWeek();
                                Log.d("onNext", "the week is " + week);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }


    public void setView(String json) {
        Gson gson = new Gson();
        DoubanBean douban = new DoubanBean();
        douban = gson.fromJson(json, DoubanBean.class);
        //
//        Glide.with(mContext).load(douban.getIcon()).into(pic);
        setWeek(WEEK.FIVE);
        title.setText(douban.getTitle());
        id.setText(douban.getId());

    }
}
