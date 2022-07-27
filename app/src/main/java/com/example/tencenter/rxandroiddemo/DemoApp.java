package com.example.tencenter.rxandroiddemo;

import java.util.Arrays;

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

        testEnum();
    }

    private void testEnum() {
        WEEK week1 = WEEK.SIX;
        WEEK week2 = WEEK.FIVE;
        WEEK[] weeks = WEEK.values();
        Log.d(TAG, "testEnum() called " + Arrays.toString(weeks));
        for (WEEK week : weeks) {
            if (week.equals(week1)) {
                System.out.println("week1");
            } else if (week.equals(week2)) {
                System.out.println("week2");
            } else {
                System.out.println("unknown");
            }
        }

        TestAll.test();
        TestAll.AAA aa = TestAll.AAA.A;
        System.out.println(aa);

        Wrapper wrapper = new Wrapper();
        wrapper.aaaa();
    }
}
