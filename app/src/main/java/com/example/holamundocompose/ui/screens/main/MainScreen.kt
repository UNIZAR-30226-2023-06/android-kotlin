package com.example.holamundocompose.ui.screens.main

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.holamundocompose.navigation.NavItem
import com.example.holamundocompose.ui.screens.menu.MenuScreen
import com.example.holamundocompose.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    MainComposeTheme {
        var menuVisible by remember { mutableStateOf(false) }
        Scaffold (
            topBar = {
                TopAppBar (
                    title = { Text(text = "", color = Color.White, fontFamily = FontFamily.SansSerif) },
                    navigationIcon = {
                        IconButton(onClick = { /*navController.navigate(NavItem.Menu.route)*/ menuVisible =! menuVisible }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = Color.White)
                        }
                    },
                    backgroundColor = Blue2
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(0f to Blue2, 1000f to Blue5)),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                DateAndHour(navController)
                MenuIcons(navController)
            }

        }

        // OPTIONS MENU
        AnimatedVisibility(visible = menuVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) ){
            Surface(
                modifier = Modifier.fillMaxSize()
                                    .clickable { menuVisible = !menuVisible },
                color = Color.Black.copy(alpha = 0.6f)
            ){}
        }

        AnimatedVisibility(visible = menuVisible,
            enter = expandHorizontally (animationSpec = tween(1000)),
            exit = shrinkHorizontally (animationSpec = tween(1000)) ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.70f) // 70% of screen
                    .background(Color.White)
            ) {
                MenuScreen(navController, {menuVisible = it})
            }
        }
    }
}

@Composable
fun DateAndHour(navController: NavHostController) {
    // Current Hour
    val hourFormat = SimpleDateFormat("HH:mm")
    val currentHour = hourFormat.format(Date())
    Text(
        text = "$currentHour",
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier.padding(8.dp)
            .clickable { navController.navigate(NavItem.Map.route);  },
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
    )

    // Current Date
    val dateFormat = SimpleDateFormat("dd/M/yyyy")
    val currentDate = transformDate(dateFormat.format(Date()))
    Text(
        text = "$currentDate",
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        color = Color.White,
        fontFamily = FontFamily.SansSerif
    )
}

fun transformDate (preDate: String):String{
    //val month = preDate.substringBeforeLast('/').substringAfterLast('/')
    val dayMonthYear = preDate.split('/')
    var newDate = dayMonthYear[0] + " de "
    newDate += when (dayMonthYear[1]) {
        "1" -> "enero"
        "2" -> "febrero"
        "3" -> "marzo"
        "4" -> "abril"
        "5" -> "mayo"
        "6" -> "junio"
        "7" -> "julio"
        "8" -> "agosto"
        "9" -> "septiembre"
        "10" -> "octubre"
        "11" -> "noviembre"
        "12" -> "diciembre"
        else -> "***"
    }
    return newDate + " de " + dayMonthYear[2]
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuIcons(navController: NavHostController) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(20.dp),
        cells = GridCells.Fixed(2),
    ) {
        item {
            // Clock
            IconName(navController, "Marcar", Icons.Default.Timer)
        }
        item {
            // Calendar
            IconName(navController, "Calendario", Icons.Default.CalendarToday)
        }
        item {
            // Actual state
            IconName(navController, "Estado actual", Icons.Default.Badge)
        }
        item {
            // Marcajes
            IconName(navController, "Marcajes", Icons.Default.SyncAlt)
        }
    }
}

@Composable
fun IconName(navController: NavHostController, text: String, imageVector: ImageVector) {
    Column(Modifier.padding(vertical = 20.dp).clickable { navController.navigate(NavItem.ClockIn.creteNavRoute(text)) }) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(70.dp)
                .align(CenterHorizontally)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(CenterHorizontally)
        )
    }
}
