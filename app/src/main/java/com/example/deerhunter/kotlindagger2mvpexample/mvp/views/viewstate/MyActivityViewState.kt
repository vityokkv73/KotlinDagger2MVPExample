package com.example.deerhunter.kotlindagger2mvpexample.mvp.views.viewstate

import android.os.Bundle
import com.example.deerhunter.kotlindagger2mvpexample.mvp.models.Question
import com.example.deerhunter.kotlindagger2mvpexample.mvp.models.StackOverflowQuestions
import com.example.deerhunter.kotlindagger2mvpexample.mvp.views.MainActivityMvpView
import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState

/**
 * Created by deerhunter on 6/23/16.
 */
class MyCustomViewState : RestorableViewState<MainActivityMvpView> {
    private val KEY_STATE = "MyCustomViewState-flag"
    private val KEY_DATA = "MyCustomViewState-data"
    var data = StackOverflowQuestions()
    var state = State.TEXT_VIEW

    override fun apply(view: MainActivityMvpView?, retained: Boolean) {
        when (state) {
            State.LIST -> {
                view?.setListData(data)
                view?.showListMode()
            }
            State.TEXT_VIEW -> view?.showTextViewMode()
        }
    }

    override fun saveInstanceState(out: Bundle) {
        out.putInt(KEY_STATE, state.ordinal)
        out.putParcelableArrayList(KEY_DATA, data.items)
    }

    override fun restoreInstanceState(bundle: Bundle?): RestorableViewState<MainActivityMvpView>? {
        if (bundle == null) {
            return null
        }

        state = State.values()[bundle.getInt(KEY_STATE)]
        data.items = bundle.getParcelableArrayList<Question>(KEY_DATA)
        return this
    }

    enum class State{
        LIST,
        TEXT_VIEW
    }
}