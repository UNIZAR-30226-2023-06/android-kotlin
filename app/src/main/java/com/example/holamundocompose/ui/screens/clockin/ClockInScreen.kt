package com.example.holamundocompose.ui.screens.clockin

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.holamundocompose.navigation.NavItem
import com.example.holamundocompose.ui.screens.menu.MenuScreen
import com.example.holamundocompose.ui.theme.Blue2
import com.example.holamundocompose.ui.theme.GreenButton
import com.example.holamundocompose.ui.theme.MainComposeTheme
import com.example.holamundocompose.ui.theme.OrangeButton
import java.util.*


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClockInScreen(navController: NavHostController, param1: String) {
    MainComposeTheme {
        var menuVisible by remember { mutableStateOf(false) }
        Scaffold (
            topBar = {
                TopAppBar (
                    title = { Text(text = "Marcar", color = Color.White, fontFamily = FontFamily.SansSerif) },
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
            /*
            var checkedOne1 by remember { mutableStateOf(true) }
            var checkedOne2 by remember { mutableStateOf(false) }
            var checkedOne3 by remember { mutableStateOf(false) }
            var checkedOne4 by remember { mutableStateOf(false) }
            var checkedOne5 by remember { mutableStateOf(false) }
            var checkedOne6 by remember { mutableStateOf(false) }*/
            Column() {
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(8f)
                ){
                    item {
                        Line ("Sin incidencia", GreenButton, 0)
                    }
                    item {
                        Line ("ENFERMEDAD PERSONAL", OrangeButton, 1)
                    }
                    item {
                        Line ("S. AT", OrangeButton, 2)
                    }
                    item {
                        Line ("S. EC", OrangeButton, 3 )
                    }
                    item {
                        Line ("S. REPOSO MÉDICO", OrangeButton, 4)
                    }
                    item {
                        Line ("S. AP/L.DISP.", OrangeButton, 5)
                    }
                    item {
                        Line ("AP ZARAGOZA", OrangeButton, 6)
                    }
                    item {
                        Line ("LICENCIAS RET.", OrangeButton, 7)
                    }
                    item {
                        Line ("VACACIONES", OrangeButton, 8)
                    }
                    item {
                        Line ("MOTIVOS PERSONALES", OrangeButton, 9)
                    }
                    item {
                        Line ("DESCANSO", OrangeButton, 10)
                    }
                    item {
                        Line ("OTROS", OrangeButton, 11)
                    }
                    item {
                        Line ("COMIDA", OrangeButton, 12)
                    }
                }
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        onClick = { navController.navigate(NavItem.ClockInAcceptCancel.route) },
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .fillMaxHeight(0.6f)
                    ) {
                        Text(
                            text = "Marcar",
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
        // OPTIONS MENU
        AnimatedVisibility(visible = menuVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) ){
            Surface(
                modifier = Modifier
                    .fillMaxSize()
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

/*
@Composable
fun Line (text: String, color: Color, id: Int, setCheckedState: (Int) -> Unit) {
    var checkedState by remember { mutableStateOf(0) }
    setCheckedState(checkedState)
    var colorBackground = when ( id % 2 ) {
        0 -> Color.White
        else -> Color.LightGray
    }
    if (checkedState == id) colorBackground = Color.Gray
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { if (checkedState != id) checkedState = id }
            .background(colorBackground)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(20.dp)

            )

            Text(text = text,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
        }
        Switch(
            checked = (checkedState == id),
            onCheckedChange = { if (checkedState != id) checkedState =  id},
            modifier = Modifier.padding(end = 15.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.LightGray,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = color,
                uncheckedTrackColor = Color.White,
            )
        )
    }
}
*/

@Composable
fun Line (text: String, color: Color, id: Int) {
    var checkedState by remember { mutableStateOf(false) }
    var colorBackground = when ( id % 2 ) {
        0 -> Color.White
        else -> Color.LightGray
    }
    if (checkedState) colorBackground = Color.Gray
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { checkedState = !checkedState }
            .background(colorBackground)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(20.dp)

            )

            Text(text = text,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
        }
        Switch(
            checked = checkedState,
            onCheckedChange = { checkedState = !checkedState },
            modifier = Modifier.padding(end = 15.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.LightGray,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = color,
                uncheckedTrackColor = Color.White,
            )
        )
    }
}