package com.example.mycatan

import androidx.compose.foundation.background
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
import com.example.mycatan.ui.theme.AzulClaro
import com.example.mycatan.ui.theme.AzulOscuro
import com.example.mycatan.ui.theme.Blanco
import com.example.mycatan.ui.theme.Purple700

@Composable
fun LoginPage(navController: NavHostController) {

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

        Text(text = "Iniciar sesión", style = TextStyle(fontSize = 40.sp, color = AzulOscuro, fontWeight = FontWeight.Bold))

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Correo electrónico") },
            value = username.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Blanco),
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Contraseña") },
            value = password.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Blanco),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(
            text = AnnotatedString("¿Has olvidado la contraseña?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                color = AzulOscuro
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
            Button(
                onClick = { navController.navigate(Routes.Home.route)},
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

        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
            Button(
                onClick = { navController.navigate(Routes.Registro.route)},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Text(text = "Registrarse",
                    style = TextStyle(color = Blanco)
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    val navController = rememberNavController()
    LoginPage(navController = navController)
}