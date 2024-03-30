package com.example.projectsagarbhat


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Interface defining the API endpoints for fetching image data.
 */
interface ApiImageInterface {

    /**
     * Retrieves image data from the API.
     *
     * @return A Retrofit [Call] object wrapping a list of [Images].
     */
    @GET("photos")
    fun getData(): Call<List<Images>>
}

/**
 * Singleton class responsible for creating and configuring the Retrofit instance for image API calls.
 */
class ApiClientImage {

    companion object {
        /**
         * Retrofit builder to create and configure the Retrofit instance.
         */
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiImageInterface::class.java)
    }
}
