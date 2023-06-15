package com.example.compose_mvvm2.ui.detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import com.example.compose_mvvm2.model.response.mealzModel
import com.example.compose_mvvm2.ui.theme.Purple200
import kotlin.math.min


@Composable
fun materialDetails(meal: mealzModel?) {

//    var scrollState = rememberScrollState()
    val scrollState = rememberLazyListState()
    val offSet= min(1f,1-(scrollState.firstVisibleItemScrollOffset/600f+scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp,200.dp*offSet))

//    var profileState by remember{ mutableStateOf(profileAnimation.NORMAL)}
//    var transition= updateTransition(targetState = profileState,"")
//    val imageSizeDp by transition.animateDp(targetValueByState = {it.dp}, label = "")
//    val color by transition.animateColor(targetValueByState = {it.colorStorke}, label = "")
//    val width by transition.animateDp(targetValueByState = {it.width}, label = "")


//    var isExapndImage by remember { mutableStateOf(false) }
//    val animationImageExpanding: Dp by animateDpAsState(targetValue = if(isExapndImage)  200.dp else 100.dp)

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column() {

        Surface(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
            Row() {
                Card(
                    modifier = Modifier.padding(10.dp),
                    shape= CircleShape,
                    border = BorderStroke(5.dp, Color.Cyan)
                ) {

                    AsyncImage(
                        model = meal?.strCategoryThumb,
                        contentDescription = null,
                        modifier = Modifier.size(size),
                    )
                }
                Text(meal?.strCategory ?: "", modifier = Modifier.align(alignment = Alignment.CenterVertically))
            }
        }
            val dummyList=(0..100).map { it.toString() }
            LazyColumn(modifier = Modifier.fillMaxWidth(),state = scrollState, ){
                items(dummyList){dummyList->
                    Text(text = dummyList, modifier = Modifier.padding(24.dp))
                }
            }

//            Button(onClick = {
//
////            isExapndImage=!isExapndImage
//
////           profileState= if(profileState==profileAnimation.NORMAL)
////               profileAnimation.EXAPND
////            else
////                profileAnimation.NORMAL
//
//            }) {
//                Text(text = "change")
//            }



//            Column(modifier = Modifier.verticalScroll(scrollState)) {
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//                Text(text = "This Text", modifier = Modifier.padding(10.dp))
//            }
        }
    }



}

enum class profileAnimation(val colorStorke:Color,val dp:Dp,val width:Dp){
    NORMAL(Color.Gray,120.dp,8.dp),
    EXAPND(Color.Magenta,200.dp,24.dp)
}
