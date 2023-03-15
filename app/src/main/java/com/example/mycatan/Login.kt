package com.example.mycatan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
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
import com.auth0.jwt.JWT
import com.example.mycatan.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

/*import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse*/
import kotlinx.serialization.json.Json
import okhttp3.internal.ignoreIoExceptions
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTPartsParser



@Composable
fun LoginPage(navController: NavHostController) {
    var errorDatosIncorrectos by remember { mutableStateOf(false) }
    var errorActualizado by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxHeight()
        .paint(
            painterResource(R.drawable.wave_3),
            contentScale = ContentScale.FillBounds
        ),
            contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = Transp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }


            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = "My image",
                modifier = Modifier.width(150.dp)

            )

            TextField(
                modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),
                singleLine = true,
                label = { Text(text = "Correo electrónico") },
                value = username.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent),
                shape = RoundedCornerShape(50.dp),
                onValueChange = { username.value = it })

            TextField(
                modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),
                singleLine = true,
                label = { Text(text = "Contraseña") },
                value = password.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(50.dp),
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
            if(errorDatosIncorrectos){
                Text(text = "ERROR: Datos incorrectos", style = TextStyle(color = Rojo, fontWeight = FontWeight.Bold))
            }
            else if(errorActualizado){
                errorActualizado = false
                navController.navigate(Routes.Splash.route)
            }
            Spacer(modifier = Modifier.height(5.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
                Button(

                    onClick = {
                        if (username.value.text.isEmpty() || password.value.text.isEmpty()){
                            errorDatosIncorrectos = true
                        }
                        enviarLogin( username.value.text ,
                            password.value.text,
                            onErrorClick = {  errorDatosIncorrectos=it
                                            errorActualizado = true},

                        )


                    },
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

            Spacer(modifier = Modifier.height(5.dp))

            Row{
                Text(
                    text = AnnotatedString("¿Nuevo usuario? "),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        color = AzulOscuro
                    )
                )

                ClickableText(
                    text = AnnotatedString("Registrate"),
                    onClick = { navController.navigate(Routes.Registro.route)},
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

fun enviarLogin(username: String, password: String, onErrorClick: (err: Boolean) -> Unit) {
    println("username: $username")
    println("password: $password")

    // Inicie un subproceso en segundo plano

        val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body = RequestBody.create(
            mediaType,
            "&grant_type=password&username=$username&password=$password&scope=&client_id=client&client_secret=secret"
        )
        val request = Request.Builder()

            .url("http://$ipBackend:8000/login")
            .post(body)
            .addHeader("accept", "application/json")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()

            val client = OkHttpClient()

            //val response = client.newCall(request).execute()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // manejo de errores
                    println(call)
                    println("ERROR al conectar con backend")
                }

                override fun onResponse(call: Call, response: Response) {
                    val respuesta = response.body?.string().toString()
                    //transform the string to json object
                    val json = JSONObject(respuesta)
                    //get the string from the response
                    val status = json.getString("detail")
                    if(status == "Incorrect email"){
                        //TODO: gestionar email incorrecto
                        println("EMAIL INCORRECTO")
                        onErrorClick(true)
                    }else if (status == "Incorrect password"){
                        //TODO: gestional contraseña incorrecta
                        println("CONTRASEÑA INCORRECTA")
                        onErrorClick(true)
                    }else if (status == "Logged in successfully"){
                        onErrorClick(false)

                        val accessToken = json.getString("access_token")
                        println("TOKEN DE ACCESO $accessToken")
                        val user = JWT.decode(accessToken)
                        val id = user.getClaim("id").asInt()
                        val email = user.getClaim("email").asString()
                        val username = user.getClaim("username").asString()
                        //TODO: terminar esto
                    }
                }
            })

}


