package com.example.holamundocompose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
){
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Main : NavItem("main")
    object ClockIn : NavItem("clockin", listOf(NavArg.ClockInParam1)) {
        fun creteNavRoute(param1: String) = "$baseRoute/$param1"
    }
    object ClockInAcceptCancel : NavItem("clockinacceptcancel")
    object Map : NavItem("map")
}

enum class NavArg (val key: String, val navType: NavType<*>) {
    ClockInParam1("param1", NavType.StringType)
}