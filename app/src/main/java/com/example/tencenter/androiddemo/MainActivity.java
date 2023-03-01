package com.example.tencenter.androiddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.tencenter.androiddemo.inner.Direction;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    /**
     * 被观察者，事件源
     */
    private Observable<String> myObserveable;

    /**
     * 只发出一个事件就结束的Observable
     */
    private Observable<String> oneActionObserveable;
    /**
     * 观察者
     */
    private Subscriber<String> mySubscriber;

    private Direction direction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        WindowInsetsControllerCompat controllerCompat =
                new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        controllerCompat.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE);
        controllerCompat.hide(WindowInsetsCompat.Type.navigationBars());

        direction = Direction.LEFT;
        switch (direction) {
            case LEFT:
                System.out.println("left");
                break;
            default:
                System.out.println("any");
                break;
        }
        findViewById(R.id.RxAndroid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxDemoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testConsumer(10, new Consumer() {
                    @Override
                    public void accept(Object o) {
                        Toast.makeText(MainActivity.this, "sum = " + o, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.auth).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AuthActivity.class)));
        findViewById(R.id.webview_test).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WebViewActivity.class)));

        InitObserver();
        InitSubscriber();

//        myObserveable.subscribe(mySubscriber);
//        oneActionObserveable.subscribe(mySubscriber);


        /**
         * 上述实现，如果在不考虑onError和onComplete 的前提下，用以下代码即可实现
         */
//        Observable.just("hello world")
//                .map(new Func1<String, String>() {
//
//                    @Override
//                    public String call(String s) {
//                        return s.toUpperCase();
//                    }
//                })
//                .map(new Func1<String, String>() {
//
//                    @Override
//                    public String call(String s) {
//                        return s.substring(6);
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
////                        Log.e(MainActivity.class.getSimpleName(), "call--->" + s);
//                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                    }
//                });


        /**
         * 假设将被观察者事件源发生的事件 'hello world' 在观察者Subscribe中实现时，需要发生一些变化。
         * 操作符就是为了解决对Observable对象的变换的问题，
         * 操作符用于在Observable和最终的Subscriber之间修改Observable发出的事件。
         * RxJava提供了很多很有用的操作符。
         *
         */
//        Observable.just("hello world").
//                map(new Func1<String, Object>() {
//
//                    @Override
//                    public Object call(String s) {
//                        return s + "---->china";
//                    }
//                }).
//                subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        Log.e(MainActivity.class.getSimpleName(), "onNext--->" + o.toString());
//                        Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("item_" + i);
        }


        /**
         * flatMap
         */

//        Observable.just(datas)
//                .flatMap(new Func1<ArrayList<String>, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(ArrayList<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//                .map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        return s.hashCode();
//                    }
//                })
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer Integer) {
////                        Log.e(MainActivity.class.getSimpleName(), "call---->" + Integer);
//                    }
//                });


        /**
         * from
         */
//        Observable.from(datas)
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        return !s.equals("item_5");
//                    }
//                })
//                .take(5)
//                .doOnNext(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        s=s.substring(0,3).toUpperCase();
//                        Log.e(MainActivity.class.getSimpleName(), "call---->" + s);
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.e(MainActivity.class.getSimpleName(), "call---->" + s);
//                    }
//                });

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            nums.add(i * (i + 10));
        }

        Observable.range(12, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
//                        Log.e(MainActivity.class.getSimpleName(), "the integer is " + integer);
            }
        });

        /**
         * just
         */
        Observable.just(System.currentTimeMillis()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e(MainActivity.class.getSimpleName(), "the aLong is " + aLong);
            }
        });


//        Observable
//                .from(datas)
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s.toUpperCase();
//                    }
//                })
//                .toList()
//                .map(new Func1<List<String>, List<String>>() {
//                    @Override
//                    public List<String> call(List<String> strings) {
//                        Collections.reverse(strings);
//                        return strings;
//                    }
//                })
//                .flatMap(new Func1<List<String>, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(List<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.e(MainActivity.class.getSimpleName(), s);
//                    }
//                });


        /**
         * onError & onComplete()
         */

        List<Integer> numbers = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            numbers.add(i);
        }

        Observable.from(numbers).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer != 0;
            }
        }).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return 10 % integer;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(MainActivity.class.getSimpleName(), "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(MainActivity.class.getSimpleName(), "onError---->" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(MainActivity.class.getSimpleName(), "onNext---- >Remainder is " + integer);
            }
        });


    }

    /**
     * 创建观察者Subscribe
     */
    private void InitSubscriber() {
        mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(MainActivity.class.getSimpleName(), "onNext--->" + s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * 创建被观察者Observable
     */
    private void InitObserver() {
        myObserveable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
                subscriber.onCompleted();
            }
        });
        /**
         * 只发出一个事件就结束的Observable
         */
        oneActionObserveable = Observable.just("hello world");
    }

    private void testConsumer(int i, Consumer callback) {
        int sum = 0;
        for (int i1 = 0; i1 < i; i1++) {
            sum = sum + i1;
        }
        callback.accept(sum);
    }
}
