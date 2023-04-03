package com.example.mycatan.pantallas

import android.graphics.Paint
import android.util.Log
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

var clickedVertex: Offset? = null
@Composable
fun CatanBoard(navController: NavHostController) {
    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
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
    Box(
        modifier = Modifier
            .background(AzulClaro)
            .padding(20.dp)
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
    Canvas(modifier = Modifier.fillMaxSize().pointerInput(Unit)
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
        detectTapGestures { tap ->
            val x = tap.x
            val y = tap.y

            for (tile in tiles) {
                val tileX = boardX + (tile.coordinates.first + tile.coordinates.second / 2f) * hexWidth
                val tileY = boardY + tile.coordinates.second * 1.5f * hexRadius

                for (vertex in getHexagonVertices(tileX, tileY, hexRadius)) {
                    println("VERTEX: $vertex")
                    println("X:  $y")
                    println("Y: $x")

                    if (x >= vertex.x - 6f && x <= vertex.x + 6f &&
                        y >= vertex.y - 6f && y <= vertex.y + 6f) {
                        clickedVertex = vertex
                        break
                    }


                }

                if (clickedVertex != null) {
                    // hacer lo que necesites hacer cuando se hace clic en un vértice
                    // ...
                    val toast = Toast.makeText(context, "$clickedVertex", Toast.LENGTH_SHORT)
                    toast.show()
                    break
                }
            }

            clickedVertex = null
        }
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
