package com.example.compose_mvvm2.model.response

import com.google.gson.annotations.SerializedName

data class mealzModelList(val categories: ArrayList<mealzModel>)

data class mealzModel(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String
)