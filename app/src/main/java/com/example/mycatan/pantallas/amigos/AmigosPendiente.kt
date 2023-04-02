package com.example.mycatan.pantallas.amigos

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.dBaux.*
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.pantallas.MenuScreen
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun AmigosPendientePage(navController: NavHostController) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .paint(
            painterResource(R.drawable.talado),
            contentScale = ContentScale.FillBounds)
        .padding(40.dp, 40.dp, 40.dp, 40.dp))
    {
        val list = getAmigosPendiente(Globals.Token)
        val items by remember { mutableStateOf(list)}
        var filteredItems by remember { mutableStateOf(list) } // Inicializamos con la lista para ver los items al principio
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
                    onClick = { isSelectedTodos= true;
                        isSelectedPendiente = false
                        navController.navigate(Routes.AmigosTodos.route)
                    },
                    style = TextStyle(
                        color = if (isSelectedTodos) Color.White else AzulOscuro,
                    )
                )
            }
            Spacer(modifier = Modifier.width(20.dp))

            Column(){
                ClickableText(
                    text = AnnotatedString("Pendiente"),
                    onClick = { isSelectedPendiente= true;
                        isSelectedTodos = false},
                    style = TextStyle(
                        color = if (isSelectedPendiente) Color.White else AzulOscuro,
                    )
                )
            }
        }

        Row(){
            LazyColumn {
                // on below line we are populating
                // items for listview.
                items(filteredItems) { id ->
                    val username = getUserID(id)
                    // on below line we are specifying
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Spacer(modifier = Modifier.width(10.dp))

                        Box(modifier = Modifier
                            .fillMaxWidth(0.70f)){
                            Text("$username te ha enviado una solicitud de amistad" ,
                                modifier = Modifier.padding(15.dp),
                                style = TextStyle(color = Blanco))
                        }
                        // Boton dejar de seguir
                        Button(
                            onClick = {
                                if(postAcceptRequestFriend(id, Globals.Token)){
                                    Toast.makeText(context, "OK peticion de amistad aceptada", Toast.LENGTH_SHORT).show()
                                } else{
                                    Toast.makeText(context, "ERROR peticion de amistad no se ha podido aceptar", Toast.LENGTH_SHORT).show()
                                } },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxHeight(0.75f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                        ) {
                            Text(text = "Aceptar",
                                style = TextStyle(color = Blanco)
                            )

                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(
                            onClick = {
                                if(postRejectRequestFriend(id, Globals.Token)){
                                    Toast.makeText(context, "OK peticion de amistad rechazada", Toast.LENGTH_SHORT).show()
                                } else{
                                    Toast.makeText(context, "ERROR peticion de amistad no se ha podido rechazar", Toast.LENGTH_SHORT).show()
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxHeight(0.75f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                        ) {
                            Text(text = "Rechazar",
                                style = TextStyle(color = Blanco)
                            )

                        }
                    }
                }
            }
        }

    }




}


