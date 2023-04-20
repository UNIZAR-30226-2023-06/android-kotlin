package com.example.mycatan.pantallas

import android.graphics.Paint
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.ui.theme.*
import kotlin.math.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.window.Dialog
import com.example.mycatan.R
import com.example.mycatan.dBaux.buyPersonaje
import com.example.mycatan.dBaux.changeProfilePicture
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Partida
import com.example.mycatan.others.Routes

var clickedVertex: Offset? = null
@Composable
fun CatanBoard(navController: NavHostController) {

    Partida.Madera = "5"
    Partida.Arcilla = "5"
    Partida.Roca = "5"
    Partida.Ovejas = "5"
    Partida.Trigo = "5"


    val showTablaCostes =  remember { mutableStateOf(false) }
    val tradePlayer1 =  remember { mutableStateOf(false) }
    val tradePlayer2 =  remember { mutableStateOf(false) }
    val tradePlayer3 =  remember { mutableStateOf(false) }


    //Registro nombres de los jugadores
    var namePlayer1 =  remember { mutableStateOf("player1") }
    var namePlayer2 =  remember { mutableStateOf("player2") }
    var namePlayer3 =  remember { mutableStateOf("player3") }

    //Registro fotos de los jugadores
    var paint1temp = painterResource(R.drawable.skin4)
    val paint1 =  remember { mutableStateOf(paint1temp) }
    val paint2 =  remember { mutableStateOf(paint1temp) }
    paint2.value = painterResource(R.drawable.skin2)
    val paint3 =  remember { mutableStateOf(paint1temp) }
    paint3.value = painterResource(R.drawable.skin3)


    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        offset += offsetChange
    }
    val tiles = listOf(
        Tile("bosque", 5, Pair(0, 0)),
        Tile("lago", 2, Pair(1, 0)),
        Tile("montaña", 9, Pair(2, 0)),
        Tile("montaña", 8, Pair(-1, 1)),
        Tile("lago", 3, Pair(0, 1)),
        Tile("montaña", 10, Pair(1, 1)),
        Tile("bosque", 6, Pair(2, 1)),
        Tile("montaña", 12, Pair(-2, 2)),
        Tile("lago", 11, Pair(-1, 2)),
        Tile("montaña", 4, Pair(0, 2)),
        Tile("montaña", 8, Pair(1, 2)),
        Tile("lago", 10, Pair(2, 2)),
        Tile("bosque", 9, Pair(-2, 3)),
        Tile("bosque", 4, Pair(-1, 3)),
        Tile("montaña", 5, Pair(0, 3)),
        Tile("montaña", 10, Pair(1, 3)),
        Tile("desierto", 11, Pair(-2, 4)),
        Tile("bosque", 3, Pair(-1, 4)),
        Tile("montaña", 6, Pair(0, 4))

    )
    Scaffold(
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
                        rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TileGrid(tiles)
            }

            if(showTablaCostes.value)
                showTablaCostes(setShowDialog = {
                    showTablaCostes.value = it
                })
            if(tradePlayer1.value)
                showTrading(name = namePlayer1.value , foto = paint1.value , setShowDialog = {tradePlayer1.value = it})
            if(tradePlayer2.value)
                showTrading(name = namePlayer2.value , foto = paint2.value , setShowDialog = {tradePlayer2.value = it})
            if(tradePlayer3.value)
                showTrading(name = namePlayer3.value , foto = paint3.value , setShowDialog = {tradePlayer3.value = it})

            //Jugadores
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            )
            {
                dataJugador(player = 1 ,colorEne = Verde, foto = paint1.value , ptsV = 5 , ejercito = false, carreteras = false, onCardClick = { tradePlayer1.value=true })

            }

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            )
            {
                dataJugador(player = 2,colorEne = Rojo, foto = paint2.value, ptsV = 2, ejercito = false, carreteras = false, onCardClick = { tradePlayer2.value=true })

            }

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 55.dp),
                contentAlignment = Alignment.BottomEnd
            )
            {

                dataJugador(player = 3 ,colorEne = Purple200, foto = paint3.value, ptsV = 3, ejercito = true, carreteras = false, onCardClick = { tradePlayer3.value=true })

            }
            //MI INFO
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 55.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(horizontalAlignment = Alignment.Start) {


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

                    Spacer(modifier = Modifier.height(5.dp))

                    //Chat
                   /* Button(
                        onClick = { navController.navigate(Routes.Chat.route)},
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Blanco
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))*/

                    dataJugador(player = 0 ,colorEne = Amarillo,foto = painterResource(R.drawable.skin3),  ptsV = 3, ejercito = false, carreteras = true, onCardClick = {})
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
fun incIntercambio( tipo : String){
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
            Icon(Icons.Filled.KeyboardArrowDown,contentDescription = "Decrement",)
        }
        Text(text = count.toString(), fontSize = 14.sp,)
        IconButton(
            onClick = {
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
            },

            modifier = Modifier.size(25.dp) ) {
            Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
        }
    }
}

@Composable
fun intercambioRecurso(id: String) {

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
        incIntercambio(id)

    }
}




@Composable
fun playerFoto(modifier: Modifier, foto: Painter){
    Card(
        modifier = modifier,

        shape = CircleShape,
        backgroundColor = Blanco,
    ){
        Image(
            painter = foto,
            contentDescription = null,
        )
    }
}


@Composable
fun showTrading(name :String ,foto: Painter, setShowDialog: (Boolean) -> Unit) {

    val context = LocalContext.current

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

                    Row() {

                        Spacer(modifier = Modifier.width(15.dp))

                        Column() {
                            intercambioRecurso(id = "arcilla")
                            intercambioRecurso(id = "roca")
                            intercambioRecurso(id = "oveja")
                            intercambioRecurso(id = "trigo")
                            intercambioRecurso(id = "madera")
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp),
                            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                            playerFoto( modifier= Modifier.size(130.dp) ,foto = foto )

                            Spacer(modifier = Modifier.height(5.dp))

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

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                    .width(130.dp)
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
fun dataJugador(player: Int, colorEne: Color, ptsV: Int, foto: Painter, ejercito: Boolean, carreteras: Boolean, onCardClick: () -> Unit  ) {

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


class Tile(val terrain: String, val number: Int, val coordinates: Pair<Int, Int>){
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
fun TileGrid(tiles: List<Tile>) {
    val context = LocalContext.current
    val isClicked = remember { mutableStateOf(false) }
    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit)
        {
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
                    for (tile in tiles) {
                        val tileX =
                            boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
                        val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius
                        for (vertex in getHexagonVertices(tileX, tileY, hexRadius)) {
                            val radius = 6f

                            // Calcula la distancia entre el centro del círculo y la posición del clic del mouse
                            val distance =
                                sqrt((offset.x - vertex.x).pow(2) + (offset.y - vertex.y).pow(2))
                            // Verifica si la distancia es menor que el radio del círculo
                            if (distance <= radius) {
                                // El clic está dentro del círculos
                                println("punto: ${offset.x} tap: ${vertex.x}")
                                Toast
                                    .makeText(context, "OK", Toast.LENGTH_SHORT)
                                    .show()
                                // Aquí puedes agregar el código para manejar el evento de clic en el círculo
                                clickedVertex = vertex
                                break
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

        // Dibujar los hexágonos y los números en el tablero
        for (tile in tiles) {
            val tileX = boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
            val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius

            val path = Path().apply {
                moveTo(tileX, tileY + hexRadius)
                lineTo(tileX - hexWidth / 2f, tileY + hexRadius / 2f)
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
            if (tile.number > 0) {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 30f
                        textAlign = Paint.Align.CENTER
                    }
                    canvas.nativeCanvas.drawText(
                        tile.number.toString(),
                        tileX,
                        tileY + paint.textSize / 2f,
                        paint
                    )
                }
            }

            // Dibujar círculos clicables en cada vértice
            for (vertex in getHexagonVertices(tileX, tileY, hexRadius)) {
                val color = if (vertex == clickedVertex) {
                    Color.Blue
                } else {
                    Color.Black
                }
                drawCircle(
                    center = vertex,
                    radius = 6f,
                    color = color,
                )


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

@Preview
@Composable
fun PreviewTileGrid() {
    val tiles = listOf(Tile("bosque", 5, Pair(0, 0)), Tile("lago", 10, Pair(1, 0)), Tile("montaña", 3, Pair(2, 0)))
    TileGrid(tiles)
}
