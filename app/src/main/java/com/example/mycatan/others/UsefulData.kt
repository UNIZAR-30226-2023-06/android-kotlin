package com.example.mycatan.others

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

//Javi 192.168.1.39
val ipBackend = "192.168.1.39"
//loreto: "192.168.1.133"

object Globals {
    lateinit var Token: String
    lateinit var Id: String
    lateinit var Username: String
    lateinit var Email: String
    lateinit var fotosCompradas: BooleanArray
    lateinit var Coins: String
}