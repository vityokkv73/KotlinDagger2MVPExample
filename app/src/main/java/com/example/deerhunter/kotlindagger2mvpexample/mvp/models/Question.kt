package com.example.deerhunter.kotlindagger2mvpexample.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by deerhunter on 6/22/16.
 */
data class Question(val title : String, val link: String) : Parcelable {
    constructor(source: Parcel): this(source.readString(), source.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(link)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<Question> = object : Parcelable.Creator<Question> {
            override fun createFromParcel(source: Parcel): Question {
                return Question(source)
            }

            override fun newArray(size: Int): Array<Question?> {
                return arrayOfNulls(size)
            }
        }
    }
}