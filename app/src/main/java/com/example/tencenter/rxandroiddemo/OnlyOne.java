package com.example.tencenter.rxandroiddemo;

import android.os.SystemClock;

public class OnlyOne implements Runnable{
    private OnlyOne() {

    }

    public static OnlyOne getInstance() {
        return OnlyOneHolder.INSTANCE;
    }

    @Override
    public void run() {

    }

    private static class OnlyOneHolder {
        public static final OnlyOne INSTANCE = new OnlyOne();
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
