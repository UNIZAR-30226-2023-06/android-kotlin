package com.example.mycatan.pantallas

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun EditarPerfil(navController: NavHostController) {

    var menuVisible by remember { mutableStateOf(false) }
    var EditClicked by remember { mutableStateOf(-1) }

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
                                    EditClicked = 1
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
                                        EditClicked = 2
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
                                        EditClicked = 3
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






        // OPTIONS MENU
        AnimatedVisibility(
            visible = menuVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { menuVisible = !menuVisible },
                color = Color.Black.copy(alpha = 0.6f)
            ) {

            }
        }
        AnimatedVisibility(
            visible = menuVisible,
            enter = expandHorizontally(animationSpec = tween(1000)),
            exit = shrinkHorizontally(animationSpec = tween(1000))
        )
        {
            if(EditClicked==1) // se clico editar de personaje
                EditPersonaje(navController)
            else if (EditClicked==2) // se clico editar de piezas
                EditPieza(navController)
            else if (EditClicked==3) // se clico editar de tablero
                EditMapa(navController)
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