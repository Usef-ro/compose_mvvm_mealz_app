package com.example.compose_mvvm2.ui.mealz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.compose_mvvm2.model.mealsRepository
import com.example.compose_mvvm2.model.response.mealzModel


class detailMealzViewModel(val state: SavedStateHandle) : ViewModel() {

    val mealzDet = mutableStateOf<mealzModel?>(null)
    val mealsRepositor: mealsRepository = mealsRepository.getInstance()

    init {
        val mealId = state.get<String>("meal_category_id") ?: ""
        mealzDet.value = mealsRepositor.getDetail(mealId)
    }

}