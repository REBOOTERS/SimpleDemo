package com.example.tencenter.rxandroiddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
        }
    }
}
