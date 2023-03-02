package com.example.mycatan

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ScreenMain(){
    val navController = rememberNavController()
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
    }
}