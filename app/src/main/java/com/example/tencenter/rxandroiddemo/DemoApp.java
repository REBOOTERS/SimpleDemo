package com.example.tencenter.rxandroiddemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class DemoApp extends Application {
    private static final String TAG = "DemoApp";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext() called with: base = [" + base + "]");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }
}
