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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.ui.theme.*

@Composable
fun MenuScreen(navController: NavHostController) {
    val amigo = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.width(300.dp)
            .fillMaxHeight()
            //.background(color=Blanco)
    ) {


            // IMAGE, PERFIL DEL USUARIO
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color=AzulClaro)
                    .padding(10.dp, 10.dp, 10.dp, 10.dp),
                horizontalAlignment = CenterHorizontally,

            ) {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = AzulOscuro,
                    modifier = Modifier.size(70.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "JTorci#1234",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { navController.navigate(Routes.Registro.route)},
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(text = "Editar perfil",
                        style = TextStyle(color = Blanco)
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp)
                    .fillMaxHeight(0.90f),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ){
                    OutlinedTextField(
                        modifier = Modifier.width(200.dp),
                        singleLine = true,
                        label = { Text(text = "Nombre#1234") },
                        value = amigo.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Blanco),
                        onValueChange = {
                            if( it.text.length <= 76)
                                amigo.value = it }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        onClick = { navController.navigate(Routes.Registro.route)},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                    ) {
                        Text(text = "Enviar",
                            style = TextStyle(color = Blanco)
                        )

                    }
                }
            }


    }

}


