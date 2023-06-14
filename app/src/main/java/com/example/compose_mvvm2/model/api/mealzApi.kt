package com.example.compose_mvvm2.model.api

import com.example.compose_mvvm2.model.response.mealzModelList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class mealzApi {

    lateinit var apiMealz: mealzApi

    init {

        val ret = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiMealz = ret.create(mealzApi::class.java)

    }


    suspend fun getMealz(): mealzModelList {
        return apiMealz.getMealzApi()
    }

    interface mealzApi {
        @GET("categories.php")
        suspend fun getMealzApi(): mealzModelList

        /*
        You can use call ack instead coroutin
         */
//        fun getMealzApi() :Call<mealzModelList>
    }

}