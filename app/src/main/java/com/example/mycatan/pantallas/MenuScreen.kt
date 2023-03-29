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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        modifier = Modifier.width(300.dp)
            .fillMaxHeight()
        //.background(color=Blanco)
    ) {


        // IMAGE, PERFIL DEL USUARIO
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = AzulClaro)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            horizontalAlignment = CenterHorizontally,

            ) {
            Card(
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp),

                shape = CircleShape,
                backgroundColor = Blanco,
                border = BorderStroke(5.dp, AzulOscuro),
            ){
                Image(

                    painter = painterResource(R.drawable.personaje8),
                    contentDescription = null,


                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${Globals.Username} #${Globals.Id}",
                fontSize = 20.sp,
                color = AzulOscuro,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(Routes.EditarPerfil.route) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Text(
                    text = "Editar perfil",
                    style = TextStyle(color = Blanco)
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(Routes.AmigosTodos.route) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Text(
                    text = "Amigos",
                    style = TextStyle(color = Blanco)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(Routes.AmigosTodos.route) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Text(
                    text = "Cerrar sesión",
                    style = TextStyle(color = Blanco)
                )

            }


            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp)
                    .fillMaxHeight(0.90f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier.width(200.dp),
                    singleLine = true,
                    label = { Text(text = "1234") },
                    value = amigo.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Blanco
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
                        .width(100.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(
                        text = "Enviar",
                        style = TextStyle(color = Blanco)
                    )
                }

            }


        }

    }
}



