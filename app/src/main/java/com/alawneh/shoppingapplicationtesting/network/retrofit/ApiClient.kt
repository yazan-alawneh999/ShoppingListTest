package com.alawneh.shoppingapplicationtesting.network.retrofit

import com.alawneh.shoppingapplicationtesting.util.ConstantUtil.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        val instacne by lazy { ApiClient() }
        val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        val api by lazy {
            retrofit.create(PixabayApi::class.java)
        }


    }
}