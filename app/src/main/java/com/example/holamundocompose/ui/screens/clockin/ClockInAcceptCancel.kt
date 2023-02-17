package com.example.holamundocompose.ui.screens.clockin

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.holamundocompose.navigation.NavItem
import com.example.holamundocompose.ui.screens.menu.MenuScreen
import com.example.holamundocompose.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClockInAcceptCancel(navController: NavHostController) {
    MainComposeTheme {
        Scaffold (
            topBar = {
                TopAppBar (
                    title = { Text(text = "Marcar", color = Color.White, fontFamily = FontFamily.SansSerif) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = null, tint = Color.White)
                        }
                    },
                    backgroundColor = Blue2
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Column() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(8f)
                ){
                    MapPosition()
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    // ACCEPT
                    Button(
                        onClick = { navController.popBackStack(NavItem.Main.route, false) },
                        modifier = Modifier
                            .fillMaxHeight(0.6f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green2)
                    ) {
                        Text(
                            text = "Aceptar",
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                    // CANCEL
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxHeight(0.6f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Red1)
                    ) {
                        Text(
                            text = "Cancelar",
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MapPosition() {

}