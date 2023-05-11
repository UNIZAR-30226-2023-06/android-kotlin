package com.example.mycatan.others

import android.media.AsyncPlayer
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import com.example.mycatan.pantallas.Tile
import com.example.mycatan.pantallas.clickedVertex
import com.example.mycatan.pantallas.getHexagonVertices
import com.example.mycatan.ui.theme.Blanco
import kotlinx.serialization.json.Json
import org.json.JSONObject
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.properties.Delegates

//Javi 192.168.1.39
val ipBackend = "192.168.1.39"
//loreto: "192.168.1.133       "192.168.1.139"    10.1.54.191" "192.168.1.143" "192.168.1.135" "10.1.20.249"  "192.168.1.128"

object Globals {
    lateinit var Token: String
    lateinit var Id: String
    lateinit var Username: String
    lateinit var Email: String
    lateinit var fotosCompradas: BooleanArray
    lateinit var piezasCompradas: BooleanArray
    lateinit var mapasCompradas: BooleanArray
    lateinit var precioFotos: IntArray
    //lateinit var precioMapas: IntArray
    //lateinit var precopPiezas: IntArray
    lateinit var Coins: String
    lateinit var Personaje: String
    lateinit var Piezas: String
    lateinit var Mapa: String
    lateinit var lobbyId: String
    // El JSON COMPLETO DEL JUEGO/LOBBY
    // EJEMPLO, Si quieres coger información sobre los jugadores del lobby, haces:
    // Globals.juego.getJSONObject("game").getJSONArray("jugadores")
    lateinit var juego: JSONObject
    lateinit var gameState: JSONObject
    var lastPlayer = "nada"
    var lastFase = "nada"
    lateinit var dado1: String
    lateinit var dado2: String

}

object Partida {
    lateinit var Arcilla: String
    lateinit var Madera: String
    lateinit var Roca: String
    lateinit var Trigo: String
    lateinit var Ovejas: String
    lateinit var Vertices: HashMap<String,String>
    lateinit var Aristas: HashMap<String,String>
    lateinit var CoordVertices: HashMap<Offset, String>
    lateinit var CoordAristas: HashMap<Offset, String>
    lateinit var miColor : String
    var casaINIdisp =  mutableStateOf(false)
    var caminoINIdisp =  mutableStateOf(false)
    var nodosLegales = mutableListOf<String>("0")
    var edgesLegales = mutableListOf<String>("0")
    var iniciadasAristas = false
    val listUpRight = listOf<String>("38", "5A", "7C", "36","58", "7A", "9C", "34", "56", "78", "9A", "BC", "54", "76", "98", "BA", "74", "96", "B8")
    val listDownRight = listOf<String>("47", "69", "8B", "45","67", "89", "AB", "43", "65", "87", "A9", "CB", "63", "85", "A7", "C9", "83", "A5", "C7")
    val listDownLeft = listOf<String>("36", "58", "7A", "34","56", "78", "9A", "32", "54", "76", "98", "BA", "52", "74", "96", "B8", "72", "94", "B6")
    val listLeft = listOf<String>("26", "48", "6A", "24","46", "68", "8A", "22", "44", "66", "88", "AA", "42", "64", "86", "A8", "62", "84", "A6")
    val listRight = listOf<String>("48", "6A", "8C", "46","68", "8A", "AC", "44", "66", "88", "AA", "CC", "64", "86", "A8", "CA", "84", "A6", "C8")
    val listUpLeft = listOf<String>("27", "49", "6B", "25","47", "69", "8B", "23", "45", "67", "89", "AB", "43", "65", "87", "A9", "63", "85", "A7")

}

fun inicializarVertices() {
    val claves = listOf(
        "38", "5A", "7C", "27", "49", "6B", "8D", "36", "58", "7A", "9C", "25", "47", "69", "8B", "AD",
        "34", "56", "78", "9A", "BC", "23", "45", "67", "89", "AB", "CD", "32", "54", "76", "98", "BA",
        "DC", "43", "65", "87", "A9", "CB", "52", "74", "96", "B8", "DA", "63", "85", "A7", "C9", "72",
        "94", "B6", "D8", "83", "A5", "C7"
    )
    Partida.Vertices = hashMapOf("hola" to "hola")
    for (clave in claves) {
        Partida.Vertices[clave] = "nada"
    }
}

fun inicializarCoords(){
    Partida.CoordVertices = hashMapOf(Offset(0f, 0f) to "Arbitrario")
    Partida.CoordAristas = hashMapOf(Offset(0f, 0f) to "Arbitrario")
}



fun inicializarAristas() {
    val claves = listOf(
        "27", "38", "49", "5A", "6B", "7C", "26", "48", "6A", "8C", "25", "36", "47", "58", "69", "7A",
        "8B", "9C", "24", "46", "68", "8A", "AC", "23", "34", "45", "56", "67", "78", "89", "9A", "AB",
        "BC", "22", "44", "66", "88", "AA", "CC", "32", "43", "54", "65", "76", "87", "98", "A9", "BA",
        "CB", "42", "64", "86", "A8", "CA", "52", "63", "74", "85", "96", "A7", "B8", "C9", "62", "84",
        "A6", "C8", "72", "83", "94", "A5", "B6", "C7"
    )
    Partida.Aristas = hashMapOf("hola" to "hola")
    for (clave in claves) {
        Partida.Aristas[clave] = "nada"
    }
}


/*
object Lobby {
    lateinit var id: String
    var is_full by Delegates.notNull<Boolean>()
    var game_has_started by Delegates.notNull<Boolean>()
    lateinit var max_Players: Number
    lateinit var current_Players: Number
    lateinit var game: Game
    var hay_ladron by Delegates.notNull<Boolean>()
    lateinit var max_tiempo_turno: Number
    lateinit var elo: Number
}

object Mano {
    lateinit var cartas_desarrollo: Array<Number>
    lateinit var arcilla: Number
    lateinit var madera: Number
    lateinit var trigo: Number
    lateinit var piedra: Number
    lateinit var oveja: Number
}

object Jugadores {
    lateinit var id: String
    lateinit var puntos_victoria: Number
    lateinit var color: Number
    lateinit var mano: Mano
    lateinit var caballeros_usados: Number
    var tiene_bono_carreteras by Delegates.notNull<Boolean>()
    var tiene_bono_caballeros by Delegates.notNull<Boolean>()
    var esta_preparado by Delegates.notNull<Boolean>()
    lateinit var elo: Number
    var activo by Delegates.notNull<Boolean>()
}

object Board {
    lateinit var board: Array<Number>

}

object Game {
    lateinit var jugadores: Array<Jugadores>
    lateinit var num_jugadores: Number
    lateinit var num_jugadores_activos: Number
    lateinit var turno: Number
    lateinit var fase_turno: Number
    lateinit var tiempo_turno: Number
    lateinit var jugadores_seleccionados: Array<Number>
    var hay_ladron by Delegates.notNull<Boolean>()
    lateinit var board: Board
}*/


