package com.alawneh.shoppingapplicationtesting.network.retrofit

import com.alawneh.shoppingapplicationtesting.BuildConfig
import com.alawneh.shoppingapplicationtesting.data.remote.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PixabayApi {

    @GET("/api")
    fun getImage(@Query("q") prompt:String,
                 @Query("key") apiKey:String = BuildConfig.API_KEY):Response<ImageResponse>
}