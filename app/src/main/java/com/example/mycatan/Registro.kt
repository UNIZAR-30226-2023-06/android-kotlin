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
import androidx.compose.runtime.saveable.rememberSaveable
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

var error = false
@Composable
fun RegistroPage(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth()
        .background(color = AzulClaro),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,){
        Text(text = "Registro", style = TextStyle(fontSize = 40.sp, color = AzulOscuro, fontWeight = FontWeight.Bold))
        val correo = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val nombre = remember { mutableStateOf(TextFieldValue()) }
        val confirmarContrasena = remember { mutableStateOf(TextFieldValue()) }
        var ruta = Routes.Login.route;
        Spacer(modifier = Modifier.height(30.dp))

        Row{
            OutlinedTextField(
                label = { Text(text = "Correo electrónico") },
                value = correo.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Blanco),
                onValueChange = { correo.value = it })
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                label = { Text(text = "Nombre") },
                value = nombre.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Blanco),
                onValueChange = { nombre.value = it })
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row{
            OutlinedTextField(
                label = { Text(text = "Contraseña") },
                value = password.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Blanco),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password.value = it })
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                label = { Text(text = "Confirmar contraseña") },
                value = confirmarContrasena.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Blanco),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { confirmarContrasena.value = it })
        }
        Spacer(modifier = Modifier.height(10.dp))

        if(error){
            Text(text = "ERROR: Las contraseñas no coinciden. Vuelva a intentarlo.", style = TextStyle(color = Rojo))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
            Button(
                onClick = {
                    if(password.value != confirmarContrasena.value){
                        error = true
                        ruta = Routes.Registro.route
                    }else{
                        error = false
                        ruta = Routes.Login.route
                    }
                    navController.navigate(ruta)

                          },
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
fun DefaultPreview4() {
    val navController = rememberNavController()
    RegistroPage(navController = navController)
}

