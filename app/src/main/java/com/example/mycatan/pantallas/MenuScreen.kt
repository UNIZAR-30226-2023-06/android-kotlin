package com.example.mycatan.pantallas

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.painter.Painter
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

    Column(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()
        .background(AzulOscuro)){
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            item {
                // IMAGE
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(15.dp))

                    //FotoPerfil(foto = Globals.Personaje) {}

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${Globals.Username} #${Globals.Id}",
                        fontSize = 20.sp,
                        color = Blanco,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "ELO: 500",
                        fontSize = 20.sp,
                        color = Blanco,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Row{
                        Text(
                            text = Globals.Coins,
                            fontSize = 20.sp,
                            color = Blanco,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Image( painter = painterResource(R.drawable.moneda),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                }
            }

            // BUTTONS
            item {
                RowMenu(navController, "Inicio", Icons.Default.Home)
            }
            item {
                RowMenu(navController, "Editar perfil", Icons.Default.Edit)
            }
            item {
                RowMenu(navController, "Amigos", Icons.Default.Person)
            }
            item {
                RowMenu(navController, "Tienda", Icons.Default.ShoppingCart)
            }
            item {
                RowMenu(navController, "Cerrar sesión", Icons.Default.Close)
            }
        }

        Row(
            modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 15.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,


            ) {
            TextField(
                modifier = Modifier.fillMaxWidth(0.7f),
                singleLine = true,
                label = { Text(text = "1234", color= Blanco) },
                value = amigo.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Transparent,
                    focusedBorderColor = Blanco,
                    unfocusedBorderColor = Blanco,
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
                Text(text = "Enviar")
            }

        }
    }



}

@Composable
fun RowMenu(navController: NavHostController,  text: String, imageVector: ImageVector) {
    Row (
        modifier = Modifier.fillMaxWidth()
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
            color = Blanco,
        )
    }
}





