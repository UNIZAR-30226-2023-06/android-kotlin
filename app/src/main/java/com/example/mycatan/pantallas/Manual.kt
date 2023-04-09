package com.example.mycatan.pantallas

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.dBaux.*
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.*

@Composable
fun ManualPage(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.talado),
                contentScale = ContentScale.FillBounds
            )
            .background(TransparenteBlanco)
            .padding(10.dp, 10.dp, 10.dp, 10.dp)
    )
    {
        LazyColumn(){
            item {
                Spacer(modifier = Modifier.height(70.dp))
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  1",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Frente a ti se encuentra la isla de Catán. Se compone de 19 hexágonos de terreno, rodeados por el mar. Tu objetivo es colonizar la isla.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Image( painter = painterResource(R.drawable.board),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  2",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "En Catán hay un desierto y cinco tipos diferentes de terreno: Cada uno de ellos produce una materia prima diferente. Los bosques producen madera, los cerros arcilla, los pastos lana, Los sembrados producen cereales, las montañas minerales y el desierto no produce nada",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify,

                )
                Image( painter = painterResource(R.drawable.catan2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  3",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Empiezas la partida con 2 poblados y 2 carreteras. Cada poblado vale 1 punto de victoria, así que ya empiezas con un total de 2 puntos de victoria. El primer jugador que consiga 10 puntos de victoria, gana la partida.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  4",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Para conseguir más puntos de victoria tienes que construir nuevas carreteras y poblados, y ampliar tus poblados a ciudades. Una ciudad vale 2 puntos de victoria. Pero, para construir, necesitas materias primas.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Image( painter = painterResource(R.drawable.costes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp).size(180.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  5",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "¿Cómo consigues las materias primas? Muy sencillo: en cada turno se determina el terreno que va a producir. Esto se hace tirando dos dados. En cada terreno hay una ficha numerada redonda. Si por ejemplo, la suma de los dados es un “3”, todos los terrenos marcados con un “3” producirán.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  6",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Sólo consiguen materias primas los jugadores que tengan un poblado o una ciudad adyacentes a estos terrenos. En la ilustración el poblado blanco (D) está adyacente a un bosque y los poblados azul (B) y naranja (C) a una montaña. el resultado de la tirada es un 3, el jugador rojo recibirá 1 madera y los jugadores azul y naranja recibirán 1 mineral cada uno.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Image( painter = painterResource(R.drawable.board1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp).size(100.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  7",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Lo ideal es que la mayoría de los poblados estén adyacentes a varios terrenos (el máximo posible es 3). De esta forma, podrán conseguir, según el resultado de los dados, diferentes materias primas. En la ilustración, el poblado B está adyacente a 3 terrenos: bosque, montaña y cerro",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  8",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "No puedes tener poblados adyacentes a todos los terrenos y todas las fichas numeradas, así que algunas de las materias primas te serán imposibles o muy difíciles de conseguir. Pero, para construir nuevos edificios, necesitas diferentes combinaciones de materias primas.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= "  9",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Por este motivo tienes que comerciar con el resto de jugadores. Haz una oferta o escucha las ofertas que te hagan y, si llegáis a un acuerdo, podrás conseguir la carta que te hace falta para construir un nuevo poblado.",
                    style = TextStyle(
                        color = AzulOscuro,
                        fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= " 10",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Puedes construir un poblado nuevo en una encrucijada que esté libre, con la condición de que una de tus propias carreteras lleve hasta ella y que el poblado más cercano esté, como mínimo, a dos encrucijadas de distancia.",
                    style = TextStyle(
                        color = AzulOscuro,fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
                Image( painter = painterResource(R.drawable.encrucijada),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp).size(50.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Box(modifier = Modifier
                    .width(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = AzulOscuro)
                    .align(Alignment.Center),){
                    Text(
                        text= " 11",
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = "Pero has de pensar bien dónde construir los poblados. Los números de las fichas numeradas están impresos en tamaños diferentes. Esto indica que los números impresos más grandes salen con más frecuencia en los dados que los números impresos más pequeños. Los números 6 y 8 son los que están impresos más grandes y son de color rojo, para indicar que son los que salen con más frecuencia. En conclusión, cuantas más veces salga un número, más veces conseguirás materias primas del terreno que tenga ese número.",
                    style = TextStyle(
                        color = AzulOscuro,fontWeight = FontWeight.W500
                    ),
                    textAlign = TextAlign.Justify
                )
            }


        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Button(
                onClick = { navController.navigate(Routes.Home.route) },
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AzulOscuro)

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Blanco
                )
            }

        }
    }
}

