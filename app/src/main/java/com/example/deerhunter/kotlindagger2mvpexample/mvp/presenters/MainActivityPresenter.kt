package com.example.deerhunter.kotlindagger2mvpexample.mvp.presenters

import android.view.MenuItem
import com.example.deerhunter.kotlindagger2mvpexample.mvp.views.MainActivityMvpView
import com.example.deerhunter.kotlindagger2mvpexample.network.StackOverflowAPIService
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by deerhunter on 6/22/16.
 */
class MainActivityPresenter @Inject constructor(val apiService: StackOverflowAPIService) : MvpBasePresenter<MainActivityMvpView>() {
    fun loadList(tag: String) {
        view?.showProgressBar()
        apiService.loadQuestions(tag).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { data -> view?.setListData(data) },
                {
                    view?.showTextViewMode()
                    view?.hideProgressBar()
                },
                {
                    view?.showListMode()
                    view?.hideProgressBar()
                }
        )
    }

    fun processFloatingButtonClicked() {
        view?.showSnackbar("Text from presenter")
    }

    fun processMenuClicked() {
        view?.showDrawer()
    }

    fun processMenuItemClicked(item: MenuItem) {
        view?.closeDrawer()
    }

    fun processButtonClicked() {
        loadList("android")
    }
}