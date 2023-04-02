package com.example.mycatan.pantallas

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.dBaux.postSendRequestFriend
import com.example.mycatan.findActivity
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*

@Composable
fun MenuScreen(navController: NavHostController) {
    val context = LocalContext.current
    val amigo = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight(),
        //.background(color=Blanco)
    ) {


        // IMAGE, PERFIL DEL USUARIO
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = AzulOscuro)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            horizontalAlignment = CenterHorizontally,

            ) {

            PerfilItem(foto = Globals.Personaje) {}

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${Globals.Username} #${Globals.Id}",
                fontSize = 15.sp,
                color = Blanco,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "ELO: 500",
                fontSize = 15.sp,
                color = Blanco,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(10.dp))

            // BUTTONS
            Column(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                horizontalAlignment = Alignment.Start,
            ){
                RowMenu(navController, "Editar perfil", Icons.Default.Edit)

                RowMenu(navController, "Amigos", Icons.Default.Person)

                RowMenu(navController, "Cerrar sesión", Icons.Default.Close)
            }




            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp)
                    .fillMaxHeight(0.90f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier = Modifier.width(200.dp),
                    singleLine = true,
                    label = { Text(text = "1234", color=Blanco) },
                    value = amigo.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Transparent,
                        unfocusedBorderColor = Blanco,
                        focusedBorderColor = Blanco,
                        disabledBorderColor = Blanco
                    ),
                    onValueChange = {
                        if (it.text.length <= 76)
                            amigo.value = it
                    }
                )

                Spacer(modifier = Modifier.width(5.dp))
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
                        .width(50.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Azul)

                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        tint = Blanco,
                        contentDescription = "Enviar solicitud de amistad"
                    )
                }

            }


        }

    }
}

@Composable
fun RowMenu(navController: NavHostController,  text: String, imageVector: ImageVector) {
    Row (
        modifier = Modifier
            .clickable {
                if( text == "Cerrar sesión"){
                    navController.navigate(Routes.Login.route)
                } else if ( text == "Inicio"){
                    navController.navigate(Routes.Home.route)

                } else if ( text == "Editar perfil"){
                    navController.navigate(Routes.EditarPerfil.route)

                } else if ( text == "Amigos"){
                    navController.navigate(Routes.AmigosTodos.route)

                } else if ( text == "Tienda"){
                    navController.navigate(Routes.Tienda.route)

                }

            }
    , horizontalArrangement = Arrangement.Start
    ){
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Blanco,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        )

        Text(text = text,
            modifier = Modifier.align(CenterVertically),
            fontSize = 15.sp,
            color = Blanco,
            fontFamily = FontFamily.SansSerif,
        )
    }
}




