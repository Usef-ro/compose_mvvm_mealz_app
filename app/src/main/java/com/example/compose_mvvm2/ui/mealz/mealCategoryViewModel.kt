package com.example.compose_mvvm2.ui.mealz

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_mvvm2.checkLive
import com.example.compose_mvvm2.model.mealsRepository
import com.example.compose_mvvm2.model.response.mealzModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class mealCategoryViewModel(private val repository: mealsRepository = mealsRepository.getInstance()) :
    ViewModel() {

//    val mealJob=Job()

    private lateinit var connectivityManager: checkLive

    init {
//        val scope= CoroutineScope(mealJob + Dispatchers.IO)
        viewModelScope.launch(Dispatchers.IO) {
            var attempts = 0
            var meals: List<mealzModel>? = null
            while (attempts < 3 && meals.isNullOrEmpty()) {
                try {
                    meals = getList()

                    Log.e("viemodel", "" + meals)
                    remeberMealz.value = meals
                } catch (e: Exception) {
                    Log.e("catch", "" + attempts)
                    attempts++
                    delay(1000)
                }

            }

        }

    }


    val remeberMealz: MutableState<List<mealzModel>> = mutableStateOf(emptyList<mealzModel>())


    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
//    override fun onCleared() {
//        super.onCleared()
//        mealJob.cancel()
//    }

    suspend fun getList(): ArrayList<mealzModel> {
        return repository.getMeals().categories
    }

//    fun getList(successCallback :(response: mealzModelList?) -> Unit){
//         repository.getMeals{response ->
//           successCallback(response)
//        }
//    }
}