package com.example.mycatan.pantallas

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.ui.theme.*

/*import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse*/
import com.example.mycatan.dBaux.enviarLogin
import com.example.mycatan.others.Routes


@Composable
fun LoginPage(navController: NavHostController) {
    var errorDatosIncorrectos by remember { mutableStateOf(false) }
    var errorActualizado by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxHeight()
        .paint(
            painterResource(R.drawable.talado),
            contentScale = ContentScale.FillBounds
        ),
            contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }


            Image(
                painter = painterResource(R.drawable.icon),
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
                errorDatosIncorrectos = false
                val toast = Toast.makeText(context, "ERROR: Datos incorrectos", Toast.LENGTH_SHORT)
                toast.show()

                //Text(text = "ERROR: Datos incorrectos", style = TextStyle(color = Rojo, fontWeight = FontWeight.Bold))
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




