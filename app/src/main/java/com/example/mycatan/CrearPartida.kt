package com.example.mycatan

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    var numeroJugadores by remember { mutableStateOf(4) }
    Row(modifier = Modifier
        .paint(
            painterResource(R.drawable.wave_3),
            contentScale = ContentScale.FillBounds)){
        // MENU LATERAL
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.40f)
                .background(color = AzulMedio)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),

            )
        {

            Text(text = "CONFIGURACIÓN",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Divider(color = Color.White, thickness = 1.dp)

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ){

                Row(
                   horizontalArrangement = Arrangement.SpaceEvenly,
                   verticalAlignment = Alignment.CenterVertically)
                {
                    Text(
                        text = "Tiempo de turno",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(40.dp))
                    incrementador("turno", onFlechaClick = {})
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = "Ladrón",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    var ladron = remember { mutableStateOf(true) }
                    Switch(
                        checked = (ladron.value),
                        onCheckedChange = { ladron.value = it},
                        modifier = Modifier.padding(end = 15.dp),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.LightGray,
                            uncheckedThumbColor = Color.Gray,
                            checkedTrackColor = AzulClaro,
                            uncheckedTrackColor = Color.White,
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = "Puntos de victoria",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    incrementador("victoria", onFlechaClick = {})
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = "Numero de jugadores",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    incrementador("jugadores", onFlechaClick = {numeroJugadores = it})
                }
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Transp)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

            ) {
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp)
                        .padding(10.dp, 10.dp, 10.dp, 10.dp),

                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

                ) {
                    Text(
                        text = "YO",
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }
            botones()
            if( numeroJugadores == 4){
                botones()
                botones()
            } else if (numeroJugadores == 3){
                botones()
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.CatanBoard.route)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(280.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(text = "Comenzar",
                        style = TextStyle(color = Blanco)
                    )

                }
            }



        }


    }

}
@Composable
fun incrementador(tipo: String, onFlechaClick: (num: Int) -> Unit ){
    // INCREMENTADOR/DECREMENTADOR NUMERO
    var inicio = 0;
    if(tipo == "turno"){
        inicio = 60;
    }
    if(tipo == "victoria"){
        inicio = 10;
    }
    if(tipo == "jugadores"){
        inicio = 4;
    }
    var count by remember { mutableStateOf(inicio) }

    Row (modifier = Modifier
        .background(Color.White, RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ){

        IconButton(
            onClick = {
                if(count != 2){
                    count--
                    if(tipo == "jugadores"){
                        onFlechaClick(count);
                    }
                } },
            modifier = Modifier.size(25.dp)  ) {
            Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement",)
        }
        Text(text = count.toString(), fontSize = 14.sp,)
        IconButton(
            onClick = {
                if(tipo == "turno" && count != 300){
                    count++
                }
                if(tipo == "victoria" && count != 30){
                    count++
                }
                if(tipo == "jugadores" && count != 4){
                    count++
                    onFlechaClick(count);
                }
                 },

            modifier = Modifier.size(25.dp) ) {
            Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
        }
    }
}


@Composable
fun botones(){
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),) {
        Button(
            onClick = { },
            modifier = Modifier
                .width(280.dp)
                .height(60.dp)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),

            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

        ) {
            Text(
                text = "INVITAR",
                style = TextStyle(
                    color = AzulOscuro,
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    val navController = rememberNavController()
    CrearPartidaPage(navController = navController)
}