package com.example.mycatan.pantallas

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.LockScreenOrientation
import com.example.mycatan.others.Routes
import com.example.mycatan.pantallas.amigos.AmigosPendientePage
import com.example.mycatan.pantallas.amigos.AmigosTodosPage

@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    //LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }
        composable(Routes.Home.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            HomePage(navController = navController)
        }
        composable(Routes.Registro.route) {
            RegistroPage(navController = navController)
        }
        composable(Routes.CrearPartida.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            CrearPartidaPage(navController = navController)
        }
        composable(Routes.Tienda.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            TiendaPage(navController = navController)
        }
        composable(Routes.Splash.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            SplashScreen(navController = navController)
        }
        composable(Routes.CatanBoard.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            CatanBoard(navController = navController)
        }
        composable(Routes.AmigosTodos.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            AmigosTodosPage(navController = navController)
        }
        composable(Routes.AmigosPendiente.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            AmigosPendientePage(navController = navController)
        }
        composable(Routes.EditarPerfil.route) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            EditarPerfil(navController = navController)
        }
    }
}