package com.asiflogin.loginmvvm0126.trade;

import android.app.Application;

import com.asiflogin.loginmvvm0126.trade.di.AppComponent;
import com.asiflogin.loginmvvm0126.trade.di.DaggerAppComponent;

import io.realm.Realm;


public class LoginApplication extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().build();
        Realm.init(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}