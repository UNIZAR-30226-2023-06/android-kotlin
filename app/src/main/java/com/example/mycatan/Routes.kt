package com.example.mycatan

sealed class Routes(val route: String) { //para navegar entre pantallas
    object Login : Routes("Login")
    object Home : Routes("Home")
}