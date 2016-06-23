package com.example.deerhunter.kotlindagger2mvpexample.inject.components

import com.example.deerhunter.kotlindagger2mvpexample.MvpApplication
import com.example.deerhunter.kotlindagger2mvpexample.inject.modules.ApplicationModule
import com.example.deerhunter.kotlindagger2mvpexample.inject.scopes.ActivityScope
import com.example.deerhunter.kotlindagger2mvpexample.network.StackOverflowAPIService
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by deerhunter on 6/22/16.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application : MvpApplication)

    fun retrofit(): Retrofit
    fun stackoverflowService(): StackOverflowAPIService
}