package com.example.deerhunter.kotlindagger2mvpexample.inject.modules

import android.app.Application
import com.example.deerhunter.kotlindagger2mvpexample.network.StackOverflowAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by deerhunter on 6/22/16.
 */
@Module
class ApplicationModule(val application: Application) {
    @Provides @Singleton fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl("https://api.stackexchange.com").addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
    }

    @Provides @Singleton fun provideStackoverflowService(retrofit: Retrofit): StackOverflowAPIService {
        return retrofit.create(StackOverflowAPIService::class.java)
    }
}