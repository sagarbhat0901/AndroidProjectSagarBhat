package com.example.projectsagarbhat

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//Creating an interface for the API that we are using to get data
interface ApiImageInterface{
    @GET("photos")
    fun getData() : Call<List<Images>>
}

class ApiClientImage {
    //Creating companion object to pass the base URL of the images
    companion object{
        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiImageInterface::class.java)
    }
}