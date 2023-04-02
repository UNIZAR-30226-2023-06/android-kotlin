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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*

@Composable
fun EditPieza(navController: NavHostController) {

    var piezaClicked  by remember { mutableStateOf("null") }
    var colorClicado: Color

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .background(AzulClaro)
                .width(350.dp)
                .height(250.dp)
                .border(width = 5.dp, color = AzulOscuro),
            contentAlignment = Alignment.Center

        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

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
                            colorClicado = Color.Black.copy(alpha = 0.6f)
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


                if (Globals.Piezas == piezaClicked || (Globals.Piezas == "default" && piezaClicked == "0") ) {
                    Text(
                        text = "Ya tienes estas piezas equipadas ",
                        fontSize = 16.sp,
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold

                        )
                    )
                } else {
                    Text(
                        text = "Cambiar piezas ",
                        fontSize = 16.sp,
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold

                        )
                    )
                }


                Spacer(modifier = Modifier.height(10.dp))

                if ( ((Globals.Piezas != piezaClicked && Globals.Piezas !="default" ) || (Globals.Piezas=="default"&& piezaClicked != "0" )) && "null" != piezaClicked ) {
                    Row() {

                        Button(
                            onClick = { navController.navigate(Routes.EditarPerfil.route) },
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
                                changedPiezas(piezaClicked)
                                navController.navigate(Routes.EditarPerfil.route)
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
                        onClick = { navController.navigate(Routes.EditarPerfil.route) },
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
