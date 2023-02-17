package com.example.holamundocompose.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.holamundocompose.navigation.NavItem
import com.example.holamundocompose.ui.theme.*

@Composable
fun MenuScreen(navController: NavHostController, setMenuVisible: (Boolean) -> Unit) {

    //Image(painter = , contentDescription = )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {

        item {
            // IMAGE
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Brush.verticalGradient(0f to Blue2, 1000f to Blue5)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.WhereToVote,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(70.dp)
                )
                Text(text = "App Fichar",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        // BUTTONS
        item {
            RowMenu(navController, setMenuVisible,  "Inicio", Icons.Default.Home)
        }
        item {
            RowMenu(navController, setMenuVisible, "Cambiar contraseña", Icons.Default.Password)
        }
        item {
            RowMenu(navController, setMenuVisible, "Privacidad", Icons.Default.VisibilityOff)
        }
        item {
            RowMenu(navController, setMenuVisible, "Ayuda", Icons.Default.Help)
        }
        item {
            RowMenu(navController, setMenuVisible, "Log de Sesión.", Icons.Default.Description)
        }
        item {
            RowMenu(navController, setMenuVisible, "Enviar Log.", Icons.Default.UploadFile)
        }
        item {
            RowMenu(navController, setMenuVisible, "Cerrar sesión", Icons.Default.PowerSettingsNew)
        }
    }

}

@Composable
fun RowMenu(navController: NavHostController, setMenuVisible: (Boolean) -> Unit, text: String, imageVector: ImageVector) {
    var menuVisible by remember { mutableStateOf(true) }
    Row (
        modifier = Modifier.fillMaxWidth()
            .clickable { menuVisible = !menuVisible }
    ){
        setMenuVisible(menuVisible)
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        )

        Text(text = text,
            modifier = Modifier.align(CenterVertically),
            color = Color.DarkGray,
            fontFamily = FontFamily.SansSerif
        )
    }
}

