package com.asiflogin.loginmvvm0126.trade.di;

import com.asiflogin.loginmvvm0126.trade.repositories.RealmUserRepository;
import com.asiflogin.loginmvvm0126.trade.repositories.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    @Provides
    @Singleton
    UserRepository provideIssueRepository(RealmUserRepository repository) {
        return repository;
    }
}
