package com.example.mycatan.pantallas

import android.graphics.Paint
import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
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
import androidx.compose.ui.geometry.Offset


@Composable
fun CatanBoard(navController: NavHostController) {
    val tiles = listOf(
        Tile("bosque", 5, Pair(0, 0)),
        Tile("lago", 2, Pair(1, 0)),
        Tile("montaña", 9, Pair(2, 0)),
        Tile("montaña", 8, Pair(-1, 1)),
        Tile("montaña", 3, Pair(0, 1)),
        Tile("montaña", 10, Pair(1, 1)),
        Tile("montaña", 6, Pair(2, 1)),
        Tile("montaña", 12, Pair(-2, 2)),
        Tile("montaña", 11, Pair(-1, 2)),
        Tile("montaña", 4, Pair(0, 2)),
        Tile("montaña", 8, Pair(1, 2)),
        Tile("montaña", 10, Pair(2, 2)),
        Tile("montaña", 9, Pair(-2, 3)),
        Tile("montaña", 4, Pair(-1, 3)),
        Tile("montaña", 5, Pair(0, 3)),
        Tile("montaña", 10, Pair(1, 3)),
        Tile("montaña", 11, Pair(-2, 4)),
        Tile("montaña", 3, Pair(-1, 4)),
        Tile("montaña", 6, Pair(0, 4))

    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulClaro)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TileGrid(tiles)
        }
    }


}

class Tile(val terrain: String, val number: Int, val coordinates: Pair<Int, Int>){
    // Calculo lista de vertices de cada hexagono para hacerlos clicables y dibujarlos con el canvas
    val vertices = listOf(
        Pair(coordinates.first, coordinates.second - 1),
        Pair(coordinates.first + 1, coordinates.second - 1),
        Pair(coordinates.first + 1, coordinates.second),
        Pair(coordinates.first, coordinates.second + 1),
        Pair(coordinates.first - 1, coordinates.second),
        Pair(coordinates.first - 1, coordinates.second - 1)
    )
}

@Composable
fun TileGrid(tiles: List<Tile>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Obtener el ancho y la altura del canvas
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Calcular el ancho y la altura del tablero
        val hexRadius = 48
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
            /*for (vertex in tile.vertices) {
                val vertexX = boardX + (vertex.first + vertex.second / 2f) * hexWidth
                val vertexY = boardY + vertex.second * 1.5f * hexRadius

                drawCircle(
                    color = Color.Black,
                    radius = 10f,
                    center = Offset(vertexX, vertexY)
                )
            }*/
        }
    }
}


@Preview
@Composable
fun PreviewTileGrid() {
    val tiles = listOf(Tile("bosque", 5, Pair(0, 0)), Tile("lago", 10, Pair(1, 0)), Tile("montaña", 3, Pair(2, 0)))
    TileGrid(tiles)
}
