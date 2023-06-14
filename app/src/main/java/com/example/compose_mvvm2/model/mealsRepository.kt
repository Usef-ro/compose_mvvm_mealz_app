package com.example.compose_mvvm2.model

import com.example.compose_mvvm2.model.api.mealzApi
import com.example.compose_mvvm2.model.response.mealzModel
import com.example.compose_mvvm2.model.response.mealzModelList

class mealsRepository(val webService: mealzApi = mealzApi()) {

    var catchMealz = ArrayList<mealzModel>()

    suspend fun getMeals(): mealzModelList {
        val res = webService.getMealz()
        catchMealz = res.categories
        return webService.getMealz()
    }

    fun getDetail(id: String): mealzModel? {
        return catchMealz.firstOrNull {
            it.idCategory == id
        }
    }

    companion object {
        @Volatile
        var instace: mealsRepository? = null
        fun getInstance() = instace ?: synchronized(this) {
            instace ?: mealsRepository().also {
                instace = it
            }
        }

    }
    /*

    funcation call bakc

     */
//    fun getMeals(successCallback :(response:mealzModelList?) -> Unit) {
//
//         webService.getMealz().enqueue(object:Callback<mealzModelList>{
//
//            override fun onResponse(
//                call: Call<mealzModelList>,
//                response: Response<mealzModelList>
//            ) {
//                if(response.isSuccessful){
//                    successCallback(response.body())
//                }
//                Log.e("respose",""+response.body()?.categories)
//                Log.e("respose",""+response.message())
//
//            }
//
//            override fun onFailure(call: Call<mealzModelList>, t: Throwable) {
//                Log.e("onFailure",""+t.message)
//            }
//
//        })
//    }
}