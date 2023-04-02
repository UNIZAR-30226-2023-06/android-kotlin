package com.example.mycatan.pantallas.amigos

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.mycatan.pantallas.PerfilItem
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun AmigosTodosPage(navController: NavHostController) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(R.drawable.talado),
            contentScale = ContentScale.FillBounds
        )
        .padding(30.dp, 30.dp, 30.dp, 30.dp))
    {
        val list = getAmigosTodos(Globals.Token)
        val listFilter = mutableListOf<String>()
        for (i in list.indices) {
            val valor = list[i].name + list[i].id
            listFilter.add(valor)
        }
        val items by remember { mutableStateOf(listFilter)}
        var filteredItems by remember { mutableStateOf(listFilter) }

        Row(){
            SearchBar(onSearch = { query ->
                filteredItems = items.filter { it.contains(query, ignoreCase = true) } as MutableList<String>
            })
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ){
            var isSelectedTodos by remember { mutableStateOf(true) }
            var isSelectedPendiente by remember { mutableStateOf(false) }

            ClickableText(
                text = AnnotatedString("Todos"),
                onClick = {
                    isSelectedPendiente = false
                },
                style = TextStyle(
                    color = if (isSelectedTodos) Azul else AzulOscuro,
                )
            )
            Spacer(modifier = Modifier.width(20.dp))

            ClickableText(
                text = AnnotatedString("Pendiente"),
                onClick = { isSelectedPendiente= !isSelectedPendiente;
                    isSelectedTodos = false
                    navController.navigate(Routes.AmigosPendiente.route)},
                style = TextStyle(
                    color = if (isSelectedPendiente) Azul else AzulOscuro,
                )
            )

        }
        Spacer(modifier = Modifier.height(5.dp))

            LazyColumn {
                // on below line we are populating
                // items for listview.
                items(filteredItems) { persona ->
                    println("func: todos tus amigos")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.1f)
                            .clip(RoundedCornerShape(15.dp))
                            .background(TranspOscuro)
                    ){
                        Spacer(modifier = Modifier.width(10.dp))
                        // foto del usuario
                        val photo = getUserID(persona.takeLast(4)).toInt()
                        Image(
                            painter = painterResource(R.drawable.personaje4),
                            contentDescription = "foto perfil",
                            modifier = Modifier
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        // Nombre#123 de usuario
                        Box{
                            Text(persona/*persona.name+"#"+persona.id*/,
                                style = TextStyle(color = Blanco))

                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        // Boton dejar de seguir
                        Button(
                            onClick = {
                                if(postdeleteFriend(persona.takeLast(4)/*persona.id*/, Globals.Token)){
                                    Toast.makeText(context, "OK has dejado de seguir a ${persona.dropLast(4)}", Toast.LENGTH_SHORT).show()
                                } else{
                                    Toast.makeText(context, "ERROR no se ha podido completar la solicitud", Toast.LENGTH_SHORT).show()
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxHeight(0.75f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                        ) {
                            Text(text = "Dejar de seguir",
                                style = TextStyle(color = Blanco)
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }


    }

}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = query,
            onValueChange = { query = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
            ),
            label = { Text("Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(query)
                }
            )
        )

    }
}

