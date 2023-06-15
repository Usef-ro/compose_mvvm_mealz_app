package com.example.compose_mvvm2.ui.detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_mvvm2.model.response.mealzModel
import com.example.compose_mvvm2.ui.theme.Purple200


@Composable
fun materialDetails(meal: mealzModel?) {

    var profileState by remember{ mutableStateOf(profileAnimation.NORMAL)}
    var transition= updateTransition(targetState = profileState,"")
    val imageSizeDp by transition.animateDp(targetValueByState = {it.dp}, label = "")
    val color by transition.animateColor(targetValueByState = {it.colorStorke}, label = "")
    val width by transition.animateDp(targetValueByState = {it.width}, label = "")


//    var isExapndImage by remember { mutableStateOf(false) }
//    val animationImageExpanding: Dp by animateDpAsState(targetValue = if(isExapndImage)  200.dp else 100.dp)

    Column() {
        Row() {
            Card(
                modifier = Modifier.padding(10.dp),
                border = BorderStroke(width, color)
            ) {

                AsyncImage(
                    model = meal?.strCategoryThumb,
                    contentDescription = null,
                    modifier = Modifier.size(imageSizeDp),
                    )
            }
            Text(meal?.strCategory ?: "")
        }

        Button(onClick = {

//            isExapndImage=!isExapndImage

           profileState= if(profileState==profileAnimation.NORMAL)
               profileAnimation.EXAPND
            else
                profileAnimation.NORMAL

        }) {
            Text(text = "change")
        }

    }

}

enum class profileAnimation(val colorStorke:Color,val dp:Dp,val width:Dp){
    NORMAL(Color.Gray,120.dp,8.dp),
    EXAPND(Color.Magenta,200.dp,24.dp)
}
