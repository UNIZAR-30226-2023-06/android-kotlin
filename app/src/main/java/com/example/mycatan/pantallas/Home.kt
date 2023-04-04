package com.example.mycatan.pantallas

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.R
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomePage(navController: NavHostController) {

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.talado),
                    contentScale = ContentScale.FillBounds
                ),
            //.fillMaxWidth()
            //.background(color = AzulClaro),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                Button(
                    onClick = { navController.navigate(Routes.Home.route) },
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(
                        text = "BUSCAR PARTIDA",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                Button(
                    onClick = { navController.navigate(Routes.CrearPartida.route) },
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(
                        text = "UNIRSE CON CÓDIGO",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                Button(
                    onClick = { navController.navigate(Routes.CrearPartida.route) },
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(
                        text = "CREAR PARTIDA CON AMIGOS",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }


        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}

@Composable
fun FotoPerfil( navController: NavHostController, foto: String , onCardClick: () -> Unit ){



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
            .clickable {navController.navigate(Routes.EditarPerfil.route)
            }
            .padding(10.dp)
            .fillMaxHeight(),

        shape = CircleShape,
        backgroundColor = Blanco,
    ){
        Image(
            painter = painterID,
            contentDescription = null,

            )
    }
}