package com.example.mycatan.pantallas

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.dBaux.getNumAmigosPendiente
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
                .padding(10.dp, 10.dp, 10.dp, 10.dp)

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


            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        PerfilItem2(foto = Globals.Personaje) {}

                        Spacer(modifier = Modifier.width(5.dp))


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

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${Globals.Username} #${Globals.Id}",
                        fontSize = 35.sp,
                        color = AzulOscuro,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        onClick = { },
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

                    Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        onClick = { },
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


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "Mis Piezas",
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

                        Spacer(modifier = Modifier.width(5.dp))

                        PerfilItem(foto = Globals.Piezas) {}

                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "Mi Tablero",
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
fun EditacionPersonaje( setShowDialog: (Boolean) -> Unit ) {

    var personajeClicked by remember { mutableStateOf("null") }
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
                            if( personajeClicked == it.toString() ){
                                colorClicado = GrisAzuladoClaro
                            }
                            else{
                                colorClicado = Color.Transparent
                            }
                            Box(modifier = Modifier
                                .background(colorClicado)

                            ){
                                PerfilItem(foto = it.toString(),
                                    onCardClick = {personajeClicked=it.toString()
                                        println(it.toString())})
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
                    } else {
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
                                    changedPersonaje(personajeClicked)
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
                            onClick = { setShowDialog(true) },
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
                                    changedPiezas(piezaClicked)
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
                            onClick = { setShowDialog(true) },
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
                                    changedMapa(mapaClicked)
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
                            onClick = { setShowDialog(true) },
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
            .clickable { onCardClick() }
            .fillMaxWidth(0.50f),

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