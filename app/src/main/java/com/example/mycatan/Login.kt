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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.mycatan.ui.theme.*

@Composable
fun LoginPage(navController: NavHostController) {

        /*Image(
            painter = painterResource(R.drawable.wave),
            contentDescription = "My image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )*/
    Box(modifier = Modifier
        .fillMaxHeight()
        .paint(painterResource(R.drawable.wave_3),
            contentScale = ContentScale.FillBounds),
            contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color= Transp),
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
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
                Button(
                    onClick = { navController.navigate(Routes.Splash.route)},
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


//val client = OkHttpClient()
//
//val mediaType = MediaType.parse("application/x-www-form-urlencoded")
//val body = RequestBody.create(mediaType, "grant_type=&username=hash%40hash&password=1234&scope=&client_id=&client_secret=")
//val request = Request.Builder()
//  .url("http://localhost:8000/login")
//  .post(body)
//  .addHeader("accept", "application/json")
//  .addHeader("Content-Type", "application/x-www-form-urlencoded")
//  .build()
//
//val response = client.newCall(request).execute()

fun enviarLogin( username: String, password: String ){
    " &password=" + password.value + "&scope=&client_id=&client_secret=")
    val request = Request.Builder()
        .url("http://localhost:8000/login")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val response = client.newCall(request).execute()



    val values = mapOf("username" to username.value, "occupation" to "gardener")

    val objectMapper = ObjectMapper()
    val requestBody: String = objectMapper
        .writeValueAsString(values)

    val client = HttpClient.newBuilder().build();
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://httpbin.org/post"))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString());
    println(response.body())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    val navController = rememberNavController()
    LoginPage(navController = navController)
}