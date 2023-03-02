package com.example.mycatan

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
fun CrearPartidaPage(navController: NavHostController) {
    var numberValue: Int by rememberSaveable { mutableStateOf(15) }

    Row(){
        // MENU LATERAL
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.40f)
                .background(color = AzulOscuro)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),

            ) {

            Text(text = "CONFIGURACIÓN",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Divider(color = Color.White, thickness = 1.dp)

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(text = "Tiempo de turno",
                    fontSize = 10.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                // INCREMENTADOR/DECREMENTADOR NUMERO
                var count by remember { mutableStateOf(60) }
                
                Row (modifier = Modifier
                    .background(Color.White, RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                ){
                    IconButton(onClick = { count-- },modifier = Modifier.size(30.dp)  ) {
                        Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement",)
                    }
                    Text(text = count.toString())
                    IconButton(onClick = { count++ },modifier = Modifier.size(30.dp) ) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
                    }
                }
            }

            Row(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Ladrón",
                    fontSize = 10.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )

            }

            Row(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Puntos de victoria",
                    fontSize = 10.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                // INCREMENTADOR/DECREMENTADOR NUMERO
                var countVictoria by remember { mutableStateOf(10) }

                Row (modifier = Modifier
                    .background(Color.White, RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = { countVictoria-- },modifier = Modifier.size(30.dp)  ) {
                        Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement",)
                    }
                    Text(text = countVictoria.toString())
                    IconButton(onClick = { countVictoria++ },modifier = Modifier.size(30.dp) ) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Numero de jugadores",
                    fontSize = 10.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(15.dp))

                // INCREMENTADOR/DECREMENTADOR NUMERO
                var countJugadores by remember { mutableStateOf(4) }

                Row (modifier = Modifier
                    .background(Color.White, RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = { countJugadores-- },modifier = Modifier.size(30.dp)  ) {
                        Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement",)
                    }
                    Text(text = countJugadores.toString())
                    IconButton(onClick = { countJugadores++ },modifier = Modifier.size(30.dp) ) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
                    }
                }
            }

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = AzulClaro)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

        }


    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    val navController = rememberNavController()
    CrearPartidaPage(navController = navController)
}