package com.example.holamundocompose.ui.screens.mapPlaces

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.holamundocompose.ui.screens.menu.MenuScreen
import com.example.holamundocompose.ui.theme.*


@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun MapScreen(navController: NavHostController) {
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

            MapScreenContent(navController)

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

@Composable
fun MapScreenContent(navController: NavHostController) {

    var placeSelected = remember { mutableStateOf("Borneo1") }
    var colorSala01 by remember { mutableStateOf(Galapagos3) }
    var colorSala10 by remember { mutableStateOf(Galapagos3) }
    var colorSala11 by remember { mutableStateOf(Galapagos3) }
    if ( placeSelected.value == "Sala 01" ) colorSala01 = Color.Gray ; else colorSala01 = Galapagos3
    if ( placeSelected.value == "Sala 10" ) colorSala10 = Color.Gray ; else colorSala10 = Galapagos3
    if ( placeSelected.value == "Sala 11" ) colorSala11 = Color.Gray ; else colorSala11 = Galapagos3

    Row(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.3f)
        ) {
            // SPACE BETWEEN
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
            ){}
            // SALA 10
            Row (
                modifier = Modifier
                    .background(colorSala10)
                    .fillMaxWidth()
                    .weight(1f)
                    .border(1.dp, Color.Black)
                    .clickable { placeSelected.value = "Sala 10" },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Sala 10",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            // SPACE BETWEEN
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ){}
            // SALA 01
            Row (
                modifier = Modifier
                    .background(colorSala01)
                    .fillMaxWidth()
                    .weight(1f)
                    .border(1.dp, Color.Black)
                    .clickable { placeSelected.value = "Sala 01" },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Sala 11",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }


        }


        Column() {
            // SALA 11
            Row (
                modifier = Modifier
                    .background(colorSala11)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(0.4f)
                    .align(CenterHorizontally)
                    .border(1.dp, Color.Black)
                    .clickable { placeSelected.value = "Sala 11" },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Sala 11",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            // TABLES
            SalaMapScreenContent(placeSelected)
        }
    }

}

@Composable
fun SalaMapScreenContent(placeSelected: MutableState<String>) {

    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }
    Row(
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            // add transformable to listen to multitouch transformation events
            // after offset
            .transformable(state = state)
            //.aspectRatio(0.56f)
            .fillMaxSize()
            .border(2.dp, Color.Black)
            .border(1.dp, Color.White)
            .padding(10.dp)

    ) {
        //--------------------------
        // LEFT COLUMN
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            // BORNEO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
            ) {
                Island4Places("Borneo", Borneo1, Borneo2, Borneo3, Borneo4, placeSelected)
            }
            // GALÁPAGOS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            ) {
                Island4Places("Galápagos", Galapagos1, Galapagos2, Galapagos3, Galapagos4, placeSelected)
            }
            // BALI
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                Island4Places("Bali", Bali1, Bali2, Bali1, Bali2, placeSelected)
            }
            // Selected
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "SELECTED ${placeSelected.value}",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        //--------------------------
        // MIDDLE COLUMN
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
        ) {}
        //--------------------------
        // RIGHT COLUMN
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            // MARTINICA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
            ) {
                Island4Places("Martinica", Martinica1, Martinica2, Martinica1, Martinica2, placeSelected)
            }
            // BORA BORA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            ) {
                Island4Places("Bora Bora", BoraBora1, BoraBora2, BoraBora3, BoraBora4, placeSelected)
            }
            // SANTORINI
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                Island4Places("Santorini", Santorini1, Santorini2, Santorini3, Santorini4, placeSelected)
            }
            // MADAGASCAR
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            ) {
                Island4Places("Madagascar", Madagascar1, Madagascar2, Madagascar3, Madagascar4, placeSelected)
            }
        }

    }
}

@Composable
fun Island4Places (islandName: String, color1: Color, color2: Color, color3: Color, color4: Color,
                   placeSelected: MutableState<String>) {

    var color1Mutable by remember { mutableStateOf(color1) }
    var color2Mutable by remember { mutableStateOf(color2) }
    var color3Mutable by remember { mutableStateOf(color3) }
    var color4Mutable by remember { mutableStateOf(color4) }

    if ( placeSelected.value == "${islandName}1" ) color1Mutable = Color.Gray ; else color1Mutable = color1
    if ( placeSelected.value == "${islandName}2" ) color2Mutable = Color.Gray ; else color2Mutable = color2
    if ( placeSelected.value == "${islandName}3" ) color3Mutable = Color.Gray ; else color3Mutable = color3
    if ( placeSelected.value == "${islandName}4" ) color4Mutable = Color.Gray ; else color4Mutable = color4

    // 1 & 2
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.33f),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.33f)
                .background(color1Mutable)
                .border(1.dp, Color.Black)
                .clickable { placeSelected.value = "${islandName}1" },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "1",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.5f)
                .background(color2Mutable)
                .border(1.dp, Color.Black)
                .clickable { placeSelected.value = "${islandName}2" },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "2",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
    // Name
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.White)
            .border(2.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = islandName,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }

    // 3 & 4
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.33f)
                .background(color3Mutable)
                .border(1.dp, Color.Black)
                .clickable { placeSelected.value = "${islandName}3" },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "3",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.5f)
                .background(color4Mutable)
                .border(1.dp, Color.Black)
                .clickable { placeSelected.value = "${islandName}4" },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "4",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}