package com.example.secondexercise.rest

import com.example.secondexercise.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/search/users?q=location:Ireland")
    fun getUserDetails(@Query("location") tag: String): Call<Data>

}
