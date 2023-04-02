package com.example.mycatan.pantallas

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mycatan.LockScreenOrientation
import com.example.mycatan.R
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.Azul
import com.example.mycatan.ui.theme.AzulClaro
import com.example.mycatan.ui.theme.AzulOscuro
import com.example.mycatan.ui.theme.Transp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true){
        delay(5000)
        navController.popBackStack()
        navController.navigate(Routes.Home.route)

    }
    Splash()
}

@Composable
fun Splash() {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    Column(modifier = Modifier
        .fillMaxSize()
        .paint(painterResource(R.drawable.talado),
            contentScale = ContentScale.FillBounds),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = Modifier.width(150.dp)
        )
        LinearProgressIndicator(
            modifier = Modifier.width(150.dp),
            color = Azul,
            backgroundColor = AzulOscuro
        )
    }
}

