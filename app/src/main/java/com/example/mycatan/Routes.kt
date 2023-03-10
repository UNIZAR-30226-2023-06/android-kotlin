package com.example.mycatan

sealed class Routes(val route: String) { //para navegar entre pantallas
    object Login : Routes("Login")
    object Home : Routes("Home")
    object Registro : Routes("Registro")
    object CrearPartida : Routes("CrearPartida")
    object Tienda : Routes("Tienda")
    object Splash : Routes("Splash")
}