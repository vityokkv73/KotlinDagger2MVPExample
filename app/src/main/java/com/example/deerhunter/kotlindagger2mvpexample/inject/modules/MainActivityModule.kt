package com.example.deerhunter.kotlindagger2mvpexample.inject.modules

import com.example.deerhunter.kotlindagger2mvpexample.inject.scopes.ActivityScope
import com.example.deerhunter.kotlindagger2mvpexample.mvp.presenters.MainActivityPresenter
import com.example.deerhunter.kotlindagger2mvpexample.network.StackOverflowAPIService
import dagger.Module
import dagger.Provides

/**
 * Created by deerhunter on 6/22/16.
 */

@Module
class MainActivityModule {
    @Provides @ActivityScope fun provideMainActivityPresenter(apiService : StackOverflowAPIService): MainActivityPresenter {
        return MainActivityPresenter(apiService)
    }
}