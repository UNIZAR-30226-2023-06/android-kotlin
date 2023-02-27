package com.example.mycatan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.ui.theme.AzulOscuro
import com.example.mycatan.ui.theme.Blanco

@Composable
fun MenuScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxWidth(0.40f)
            .fillMaxHeight()
            //.background(color=Blanco)
    ) {


            // IMAGE, PERFIL DEL USUARIO
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.40f)
                    .height(120.dp)
                    .background(Brush.verticalGradient(0f to AzulOscuro, 1000f to AzulOscuro)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
            ) {

                Text(text = "JTorci",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            }



        // BUTTONS
        Box(
            modifier = Modifier
                //.fillMaxWidth(0.40f)
                .fillMaxHeight()
                .background(color= Blanco)

        ){
            Button(
                onClick = { navController.navigate(Routes.Registro.route)},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Text(text = "Iniciar sesión",
                    style = TextStyle(color = Blanco)
                )

            }

        }

    }

}


