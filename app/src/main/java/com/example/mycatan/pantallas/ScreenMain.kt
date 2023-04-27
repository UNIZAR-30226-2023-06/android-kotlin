package com.example.mycatan.pantallas

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.others.Routes
import com.example.mycatan.others.inicializarAristas
import com.example.mycatan.others.inicializarCoords
import com.example.mycatan.others.inicializarVertices
import com.example.mycatan.pantallas.amigos.AmigosPendientePage
import com.example.mycatan.pantallas.amigos.AmigosTodosPage

@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    //LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    //maybe sirve
    inicializarVertices()
    inicializarAristas()
    inicializarCoords()

    NavHost(navController = navController, startDestination = /*Routes.Login.route*/ Routes.CatanBoard.route ) {

        composable(Routes.Login.route) {
            CatanBoard(navController = navController)

        }
        composable(Routes.Home.route) {
            HomePage(navController = navController)
        }
        composable(Routes.Registro.route) {
            RegistroPage(navController = navController)
        }
        composable(Routes.CrearPartida.route) {
            CrearPartidaPage(navController = navController)
        }
        composable(Routes.Tienda.route) {
            TiendaPage(navController = navController)
        }
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Routes.CatanBoard.route) {
            CatanBoard(navController = navController)
        }
        composable(Routes.AmigosTodos.route) {
            AmigosTodosPage(navController = navController)
        }
        composable(Routes.AmigosPendiente.route) {
            AmigosPendientePage(navController = navController)
        }
        composable(Routes.EditarPerfil.route) {
            EditarPerfil(navController = navController)
        }
        composable(Routes.Manual.route) {
            ManualPage(navController = navController)
        }
        composable(Routes.Chat.route) {
            Chat(navController = navController)
        }
    }
}