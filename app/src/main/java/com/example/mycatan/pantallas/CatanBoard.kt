package com.example.mycatan.pantallas

import android.graphics.*
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.ui.theme.*
import kotlin.math.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.window.Dialog
import com.example.mycatan.R
import com.example.mycatan.dBaux.changeProfilePicture
import com.example.mycatan.others.*
import org.json.JSONArray

var clickedVertex: Offset? = null

var jugador_0: Jugador? = null
var jugador_1: Jugador? = null
var jugador_2: Jugador? = null
var jugador_3: Jugador? = null

var jugadores: JSONArray? = null

class Jugador(var yo: Boolean, var nombre: String, var color: String, var imagen: String, var puntos: Int, var piezas: String, var tablero: String)
@Composable
fun CatanBoard(navController: NavHostController) {

    // INICIALIZACIÓN DE LOS JUGADORES EN EL TABLERO -----------------------------------------


    if ( Globals.gameState.getString("player_0") != null){ // Primer jugador
        val jugador0 = Globals.gameState.getJSONObject("player_0")
        jugador_0 = Jugador (yo = jugador0.getString("id") == Globals.Id,
            nombre = jugador0.getString("name"),
            color = jugador0.getString("color"),
            imagen = jugador0.getString("profile_pic"),
            puntos = jugador0.getInt("victory_points"),
            piezas = jugador0.getString("selected_pieces_skin"),
            tablero = jugador0.getString("selected_grid_skin")
        )
        if (jugador_0!!.yo == true){
            Partida.Madera = jugador0.getJSONObject("hand").getString("wood")
            Partida.Ovejas = jugador0.getJSONObject("hand").getString("sheep")
            Partida.Trigo = jugador0.getJSONObject("hand").getString("wheat")
            Partida.Arcilla = jugador0.getJSONObject("hand").getString("clay")
            Partida.Roca = jugador0.getJSONObject("hand").getString("rock")
        }
    }

    if ( Globals.gameState.getString("player_1") != null){ // Segundo jugador
        val jugador1 = Globals.gameState.getJSONObject("player_1")
        jugador_1 = Jugador (yo = jugador1.getString("id") == Globals.Id,
            nombre = jugador1.getString("name"),
            color = jugador1.getString("color"),
            imagen = jugador1.getString("profile_pic"),
            puntos = jugador1.getInt("victory_points"),
            piezas = jugador1.getString("selected_pieces_skin"),
            tablero = jugador1.getString("selected_grid_skin")
        )
        if (jugador_1!!.yo == true){
            Partida.Madera = jugador1.getJSONObject("hand").getString("wood")
            Partida.Ovejas = jugador1.getJSONObject("hand").getString("sheep")
            Partida.Trigo = jugador1.getJSONObject("hand").getString("wheat")
            Partida.Arcilla = jugador1.getJSONObject("hand").getString("clay")
            Partida.Roca = jugador1.getJSONObject("hand").getString("rock")
        }
    }

    if ( Globals.gameState.getString("player_2") != null){ // Tercer jugador
        val jugador2 = Globals.gameState.getJSONObject("player_2")
        jugador_2 = Jugador (yo = jugador2.getString("id") == Globals.Id,
            nombre = jugador2.getString("name"),
            color = jugador2.getString("color"),
            imagen = jugador2.getString("profile_pic"),
            puntos = jugador2.getInt("victory_points"),
            piezas = jugador2.getString("selected_pieces_skin"),
            tablero = jugador2.getString("selected_grid_skin")
        )
        if (jugador_2!!.yo == true){
            Partida.Madera = jugador2.getJSONObject("hand").getString("wood")
            Partida.Ovejas = jugador2.getJSONObject("hand").getString("sheep")
            Partida.Trigo = jugador2.getJSONObject("hand").getString("wheat")
            Partida.Arcilla = jugador2.getJSONObject("hand").getString("clay")
            Partida.Roca = jugador2.getJSONObject("hand").getString("rock")
        }
    }

    if ( Globals.gameState.getString("player_3") != null){ // Tercer jugador
        val jugador3 = Globals.gameState.getJSONObject("player_3")
        jugador_3 = Jugador (yo = jugador3.getString("id") == Globals.Id,
            nombre = jugador3.getString("name"),
            color = jugador3.getString("color"),
            imagen = jugador3.getString("profile_pic"),
            puntos = jugador3.getInt("victory_points"),
            piezas = jugador3.getString("selected_pieces_skin"),
            tablero = jugador3.getString("selected_grid_skin")
        )
        if (jugador_3!!.yo == true){
            Partida.Madera = jugador3.getJSONObject("hand").getString("wood")
            Partida.Ovejas = jugador3.getJSONObject("hand").getString("sheep")
            Partida.Trigo = jugador3.getJSONObject("hand").getString("wheat")
            Partida.Arcilla = jugador3.getJSONObject("hand").getString("clay")
            Partida.Roca = jugador3.getJSONObject("hand").getString("rock")
        }
    }

    val showTablaCostes =  remember { mutableStateOf(false) }

    val tradePlayer0 =  remember { mutableStateOf(false) }
    val tradePlayer1 =  remember { mutableStateOf(false) }
    val tradePlayer2 =  remember { mutableStateOf(false) }
    val tradePlayer3 =  remember { mutableStateOf(false) }

    var showConstruir =  remember { mutableStateOf(false) }
    var showCamino =  remember { mutableStateOf(false) }
    var verticeChosen = remember { mutableStateOf("nada") }
    var aristaChosen = remember { mutableStateOf("nada") }



    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    //var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        offset += offsetChange
    }

    val hexagonos = Globals.gameState.getJSONObject("board").getJSONObject("tiles")

    println("Ladrón: ${Globals.gameState.getBoolean("thief_enabled")} y posición: ${Globals.gameState.getInt("thief_position")}")

    var thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 55
    val tile37 = Tile(hexagonos.getJSONArray("55")[1] as Int, hexagonos.getJSONArray("55")[0] as Int, thief = thief , Pair(0, 0), id = "37")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 89
    val tile59 = Tile(hexagonos.getJSONArray("89")[1] as Int, hexagonos.getJSONArray("89")[0] as Int, thief = thief , Pair(1, 0), id = "59")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 123
    val tile7B = Tile(hexagonos.getJSONArray("123")[1] as Int, hexagonos.getJSONArray("123")[0] as Int, thief = thief , Pair(2, 0), id = "7B")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 53
    val tile35 = Tile(hexagonos.getJSONArray("53")[1] as Int, hexagonos.getJSONArray("53")[0] as Int, thief = thief , Pair(-1, 1), id = "35")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 87
    val tile57 = Tile(hexagonos.getJSONArray("87")[1] as Int, hexagonos.getJSONArray("87")[0] as Int, thief = thief , Pair(0, 1), id = "57")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 121
    val tile79 = Tile(hexagonos.getJSONArray("121")[1] as Int, hexagonos.getJSONArray("121")[0] as Int, thief = thief , Pair(1, 1), id = "79")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 155
    val tile9B = Tile(hexagonos.getJSONArray("155")[1] as Int, hexagonos.getJSONArray("155")[0] as Int, thief = thief , Pair(2, 1), id = "9B")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 51
    val tile33 = Tile(hexagonos.getJSONArray("51")[1] as Int, hexagonos.getJSONArray("51")[0] as Int, thief = thief , Pair(-2, 2), id = "33")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 85
    val tile55 = Tile(hexagonos.getJSONArray("85")[1] as Int, hexagonos.getJSONArray("85")[0] as Int, thief = thief , Pair(-1, 2), id = "55")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 119
    val tile77 = Tile(hexagonos.getJSONArray("119")[1] as Int, hexagonos.getJSONArray("119")[0] as Int, thief = thief , Pair(0, 2), id = "77")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 153
    val tile99 = Tile(hexagonos.getJSONArray("153")[1] as Int, hexagonos.getJSONArray("153")[0] as Int, thief = thief , Pair(1, 2), id = "99")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 187
    val tileBB = Tile(hexagonos.getJSONArray("187")[1] as Int, hexagonos.getJSONArray("187")[0] as Int, thief = thief , Pair(2, 2), id = "BB")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 83
    val tile53 = Tile(hexagonos.getJSONArray("83")[1] as Int, hexagonos.getJSONArray("83")[0] as Int, thief = thief , Pair(-2, 3), id = "53")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 117
    val tile75 = Tile(hexagonos.getJSONArray("117")[1] as Int, hexagonos.getJSONArray("117")[0] as Int, thief = thief , Pair(-1, 3), id = "75")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 151
    val tile97 = Tile(hexagonos.getJSONArray("151")[1] as Int, hexagonos.getJSONArray("151")[0] as Int, thief = thief , Pair(0, 3), id = "97")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 185
    val tileB9 = Tile(hexagonos.getJSONArray("185")[1] as Int, hexagonos.getJSONArray("185")[0] as Int, thief = thief , Pair(1, 3), id = "B9")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 115
    val tile73 = Tile(hexagonos.getJSONArray("115")[1] as Int, hexagonos.getJSONArray("115")[0] as Int, thief = thief , Pair(-2, 4), id = "73")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 149
    val tile95 = Tile(hexagonos.getJSONArray("149")[1] as Int, hexagonos.getJSONArray("149")[0] as Int, thief = thief , Pair(-1, 4), id = "95")
    thief = Globals.gameState.getBoolean("thief_enabled") && Globals.gameState.getInt("thief_position") == 183
    val tileB7 = Tile(hexagonos.getJSONArray("183")[1] as Int, hexagonos.getJSONArray("183")[0] as Int, thief = thief , Pair(0, 4), id = "B7")

    val tiles = listOf(tile37,tile59,tile7B,tile35,tile57,tile79,tile9B,tile33,tile55,tile77,tile99,tileBB,tile53,tile75,tile97,tileB9,tile73,tile95,tileB7)

    Scaffold(
        // BOTTOM BAR DE NAVEGACIÓN ----------------------------------------------------------------
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = true,
                    onClick = {
                        navController.navigate(Routes.CatanBoard.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Blanco,
                            modifier = Modifier.padding(5.dp)
                        )
                        /*if (pendiente.toInt() > 0) { SI HAY MENSAJES
                            Badge(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text(text = pendiente)
                            }
                        }*/
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.Chat.route)
                    }

                )

            }
        },
    ) {
        Box(
            modifier = Modifier
                .background(AzulClaro)
                .padding(5.dp)
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        // rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                TileGrid(tiles = tiles, chosenV = {verticeChosen.value = it}, onVerticeClick = {showConstruir.value = true}, chosenA = {aristaChosen.value = it}, onAristaClick = {showCamino.value = true} )
            }

            // MOSTRAR LA TABLA DE COSTES ------------------------------------------------------
            if(showTablaCostes.value)
                showTablaCostes(setShowDialog = {
                    showTablaCostes.value = it
                })

            // DIBUJANDO LAS CARDS DE LOS PLAYERS ----------------------------------------------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column( modifier = Modifier.weight(1f)
                ){
                    if (jugador_0 != null ){
                        // Pop-up de intercambio de elementos
                        if(tradePlayer0.value && jugador_0!!.yo != true)
                            showTrading(name = jugador_0!!.nombre, foto = jugador_0!!.imagen , setShowDialog = {tradePlayer0.value = it})
                        // Cajita con la info del usuario
                        Box (
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            val colorEne = when (jugador_0!!.color) {
                                "RED" -> Rojo
                                "BLUE" -> Azul
                                "GREEN" -> Verde
                                else -> Amarillo
                            }
                            val player = if (jugador_0!!.yo) { 0 } else{ 5 }
                            dataJugador(player = player ,
                                colorEne = colorEne,
                                foto = jugador_0!!.imagen ,
                                ptsV = jugador_0!!.puntos ,
                                ejercito = false,
                                carreteras = false,
                                onCardClick = {
                                    if(jugador_0!!.yo != true){
                                        tradePlayer0.value = true
                                    }
                                })

                        }

                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    if (jugador_1 != null ) {
                        if(tradePlayer1.value && jugador_1!!.yo != true)
                            showTrading(name = jugador_1!!.nombre, foto = jugador_1!!.imagen , setShowDialog = {tradePlayer1.value = it})
                        // Cajita con la info del usuario
                        Box (
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            val colorEne = when (jugador_1!!.color) {
                                "RED" -> Rojo
                                "BLUE" -> Azul
                                "GREEN" -> Verde
                                else -> Amarillo
                            }

                            val player = if (jugador_1!!.yo) { 0 } else{ 1 }

                            dataJugador(player = player,
                                colorEne = colorEne,
                                foto = jugador_1!!.imagen ,
                                ptsV = jugador_1!!.puntos ,
                                ejercito = false,
                                carreteras = false,
                                onCardClick = {
                                    if(jugador_1!!.yo != true){
                                        tradePlayer1.value = true
                                    }
                                })

                        }

                    }
                }

                Column(modifier = Modifier.weight(1f)){
                    if (jugador_2 != null ) {
                        if(tradePlayer2.value && jugador_2!!.yo != true)
                            showTrading(name = jugador_2!!.nombre, foto = jugador_2!!.imagen , setShowDialog = {tradePlayer2.value = it})
                        // Cajita con la info del usuario
                        Box (
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            val colorEne = when (jugador_2!!.color) {
                                "RED" -> Rojo
                                "BLUE" -> Azul
                                "GREEN" -> Verde
                                else -> Amarillo
                            }
                            val player = if (jugador_2!!.yo) { 0 } else{ 2 }
                            dataJugador(player = player ,
                                colorEne = colorEne,
                                foto = jugador_2!!.imagen ,
                                ptsV = jugador_2!!.puntos ,
                                ejercito = false,
                                carreteras = false,
                                onCardClick = {
                                    if(jugador_2!!.yo != true){
                                        tradePlayer2.value = true
                                    }
                                })

                        }

                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    if (jugador_3 != null) {
                        if(tradePlayer3.value && jugador_3!!.yo != true)
                            showTrading(name = jugador_3!!.nombre, foto = jugador_3!!.imagen , setShowDialog = {tradePlayer3.value = it})
                        // Cajita con la info del usuario
                        Box (
                            modifier = Modifier.fillMaxWidth(),
                        )
                        {
                            val colorEne = when (jugador_3!!.color) {
                                "RED" -> Rojo
                                "BLUE" -> Azul
                                "GREEN" -> Verde
                                else -> Amarillo
                            }
                            val player = if (jugador_3!!.yo) { 0 } else{ 3 }
                            dataJugador(player = player ,
                                colorEne = colorEne,
                                foto = jugador_3!!.imagen ,
                                ptsV = jugador_3!!.puntos ,
                                ejercito = false,
                                carreteras = false,
                                onCardClick = {
                                    if(jugador_3!!.yo != true){
                                        tradePlayer3.value = true
                                    }
                                })

                        }

                    }
                }


            }

            if(showConstruir.value)
                showConstruir(idVert = verticeChosen.value , setShowDialog = {showConstruir.value = it})
            if(showCamino.value)
                construirCamino(idArista = aristaChosen.value , setShowDialog = {showCamino.value = it})


            //INFORMACIÓN PROPIA DEL JUGADOR ( RECURSOS ) --------------------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 55.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(horizontalAlignment = Alignment.Start) {

                    // TODO: AÑADIR INFO SOBRE LAS CARTAS QUE TIENE (POP-UP) Y LAS CONTRUCCIONES QUE LLEVA(ESTO SE PUEDE EVITAR SI NO CABE, QUE LAS CUENTE)
                    Column(
                        modifier = Modifier
                            .background(
                                color = TransparenteBlanco,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(vertical = 5.dp, horizontal = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        dataRecurso(id = "oveja", cantidad = Partida.Ovejas.toInt())
                        dataRecurso(id = "arcilla", cantidad = Partida.Arcilla.toInt())
                        dataRecurso(id = "madera", cantidad = Partida.Madera.toInt())
                        dataRecurso(id = "trigo", cantidad = Partida.Trigo.toInt())
                        dataRecurso(id = "roca", cantidad = Partida.Roca.toInt())
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    //Tabla costes
                    Button(
                        onClick = { showTablaCostes.value = true},
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.hammer),
                            contentDescription = null,
                            tint = Blanco
                        )
                    }

                }
            }

        }
    }
}
@Composable
fun dataRecurso(id: String, cantidad: Int) {

    var painterID = painterResource(R.drawable.sheep)
    when (id) {
        "oveja" -> {
            painterID = painterResource(R.drawable.sheep)
        }
        "arcilla" -> {
            painterID = painterResource(R.drawable.clay)
        }
        "trigo" -> {
            painterID = painterResource(R.drawable.trigo)
        }
        "roca" -> {
            painterID = painterResource(R.drawable.rock)
        }
        "madera" -> {
            painterID = painterResource(R.drawable.wood)
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Image(
            painter = painterID,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        
        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = cantidad.toString(),
            fontSize = 14.sp,
            style = TextStyle(
                color = Negro,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun incIntercambio( tipo : String, mainPlayer: Boolean){
    // INCREMENTADOR/DECREMENTADOR NUMERO
    var inicio = 0;
    var count by remember { mutableStateOf(inicio) }

    Row (modifier = Modifier
        .background(Color.White, RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ){

        IconButton(
            onClick = {
                if(count > 0){
                    count--
                } },
            modifier = Modifier.size(25.dp)  ) {
            Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement")
        }
        Text(text = count.toString(), fontSize = 14.sp, color = AzulOscuro)
        IconButton(
            onClick = {
                if (mainPlayer){
                    if(tipo == "arcilla" && count < Partida.Arcilla.toInt()){
                        count++
                    }
                    if(tipo == "oveja" && count < Partida.Ovejas.toInt()){
                        count++
                    }
                    if(tipo == "trigo" && count < Partida.Trigo.toInt()){
                        count++
                    }
                    if(tipo == "roca" && count < Partida.Roca.toInt()){
                        count++
                    }
                    if(tipo == "madera" && count < Partida.Madera.toInt()){
                        count++
                    }
                }
                else {
                    count++
                }

            },

            modifier = Modifier.size(25.dp) ) {
            Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
        }
    }
}

@Composable
fun intercambioRecurso(id: String, mainPlayer: Boolean) {

    var painterID = painterResource(R.drawable.sheep)
    when (id) {
        "oveja" -> {
            painterID = painterResource(R.drawable.sheep)
        }
        "arcilla" -> {
            painterID = painterResource(R.drawable.clay)
        }
        "trigo" -> {
            painterID = painterResource(R.drawable.trigo)
        }
        "roca" -> {
            painterID = painterResource(R.drawable.rock)
        }
        "madera" -> {
            painterID = painterResource(R.drawable.wood)
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterID,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        incIntercambio(id, mainPlayer)

    }
}




@Composable
fun playerFoto(modifier: Modifier, foto: String){

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
        modifier = modifier,
        shape = CircleShape,
        backgroundColor = Blanco,
    ){
        Image(
            painter = painterID,
            contentDescription = null,
        )
    }
}

// DIALOG DE INTERCAMBIO DE RECURSOSO --------------------------------------------------------------
@Composable
fun showTrading(name: String ,foto: String, setShowDialog: (Boolean) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                Column(modifier = Modifier.padding(20.dp))
                {
                    Row(
                    )
                    {
                        Text(
                            text = "Intercambiar con $name",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = Blanco,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Ofreces",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            ),
                            textDecoration = TextDecoration.Underline
                        )

                        Spacer(modifier = Modifier.width(100.dp))

                        Text(
                            text = "Solicitas",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            ),
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    Row() {

                        Spacer(modifier = Modifier.width(5.dp))

                        Column() {
                            intercambioRecurso(id = "arcilla", true)
                            intercambioRecurso(id = "roca", true)
                            intercambioRecurso(id = "oveja", true)
                            intercambioRecurso(id = "trigo", true)
                            intercambioRecurso(id = "madera", true)

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column(modifier = Modifier.height(310.dp),
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {

                            Spacer(modifier = Modifier.height(40.dp))

                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                                playerFoto(modifier = Modifier.size(55.dp), foto = Globals.Personaje )

                                Spacer(modifier = Modifier.height(10.dp))

                                Icon(
                                    painter = painterResource(R.drawable.exchangearrows),
                                    contentDescription = null,
                                    tint = Blanco,
                                    modifier = Modifier.rotate(90f)
                                )


                                Spacer(modifier = Modifier.height(10.dp))

                                playerFoto(modifier = Modifier.size(55.dp), foto = foto )

                            }

                            Spacer(modifier = Modifier.height(25.dp))

                            Button(
                                onClick = {
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(130.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Text(
                                    text = "Proponer",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column() {
                            intercambioRecurso(id = "arcilla", false)
                            intercambioRecurso(id = "roca", false)
                            intercambioRecurso(id = "oveja", false)
                            intercambioRecurso(id = "trigo", false)
                            intercambioRecurso(id = "madera", false)
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                    }
                }
            }
        }
    }
}


@Composable
fun showTablaCostes(setShowDialog: (Boolean) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                modifier = Modifier
                    .paint(
                        painterResource(R.drawable.tabla),

                        )
                    .padding(20.dp),
                contentAlignment = Alignment.TopEnd
            ) {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = Negro,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable { setShowDialog(false) }
                )

            }
        }
    }
}

@Composable
fun dataJugador(player: Int, colorEne: Color, ptsV: Int, foto: String, ejercito: Boolean, carreteras: Boolean, onCardClick: () -> Unit  ) {

    var colorBorder = Color.Transparent
    if (player==0){
        colorBorder = AzulOscuro
    }
    Box(
        modifier = Modifier
            .clickable {
                if (player != 0) {
                    onCardClick()
                }
            }
            .width(150.dp)
            .height(50.dp)
            .background(color = colorEne, shape = RoundedCornerShape(15.dp))
            .border(color = colorBorder, width = 3.dp, shape = RoundedCornerShape(15.dp))
    ) {
        // Contenido de la caja aquí

        var swordsTint = Negro
        var roadTint = Negro
        if (ejercito){
            swordsTint = Blanco
        }
        if (carreteras){
            roadTint = Blanco
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            
            Spacer(modifier = Modifier.width(3.dp))

            playerFoto(modifier = Modifier.size(35.dp), foto = foto )


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {

                Text(
                    text = ptsV.toString(),
                    fontSize = 14.sp,
                    style = TextStyle(
                        color = Negro,
                        fontWeight = FontWeight.Bold
                    )
                )

                Icon(
                    painter = painterResource(R.drawable.crown),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )


            }

            Icon(
                painter = painterResource(R.drawable.swords),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = swordsTint
            )

            Icon(
                painter = painterResource(R.drawable.road),
                contentDescription = null,
                modifier = Modifier.size(45.dp),
                tint = roadTint
            )
        }
    }
}


class Tile(val terrain: Int, val number: Int, val thief: Boolean,val coordinates: Pair<Int, Int>, val id: String){
    // Calculo lista de vertices de cada hexagono para hacerlos clicables y dibujarlos con el canvas
    val vertices = listOf(
        Pair(coordinates.first, coordinates.second - 1),
        Pair(coordinates.first + 1, coordinates.second - 1),
        Pair(coordinates.first + 1, coordinates.second + 1),
        Pair(coordinates.first, coordinates.second + 1),
        Pair(coordinates.first - 1, coordinates.second + 1),
        Pair(coordinates.first - 1, coordinates.second - 1)
    )
}

@Composable
fun TileGrid(tiles: List<Tile>, chosenV: (String) -> Unit, onVerticeClick: () -> Unit, chosenA: (String) -> Unit, onAristaClick: () -> Unit) {
    val context = LocalContext.current
    val isUpdated = remember { mutableStateOf(true) }
    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit)
        {
            // Detectar si se hizo clic
            var detectClick = false
            // Obtener el ancho y la altura del canvas
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Calcular el ancho y la altura del tablero
            val hexRadius = 100
            val hexHeight = hexRadius * 2
            val hexWidth = (sqrt(3f) / 2f) * hexHeight
            val boardWidth = hexWidth * 5
            val boardHeight = hexRadius * 6

            // Calcular el centro del canvas
            val centerX = canvasWidth / 2f
            val centerY = canvasHeight / 2f

            // Calcular la posición del tablero en el canvas
            val boardX = centerX - boardWidth / 2f
            val boardY = centerY - boardHeight / 2f
            // Detectar si se hizo clic en el círculo
            // Detectar si se hizo clic en un círculo clicable
            detectTapGestures(
                onTap = { offset ->
                    //println("tapped $offset")
                    detectClick = false
                    for (tile in tiles) {
                        val tileX =
                            boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
                        val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius
                        /*// Devolver las aristas para cada hexágono -----------------------------
                        val vertices = getHexagonVertices(tileX, tileY, hexRadius)
                        val coordinates =
                            mutableListOf<Offset>() // Lista de coordenadas de las aristas
                        for (i in 0 until vertices.size - 1) {
                            val vertex1 = vertices[i]
                            val vertex2 = vertices[i + 1]
                            println("Vertices $vertex1 $vertex2")
                            coordinates.addAll(getHexagonLineCoordinates(vertex1, vertex2))
                        }
                        // Agrega también la última arista que va desde el último vértice hasta el primer vértice
                        val vertex1 = vertices.last()
                        val vertex2 = vertices.first()
                        coordinates.addAll(getHexagonLineCoordinates(vertex1, vertex2))
                        println(coordinates)*/
                        // Detectar si el clic se hizo en una arista ------------------------------

                        // Deetectar si el click se hace en un vértice --------------------------
                        for (vertex in getHexagonVertices(tileX, tileY, hexRadius)) {
                            val radius = 10f

                            // Calcula la distancia entre el centro del círculo y la posición del clic del mouse
                            val distance =
                                sqrt((offset.x - vertex.x).pow(2) + (offset.y - vertex.y).pow(2))
                            // Verifica si la distancia es menor que el radio del círculo
                            if (distance <= radius) {
                                detectClick = true
                                // El clic está dentro del círculos
                                println("punto: ${offset.x} tap: ${vertex.x}")
                                Toast
                                    .makeText(context, "OK", Toast.LENGTH_SHORT)
                                    .show()
                                // Aquí puedes agregar el código para manejar el evento de clic en el círculo
                                var idCoord = Partida.CoordVertices[vertex]

                                if (Partida.Vertices[idCoord.toString()] == "nada") {
                                    println("verticeclicado: ${idCoord.toString()}")
                                    chosenV(idCoord.toString())
                                    onVerticeClick()

                                    clickedVertex = vertex
                                }

                                break
                            }
                        }

                        for (coordinate in Partida.CoordAristas.keys) {//maybe no hay que leer tods los  hexagonos

                            val radius = 20f
                            // Calcula la distancia entre el centro del círculo y la posición del clic del mouse
                            val distance =
                                sqrt(
                                    (offset.x - coordinate.x).pow(2) + (offset.y - coordinate.y).pow(
                                        2
                                    )
                                )
                            // Verifica si la distancia es menor que el radio del círculo
                            if (distance <= radius) {
                                // El clic está dentro del círculos
                                //println("punto: ${offset.x} tap: ${coordinate.x}")
                                // CLICK DE UNA ARISTA ---------------------------------------------------------------
                                if (!detectClick) {
                                    Toast
                                        .makeText(context, "ARIST", Toast.LENGTH_SHORT)
                                        .show()
                                    detectClick = true

                                    var idCoord = Partida.CoordAristas[coordinate]

                                    if (Partida.Aristas[idCoord.toString()] == "nada") {
                                        println("aristaclicado: ${idCoord.toString()}")
                                        chosenA(idCoord.toString())
                                        onAristaClick()

                                    }


                                    //Partida.Aristas[idCoord.toString()] = "carretera"
                                    //println("arista clicada: ${Partida.CoordAristas[coordinate]}")
                                }

                            }
                        }


                    }
                }
            )

        }

    ) {
        // Obtener el ancho y la altura del canvas
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Calcular el ancho y la altura del tablero
        val hexRadius = 100
        val hexHeight = hexRadius * 2
        val hexWidth = (sqrt(3f) / 2f) * hexHeight
        val boardWidth = hexWidth * 5
        val boardHeight = hexRadius * 6

        // Calcular el centro del canvas
        val centerX = canvasWidth / 2f
        val centerY = canvasHeight / 2f

        // Calcular la posición del tablero en el canvas
        val boardX = centerX - boardWidth / 2f
        val boardY = centerY - boardHeight / 2f


        for (tile in tiles) {
            val tileX = boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
            val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius

            getVertexCoord(tileX, tileY, hexRadius, tile.id)
        }

        for (tile in tiles) {
            val tileX = boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
            val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius

            getAristasCoord(tileX, tileY, hexRadius, tile.id)
        }

        // Dibujar los hexágonos y los números en el tablero

        var aristasPintadas = HashMap(Partida.Aristas)
        for (i in aristasPintadas.keys){
            aristasPintadas[i] = "not painted"
        }

        for (tile in tiles) {
            val tileX = boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
            val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius


            //getVertexCoord(tileX, tileY, hexRadius, tile.id)
            //getAristasCoord(tileX, tileY, hexRadius, tile.id)
            // Devolver las aristas para cada hexágono -----------------------------
            val vertices = getHexagonVertices(tileX, tileY, hexRadius)

            // Lista de coordenadas de las aristas


            val path = Path().apply {
                moveTo(tileX, tileY + hexRadius)


                lineTo(tileX - hexWidth / 2f, tileY - hexRadius / 2f)
                lineTo(tileX, tileY - hexRadius)
                lineTo(tileX + hexWidth / 2f, tileY - hexRadius / 2f)
                lineTo(tileX + hexWidth / 2f, tileY + hexRadius / 2f)
                close()
            }

            drawPath(
                path = path,
                color = Color.White,
                style = Stroke(2f)
            )
            //Poner numero
            if (tile.number >= 0) {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 30f
                        textAlign = Paint.Align.CENTER
                    }

                    if(tile.terrain == 1){
                        // Obtener el Drawable de la imagen desde el contexto
                        val drawable = context.resources.getDrawable(R.drawable.sheephexagon, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -85).toInt(), (tileY-95).toInt(), (tileX + 85).toInt(), (tileY + 95).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                    if(tile.terrain == 4){
                        // Obtener el Drawable de la imagen desde el contexto
                        val drawable = context.resources.getDrawable(R.drawable.rockhexagon, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -100).toInt(), (tileY-102).toInt(), (tileX + 101).toInt(), (tileY + 104).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                    if(tile.terrain == 6){
                        // Obtener el Drawable de la imagen desde el contexto
                        val drawable = context.resources.getDrawable(R.drawable.deserthexagon, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -120).toInt(), (tileY-121).toInt(), (tileX + 122).toInt(), (tileY + 121).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                    if(tile.terrain == 5){
                        // Obtener el Drawable de la imagen desde el contexto
                        val drawable = context.resources.getDrawable(R.drawable.trigohexagon, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -95).toInt(), (tileY-108).toInt(), (tileX + 95).toInt(), (tileY + 108).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                    if(tile.terrain == 2){
                        // Obtener el Drawable de la imagen desde el contexto
                        val drawable = context.resources.getDrawable(R.drawable.clayhexagon, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -85).toInt(), (tileY-97).toInt(), (tileX + 85).toInt(), (tileY + 99).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                    if(tile.terrain == 3){
                        // Obtener el Drawable de la imagen desde el contexto
                        val drawable = context.resources.getDrawable(R.drawable.woodhexagon, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -85).toInt(), (tileY-95).toInt(), (tileX + 85).toInt(), (tileY + 98).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                    if (tile.terrain == 6){

                        val drawable = context.resources.getDrawable(R.drawable.thief, null)

                        // Dibujar la imagen en el canvas
                        drawable.setBounds((tileX -90).toInt(), (tileY-90).toInt(), (tileX + 0).toInt(), (tileY + 0).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }


                    var colorNumber = Color.Black
                    if (tile.terrain != 6){

                        // Dibuja el círculo blanco
                        paint.apply {
                            color = Color.White.toArgb()
                            style = Paint.Style.FILL
                        }
                        canvas.nativeCanvas.drawCircle((tileX+1), (tileY-0), 17f, paint)

                        if (tile.number == 8 || tile.number == 9){
                            colorNumber = Color.Red
                        }

                        canvas.nativeCanvas.drawText(
                            tile.number.toString(),
                            tileX,
                            tileY + paint.textSize -36 / 2f,
                            Paint().apply {
                                color = colorNumber.toArgb()
                                textSize = 30f
                                textAlign = Paint.Align.CENTER
                                isFakeBoldText = true
                            }
                        )
                    }


                }
            }

            for (i in vertices.indices) {
                var vertex1 : Offset
                var vertex2 : Offset

                if (i == vertices.size-1){
                    vertex1 = vertices.last()
                    vertex2 = vertices.first()
                }
                else{
                    vertex1 = vertices[i]
                    vertex2 = vertices[i + 1]
                }




                // TODO: ANTES DE CONSTRUIR GET-LEGAL-EDGES PARA VER SI SE PUEDE CONSTRUIR EN LA ARISTA
                for (c in getHexagonLineCoordinates(vertex1, vertex2)){ // se guardacada coordenada de una arista en su correspondiente id



                    var temp1 = Partida.CoordAristas[c]

                    if (Partida.Aristas[temp1.toString()] == "carretera" && aristasPintadas[temp1.toString()] =="not painted"){

                        aristasPintadas[temp1.toString()] = "painted"

                        //println(temp1)


                        //se chekean entre que vertices son y se printea la carreteraen funcion
                        drawIntoCanvas { canvas ->

                            //vertical
                            if (i == 0){
                                println("downright")
                                var colorEne = R.drawable.amarillo_carretera_1_dcha // valor predeterminado

                                if (jugador_0!!.yo == true) {
                                    colorEne = when (jugador_0!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }
                                if (jugador_1!!.yo == true) {
                                    colorEne = when (jugador_1!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }
                                if (jugador_2!!.yo == true) {
                                    colorEne = when (jugador_2!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }
                                if (jugador_3!!.yo == true) {
                                    colorEne = when (jugador_3!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }


                                val drawable = context.resources.getDrawable(colorEne, null)

                                // Dibujar la imagen en el canvas
                                drawable.setBounds((tileX + 2).toInt(), (tileY + 30).toInt(), (tileX + 85).toInt(), (tileY + 100).toInt())


                                drawable.draw(canvas.nativeCanvas)

                            }
                            else if (i ==1){
                                println("downleft")
                                var colorEne = R.drawable.amarillo_carretera_1_izq // valor predeterminado

                                if (jugador_0!!.yo == true) {
                                    colorEne = when (jugador_0!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                if (jugador_1!!.yo == true) {
                                    colorEne = when (jugador_1!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                if (jugador_2!!.yo == true) {
                                    colorEne = when (jugador_2!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                if (jugador_3!!.yo == true) {
                                    colorEne = when (jugador_3!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                val drawable = context.resources.getDrawable(colorEne, null)

                                // Dibujar la imagen en el canvas
                                drawable.setBounds((tileX - 85).toInt(), (tileY + 30).toInt(), (tileX + 2).toInt(), (tileY + 100).toInt())
                                drawable.draw(canvas.nativeCanvas)
                            }

                            //izq
                            else if (i ==2){
                                println("left")
                                var colorEne = R.drawable.amarillo_carretera_1 // valor predeterminado

                                if (jugador_0!!.yo == true) {
                                    colorEne = when (jugador_0!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                if (jugador_1!!.yo == true) {
                                    colorEne = when (jugador_1!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                if (jugador_2!!.yo == true) {
                                    colorEne = when (jugador_2!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                if (jugador_3!!.yo == true) {
                                    colorEne = when (jugador_3!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                val drawable = context.resources.getDrawable(colorEne, null)

                                // Dibujar la imagen en el canvas
                                drawable.setBounds((tileX - 92).toInt(), (tileY - 40).toInt(), (tileX - 65).toInt(), (tileY + 40).toInt())

                                drawable.draw(canvas.nativeCanvas)
                            }
                            else if (i ==3){
                                println("topleft")
                                var colorEne = R.drawable.amarillo_carretera_1_dcha // valor predeterminado

                                if (jugador_0!!.yo == true) {
                                    colorEne = when (jugador_0!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }
                                if (jugador_1!!.yo == true) {
                                    colorEne = when (jugador_1!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }
                                if (jugador_2!!.yo == true) {
                                    colorEne = when (jugador_2!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }
                                if (jugador_3!!.yo == true) {
                                    colorEne = when (jugador_3!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_dcha
                                        "BLUE" -> R.drawable.azul_carretera_1_dcha
                                        "GREEN" -> R.drawable.verde_carretera_1_dcha
                                        else -> R.drawable.amarillo_carretera_1_dcha
                                    }
                                }

                                val drawable = context.resources.getDrawable(colorEne, null)

                                // Dibujar la imagen en el canvas
                                drawable.setBounds((tileX - 85).toInt(), (tileY - 105).toInt(), (tileX - 1).toInt(), (tileY - 35).toInt())

                                drawable.draw(canvas.nativeCanvas)
                            }


                            //dcha
                            else if (i ==4){
                                println("topright")

                                var colorEne = R.drawable.amarillo_carretera_1_izq // valor predeterminado

                                if (jugador_0!!.yo == true) {
                                    colorEne = when (jugador_0!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                if (jugador_1!!.yo == true) {
                                    colorEne = when (jugador_1!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                if (jugador_2!!.yo == true) {
                                    colorEne = when (jugador_2!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                if (jugador_3!!.yo == true) {
                                    colorEne = when (jugador_3!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1_izq
                                        "BLUE" -> R.drawable.azul_carretera_1_izq
                                        "GREEN" -> R.drawable.verde_carretera_1_izq
                                        else -> R.drawable.amarillo_carretera_1_izq
                                    }
                                }
                                val drawable = context.resources.getDrawable(colorEne, null)

                                // Dibujar la imagen en el canvas
                                drawable.setBounds((tileX -1).toInt(), (tileY-105).toInt(), (tileX + 82).toInt(), (tileY + -35).toInt())
                                drawable.draw(canvas.nativeCanvas)
                            }
                            else if (i==5){
                                println("right")
                                var colorEne = R.drawable.amarillo_carretera_1 // valor predeterminado

                                if (jugador_0!!.yo == true) {
                                    colorEne = when (jugador_0!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                if (jugador_1!!.yo == true) {
                                    colorEne = when (jugador_1!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                if (jugador_2!!.yo == true) {
                                    colorEne = when (jugador_2!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                if (jugador_3!!.yo == true) {
                                    colorEne = when (jugador_3!!.color) {
                                        "RED" -> R.drawable.rojo_carretera_1
                                        "BLUE" -> R.drawable.azul_carretera_1
                                        "GREEN" -> R.drawable.verde_carretera_1
                                        else -> R.drawable.amarillo_carretera_1
                                    }
                                }
                                val drawable = context.resources.getDrawable(colorEne, null)

                                // Dibujar la imagen en el canvas
                                drawable.setBounds((tileX +65).toInt(), (tileY-40).toInt(), (tileX + 92).toInt(), (tileY + 40).toInt())
                                drawable.draw(canvas.nativeCanvas)
                            }

                        }

                        break
                    }
                }

            }


            // Dibujar círculos clicables en cada vértice

            // TODO: ANTES DE CONSTRUIR GET-LEGAL-NODES PARA VER SI SE PUEDE CONSTRUIR EN UN VERTICE
            for (vertex in getHexagonVertices(tileX, tileY, hexRadius)) {
                val color = if (vertex == clickedVertex) {
                    Color.Blue
                } else {
                    Color.Black
                }

                drawIntoCanvas { canvas ->
                    drawCircle(
                        center = vertex,
                        radius = 6f,
                        color = color,
                    )
                    // COLOCACION CASA CIUDAD DE CADA JUGADOR -----------------------------------------------
                    val idVert = Partida.CoordVertices[vertex]
                    if (Partida.Vertices[idVert.toString()] == "poblado"){
                        var colorEne = R.drawable.rojo_poblado_1 // valor predeterminado

                        if (jugador_0!!.yo == true) {
                            colorEne = when (jugador_0!!.color) {
                                "RED" -> R.drawable.rojo_poblado_1
                                "BLUE" -> R.drawable.azul_poblado_1
                                "GREEN" -> R.drawable.verde_poblado_1
                                else -> R.drawable.amarillo_poblado_1
                            }
                        }
                        if (jugador_1!!.yo == true) {
                            colorEne = when (jugador_1!!.color) {
                                "RED" -> R.drawable.rojo_poblado_1
                                "BLUE" -> R.drawable.azul_poblado_1
                                "GREEN" -> R.drawable.verde_poblado_1
                                else -> R.drawable.amarillo_poblado_1
                            }
                        }
                        if (jugador_2!!.yo == true) {
                            colorEne = when (jugador_2!!.color) {
                                "RED" -> R.drawable.rojo_poblado_1
                                "BLUE" -> R.drawable.azul_poblado_1
                                "GREEN" -> R.drawable.verde_poblado_1
                                else -> R.drawable.amarillo_poblado_1
                            }
                        }
                        if (jugador_3!!.yo == true) {
                            colorEne = when (jugador_3!!.color) {
                                "RED" -> R.drawable.rojo_poblado_1
                                "BLUE" -> R.drawable.azul_poblado_1
                                "GREEN" -> R.drawable.verde_poblado_1
                                else -> R.drawable.amarillo_poblado_1
                            }
                        }

                        val drawable = context.resources.getDrawable(colorEne, null)

                        val x = vertex.x // coordenada x
                        val y = vertex.y // coordenada y
                        drawable.setBounds((x-30).toInt(), (y-29).toInt(), (x +30).toInt(), (y +25).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }
                    if (Partida.Vertices[idVert.toString()] == "ciudad"){

                        var colorEne = R.drawable.rojo_ciudad_1 // valor predeterminado

                        if (jugador_0!!.yo == true) {
                            colorEne = when (jugador_0!!.color) {
                                "RED" -> R.drawable.rojo_ciudad_1
                                "BLUE" -> R.drawable.azul_ciudad_1
                                "GREEN" -> R.drawable.verde_ciudad_1
                                else -> R.drawable.amarillo_ciudad_1
                            }
                        }
                        if (jugador_1!!.yo == true) {
                            colorEne = when (jugador_1!!.color) {
                                "RED" -> R.drawable.rojo_ciudad_1
                                "BLUE" -> R.drawable.azul_ciudad_1
                                "GREEN" -> R.drawable.verde_ciudad_1
                                else -> R.drawable.amarillo_ciudad_1
                            }
                        }
                        if (jugador_2!!.yo == true) {
                            colorEne = when (jugador_2!!.color) {
                                "RED" -> R.drawable.rojo_ciudad_1
                                "BLUE" -> R.drawable.azul_ciudad_1
                                "GREEN" -> R.drawable.verde_ciudad_1
                                else -> R.drawable.amarillo_ciudad_1
                            }
                        }
                        if (jugador_3!!.yo == true) {
                            colorEne = when (jugador_3!!.color) {
                                "RED" -> R.drawable.rojo_ciudad_1
                                "BLUE" -> R.drawable.azul_ciudad_1
                                "GREEN" -> R.drawable.verde_ciudad_1
                                else -> R.drawable.amarillo_ciudad_1
                            }
                        }

                        val drawable = context.resources.getDrawable(colorEne, null)

                        val x = vertex.x // coordenada x
                        val y = vertex.y // coordenada y
                        drawable.setBounds((x-30).toInt(), (y-35).toInt(), (x +30).toInt(), (y +25).toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }

                }

            }



        }
    }

}

fun getHexagonVertices(centerX: Float, centerY: Float, radius: Int): List<Offset> {
    val vertices = mutableListOf<Offset>()
    val angle_deg = 60f
    for (i in 0 until 6) {
        val angle_rad = Math.PI / 180 * (angle_deg * i + 30)
        val x = centerX + radius * cos(angle_rad).toFloat()
        val y = centerY + radius * sin(angle_rad).toFloat()
        vertices.add(Offset(x, y))
    }
    return vertices
}

// Function to get hexagon arist coordinates
fun getHexagonLineCoordinates(vertx1: Offset, vertx2: Offset): List<Offset>{
    // Check if vertices are in the same x axis
    if (vertx1.x == vertx2.x) {
        val startY = minOf(vertx1.y, vertx2.y)
        val endY = maxOf(vertx1.y, vertx2.y)
        return (startY.toInt()..endY.toInt()).map { Offset(vertx1.x, it.toFloat()) }
    }

    // Get the coordinates between vertx1 and vertx2 in a straight line
    val lineCoordinates = mutableListOf<Offset>()
    val x1 = vertx1.x
    val x2 = vertx2.x
    val y1 = vertx1.y
    val y2 = vertx2.y

    val m = (y2-y1)/(x2-x1)
    val b = y1 - m*x1

    for (x in minOf(x1.toInt(), x2.toInt())..maxOf(x1.toInt(), x2.toInt())) {
        val y = m*x + b
        lineCoordinates.add(Offset(x.toFloat(), y))
    }

    return lineCoordinates
}


fun getVertexCoord(centerX: Float, centerY: Float, radius: Int, id: String){

    val angle_deg = 60f
    var ids  = listOf<String>("error","error", "error", "error", "error", "error")

    if (id == "37") {
        ids = listOf<String>("58", "47", "36", "27", "38", "49")
    } else if (id == "59") {
        ids = listOf<String>("7A", "69", "58", "49", "5A", "6B")
    } else if (id == "7B") {
        ids = listOf<String>("9C", "8B", "7A", "6B", "7C", "8D")
    } else if (id == "35") {
        ids = listOf<String>("56", "45", "34", "25", "36", "47")
    } else if (id == "57") {
        ids = listOf<String>("78", "67", "56", "47", "58", "69")
    } else if (id == "79") {
        ids = listOf<String>("9A", "89", "78", "69", "7A", "8B")
    } else if (id == "9B") {
        ids = listOf<String>("BC", "AB", "9A", "8B", "9C", "AD")
    } else if (id == "33") {
        ids = listOf<String>("54", "43", "32", "23", "34", "45")
    } else if (id == "55") {
        ids = listOf<String>("76", "65", "54", "45", "56", "67")
    } else if (id == "77") {
        ids = listOf<String>("98", "87", "76", "67", "78", "89")
    } else if (id == "99") {
        ids = listOf<String>("BA", "A9", "98", "89", "9A", "AB")
    } else if (id == "BB") {
        ids = listOf<String>("DC", "CB", "BA", "AB", "BC", "CD")
    } else if (id == "53") {
        ids = listOf<String>("74", "63", "52", "43", "54", "65")
    } else if (id == "75") {
        ids = listOf<String>("96", "85", "74", "65", "76", "87")
    } else if (id == "97") {
        ids = listOf<String>("B8", "A7", "96", "87", "98", "A9")
    } else if (id == "B9") {
        ids = listOf<String>("DA", "C9", "B8", "A9", "BA", "CB")
    } else if (id == "73") {
        ids = listOf<String>("94", "83", "72", "63", "74", "85")
    } else if (id == "95") {
        ids = listOf<String>("B6", "A5", "94", "85", "96", "A7")
    } else if (id == "B7") {
        ids = listOf<String>("D8", "C7", "B6", "A7", "B8", "C9")
    }


    //println("id: $id")
    for (i in 0 until 6) {
        val angle_rad = Math.PI / 180 * (angle_deg * i + 30)
        val x = centerX + radius * cos(angle_rad).toFloat()
        val y = centerY + radius * sin(angle_rad).toFloat()

        //Partida.CoordVertices[Offset(x,y)] = ids[i]
        Partida.CoordVertices.put(Offset(x,y),ids[i])
        //println("offset: ${Offset(x,y)}")
        //println("vert: ${Partida.CoordVertices[Offset(x,y)]}")


    }
}

fun getAristasCoord(centerX: Float, centerY: Float, radius: Int, id: String){

    val angle_deg = 60f
    var ids  = listOf<String>("error","error", "error", "error", "error", "error")

    if (id == "37") {
        ids = listOf<String>("47", "36", "26", "27", "38", "48")
    } else if (id == "59") {
        ids = listOf<String>("69", "58", "48", "49", "5A", "6A")
    } else if (id == "7B") {
        ids = listOf<String>("8B", "7A", "6A", "6B", "7C", "8C")
    } else if (id == "35") {
        ids = listOf<String>("45", "34", "24", "25", "36", "46")
    } else if (id == "57") {
        ids = listOf<String>("67", "56", "46", "47", "58", "68")
    } else if (id == "79") {
        ids = listOf<String>("89", "78", "68", "69", "7A", "8A")
    } else if (id == "9B") {
        ids = listOf<String>("AB", "9A", "8A", "8B", "9C", "AC")
    } else if (id == "33") {
        ids = listOf<String>("43", "32", "22", "23", "34", "44")
    } else if (id == "55") {
        ids = listOf<String>("65", "54", "44", "45", "56", "66")
    } else if (id == "77") {
        ids = listOf<String>("87", "76", "66", "67", "78", "88")
    } else if (id == "99") {
        ids = listOf<String>("A9", "98", "88", "89", "9A", "AA")
    } else if (id == "BB") {
        ids = listOf<String>("CB", "BA", "AA", "AB", "BC", "CC")
    } else if (id == "53") {
        ids = listOf<String>("63", "52", "42", "43", "54", "64")
    } else if (id == "75") {
        ids = listOf<String>("85", "74", "64", "65", "76", "86")
    } else if (id == "97") {
        ids = listOf<String>("A7", "96", "86", "87", "98", "A8")
    } else if (id == "B9") {
        ids = listOf<String>("C9", "B8", "A8", "A9", "BA", "CA")
    } else if (id == "73") {
        ids = listOf<String>("83", "72", "62", "63", "74", "84")
    } else if (id == "95") {
        ids = listOf<String>("A5", "94", "84", "85", "96", "A6")
    } else if (id == "B7") {
        ids = listOf<String>("C7", "B6", "A6", "A7", "B8", "C8")
    }


    // Devolver las aristas para cada hexágono -----------------------------
    val vertices = getHexagonVertices(centerX, centerY, radius)

    println("id_hexagono: $id")

    for (i in vertices.indices) {
        var vertex1: Offset
        var vertex2: Offset

        if (i == vertices.size - 1) {
            vertex1 = vertices.last()
            vertex2 = vertices.first()
        } else {
            vertex1 = vertices[i]
            vertex2 = vertices[i + 1]
        }

        //var x1 = Offset(179.6F, 867.5F)
        //var x2 = Offset(93.0F, 917.5F)
        //println(getHexagonLineCoordinates(x1,x2))


        /*println("verticefake1: $vertex1")
        println("verticefake1: $vertex2")
        println("vertice1: ${Partida.CoordVertices[vertex1]}")
        println("vertice2: ${Partida.CoordVertices[vertex2]}")
        println("coord: ${getHexagonLineCoordinates(vertex1,vertex2)}")*/

        var ya = false
        for (c in getHexagonLineCoordinates(vertex1, vertex2)){ // se guardacada coordenada de una arista en su correspondiente id
            var data = ids[i]
            Partida.CoordAristas.put(c, data)
            if (!ya){
                //println("arista colocada: ${Partida.CoordAristas[c]}")
                ya= true
            }
        }


    }

}

fun buildCiudad(){
    Partida.Trigo = (Partida.Trigo.toInt() -2).toString()
    Partida.Roca = (Partida.Roca.toInt() -3).toString()
}

fun buildPoblado(){
    Partida.Trigo = (Partida.Trigo.toInt() -1).toString()
    Partida.Madera = (Partida.Madera.toInt() -1).toString()
    Partida.Ovejas = (Partida.Ovejas.toInt() -1).toString()
    Partida.Arcilla = (Partida.Arcilla.toInt() -1).toString()
}

fun buildCamino(){
    Partida.Madera = (Partida.Madera.toInt() -1).toString()
    Partida.Arcilla = (Partida.Arcilla.toInt() -1).toString()
}

@Composable
fun showConstruir( idVert: String, setShowDialog: (Boolean) -> Unit) {
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
                            text = "Seleccione una construccion",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = Blanco,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = "Poblado",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            ),
                            textDecoration = TextDecoration.Underline
                        )

                        Spacer(modifier = Modifier.width(90.dp))

                        Text(
                            text = "Ciudad",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            ),
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {


                            Image(painter = painterResource(id = R.drawable.blanco_poblado_1), contentDescription = null, modifier = Modifier.size(50.dp))

                            Spacer(modifier = Modifier.height(15.dp))

                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Image(painter = painterResource(id = R.drawable.wood), contentDescription = null, modifier = Modifier.size(30.dp))
                                    Text(
                                        text = "1",
                                        color = Blanco,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Image(painter = painterResource(id = R.drawable.clay), contentDescription = null, modifier = Modifier.size(30.dp))
                                    Text(
                                        text = "1",
                                        color = Blanco,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Image(painter = painterResource(id = R.drawable.trigo), contentDescription = null, modifier = Modifier.size(30.dp))
                                    Text(
                                        text = "1",
                                        color = Blanco,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Image(painter = painterResource(id = R.drawable.sheep), contentDescription = null, modifier = Modifier.size(30.dp))
                                    Text(
                                        text = "1",
                                        color = Blanco,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            if(Partida.Trigo.toInt()>=1 && Partida.Madera.toInt()>=1 && Partida.Ovejas.toInt()>=1 && Partida.Arcilla.toInt()>=1 ){
                                Button(
                                    onClick = {
                                        Partida.Vertices[idVert] = "poblado"
                                        buildPoblado()
                                        setShowDialog(false) },
                                    modifier = Modifier
                                        //.fillMaxWidth(0.5f)
                                        .width(100.dp)
                                        .height(50.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                    shape = RoundedCornerShape(50.dp),
                                    border = BorderStroke(3.dp, AzulOscuro),

                                    ) {
                                    Text(
                                        text = "Construir",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                            else{
                                Text(
                                    text = "Sin Materiales",
                                    color = Color.Red,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    textDecoration = TextDecoration.Underline
                                )

                            }
                        }

                        Spacer(modifier = Modifier.width(45.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                            Image(painter = painterResource(id = R.drawable.blanco_ciudad_1), contentDescription = null, modifier = Modifier.size(50.dp))

                            Spacer(modifier = Modifier.height(15.dp))

                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Image(painter = painterResource(id = R.drawable.trigo), contentDescription = null, modifier = Modifier.size(30.dp))
                                    Text(
                                        text = "2",
                                        color = Blanco,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Image(painter = painterResource(id = R.drawable.rock), contentDescription = null, modifier = Modifier.size(30.dp))
                                    Text(
                                        text = "3",
                                        color = Blanco,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            if(Partida.Trigo.toInt()>=2 && Partida.Roca.toInt()>=3 ){
                                Button(
                                    onClick = {
                                        Partida.Vertices[idVert] = "ciudad"
                                        buildCiudad()
                                        setShowDialog(false) },
                                    modifier = Modifier
                                        //.fillMaxWidth(0.5f)
                                        .width(100.dp)
                                        .height(50.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                    shape = RoundedCornerShape(50.dp),
                                    border = BorderStroke(3.dp, AzulOscuro),

                                    ) {
                                    Text(
                                        text = "Construir",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                            else{
                                Text(
                                    text = "Sin Materiales",
                                    color = Color.Red,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    textDecoration = TextDecoration.Underline
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(20.dp))

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}

@Composable
fun construirCamino(idArista: String, setShowDialog: (Boolean) -> Unit) {


    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Construir camino",
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

                    Image(painter = painterResource(id = R.drawable.rojo_carretera_1_izq), contentDescription = null, modifier = Modifier.size(50.dp))

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painter = painterResource(id = R.drawable.clay), contentDescription = null, modifier = Modifier.size(30.dp))
                            Text(
                                text = "1",
                                color = Blanco,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painter = painterResource(id = R.drawable.wood), contentDescription = null, modifier = Modifier.size(30.dp))
                            Text(
                                text = "1",
                                color = Blanco,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    if(Partida.Arcilla.toInt()<=1 && Partida.Madera.toInt()<=1 ){
                        Button(
                            onClick = {
                                Partida.Aristas[idArista] = "carretera"
                                buildCamino()
                                setShowDialog(false) },
                            modifier = Modifier
                                //.fillMaxWidth(0.5f)
                                .width(150.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro),

                            ) {
                            Text(
                                text = "Construir",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    else{
                        Text(
                            text = "Sin Materiales",
                            color = Color.Red,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            ),
                            textDecoration = TextDecoration.Underline
                        )
                    }

                }
            }
        }
    }
}

/*@Preview
@Composable
fun PreviewTileGrid() {
    val tiles = listOf(Tile("bosque", 5, Pair(0, 0)), Tile("lago", 10, Pair(1, 0)), Tile("montaña", 3, Pair(2, 0)))
    TileGrid(tiles)
}*/
