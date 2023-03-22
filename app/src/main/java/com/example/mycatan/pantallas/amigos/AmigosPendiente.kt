package com.example.mycatan.pantallas.amigos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.ui.theme.*

@Composable
fun AmigosPendientePage(navController: NavHostController) {
    Column(modifier = Modifier
        .paint(
            painterResource(R.drawable.wave_3),
            contentScale = ContentScale.FillBounds)
        .background(color = Transp)
        .padding(40.dp, 40.dp, 40.dp, 40.dp))
    {
        val items by remember { mutableStateOf(listOf(
            "C++", "C", "C#", "Java", "Kotlin", "Dart", "Python", "Javascript", "SpringBoot",
            "XML", "Dart", "Node JS", "Typescript", "Dot Net", "GoLang", "MongoDb",
        ))}
        var filteredItems by remember { mutableStateOf(listOf<String>()) }
        Row(){
            SearchBar(onSearch = { query ->
                filteredItems = items.filter { it.contains(query, ignoreCase = true) }
            })
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ){
            var isSelectedTodos by remember { mutableStateOf(true) }
            var isSelectedPendiente by remember { mutableStateOf(false) }

            Column(){
                ClickableText(
                    text = AnnotatedString("Todos"),
                    onClick = { isSelectedTodos= !isSelectedTodos;
                                isSelectedPendiente = false
                              },
                    style = TextStyle(
                        color = if (isSelectedTodos) AzulOscuro else Color.White,
                        textDecoration = if (isSelectedTodos) TextDecoration.Underline else TextDecoration.None
                    )
                )
            }
            Spacer(modifier = Modifier.width(20.dp))

            Column(){
                ClickableText(
                    text = AnnotatedString("Pendiente"),
                    onClick = { isSelectedPendiente= !isSelectedPendiente;
                                isSelectedTodos = false},
                    style = TextStyle(
                        color = if (isSelectedPendiente) AzulOscuro else Color.White,
                        textDecoration = if (isSelectedPendiente) TextDecoration.Underline else TextDecoration.None
                    )
                )
            }
        }

        Row(){
            LazyColumn {
                // on below line we are populating
                // items for listview.
                items(filteredItems) { language ->
                    // on below line we are specifying ui for each item of list view.
                    // we are specifying a simple text for each item of our list view.
                    Text(language, modifier = Modifier.padding(15.dp))
                    // on below line we are specifying
                    // divider for each list item
                    Divider()
                }
            }
        }

    }
    //marca de agua
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = painterResource(R.drawable.image),
            contentDescription = "My image",
            modifier = Modifier.width(100.dp)
        )
    }


}


