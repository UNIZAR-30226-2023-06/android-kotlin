package com.example.mycatan


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.ui.theme.*

@Composable
fun TiendaScreen(fotoId: Int,navController: NavHostController) {

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
            ){

                RackItem(foto = fotoId) {}

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "¿Estas seguro? ",
                    fontSize = 14.sp,
                    style = TextStyle(
                        color = Negro,
                        fontWeight = FontWeight.Bold

                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row() {

                    Button(
                        onClick = { navController.navigate(Routes.Tienda.route)},
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                        shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(3.dp, AzulOscuro)

                    ) {
                        Text(text = "Cancelar",
                            style = TextStyle(
                                color = AzulOscuro,fontWeight = FontWeight.Bold)
                        )

                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = { navController.navigate(Routes.Tienda.route)},
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

                            Text(text = "Comprar",
                                style = TextStyle(
                                    color = AzulOscuro,fontWeight = FontWeight.Bold)
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(text = "   25$",
                                    style = TextStyle(
                                        color = AzulOscuro,fontWeight = FontWeight.Bold)
                                )
                                Icon(

                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Amarillo
                                )
                            }
                        }


                    }
                }

            }
        }
    }

}
