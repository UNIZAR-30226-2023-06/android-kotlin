package com.example.mycatan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

var errorPswd = false
var errorNombres = false
@Composable
fun RegistroPage(navController: NavHostController) {

    Box(modifier = Modifier
        .fillMaxHeight()
        .background(color= Transp)
        .paint(
            painterResource(R.drawable.wave_1),
            contentScale = ContentScale.FillBounds),
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,){
            Text(text = "Registro", style = TextStyle(fontSize = 40.sp, color = AzulOscuro, fontWeight = FontWeight.Bold))
            Text(text = "Crea tu nueva cuenta", style = TextStyle(fontSize = 14.sp, color = AzulOscuro))


            val correo = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }
            val nombre = remember { mutableStateOf(TextFieldValue()) }
            val confirmarContrasena = remember { mutableStateOf(TextFieldValue()) }
            var ruta = Routes.Login.route;
            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                TextField(
                    singleLine = true,
                    label = { Text(text = "Correo electrónico") },
                    value = correo.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent),
                    onValueChange = {
                        if( it.text.length <= 76)
                            correo.value = it }
                )
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    singleLine = true,
                    label = { Text(text = "Nombre") },
                    value = nombre.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent),
                    onValueChange = { nombre.value = it })
            }


            Spacer(modifier = Modifier.height(5.dp))

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                TextField(
                    singleLine = true,
                    label = { Text(text = "Contraseña") },
                    value = password.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    singleLine = true,
                    label = { Text(text = "Confirmar contraseña") },
                    value = confirmarContrasena.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { confirmarContrasena.value = it })
            }


            Spacer(modifier = Modifier.height(10.dp))

            if(errorNombres){
                Text(text = "ERROR: El correo o el nombre estan vacíos no coinciden", style = TextStyle(color = Rojo))
            }
            else if(errorPswd){
                Text(text = "ERROR: Las contraseñas no coinciden  o están vacías. Vuelva a intentarlo.", style = TextStyle(color = Rojo))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
                Button(
                    onClick = {
                        if ( nombre.value.text.isEmpty()
                            || correo.value.text.isEmpty()){

                            errorNombres = true
                            ruta = Routes.Registro.route

                        }else if ( password.value != confirmarContrasena.value
                            || password.value.text.isEmpty()
                            || confirmarContrasena.value.text.isEmpty()) {

                            errorNombres=false
                            errorPswd= true
                            ruta = Routes.Registro.route
                        }
                        else {
                            errorPswd = false
                            errorNombres = false
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
            Spacer(modifier = Modifier.height(5.dp))

            Row{
                Text(
                    text = AnnotatedString("¿Ya tienes cuenta? "),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        color = AzulOscuro
                    )
                )

                ClickableText(
                    text = AnnotatedString("Inicia sesión"),
                    onClick = { navController.navigate(Routes.Login.route)},
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        color = AzulOscuro,
                        fontWeight = FontWeight.Bold
                    )
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

