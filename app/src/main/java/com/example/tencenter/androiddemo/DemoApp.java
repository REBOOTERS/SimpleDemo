package com.example.tencenter.androiddemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.os.Parcelable;
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
        testAll();
        testOnly();
    }

    private void testOnly() {
        OnlyOne onlyOne = OnlyOne.getInstance();
        System.out.println(onlyOne.getTime());
        System.out.println(onlyOne.getElapsedRealtime());
    }

    private void testAll() {
        TestAll.test();
        TestAll.AAA aa = TestAll.AAA.A;
        System.out.println(aa);
        TestAll.INSTANCE.useAAA(TestAll.AAA.C);

        Wrapper wrapper = new Wrapper();
        wrapper.aaaa();

        IFooCallback fooCallback = new IFooCallback() {
            @Override
            public void onCall() {

            }

            @Override
            public int onBack() {
                return 0;
            }
        };
        Log.d(TAG, "testAll()1 called " + Serializable.class.isAssignableFrom(IFooCallback.class));
        Log.d(TAG, "testAll()2 called " + Serializable.class.isAssignableFrom(ArrayList.class));
        Log.d(TAG, "testAll()3 called " + Serializable.class.isAssignableFrom(WEEK.class));
        List<String> ss = new ArrayList<>();
        Log.d(TAG, "testAll()4 called " + Serializable.class.isAssignableFrom(ss.getClass()));
        Log.d(TAG, "testAll()5 called " + Serializable.class.isAssignableFrom(Integer.class));
        Log.d(TAG, "testAll()6 called " + Serializable.class.isAssignableFrom(String.class));
//        Log.d(TAG,
//                "testAll()6 called " + Serializable.class.isAssignableFrom(IFooCallback$1.class));


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

    }
}
