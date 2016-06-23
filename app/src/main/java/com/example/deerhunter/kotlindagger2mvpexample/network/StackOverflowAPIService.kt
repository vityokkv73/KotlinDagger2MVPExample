package com.example.deerhunter.kotlindagger2mvpexample.network

import com.example.deerhunter.kotlindagger2mvpexample.mvp.models.StackOverflowQuestions
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by deerhunter on 6/22/16.
 */
interface StackOverflowAPIService {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    fun loadQuestions(@Query("tagged") tags: String): Observable<StackOverflowQuestions>
}