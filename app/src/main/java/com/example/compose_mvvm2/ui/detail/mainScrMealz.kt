package com.example.compose_mvvm2.ui.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose_mvvm2.model.response.mealzModel
import com.example.compose_mvvm2.ui.mealz.mealCategoryViewModel
import com.example.compose_mvvm2.ui.theme.Typography


@Composable
fun mainScrMealz(nav: (String) -> Unit) {

    val viewModel: mealCategoryViewModel = viewModel()

    val meals = viewModel.remeberMealz.value
//    var context=LocalContext.current
    LazyColumn {

        itemsIndexed(meals) { index, meal ->

            Content(meal, index, nav)

        }

//            Toast.makeText(LocalContext.current,""+sele,Toast.LENGTH_SHORT).show()
    }
}


/*
call back
viewModel.getList{response ->
    val res=response?.categories
    Log.e("ee",""+res)
    remeberMealz.value=res.orEmpty()
}
*/
//    Text(text=remeberMealz.value.toString())


@Composable
fun Content(meals: mealzModel, pos: Int, nav: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

//    var scop=rem


    var scope = rememberCoroutineScope()

    Card(shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {

//                scope.launch {
//                    Toast
//                        .makeText(context, "" + pos, Toast.LENGTH_SHORT)
//                        .show()
//                }
                nav(meals.idCategory)
            }
    ) {
        Row(Modifier.animateContentSize()) {

//          Card(shape= RoundedCornerShape(100)) {
            AsyncImage(
                model = meals.strCategoryThumb,
                contentDescription = null,
                modifier = Modifier.size(75.dp)
            )
//          }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(top = 5.dp, bottom = 5.dp)
            )
            {
                Text(
                    text = meals.strCategory,
                    style = Typography.h6,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Text(
                    text = meals.strCategoryDescription,
                    style = Typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded) 20 else 3
                )
            }

            Icon(imageVector = if (!isExpanded)
                Icons.Filled.KeyboardArrowDown
            else
                Icons.Filled.KeyboardArrowUp,
                contentDescription = "Expand row ",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { isExpanded = !isExpanded })

        }

    }
}
