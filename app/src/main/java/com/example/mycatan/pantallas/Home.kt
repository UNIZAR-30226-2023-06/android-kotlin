package com.example.mycatan.pantallas

import android.widget.Toast
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.R
import com.example.mycatan.dBaux.postSendRequestFriend
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.pantallas.amigos.CustomDialog
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomePage(navController: NavHostController) {
    val buscarPartida =  remember { mutableStateOf(false) }
    val unirsePartida =  remember { mutableStateOf(false) }

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
                //.fillMaxWidth()
                //.background(color = AzulClaro),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                if(buscarPartida.value)
                    BuscarPartida(value = "", setShowDialog = {
                        buscarPartida.value = it
                    }) {}
                if(unirsePartida.value)
                    UnirsePartida(value = "", setShowDialog = {
                        unirsePartida.value = it
                    }) {}

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                    Button(
                        onClick = { buscarPartida.value = true },
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
                        onClick = { unirsePartida.value = true },
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {

                Row(modifier = Modifier
                    .padding(10.dp, 5.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Card(
                        modifier = Modifier
                            .clickable {navController.navigate(Routes.Login.route)
                            },
                        backgroundColor = Transparent
                    ){
                        Image( painter = painterResource(R.drawable.logout),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }


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

@Composable
fun BuscarPartida(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

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
                            text = "Buscando partida",
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
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Blanco, // Cambia el color a tu gusto
                            strokeWidth = 4.dp // Cambia el grosor de la línea del Spinner
                        )
                    }



                }
            }
        }
    }
}

@Composable
fun UnirsePartida(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
    val partida = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

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
                            text = "Introduce un código",
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
                        value = partida.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Blanco,
                            unfocusedBorderColor = Blanco,
                            disabledBorderColor = Blanco
                        ),
                        onValueChange = {
                            if (it.text.length <= 76)
                                partida.value = it
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            /*if(postSendRequestFriend( partida.value.text, Globals.Token )){
                                Toast.makeText(context, "OK la peticion de amistad ha sido enviada", Toast.LENGTH_SHORT).show()
                            } else{
                                Toast.makeText(context, "ERROR la petición no ha sido enviada", Toast.LENGTH_SHORT).show()
                            }*/
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Azul)

                    ) {
                        Text(text = "Unirse")
                    }
                }
            }
        }
    }
}