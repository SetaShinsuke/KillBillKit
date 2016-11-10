package com.seta.killbillkit;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by SETA_WORK on 2016/11/4.
 */

public class RxTest {

    public void init(){


        Observable<String> myObservable = Observable.create( //创建被观察者(事件发出者)
                new Observable.OnSubscribe<String>(){ //传入一个方法<参数>
                    @Override
                    public void call(Subscriber<? super String> subscriber) { //参数：监听者
                        subscriber.onNext("Hello, world.");
                        subscriber.onCompleted();
                    }
                });
        Observable<String> observable = Observable.just("Hell world.");
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };
        observable.subscribe(action1);
        Observable.just("Hell,world")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s+"_By Seta";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }

    private void init2(){
        query("keyword1")
            .subscribe(new Action1<List<String>>() {
                @Override
                public void call(List<String> urls) {
                    for(String url:urls){
                        Log.v("hehe","url : " + url);
                    }
                }
            });

        query("keyword")
            .subscribe(new Action1<List<String>>() {
                @Override
                public void call(List<String> urls) {
                    Observable.from(urls)
                            .subscribe(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    System.out.print(s);
                                }
                            });
                }
            });
    }

    /**
     * ====================================================================
     * 常用操作符
     */
    private void init3(){
        query("keyword")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s+=".com";
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s!=null;
                    }
                })
                .take(5)
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.print("准备发射 " + s);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }

    private Observable<List<String>> query(String text){
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("http://url_0"+text);
        list.add("http://url_1"+text);
        list1.add("http://url_1"+text);
        list.add("http://url_2"+text);
        list.add("http://url_3"+text);
        list.add("http://url_4"+text);
        return Observable.just(list,list1);
    }














































}
