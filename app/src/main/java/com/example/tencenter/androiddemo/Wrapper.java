package com.example.tencenter.androiddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tencenter.androiddemo.inner.IBarInterface;

public class Wrapper {
    private static final String TAG = "Wrapper";

    private int age = 0;

    public void aaaa() {
        int m = age + String.valueOf(age).hashCode();
        bbb(m);

    }

    private void bbb(int input) {
        MyReceiver receiver = new MyReceiver();
        System.out.println(receiver.getResultCode());
        Intent intent = new Intent();
        intent.putExtra("input", input);
        receiver.onReceive(null,intent);
    }
    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent
                    + "]");

            OnlyOne onlyOne = OnlyOne.getInstance();
            onlyOne.setFooCallback(new OnlyOne.FooCallback() {
                @Override
                public void onCall() {

                }

                @Override
                public int onBack() {
                    return 0;
                }
            });
            onlyOne.listen(new IBarInterface() {
                @Override
                public void barbar() {
                    System.out.println("bar");
                }

                @Override
                public void go(String input) {
                    System.out.println(input);
                }
            });
        }
    }
}
