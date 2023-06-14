package com.example.compose_mvvm2.ui.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_mvvm2.model.response.mealzModel


@Composable
fun materialDetails(meal: mealzModel?) {
    var isExapndImage by remember { mutableStateOf(false) }
    val animationImageExpanding: Dp by animateDpAsState(targetValue = if(isExapndImage)  200.dp else 100.dp)

    Column() {
        Row() {
            Card() {
                AsyncImage(
                    model = meal?.strCategoryThumb,
                    contentDescription = null,
                    modifier = Modifier.size(animationImageExpanding),


                    )
            }
            Text(meal?.strCategory ?: "")
        }

        Button(onClick = { isExapndImage=!isExapndImage }) {

            Text(text = "change")

        }

    }

}