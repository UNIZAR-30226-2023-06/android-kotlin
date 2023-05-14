package com.example.mycatan.pantallas

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.R
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mycatan.dBaux.*
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.*
import okhttp3.Route

var stopBuscar = false
@OptIn(ExperimentalAnimationApi::class)
class MyViewModel : ViewModel() {
@Composable
fun HomePage(navController: NavHostController) {



    val context = LocalContext.current
    val buscarPartida =  remember { mutableStateOf(false) }
    val unirsePartida =  remember { mutableStateOf(false) }
    val cancelarListo =  remember { mutableStateOf(false) }
    val pendiente by remember { mutableStateOf(getNumAmigosPendiente(Globals.Token)) }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.Manual.route) },
                backgroundColor = AzulOscuro,
            ) {
                /* FAB content */
                Image(painter = painterResource(R.drawable.politicas),
                    contentDescription = "reglas",
                modifier= Modifier
                    .size(30.dp)
                    .padding(3.dp))
            }
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Blanco,
                        )
                           },
                    selected = true,
                    onClick = {
                        navController.navigate(Routes.Home.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Blanco,
                            modifier = Modifier.padding(5.dp)
                        )
                       if (pendiente.toInt() > 0) {
                            Badge(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text(text = pendiente)
                            }
                       }
                           },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.AmigosTodos.route)
                    }

                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Blanco,
                        )
                           },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.Tienda.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        FotoPerfil(navController, foto = Globals.Personaje) {}
                           },

                    selected = false,
                    onClick = {
                        navController.navigate(Routes.EditarPerfil.route)
                    }
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
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
                //.fillMaxWidth()
                //.background(color = AzulClaro),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                if(buscarPartida.value)
                    BuscarPartida(value = "", setShowDialog = {
                        buscarPartida.value = it
                    }) {}
                if(unirsePartida.value)
                    UnirsePartida(value = "", setShowDialog = {
                        unirsePartida.value = it
                    }) {}
                if(cancelarListo.value){
                    CancelarListo(value = "", setShowDialog = { cancelarListo.value = it }, navController)
                }

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            if(getLobbyFromPlayer(Globals.Token)){
                                // Si el jugador esta en un lobby, y clica aqui, le volvemos a unir al lobby
                                //Recoger la información del lobby
                                getGameState(Globals.lobbyId)
                                navController.navigate(Routes.CatanBoard.route)
                            } else{
                                buscarPartida.value = true
                                if(searchLobby(Globals.Token)){
                                    var hayCuatro = false
                                    // CUANDO HAY 4 JUGADORES BUSCANDO PARTIDA SACAR POP-UP CANCELAR LISTO
                                    viewModelScope.launch {
                                        hayCuatro = buscarJugadores(buscarPartida.value).await()
                                        if(hayCuatro){
                                            println("HAY CUATRO JUGADORES")
                                            buscarPartida.value = false
                                            cancelarListo.value = true
                                            //Hacemos un stop search para que los jugadores dejen de buscar, ya que ya han encontrado (ns si hace falta)
                                            stopSearchingLobby(Globals.Token)
                                        }

                                    }

                                } else{
                                    Toast.makeText(context, "ERROR ya se esa buscando partida", Toast.LENGTH_SHORT).show()
                                }
                            }

                                  },
                        modifier = Modifier
                            .width(280.dp)
                            .height(60.dp),

                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                    ) {
                        Text(
                            text = "BUSCAR PARTIDA",
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            unirsePartida.value = true
                                  },
                        modifier = Modifier
                            .width(280.dp)
                            .height(60.dp),

                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                    ) {
                        Text(
                            text = "UNIRSE CON CÓDIGO",
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = { navController.navigate(Routes.CrearPartida.route) },
                        modifier = Modifier
                            .width(280.dp)
                            .height(60.dp),

                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                    ) {
                        Text(
                            text = "CREAR PARTIDA CON AMIGOS",
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }
                }


            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                ImagenClickable(
                    painter = painterResource(R.drawable.logout),
                    contentDescription = null,
                    modifier= Modifier.size(30.dp)
                ) {
                    navController.navigate(Routes.Login.route)
                }
            }


        }


    }

}
}

suspend fun buscarJugadores(buscarPartida: Boolean): Deferred<Boolean> {
    return withContext(Dispatchers.Default) {
        var jugadoresEncontrados = false
        while (!jugadoresEncontrados && buscarPartida && !stopBuscar) {
            if(getLobbyFromPlayer(Globals.Token)){
                jugadoresEncontrados=true
                break
            }
            // Esperar entre 1 y 10 segundos aleatoriamente
            //val tiempoEspera = (Math.random() * 10).toInt() + 1
            delay(/*tiempoEspera*/1 * 1000L)
        }
        CompletableDeferred(jugadoresEncontrados)
    }
}

@Composable
fun ImagenClickable(
    painter: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Composable
fun FotoPerfil( navController: NavHostController, foto: String , onCardClick: () -> Unit ){



    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto=="0"){
        painterID = painterResource(R.drawable.skin1)
    }
    else if(foto=="1"){
        painterID = painterResource(R.drawable.skin2)
    }
    else if(foto=="2"){
        painterID = painterResource(R.drawable.skin3)
    }
    else if(foto=="3"){
        painterID = painterResource(R.drawable.skin4)
    }
    else if(foto=="4"){
        painterID = painterResource(R.drawable.skin5)
    }
    else if(foto=="5"){
        painterID = painterResource(R.drawable.skin6)
    }
    else if(foto=="6"){
        painterID = painterResource(R.drawable.skin7)
    }
    else if(foto=="7"){
        painterID = painterResource(R.drawable.skin8)
    }
    else if (foto=="default")
    {
        painterID = painterResource(R.drawable.skin1)
    }
    else {
        painterID = painterResource(R.drawable.skin9)
    }

    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(Routes.EditarPerfil.route)
            }
            .padding(10.dp)
            .fillMaxHeight(),

        shape = CircleShape,
        backgroundColor = Blanco,
    ){
        Image(
            painter = painterID,
            contentDescription = null,

            )
    }
}

@Composable
fun BuscarPartida(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    val context = LocalContext.current

    Dialog(onDismissRequest = { }) { // PARA QUE SOLO SE CIERRE CON LA X QUITAR ESTO JEJE
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Buscando partida",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    // CUANDO SE PULSE EL BOTÓN DE CANCELAR, SE DEBE CANCELAR LA BÚSQUEDA DE PARTIDA
                                    setShowDialog(false)
                                    // INDICAMOS QUE YA NO QUEREMOS SEGUIR BUSCANDO LOBBY
                                    stopBuscar = true
                                    if (stopSearchingLobby(Globals.Token)) {
                                        Toast.makeText(context, "Se ha cancelado la partida", Toast.LENGTH_SHORT,).show()
                                    } else {
                                        Toast.makeText(context, "ERROR No se ha podido cancelar la partida", Toast.LENGTH_SHORT).show()
                                    }

                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Blanco, // Cambia el color a tu gusto
                            strokeWidth = 4.dp // Cambia el grosor de la línea del Spinner
                        )
                    }



                }
            }
        }
    }
}

@Composable
fun UnirsePartida(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
    val partida = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Introduce un código",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "1234", color= Blanco) },
                        value = partida.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Blanco,
                            unfocusedBorderColor = Blanco,
                            disabledBorderColor = Blanco,
                            textColor = Blanco,
                            cursorColor = Blanco
                        ),
                        onValueChange = {
                            if (it.text.length <= 76)
                                partida.value = it
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Azul)

                    ) {
                        Text(text = "Unirse")
                    }
                }
            }
        }
    }
}

@Composable
fun CancelarListo(value: String, setShowDialog: (Boolean) -> Unit, navController: NavController) {
    val partida = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    val buscarPartida =  remember { mutableStateOf(false) }

    if(buscarPartida.value)
        BuscarPartida(value = "", setShowDialog = {
            buscarPartida.value = it
        }) {}

    Dialog(onDismissRequest = {}) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Column(
                        modifier = Modifier.fillMaxWidth() ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Se han encontrado más jugadores",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center

                            )
                        )
                        Text(
                            text = "¿Aceptas el desafio?",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center

                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                // DECIDE UNIRSE A LA PARTIDA
                                setPlayerReady(Globals.Token)
                                var iniciaPartica = false
                                while(!iniciaPartica){
                                    //Si existe el lobby y hay 4 jugadores la partida comienza
                                    if(getLobbyFromId(Globals.lobbyId)){
                                        Thread.sleep(1000)
                                        if(numOfReadyPlayers(Globals.Token)){
                                            iniciaPartica = true
                                            //Recoger la información del lobby
                                            getGameState(Globals.lobbyId)
                                            //Navegar a la pantalla de juego
                                            navController.navigate(Routes.CatanBoard.route)
                                        }
                                    }else{
                                        //Si no existe el lobby, vuelve a la cola
                                        iniciaPartica = true
                                        //Cerrar este pop-up
                                        setShowDialog(false)
                                        //TODO: Abrir pop-up de buscando partida y poner otra vez al jugador en busca de sala
                                    }
                                    Thread.sleep(1000)
                                }

                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.weight(1f) // asignar el mismo peso relativo a ambos botones
                            ,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Verde)

                        ) {
                            Text(text = "Aceptar",color = Blanco)
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(
                            onClick = {
                                setShowDialog(false)
                                // BORRAR EL LOBBY SI LE DAN A LA X
                                if(deleteLobby(Globals.lobbyId)){
                                    Toast.makeText(context, "La partida se ha cancelado", Toast.LENGTH_SHORT).show()
                                } else{
                                    Toast.makeText(context, "ERROR error al borrar el lobby", Toast.LENGTH_SHORT).show()
                                }
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.weight(1f) ,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo)

                        ) {
                            Text(text = "Rechazar", color = Blanco)
                        }
                    }


                }
            }
        }
    }
}