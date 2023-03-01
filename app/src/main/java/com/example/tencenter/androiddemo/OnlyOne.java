package com.example.tencenter.androiddemo;

import android.os.SystemClock;
import androidx.annotation.NonNull;

import com.example.tencenter.androiddemo.inner.IBarInterface;

public class OnlyOne implements Runnable {
    private IFooCallback fooCallback;

    private OnlyOne() {

    }

    public static OnlyOne getInstance() {
        return OnlyOneHolder.INSTANCE;
    }

    @Override
    public void run() {
        if (fooCallback != null) {
            fooCallback.onBack();
            fooCallback.onCall();
        }
    }

    private static class OnlyOneHolder {
        public static final OnlyOne INSTANCE = new OnlyOne();
    }

    interface FooCallback {
        void onCall();

        int onBack();
    }

    public long getTime() {
        long time = System.currentTimeMillis();
        if (time > 0) {
            return time / 1000;
        }
        return System.currentTimeMillis();
    }

    public long getElapsedRealtime() {
        long time = SystemClock.elapsedRealtime();
        if (time > 100) {
            time = time - 100;
        } else if (time > 50) {
            time = time - 50;
        }
        return time;
    }

    public void listen(IBarInterface barInterface) {
        barInterface.barbar();
        barInterface.go("OnlyOne");
    }

    public void setFooCallback(@NonNull IFooCallback fooCallback) {
        this.fooCallback = fooCallback;
    }
}
