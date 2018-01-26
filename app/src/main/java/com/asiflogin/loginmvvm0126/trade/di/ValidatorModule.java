package com.asiflogin.loginmvvm0126.trade.di;

import com.asiflogin.loginmvvm0126.trade.validators.EmailValidator;
import com.asiflogin.loginmvvm0126.trade.validators.PasswordValidator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ValidatorModule {


    @Provides
    @Singleton
    EmailValidator provideEmailValidator() {
        return new EmailValidator("Invalid Email");
    }


    @Provides
    @Singleton
    PasswordValidator providePasswordValidator() {
        return new PasswordValidator("Invalid Password");
    }
}
