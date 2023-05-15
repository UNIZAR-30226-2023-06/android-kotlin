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
import com.example.mycatan.dBaux.recover_password
import com.example.mycatan.others.Routes


@Composable
fun RecuperarPage(navController: NavHostController) {
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


            Text(text = "Recuperar contraseña", style = TextStyle(fontSize = 30.sp, color = AzulOscuro, fontWeight = FontWeight.Bold))
            Text(text = "Introduce tu correo electrónico", style = TextStyle(fontSize = 14.sp, color = AzulOscuro))

            TextField(
                modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp),
                singleLine = true,
                label = { Text(text = "Correo electrónico") },
                value = username.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent),
                shape = RoundedCornerShape(50.dp),
                onValueChange = { username.value = it })


            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp), ) {
                Button(

                    onClick = {
                        if (username.value.text.isEmpty()){
                            errorDatosIncorrectos = true
                        }
                        else{
                            if (recover_password(username.value.text)){
                                val toast = Toast.makeText(context, "Recuperación de contraseña correcta, revise su correo electronico", Toast.LENGTH_SHORT)
                                toast.show()
                                navController.navigate(Routes.Login.route)
                            }
                            else{
                                val toast = Toast.makeText(context, "ERROR: Introduce el correo electrónico", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(280.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

                ) {
                    Text(text = "Recuperar",
                        style = TextStyle(color = Blanco)
                    )

                }
            }

        }
    }

}




