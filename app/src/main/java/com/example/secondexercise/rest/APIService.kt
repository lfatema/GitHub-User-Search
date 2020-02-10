package com.example.secondexercise.rest

import com.example.secondexercise.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/search/users")
    fun getUserDetails(@Query("q") tag: String): Call<Data>

}
