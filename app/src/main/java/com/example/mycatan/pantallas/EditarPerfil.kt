package com.example.mycatan.pantallas

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.dBaux.*
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*

@Composable
fun EditarPerfil(navController: NavHostController) {
    val pendiente by remember { mutableStateOf(getNumAmigosPendiente(Globals.Token)) }
    var menuVisible by remember { mutableStateOf(false) }
    val editacionPersonaje =  remember { mutableStateOf(false) }
    val editacionPiezas =  remember { mutableStateOf(false) }
    val editacionMapa =  remember { mutableStateOf(false) }
    val cambiarContrasena =  remember { mutableStateOf(false) }
    val cambiarNombre =  remember { mutableStateOf(false) }

    Scaffold(
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
                            modifier = Modifier.padding(5.dp)
                        )
                        if (pendiente.toInt() > 0) {
                            Badge(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text(text = pendiente)
                            }
                        }
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
                        FotoPerfil(navController,foto = Globals.Personaje) {}
                    },

                    selected = false,
                    onClick = {
                        navController.navigate(Routes.EditarPerfil.route)
                    }
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.talado),
                    contentScale = ContentScale.FillBounds
                )
                .padding(10.dp, 40.dp, 10.dp, 10.dp)

        )
        {

            if(editacionPersonaje.value) // se clico editar de personaje
                EditacionPersonaje(setShowDialog = {
                    editacionPersonaje.value = it
                })
            if(editacionPiezas.value)// se clico editar de piezas
                EditacionPiezas(setShowDialog = {
                    editacionPiezas.value = it
                })
            if(editacionMapa.value)// se clico editar de tablero
                EditacionMapa(setShowDialog = {
                    editacionMapa.value = it
                })
            if(cambiarContrasena.value)// se clico editar de tablero
                CambiarContrasena(setShowDialog = {
                    cambiarContrasena.value = it
                })
            if(cambiarNombre.value)// se clico editar de tablero
                CambiarNombre(setShowDialog = {
                    cambiarNombre.value = it
                })

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        PerfilItem2(foto = Globals.Personaje) {}

                        Spacer(modifier = Modifier.width(10.dp))


                        //boton edit del personaje
                        Box(
                            modifier = Modifier
                                .clickable {
                                    menuVisible = !menuVisible
                                    editacionPersonaje.value = true
                                }
                                .width(45.dp)
                                .height(45.dp)
                                .background(
                                    color = AzulOscuro,
                                    shape = RoundedCornerShape(10.dp)

                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                //background(color = Amarillo),

                                tint = Blanco
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "${Globals.Username} #${Globals.Id}",
                        fontSize = 35.sp,
                        color = AzulOscuro,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        onClick = {cambiarNombre.value=true},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro),

                        ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Cambiar nombre",
                                style = TextStyle(color = Blanco)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Image(
                                painter = painterResource(R.drawable.moneda),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))


                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            PerfilItem(foto = Globals.Piezas) {}

                            Spacer(modifier = Modifier.width(5.dp))

                            Column(horizontalAlignment = Alignment.Start)
                            {

                                Text(
                                    text = "Piezas",
                                    fontSize = 20.sp,
                                    color = AzulOscuro,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                //boton edit piezas
                                Box(
                                    modifier = Modifier
                                        .clickable {
                                            menuVisible = !menuVisible
                                            editacionPiezas.value = true
                                        }
                                        .width(45.dp)
                                        .height(45.dp)
                                        .background(
                                            color = AzulOscuro,
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null,
                                        //background(color = Amarillo),

                                        tint = Blanco
                                    )
                                }

                            }

                        }
                        
                        Spacer(modifier = Modifier.width(30.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Column(
                                horizontalAlignment = Alignment.End
                            ) {

                                Text(
                                    text = "Mapa",
                                    fontSize = 20.sp,
                                    color = AzulOscuro,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                //boton edit tablero
                                Box(
                                    modifier = Modifier
                                        .clickable {
                                            menuVisible = !menuVisible
                                            editacionMapa.value = true
                                        }
                                        .width(45.dp)
                                        .height(45.dp)
                                        .background(
                                            color = AzulOscuro,
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null,
                                        //background(color = Amarillo),

                                        tint = Blanco
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            PerfilItem(foto = Globals.Mapa) {}
                        }

                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = { cambiarContrasena.value = true },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro),

                        ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Cambiar contraseña",
                                style = TextStyle(color = Blanco)
                            )
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Blanco
                            )
                        }
                    }
            }
        }

    }
    }

fun changedPersonaje( newP: String) {
    Globals.Personaje= newP
}
fun changedPiezas( newP: String) {
    Globals.Piezas= newP
}
fun changedMapa( newP: String) {
    Globals.Mapa= newP
}

@Composable
fun CambiarNombre(setShowDialog: (Boolean) -> Unit) {
    val context = LocalContext.current
    val newName = remember { mutableStateOf(TextFieldValue()) }
    var edited by remember { mutableStateOf(false) }
    var colorBoton = Azul

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
                            text = "Introduzca su nuevo nombre",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 20.sp,
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
                        label = { Text(text = Globals.Username, color= Blanco) },
                        value = newName.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Blanco,
                            unfocusedBorderColor = Blanco,
                            disabledBorderColor = Blanco,
                            textColor = Blanco,
                            cursorColor = Blanco
                        ),
                        onValueChange = {
                            newName.value = it
                            edited=true}
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if(!edited){
                        colorBoton = Azul
                    } else{
                        colorBoton = GrisAzuladoClaro
                    }

                    Button(
                        onClick = {
                            //conectar con backend
                            if(changeUsername(newName.value.text)){
                                Toast.makeText(context, "Username cambiado correctamente", Toast.LENGTH_SHORT).show()
                            } else{
                                Toast.makeText(context, "ERROR el nombre no se ha cambiado", Toast.LENGTH_SHORT).show()
                            }
                            setShowDialog(false)
                                  },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorBoton)

                    ) {
                        Text(text = "Unirse")
                    }
                }
            }
        }
    }
}


@Composable
fun CambiarContrasena(setShowDialog: (Boolean) -> Unit ) {

    val context = LocalContext.current
    var edited by remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val confirmarContrasena = remember { mutableStateOf(TextFieldValue()) }
    var colorBoton = Azul
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
                            text = "Cambiar contraseña",
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
                        label = { Text(text = "Contraseña", color= Blanco) },
                        value = password.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Blanco,
                            unfocusedBorderColor = Blanco,
                            disabledBorderColor = Blanco,
                            textColor = Blanco,
                            cursorColor = Blanco
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { password.value = it
                                          edited=true})

                    Spacer(modifier = Modifier.width(20.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Confirmar contraseña", color= Blanco) },
                        value = confirmarContrasena.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Blanco,
                            unfocusedBorderColor = Blanco,
                            disabledBorderColor = Blanco,
                            textColor = Blanco,
                            cursorColor = Blanco
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { confirmarContrasena.value = it
                                          edited=true})

                    Spacer(modifier = Modifier.height(20.dp))

                    if(!edited){
                        colorBoton = Azul
                    } else{
                        colorBoton = GrisAzuladoClaro
                    }

                    Button(
                        onClick = {
                            if ( password.value != confirmarContrasena.value
                                || password.value.text.isEmpty()
                                || confirmarContrasena.value.text.isEmpty()) {

                                val toast = Toast.makeText(context, "ERROR: Las contraseñas no coinciden  o están vacías. Vuelva a intentarlo.", Toast.LENGTH_SHORT)
                                toast.show()
                                colorBoton = Azul
                                edited = false
                            }
                            else{
                                //contectar backend
                                if(changePassword(password.value.text)){
                                    Toast.makeText(context, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show()
                                } else{
                                    Toast.makeText(context, "ERROR la contraseña no se ha cambiado", Toast.LENGTH_SHORT).show()
                                }
                                setShowDialog(false)
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorBoton)

                    ) {
                        Text(text = "Unirse")
                    }
                }
            }
        }
    }
}


@Composable
fun EditacionPersonaje( setShowDialog: (Boolean) -> Unit ) {

    val context = LocalContext.current
    var personajeClicked by remember { mutableStateOf("null") }
    var colorClicado: Color
    var sinObjetos = false

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elija su nuevo personaje ",
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

                    val fotosCompradasTrue = Globals.fotosCompradas.withIndex().filter { it.value }.map { it.index }

                    if (fotosCompradasTrue.isEmpty()) {
                        sinObjetos = true
                        Text("¡Error! No has comprado ninguna foto.")
                    } else {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            items(fotosCompradasTrue.size) { index ->
                                val foto = fotosCompradasTrue[index]
                                colorClicado = if (personajeClicked == foto.toString()) GrisAzuladoClaro else Color.Transparent
                                Box(
                                    modifier = Modifier.background(colorClicado)
                                ) {
                                    PerfilItem(
                                        foto = foto.toString(),
                                        onCardClick = {
                                            personajeClicked = foto.toString()
                                            println(foto.toString())
                                        }
                                    )
                                }
                            }
                        }
                    }



                    if(Globals.Personaje == personajeClicked || (Globals.Personaje == "default" && personajeClicked == "0") ) {
                        Text(
                            text = "Ya tienes este personaje equipado ",
                            fontSize = 16.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    } else if (!sinObjetos) {
                        Text(
                            text = "Cambiar personaje ",
                            fontSize = 16.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    if ( ((Globals.Personaje != personajeClicked && Globals.Personaje !="default" ) || (Globals.Personaje=="default"&& personajeClicked != "0" )) && "null" != personajeClicked ) {
                        Row() {

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro)

                            ) {
                                Text(
                                    text = "Cancelar",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )

                            }

                            Button(
                                onClick = {
                                    if(changeProfilePicture(personajeClicked)){
                                        Toast.makeText(context, "Personaje cambiado correctamente", Toast.LENGTH_SHORT).show()
                                        changedPersonaje(personajeClicked)
                                    } else{
                                        Toast.makeText(context, "ERROR el personaje no se ha cambiado", Toast.LENGTH_SHORT).show()
                                    }
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(200.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "Cambiar personaje",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                            }
                        }
                    } else {
                        Button(
                            onClick = {setShowDialog(false) },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro)

                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}

@Composable
fun EditacionPiezas( setShowDialog: (Boolean) -> Unit ) {

    val context = LocalContext.current
    var piezaClicked by remember { mutableStateOf("null") }
    var colorClicado: Color


    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elija sus nuevas piezas ",
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

                    //aqui van las fotos
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(5.dp), //maybe otra cosa
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(10.dp)
                    ) {

                        // deberá recorrer el bueno
                        items(9)  {
                            if( piezaClicked == it.toString() ){
                                colorClicado = GrisAzuladoClaro
                            }
                            else{
                                colorClicado = Color.Transparent
                            }
                            Box(modifier = Modifier
                                .background(colorClicado)

                            ){
                                PerfilItem(foto = it.toString(),
                                    onCardClick = {piezaClicked=it.toString()
                                        println(it.toString())})
                            }

                        }
                    }


                    if(Globals.Piezas == piezaClicked || (Globals.Piezas == "default" && piezaClicked == "0") ) {
                        Text(
                            text = "Ya tienes estas piezas equipadas ",
                            fontSize = 16.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    } else {
                        Text(
                            text = "Cambiar piezas ",
                            fontSize = 16.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    if ( ((Globals.Piezas != piezaClicked && Globals.Piezas !="default" ) || (Globals.Piezas=="default"&& piezaClicked != "0" )) && "null" != piezaClicked ) {
                        Row() {

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro)

                            ) {
                                Text(
                                    text = "Cancelar",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )

                            }

                            Button(
                                onClick = {
                                    if(changePiecesSkin(piezaClicked)){
                                        Toast.makeText(context, "Piezas cambiadas correctamente", Toast.LENGTH_SHORT).show()
                                        changedPiezas(piezaClicked)
                                    } else{
                                        Toast.makeText(context, "ERROR las piezas no se han cambiado", Toast.LENGTH_SHORT).show()
                                    }
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(200.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "Cambiar piezas",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                            }
                        }
                    } else {
                        Button(
                            onClick = { setShowDialog(false) },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro)

                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}

@Composable
fun EditacionMapa( setShowDialog: (Boolean) -> Unit ) {

    val context = LocalContext.current
    var mapaClicked by remember { mutableStateOf("null") }
    var colorClicado: Color


    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elija su nuevo mapa ",
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

                    //aqui van las fotos
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(5.dp), //maybe otra cosa
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(10.dp)
                    ) {

                        // deberá recorrer el bueno
                        items(9)  {
                            if( mapaClicked == it.toString() ){
                                colorClicado = GrisAzuladoClaro
                            }
                            else{
                                colorClicado = Color.Transparent
                            }
                            Box(modifier = Modifier
                                .background(colorClicado)

                            ){
                                PerfilItem(foto = it.toString(),
                                    onCardClick = {mapaClicked=it.toString()
                                        println(it.toString())})
                            }

                        }
                    }


                    if(Globals.Mapa == mapaClicked || (Globals.Mapa == "default" && mapaClicked == "0") ) {
                        Text(
                            text = "Ya tienes este mapa equipado ",
                            fontSize = 16.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    } else {
                        Text(
                            text = "Cambiar mapa ",
                            fontSize = 16.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    if ( ((Globals.Mapa != mapaClicked && Globals.Mapa !="default" ) || (Globals.Mapa=="default"&& mapaClicked != "0" )) && "null" != mapaClicked ) {
                        Row() {

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro)

                            ) {
                                Text(
                                    text = "Cancelar",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )

                            }

                            Button(
                                onClick = {
                                    if(changeGridSkin(mapaClicked)){
                                        Toast.makeText(context, "Mapa cambiado correctamente", Toast.LENGTH_SHORT).show()
                                        changedMapa(mapaClicked)
                                    } else{
                                        Toast.makeText(context, "ERROR el mapa no se ha cambiado", Toast.LENGTH_SHORT).show()
                                    }
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(200.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "Cambiar mapa",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                            }
                        }
                    } else {
                        Button(
                            onClick = { setShowDialog(false) },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro)

                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}


@Composable
fun PerfilItem2( foto: String , onCardClick: () -> Unit ){



    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto=="0"){
        painterID = painterResource(R.drawable.skin1)
    }
    else if(foto=="1"){
        painterID = painterResource(R.drawable.skin2)
    }
    else if(foto=="2"){
        painterID = painterResource(R.drawable.skin3)
    }
    else if(foto=="3"){
        painterID = painterResource(R.drawable.skin4)
    }
    else if(foto=="4"){
        painterID = painterResource(R.drawable.skin5)
    }
    else if(foto=="5"){
        painterID = painterResource(R.drawable.skin6)
    }
    else if(foto=="6"){
        painterID = painterResource(R.drawable.skin7)
    }
    else if(foto=="7"){
        painterID = painterResource(R.drawable.skin8)
    }
    else if (foto=="default")
    {
        painterID = painterResource(R.drawable.skin1)
    }
    else {
        painterID = painterResource(R.drawable.skin9)
    }

    Card(
        modifier = Modifier
            .clickable { onCardClick() }
            .fillMaxWidth(0.42f),

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
fun PerfilItem( foto: String , onCardClick: () -> Unit ){



    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto=="0"){
        painterID = painterResource(R.drawable.skin1)
    }
    else if(foto=="1"){
        painterID = painterResource(R.drawable.skin2)
    }
    else if(foto=="2"){
        painterID = painterResource(R.drawable.skin3)
    }
    else if(foto=="3"){
        painterID = painterResource(R.drawable.skin4)
    }
    else if(foto=="4"){
        painterID = painterResource(R.drawable.skin5)
    }
    else if(foto=="5"){
        painterID = painterResource(R.drawable.skin6)
    }
    else if(foto=="6"){
        painterID = painterResource(R.drawable.skin7)
    }
    else if(foto=="7"){
        painterID = painterResource(R.drawable.skin8)
    }
    else if (foto=="default")
    {
        painterID = painterResource(R.drawable.skin1)
    }
    else {
        painterID = painterResource(R.drawable.skin9)
    }
    Card(
        modifier = Modifier
            .clickable { onCardClick() }
            .size(90.dp),

        shape = CircleShape,
        backgroundColor = Blanco,
    ){
        Image(
            painter = painterID,
            contentDescription = null,
        )
    }
}