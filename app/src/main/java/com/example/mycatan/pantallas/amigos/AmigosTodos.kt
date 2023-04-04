package com.example.mycatan.pantallas.amigos

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.dBaux.*
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.pantallas.FotoPerfil
import com.example.mycatan.pantallas.MenuScreen
import com.example.mycatan.pantallas.PerfilItem
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun AmigosTodosPage(navController: NavHostController) {
    val context = LocalContext.current
    val showDialog =  remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  showDialog.value = true },
                backgroundColor = AzulOscuro,) {
                /* FAB content */
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = true,
                    onClick = {
                        navController.navigate(Routes.Home.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.AmigosTodos.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.Tienda.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        FotoPerfil(navController, foto = Globals.Personaje) {}
                    },

                    selected = false,
                    onClick = {
                        navController.navigate(Routes.EditarPerfil.route)
                    }
                )
            }
        },
    ) {

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


            if(showDialog.value)
                CustomDialog(value = "", setShowDialog = {
                    showDialog.value = it
                }) {}

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

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                // on below line we are populating
                // items for listview.
                items(filteredItems) { persona ->
                    println("func: todos tus amigos")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .background(TranspOscuro)
                    ){
                        // foto del usuario
                        val photo = getUserID(persona.takeLast(4))
                        FotoPerfil1(foto = photo) {}
                        Spacer(modifier = Modifier.width(5.dp))

                        // Nombre#123 de usuario
                        Box{
                            Text(persona/*persona.name+"#"+persona.id*/,
                                style = TextStyle(color = Blanco))
                        }

                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.BottomEnd){
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
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                            ) {
                                Text(text = "Dejar de seguir",
                                    style = TextStyle(color = Blanco)
                                )

                            }
                        }

                    }
                }
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

@Composable
fun FotoPerfil1(  foto: String , onCardClick: () -> Unit ){



    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto=="0"){
        painterID = painterResource(R.drawable.personaje1)
    }
    else if(foto=="1"){
        painterID = painterResource(R.drawable.personaje2)
    }
    else if(foto=="2"){
        painterID = painterResource(R.drawable.personaje3)
    }
    else if(foto=="3"){
        painterID = painterResource(R.drawable.personaje4)
    }
    else if(foto=="4"){
        painterID = painterResource(R.drawable.personaje5)
    }
    else if(foto=="5"){
        painterID = painterResource(R.drawable.personaje6)
    }
    else if(foto=="6"){
        painterID = painterResource(R.drawable.personaje7)
    }
    else if(foto=="7"){
        painterID = painterResource(R.drawable.personaje8)
    }
    else if (foto=="default")
    {
        painterID = painterResource(R.drawable.personaje1)
    }
    else {
        painterID = painterResource(R.drawable.personaje9)
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .size(40.dp),

        shape = CircleShape,
        backgroundColor = Blanco,
    ){
        Image(
            painter = painterID,
            contentDescription = null,

            )
    }

}

@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
    val amigo = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Añadir amigos",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "1234", color= Blanco) },
                        value = amigo.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Blanco,
                            unfocusedBorderColor = Blanco,
                            disabledBorderColor = Blanco
                        ),
                        onValueChange = {
                            if (it.text.length <= 76)
                                amigo.value = it
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if(postSendRequestFriend( amigo.value.text, Globals.Token )){
                                Toast.makeText(context, "OK la peticion de amistad ha sido enviada", Toast.LENGTH_SHORT).show()
                            } else{
                                Toast.makeText(context, "ERROR la petición no ha sido enviada", Toast.LENGTH_SHORT).show()
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Azul)

                    ) {
                        Text(text = "Enviar")
                    }
                }
            }
        }
    }
}