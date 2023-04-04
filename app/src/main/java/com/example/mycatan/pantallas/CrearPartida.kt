package com.example.mycatan.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.R
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CrearPartidaPage(navController: NavHostController) {
    var numeroJugadores by remember { mutableStateOf(4) }

    BottomSheetScaffold(
        sheetContent = {
            // Sheet content
           LazyColumn(modifier = Modifier.fillMaxWidth()){
               item{
                   Row (
                       modifier = Modifier.fillMaxWidth()
                           .background(AzulOscuro)
                           .padding(10.dp)
                   ){
                       Icon(
                           imageVector = Icons.Default.Settings,
                           contentDescription = null,
                           tint = Blanco,
                           modifier = Modifier
                               .size(50.dp)
                               .padding(10.dp)
                       )

                       Text(text = "Configuración",
                           modifier = Modifier.align(CenterVertically),
                           color = Blanco,
                       )
                   }

               }
               item{
                   Row (
                       modifier = Modifier.fillMaxWidth()
                           .padding(10.dp)
                   ){
                       Text(
                           text = "Tiempo de turno",
                           fontSize = 14.sp,
                           color = AzulOscuro,
                           fontFamily = FontFamily.SansSerif,
                           fontWeight = FontWeight.Bold
                       )

                       Box(modifier = Modifier.fillMaxWidth(),
                               contentAlignment = Alignment.BottomEnd){
                           incrementador("turno", onFlechaClick = {})

                       }
                   }
               }
               item{
                   Row(
                       modifier = Modifier.fillMaxWidth()
                           .padding(10.dp))

                       {
                       Text(text = "Ladrón",
                           fontSize = 14.sp,
                           color = AzulOscuro,
                           fontFamily = FontFamily.SansSerif,
                           fontWeight = FontWeight.Bold
                       )
                       var ladron = remember { mutableStateOf(true) }
                       Box(modifier = Modifier.fillMaxWidth(),
                           contentAlignment = Alignment.BottomEnd){
                           Switch(
                               checked = (ladron.value),
                               onCheckedChange = { ladron.value = it},
                               modifier = Modifier,
                               colors = SwitchDefaults.colors(
                                   checkedThumbColor = Color.LightGray,
                                   uncheckedThumbColor = Color.Gray,
                                   checkedTrackColor = AzulClaro,
                                   uncheckedTrackColor = Color.White,
                               )
                           )
                       }

                   }
               }

               item{
                   Row(
                       modifier = Modifier.fillMaxWidth()
                           .padding(10.dp))
                   {
                       Text(text = "Puntos de victoria",
                           fontSize = 14.sp,
                           color = AzulOscuro,
                           fontFamily = FontFamily.SansSerif,
                           fontWeight = FontWeight.Bold
                       )
                       Box(modifier = Modifier.fillMaxWidth(),
                           contentAlignment = Alignment.BottomEnd){
                           incrementador("victoria", onFlechaClick = {})

                       }
                   }
               }
               item{
                   Row(
                       modifier = Modifier.fillMaxWidth()
                           .padding(10.dp))
                   {
                       Text(text = "Numero de jugadores",
                           fontSize = 14.sp,
                           color = AzulOscuro,
                           fontFamily = FontFamily.SansSerif,
                           fontWeight = FontWeight.Bold
                       )
                       Box(modifier = Modifier.fillMaxWidth(),
                           contentAlignment = Alignment.BottomEnd){
                           incrementador("jugadores", onFlechaClick = {numeroJugadores = it})

                       }
                   }
               }
           }
        },
        sheetPeekHeight = 128.dp,
        // Defaults to true
        sheetGesturesEnabled = true
    ) {
        // Screen content
//BackArrow

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(R.drawable.talado),
            contentScale = ContentScale.FillBounds
        )
        .padding(10.dp, 10.dp, 10.dp, 10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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
            if (numeroJugadores == 4) {
                botones()
                botones()
            } else if (numeroJugadores == 3) {
                botones()
            }

            Box(
                modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.CatanBoard.route)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp)
                        .padding(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(
                        text = "Comenzar",
                        style = TextStyle(color = Blanco)
                    )

                }
            }

        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Button(
                onClick = { navController.navigate(Routes.Home.route) },
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Blanco
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