package com.example.mycatan.pantallas

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.R
import com.example.mycatan.others.Globals
import com.example.mycatan.ui.theme.*

@Composable
fun TiendaPage(navController: NavHostController) {

    var menuVisible by remember { mutableStateOf(false) }
    var fotoPopUp by remember { mutableStateOf(-1) }
    val precios = 25

    //no se guarda si vas para atras


    val onConfirmed: (Int) -> Unit = { index ->

        var temp = Globals.Coins.toInt()
        temp -= precios;
        Globals.Coins = temp.toString()

        //habria que postear tambien las nuevas coins del usuario
        Globals.fotosCompradas[index] = true
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AzulClaro)
        .padding(10.dp, 10.dp, 10.dp, 10.dp)
    )
    {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        )
        {
            item(){
                /*Text(text = "Tienda",
                    style = TextStyle
                        (fontSize = 40.sp, color = AzulOscuro, fontWeight = FontWeight.Bold))*/



                Text(text = "    Personajes",
                    style = TextStyle
                        (fontSize = 20.sp, color = AzulOscuro, fontWeight = FontWeight.ExtraBold))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(25.dp), //maybe otra cosa
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(25.dp)
                ) {
                    items(9)  {
                        RackItem(foto = it,
                            comprada = Globals.fotosCompradas[it],
                            onCardClick = {
                                menuVisible = !menuVisible
                                fotoPopUp = it })
                    }
                }

                Text(text = "    Packs de fichas",
                    style = TextStyle
                        (fontSize = 20.sp, color = AzulOscuro, fontWeight = FontWeight.ExtraBold))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(25.dp), //maybe otra cosa
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(25.dp)
                ) {

                    items(9)  {
                        RackItem(foto = it,
                            comprada = Globals.fotosCompradas[it],
                            onCardClick = {
                                menuVisible = !menuVisible
                                fotoPopUp = it })
                    }
                }

                Text(text = "    Mapas",
                    style = TextStyle
                        (fontSize = 20.sp, color = AzulOscuro, fontWeight = FontWeight.ExtraBold))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(25.dp), //maybe otra cosa
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(25.dp)
                ) {

                    items(9)  {
                        RackItem(foto = it,
                            comprada = Globals.fotosCompradas[it],
                            onCardClick = {
                                menuVisible = !menuVisible
                                fotoPopUp = it })
                    }
                }
            }
        }

        //SALDO

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {

            Row(modifier = Modifier
                .background(AzulOscuro)
                .padding(10.dp, 5.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {

                Icon(imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Amarillo)

                Text(
                    text = Globals.Coins,
                    style = TextStyle(
                        color = Blanco,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
    }


    // OPTIONS MENU
    AnimatedVisibility(visible = menuVisible,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000)) ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable { menuVisible = !menuVisible },
            color = Color.Black.copy(alpha = 0.6f)
        ){

        }
    }
    AnimatedVisibility(visible = menuVisible,
        enter = expandHorizontally (animationSpec = tween(1000)),
        exit = shrinkHorizontally(animationSpec = tween(1000)) )
    {
        TiendaScreen(fotoPopUp, navController, onConfirmed)
    }
}

@Composable
fun RackItem( foto: Int ,  comprada: Boolean,   onCardClick: () -> Unit ){

    var precio = "25"

    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto==0){
        painterID = painterResource(R.drawable.personaje1)
    }
    else if(foto==1){
        painterID = painterResource(R.drawable.personaje2)
    }
    else if(foto==2){
        painterID = painterResource(R.drawable.personaje3)
    }
    else if(foto==3){
        painterID = painterResource(R.drawable.personaje4)
    }
    else if(foto==4){
        painterID = painterResource(R.drawable.personaje5)
    }
    else if(foto==5){
        painterID = painterResource(R.drawable.personaje6)
    }
    else if(foto==6){
        painterID = painterResource(R.drawable.personaje7)
    }
    else if(foto==7){
        painterID = painterResource(R.drawable.personaje8)
    }else {
        painterID = painterResource(R.drawable.personaje9)
    }

    Card(
        modifier = Modifier
            .clickable {
                if (!comprada)
                    onCardClick() }
            .width(105.dp)
            .height(105.dp),

        shape = CircleShape,
        backgroundColor = Blanco,
        border = BorderStroke(5.dp, AzulOscuro),

    ) {


        Column (
            //modifier = Modifier
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(

                painter = painterID,
                contentDescription = null,
                modifier = Modifier.size(74.dp)

            )


            //PRECIO
            Box(modifier = Modifier
                .background(AzulOscuro)
                .fillMaxSize()
                .border(
                    width = 4.dp,
                    color = AzulOscuro
                ),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text =  precio,
                        fontSize = 14.sp,
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold

                        )
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Amarillo
                    )
                }
            }
        }

        //el problema esque aunq se actualicen las fotosCompradas  no se actualizan los rackitems
        if (comprada)
        {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.Black.copy(alpha = 0.6f)
            ){}
            Icon(imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Negro,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp))

        }
    }
}