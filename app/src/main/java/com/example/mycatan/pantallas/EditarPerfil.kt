package com.example.mycatan.pantallas

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun EditarPerfil(navController: NavHostController) {

    var menuVisible by remember { mutableStateOf(false) }
    var fotoPopUp by remember { mutableStateOf(-1) }


    //no se guarda si vas para atras


        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.wave_3),
                    contentScale = ContentScale.FillBounds
                )
                .background(color = Transp)
                .padding(10.dp, 10.dp, 10.dp, 10.dp)

        )
        {

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .height(125.dp),

                            shape = CircleShape,
                            backgroundColor = Blanco,
                            border = BorderStroke(5.dp, AzulOscuro),
                        ) {
                            Image(
                                painter = painterResource(R.drawable.personaje8),
                                contentDescription = null,
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = "Mi Personaje",
                                fontSize = 25.sp,
                                color = AzulOscuro,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Box(
                                modifier = Modifier
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

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${Globals.Username} #${Globals.Id}",
                        fontSize = 35.sp,
                        color = AzulOscuro,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        textDecoration = TextDecoration.Underline
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
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Amarillo
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

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
                }

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
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

                            Box(
                                modifier = Modifier
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

                        Card(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp),

                            shape = CircleShape,
                            backgroundColor = Blanco,
                            border = BorderStroke(5.dp, AzulOscuro),
                        ) {
                            Image(
                                painter = painterResource(R.drawable.personaje8),
                                contentDescription = null,
                            )
                        }
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

                            Box(
                                modifier = Modifier
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

                        Card(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp),

                            shape = CircleShape,
                            backgroundColor = Blanco,
                            border = BorderStroke(5.dp, AzulOscuro),
                        ) {
                            Image(
                                painter = painterResource(R.drawable.personaje8),
                                contentDescription = null,
                            )
                        }
                    }

                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {

                Row(modifier = Modifier
                    .background(AzulOscuro)
                    .padding(10.dp, 5.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Icon(imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Amarillo)

                    Text(
                        text = Globals.Coins,
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

            }

                /*Text(text = "    Personajes",
                style = TextStyle
                    (fontSize = 20.sp, color = AzulOscuro, fontWeight = FontWeight.ExtraBold))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.Center, //maybe otra cosa
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(25.dp)
            ) {
                items(4)  {
                   PerfilItem(foto = it,
                        onCardClick = {
                            menuVisible = !menuVisible
                            fotoPopUp = it })
                }
            }*/

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
            //TiendaScreen(fotoPopUp, navController, onConfirmed)
        }
    }




@Composable
fun PerfilItem( foto: Int , onCardClick: () -> Unit ){



    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto==0){
        painterID = painterResource(R.drawable.personaje1)
    }
    else if(foto==1){
        painterID = painterResource(R.drawable.personaje2)
    }
    else if(foto==2){
        painterID = painterResource(R.drawable.personaje3)
    }
    else if(foto==3){
        painterID = painterResource(R.drawable.personaje4)
    }
    else if(foto==4){
        painterID = painterResource(R.drawable.personaje5)
    }
    else if(foto==5){
        painterID = painterResource(R.drawable.personaje6)
    }
    else if(foto==6){
        painterID = painterResource(R.drawable.personaje7)
    }
    else if(foto==7){
        painterID = painterResource(R.drawable.personaje8)
    }else {
        painterID = painterResource(R.drawable.personaje9)
    }

    Card(
        modifier = Modifier
            .clickable { onCardClick() }
            .width(100.dp)
            .height(100.dp),

        shape = CircleShape,
        backgroundColor = Blanco,
        border = BorderStroke(5.dp, AzulOscuro),
    ){
        Image(
            painter = painterID,
            contentDescription = null,
            )
    }
}