package com.example.mycatan.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.R
import com.example.mycatan.dBaux.*
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CrearPartidaPage(navController: NavHostController) {
    var numeroJugadores by remember { mutableStateOf(4) }
    val options = listOf("default", "default2", "random")
    var tablero by remember { mutableStateOf(options[0]) }

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
                   Row (
                       modifier = Modifier.fillMaxWidth()
                           .padding(10.dp)
                   ){
                       Text(
                           text = "Tablero",
                           fontSize = 14.sp,
                           color = AzulOscuro,
                           fontFamily = FontFamily.SansSerif,
                           fontWeight = FontWeight.Bold
                       )

                       Box(modifier = Modifier.fillMaxWidth(),
                           contentAlignment = Alignment.BottomEnd){

                           ButtonToggle(
                               options = options,
                               selectedOption = tablero,
                               onOptionSelect = { tablero = it
                                   // Actualizamos el tablero en el backend
                                   set_board(tablero)
                                                },
                           )

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
                               onCheckedChange = { ladron.value = it
                                   // Actualizamos el estado del ladron
                                                 if(ladron.value){
                                                     enable_thief()
                                                 } else{
                                                     disable_thief()
                                                 }
                                                 },
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
                Text(
                    text = "Código de partida: ${Globals.lobbyId}",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = AzulOscuro,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)
            ) {
                Button(
                    onClick = {
                        // Comprobar si estan todos los jugadores ready
                        while (true) {
                            Thread.sleep(1000)// espera un segundo antes de hacer la siguiente solicitud GET
                            val jugadores = numReadyPlayers(Globals.Token)
                            if (jugadores == numeroJugadores - 1) {
                                if(setPlayerReady(Globals.Token)){
                                    getGameState(Globals.lobbyId)
                                    navController.navigate(Routes.CatanBoard.route)
                                }
                                break
                            }
                        }

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
                onClick = {
                    deleteLobby(Globals.lobbyId)
                    navController.navigate(Routes.Home.route)
                          },
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
                        set_max_players(count)
                    }
                    if(tipo == "turno") {
                        set_time_per_turn(count)
                    }
                    if(tipo == "victoria"){
                        set_points_to_win(count)
                    }
                }

                      },
            modifier = Modifier.size(25.dp)  ) {
            Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement",)
        }
        Text(text = count.toString(), fontSize = 14.sp,)
        IconButton(
            onClick = {
                if(tipo == "turno" && count != 300){
                    count++
                    set_time_per_turn(count)
                }
                if(tipo == "victoria" && count != 30){
                    count++
                    set_points_to_win(count)
                }
                if(tipo == "jugadores" && count != 4){
                    count++
                    onFlechaClick(count);
                    set_max_players(count)
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


@Composable
private fun ButtonToggle( // 1
    options: List<String>,
    selectedOption: String,
    onOptionSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier) { // 2
        options.forEachIndexed { index, option -> // 3
            val selected = selectedOption == option // 4

            val border = if (selected) BorderStroke( // 5
                width = 1.dp,
                color = MaterialTheme.colors.primary
            ) else ButtonDefaults.outlinedBorder

            val shape = when (index) { // 6
                0 -> RoundedCornerShape(
                    topStart = 4.dp,
                    bottomStart = 4.dp,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp
                )
                options.size - 1 -> RoundedCornerShape(
                    topStart = 0.dp, bottomStart = 0.dp,
                    topEnd = 4.dp,
                    bottomEnd = 4.dp
                )
                else -> CutCornerShape(0.dp)
            }

            val zIndex = if (selected) 1f else 0f

            val buttonModifier = when (index) { // 7
                0 -> Modifier.zIndex(zIndex)
                else -> {
                    val offset = -1 * index
                    Modifier
                        .offset(x = offset.dp)
                        .zIndex(zIndex)
                }
            }

            val colors = ButtonDefaults.outlinedButtonColors( // 8
                backgroundColor = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f)
                else MaterialTheme.colors.surface,
                contentColor = if (selected) MaterialTheme.colors.primary else Color.DarkGray
            )
            OutlinedButton( // 9
                onClick = { onOptionSelect(option) },
                border = border,
                shape = shape,
                colors = colors,
                modifier = buttonModifier.weight(1f)
            ) {
                if(option == "default"){
                    Image(
                        painter = painterResource(R.drawable.catan),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
                if(option == "default2"){
                    Image(
                        painter = painterResource(R.drawable.board),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
                if(option == "random"){
                    Image(
                        painter = painterResource(R.drawable.dado),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    }
}