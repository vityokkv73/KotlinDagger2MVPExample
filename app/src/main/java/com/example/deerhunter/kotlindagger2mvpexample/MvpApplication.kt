package com.example.deerhunter.kotlindagger2mvpexample

import android.app.Application
import com.example.deerhunter.kotlindagger2mvpexample.inject.components.ApplicationComponent
import com.example.deerhunter.kotlindagger2mvpexample.inject.components.DaggerApplicationComponent
import com.example.deerhunter.kotlindagger2mvpexample.inject.modules.ApplicationModule

/**
 * Created by deerhunter on 6/22/16.
 */
class MvpApplication: Application() {
    lateinit var component: ApplicationComponent
    override fun onCreate() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }
}