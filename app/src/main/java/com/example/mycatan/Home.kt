package com.example.mycatan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycatan.ui.theme.*

@Composable
fun HomePage(navController: NavHostController) {




    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(color = AzulClaro),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }



        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
            Button(
                onClick = { navController.navigate(Routes.Home.route)},
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp)
                    .border(width = 3.dp, color = AzulOscuro, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

            ) {
                Text(text = "BUSCAR PARTIDA",
                    style = TextStyle(color = AzulOscuro,
                        fontWeight = FontWeight.Bold)
                )

            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
            Button(
                onClick = { navController.navigate(Routes.Home.route)},
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp)
                    .border(width = 3.dp, color = AzulOscuro, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

            ) {
                Text(text = "CREAR PARTIDA CON AMIGOS",
                    style = TextStyle(color = AzulOscuro,
                        fontWeight = FontWeight.Bold)
                )

            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
            Button(
                onClick = { navController.navigate(Routes.Home.route)},
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp)
                    .border(width = 3.dp, color = AzulOscuro, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Amarillo)

            ) {
                Text(text = "RECUPERAR PARTIDA",
                    style = TextStyle(color = AzulOscuro,
                        fontWeight = FontWeight.Bold)
                )

            }
        }

    }

    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ){
        Text(text = "CATAN INC.", style =
        TextStyle(fontSize = 20.sp, color = GrisAzulado, fontWeight = FontWeight.Bold))
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}