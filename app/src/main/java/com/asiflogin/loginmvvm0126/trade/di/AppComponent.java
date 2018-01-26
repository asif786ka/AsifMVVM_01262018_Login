package com.asiflogin.loginmvvm0126.trade.di;

import com.asiflogin.loginmvvm0126.trade.activities.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, ViewModelModules.class, ValidatorModule.class})
public interface AppComponent {

    void inject(LoginActivity loginActivity);
}