package com.example.holamundocompose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.holamundocompose.ui.screens.clockin.ClockInAcceptCancel
import com.example.holamundocompose.ui.screens.clockin.ClockInScreen
import com.example.holamundocompose.ui.screens.main.MainScreen
import com.example.holamundocompose.ui.screens.mapPlaces.MapScreen

@ExperimentalFoundationApi
@Composable
fun Navigation () {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main.baseRoute) {
            MainScreen(navController)
        }
        composable(
            route = NavItem.ClockIn.route,
            arguments = NavItem.ClockIn.args
        ){ backStackEntry ->
            val param1 = backStackEntry.arguments?.getString(NavArg.ClockInParam1.key)
            requireNotNull(param1)
            ClockInScreen(navController, param1)
        }
        composable(NavItem.ClockInAcceptCancel.route) {
            ClockInAcceptCancel(navController)
        }
        composable(NavItem.Map.route) {
            MapScreen(navController)
        }
    }
}