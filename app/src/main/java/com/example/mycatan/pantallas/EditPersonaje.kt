package com.example.mycatan.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*

@Composable
fun EditPersonaje( navController: NavHostController, changedPersonaje: (Int) ) {

    var personajeClicked by remember { mutableStateOf(-1) }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .background(AzulClaro)
                .size(250.dp)
                .border(width = 5.dp, color = AzulOscuro),
            contentAlignment = Alignment.Center

        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.Center, //maybe otra cosa
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(25.dp)
                ) {

                    // deberá recorrer el bueno
                    items(4)  {
                        PerfilItem(foto = it,
                            onCardClick = {personajeClicked=it})
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                if (Globals.Coins.toInt() >= 25) {
                    Text(
                        text = "¿Estas seguro? ",
                        fontSize = 14.sp,
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold

                        )
                    )
                } else {
                    Text(
                        text = "Saldo Insuficiente ",
                        fontSize = 14.sp,
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold

                        )
                    )
                }


                Spacer(modifier = Modifier.height(10.dp))

                if (Globals.Coins.toInt() >= 25) {
                    Row() {

                        Button(
                            onClick = { navController.navigate(Routes.Tienda.route) },
                            modifier = Modifier
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

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            onClick = {
                                onConfirmed(fotoId)
                                navController.navigate(Routes.Tienda.route)
                            },
                            modifier = Modifier
                                .width(100.dp)
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
                        onClick = { navController.navigate(Routes.Tienda.route) },
                        modifier = Modifier
                            .width(200.dp)
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


            }
        }
    }

}
