package com.example.compose_mvvm2.ui.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose_mvvm2.model.response.mealzModel
import com.example.compose_mvvm2.ui.mealz.mealCategoryViewModel
import com.example.compose_mvvm2.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Composable
fun mainScrMealz(nav: (String) -> Unit) {

    val viewModel: mealCategoryViewModel = viewModel()

    val meals = viewModel.remeberMealz.value


//    var context=LocalContext.current
    when (meals.isEmpty()) {
        true -> progress()
        false -> {
            LazyColumn {

                itemsIndexed(meals) { index, meal ->

                    Content(meal, index, nav)

                }

//            Toast.makeText(LocalContext.current,""+sele,Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
fun progress(){
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        CircularProgressIndicator(modifier = Modifier
            .size(28.dp)
            .align(Alignment.CenterVertically)
            ,color= Color.Red
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Loading... ")
    }
}

//@Composable
//fun m(nav: (String) -> Unit,meals:List<mealzModel>){
//
//}
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
    val openDialog= remember {
        mutableStateOf(false)
    }
//    var scop=rem


    var scope = rememberCoroutineScope()

if(openDialog.value){
    alertDialogCustom(thum =meals.strCategoryThumb
        ,id = meals.idCategory,
        open ={openDialog.value=it}  )
}


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

        Card(shape= CircleShape,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        openDialog.value = true

                    }) {
            AsyncImage(
                model = meals.strCategoryThumb,
                contentDescription = meals.idCategory,
                modifier = Modifier.size(75.dp),

            )
      }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(top = 10.dp, bottom = 10.dp)
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

@Composable
fun alertDialogCustom(thum: String, id: String, open: (Boolean)->Unit) {
    Dialog(properties = DialogProperties(),onDismissRequest = {open(false)}, content =  {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White) {

            Box(contentAlignment = Alignment.Center,
            ){
                Row(modifier = Modifier.padding(5.dp)) {

                    AsyncImage(
                        modifier = Modifier
                        , model = thum, contentDescription = id
                    )
                }
            }
        }
    })
}

