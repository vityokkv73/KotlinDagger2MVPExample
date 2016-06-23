package com.example.deerhunter.kotlindagger2mvpexample.inject.components

import com.example.deerhunter.kotlindagger2mvpexample.MainActivity
import com.example.deerhunter.kotlindagger2mvpexample.inject.modules.MainActivityModule
import com.example.deerhunter.kotlindagger2mvpexample.inject.scopes.ActivityScope
import dagger.Component

/**
 * Created by deerhunter on 6/22/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}