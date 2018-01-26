package com.asiflogin.loginmvvm0126.trade.di;

import com.asiflogin.loginmvvm0126.trade.viewmodels.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
abstract class ViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract LoginViewModel provideLoginViewModel(LoginViewModel viewModel);

}
