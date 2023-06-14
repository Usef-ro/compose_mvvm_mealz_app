package com.example.compose_mvvm2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose_mvvm2.ui.detail.mainScrMealz
import com.example.compose_mvvm2.ui.detail.materialDetails
import com.example.compose_mvvm2.ui.mealz.detailMealzViewModel
import com.example.compose_mvvm2.ui.theme.Compose_mvvm2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        if error to build first build you must :

            1- lower sdk
            2- version kotlin and version compose must they are equal

         */
        setContent {
            Compose_mvvm2Theme {
                var context = LocalContext.current
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//
//
//
//                }
                FoodzApp()
            }
        }
    }
}


@Composable
fun FoodzApp() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "des_meals_list") {
        composable(route = "des_meals_list") {
            mainScrMealz() { navId ->
                navHostController.navigate("des_meals_list/$navId")

            }
        }
        composable(
            route = "des_meals_list/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewM: detailMealzViewModel = viewModel()

            materialDetails(viewM.mealzDet.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_mvvm2Theme {
//        mainScrMealz()

    }
}