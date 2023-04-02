package com.example.mycatan.others

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

//Javi 192.168.1.39
val ipBackend = "192.168.1.139"
//loreto: "192.168.1.133           10.1.54.191"

object Globals {
    lateinit var Token: String
    lateinit var Id: String
    lateinit var Username: String
    lateinit var Email: String
    lateinit var fotosCompradas: BooleanArray
    lateinit var Coins: String
    lateinit var Personaje: String
    lateinit var Piezas: String
    lateinit var Mapa: String
}