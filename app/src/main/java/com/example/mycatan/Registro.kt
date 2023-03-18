package com.example.mycatan

import android.provider.ContactsContract.CommonDataKinds.Email
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
import com.auth0.jwt.JWT
import com.example.mycatan.ui.theme.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException




@Composable
fun RegistroPage(navController: NavHostController) {
    var errorPswd by remember { mutableStateOf(false) }
    var errorNombres by remember { mutableStateOf(false) }
    var errorRegistro by remember { mutableStateOf(false) }
    var errorActualizado by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxHeight()
        .background(color= Transp)
        .paint(
            painterResource(R.drawable.wave_3),
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
            else if(errorRegistro){
                Text(text = "ERROR: El email introducido está siendo usado o es incorrecto", style = TextStyle(color = Rojo))
            }
            else if(errorActualizado){
                errorActualizado = false
                navController.navigate(Routes.Login.route)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
                Button(
                    onClick = {
                        if ( nombre.value.text.isEmpty()
                            || correo.value.text.isEmpty()){

                            errorNombres = true

                        }
                        else if ( password.value != confirmarContrasena.value
                            || password.value.text.isEmpty()
                            || confirmarContrasena.value.text.isEmpty()) {

                            errorNombres=false
                            errorPswd= true
                        }
                        else {
                            errorPswd = false
                            errorNombres = false

                            enviarRegistro(
                                correo.value.text,
                                password.value.text,
                                nombre.value.text,
                                onErrorClick = {  errorRegistro=it
                                    errorActualizado = true})
                        }

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

fun enviarRegistro(email: String, password: String, name:String, onErrorClick: (err: Boolean) -> Unit) {
    println("correo: $email")
    println("username: $name")
    println("password: $password")

    // Inicie un subproceso en segundo plano

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("http://$ipBackend:8000/register?name=$name&email=$email&password=$password&coins=0&selected_grid_skin=default&selected_piece_skin=default&saved_music=default&elo=500")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    println(request)

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores
            println(call)
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            var invalidEmail = ""
            val respuesta = response.body?.string().toString()

            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")
            val jsonArray = try {
                JSONArray(status)
            } catch (e: JSONException) {
                null
            }
            if(jsonArray != null){
                val jsonObject = jsonArray.getJSONObject(0)
                invalidEmail = jsonObject.getString("msg")
            }

            if(invalidEmail == "value is not a valid email address"){     // Correo invalido
                //TODO: gestionar email incorrecto
                println("EMAIL INCORRECTO")
                onErrorClick(true)
            }else if (status == "Email already exists"){            // Ya existe el email
                //TODO: gestional email ya existente
                println("EMAIL YA EXISTE")
                onErrorClick(true)
            }else if (status == "User created"){                    // Usuario creado
                onErrorClick(false)

                val accessToken = json.getString("access_token")
                println("TOKEN DE ACCESO $accessToken")
                val user = JWT.decode(accessToken)
                var tempId = user.getClaim("id").asInt()
                Globals.Id = tempId.toString()
                Globals.Email = user.getClaim("email").asString()
                Globals.Username = user.getClaim("username").asString()
                println(" ID: ${Globals.Id}, EMAIL: ${Globals.Email}, USERNAME: ${Globals.Username}")
            }
        }
    })

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    val navController = rememberNavController()
    RegistroPage(navController = navController)
}

