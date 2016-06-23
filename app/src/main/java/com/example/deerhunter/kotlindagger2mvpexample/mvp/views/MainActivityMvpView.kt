package com.example.deerhunter.kotlindagger2mvpexample.mvp.views

import com.example.deerhunter.kotlindagger2mvpexample.mvp.models.StackOverflowQuestions
import com.hannesdorfmann.mosby.mvp.MvpView

/**
 * Created by deerhunter on 6/22/16.
 */
interface MainActivityMvpView: MvpView {
    fun showSnackbar(text: String)
    fun showDrawer()
    fun showTextViewMode()
    fun showProgressBar()
    fun hideProgressBar()
    fun showListMode()
    fun setListData(data: StackOverflowQuestions)
    fun closeDrawer()
}