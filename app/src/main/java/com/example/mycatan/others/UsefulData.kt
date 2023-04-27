package com.example.mycatan.others

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Vertices
import com.example.mycatan.pantallas.Tile
import com.example.mycatan.pantallas.clickedVertex
import com.example.mycatan.pantallas.getHexagonVertices
import kotlin.math.pow
import kotlin.math.sqrt

//Javi 192.168.1.39
val ipBackend = "192.168.1.39"
//loreto: "192.168.1.133       "192.168.1.139"    10.1.54.191"

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


