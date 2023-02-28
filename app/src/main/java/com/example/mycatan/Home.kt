package com.example.mycatan

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.ui.theme.*

@Composable
fun HomePage(navController: NavHostController) {
    var menuVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AzulClaro)
        .padding(10.dp, 10.dp, 10.dp, 10.dp))
    {

        //boton perfil
        Button(
            onClick = { menuVisible =! menuVisible },
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(50.dp)
                .height(50.dp),
                //maybe padding o algo para que no  quede tan a a la  esquina

            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

        ) {
            //imagen|icono
            Icon(imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Blanco,
                modifier = Modifier.width(50.dp)
                    .height(50.dp)
            )

        }

        //marca de agua
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = "CATAN INC.", style =
                TextStyle(fontSize = 20.sp, color = GrisAzulado, fontWeight = FontWeight.Bold)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Row(){
                Column(){

                    Icon(imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Amarillo)

                    Text(
                        text = "12$",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))
                //boton tienda
                Button(
                    onClick = { /*dirigira a la tienda */ },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    //maybe padding o algo para que no  quede tan a a la  esquina

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    //imagen|icono
                    Icon(imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Blanco,
                        modifier = Modifier.width(50.dp)
                            .height(50.dp))

                }
            }

        }


        //boton amigos
        /*Button(
            onClick = {  cosas de los amigos },
            modifier = Modifier
                .align(Alignment.BottomStart )
                .width(65.dp)
                .height(65.dp)
                .border(
                    width = 3.dp,
                    color = AzulOscuro,
                    shape = RoundedCornerShape(15.dp)
                ),
            //maybe padding o algo para que no  quede tan a a la  esquina

            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

        ) {
            //imagen|icono
        }*/


        Column(
            modifier = Modifier
                .fillMaxSize(),
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
                        .height(50.dp)
                        .border(
                            width = 3.dp,
                            color = AzulOscuro,
                            shape = RoundedCornerShape(15.dp)
                        ),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

                ) {
                    Text(
                        text = "BUSCAR PARTIDA",
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                Button(
                    onClick = { navController.navigate(Routes.Home.route) },
                    modifier = Modifier
                        .width(280.dp)
                        .height(50.dp)
                        .border(
                            width = 3.dp,
                            color = AzulOscuro,
                            shape = RoundedCornerShape(15.dp)
                        ),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

                ) {
                    Text(
                        text = "CREAR PARTIDA CON AMIGOS",
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                Button(
                    onClick = { navController.navigate(Routes.Home.route) },
                    modifier = Modifier
                        .width(280.dp)
                        .height(50.dp)
                        .border(
                            width = 3.dp,
                            color = AzulOscuro,
                            shape = RoundedCornerShape(15.dp)
                        ),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

                ) {
                    Text(
                        text = "RECUPERAR PARTIDA",
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }


        }

    }

    // OPTIONS MENU
    AnimatedVisibility(visible = menuVisible,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000)) ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable { menuVisible = !menuVisible },
            color = Color.Black.copy(alpha = 0.6f)
        ){
            MenuScreen(navController)
        }
    }

   /*AnimatedVisibility(visible = menuVisible,
        enter = expandHorizontally (animationSpec = tween(1000)),
        exit = shrinkHorizontally(animationSpec = tween(1000)) ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.20f) // 70% of screen
                //.background(Color.White)
        ) {
            MenuScreen(navController, {menuVisible = it})
        }
    }*/


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}