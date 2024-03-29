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
fun TiendaPage(navController: NavHostController) {

    val pendiente by remember { mutableStateOf(getNumAmigosPendiente(Globals.Token)) }
    var menuVisible = remember { mutableStateOf(false) }
    var fotoPopUp by remember { mutableStateOf(-1) }
    var clicked by remember { mutableStateOf(-1) }
    val precios = 25

    //no se guarda si vas para atras

    val onConfirmedFoto: (Int) -> Unit = { index ->

        subCoins(Globals.precioFotos[index])
        Globals.fotosCompradas[index] = true
    }

    val onConfirmedPieza: (Int) -> Unit = { index ->
        subCoins(precios)
        Globals.piezasCompradas[index] = true
    }

    val onConfirmedMapa: (Int) -> Unit = { index ->
        subCoins(precios)
        Globals.mapasCompradas[index] = true
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.Home.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Blanco,
                            modifier = Modifier.padding(5.dp)
                        )
                        if (pendiente.toInt() > 0) {
                            Badge(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text(text = pendiente)
                            }
                        }
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.AmigosTodos.route)
                    }

                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = true,
                    onClick = {
                        navController.navigate(Routes.Tienda.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        FotoPerfil(navController, foto = Globals.Personaje) {}
                    },

                    selected = false,
                    onClick = {
                        navController.navigate(Routes.EditarPerfil.route)
                    }
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.talado),
                    contentScale = ContentScale.FillBounds
                )
                .padding(10.dp, 10.dp, 10.dp, 10.dp)
        )
        {

            if(menuVisible.value && clicked==1)
                TiendaPOPfoto(fotoPopUp, setShowDialog = {
                    menuVisible.value = it }, onConfirmedFoto, precios)

            if(menuVisible.value && clicked==2)
                TiendaPOPpiezas(fotoPopUp, setShowDialog = {
                    menuVisible.value = it }, onConfirmedPieza, precios)

            if(menuVisible.value && clicked==3)
                TiendaPOPmapa(fotoPopUp, setShowDialog = {
                    menuVisible.value = it }, onConfirmedMapa, precios)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                item() {
                    /*Text(text = "Tienda",
                        style = TextStyle
                            (fontSize = 40.sp, color = AzulOscuro, fontWeight = FontWeight.Bold))*/



                    Text(
                        text = "Personajes",
                        style = TextStyle
                            (
                            fontSize = 20.sp,
                            color = AzulOscuro,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(25.dp), //maybe otra cosa
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(25.dp)
                    ) {
                        items(9) {
                            RackItem(foto = it,
                                precio = Globals.precioFotos[it],
                                comprada = Globals.fotosCompradas[it],
                                onCardClick = {
                                    fotoPopUp = it
                                    menuVisible.value = true
                                    clicked = 1
                                })
                        }
                    }

                    Text(
                        text = "Packs de fichas",
                        style = TextStyle
                            (
                            fontSize = 20.sp,
                            color = AzulOscuro,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(25.dp), //maybe otra cosa
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(25.dp)
                    ) {

                        items(9) {
                            RackItem(foto = it,
                                precio = 25,
                                comprada = Globals.piezasCompradas[it],
                                onCardClick = {
                                    fotoPopUp = it
                                    menuVisible.value = true
                                    clicked = 2
                                })
                        }
                    }

                    Text(
                        text = "Mapas",
                        style = TextStyle
                            (
                            fontSize = 20.sp,
                            color = AzulOscuro,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(25.dp), //maybe otra cosa
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(25.dp)
                    ) {

                        items(9) {
                            RackItem(foto = it,
                                precio = 25,
                                comprada = Globals.mapasCompradas[it],
                                onCardClick = {
                                    fotoPopUp = it
                                    menuVisible.value = true
                                    clicked = 3
                                })
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
                    .padding(10.dp, 5.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Text(
                        text = Globals.Coins,
                        style = TextStyle(
                            color = AzulOscuro,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Image( painter = painterResource(R.drawable.moneda),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )

                }

            }

        }
    }
}

@Composable
fun TiendaPOPfoto(
    fotoId: Int,
    setShowDialog: (Boolean) -> Unit,
    onConfirmed: (Int) -> Unit,
    precios: Int
) {

    val context = LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elemento seleccionado",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    RackItem(foto = fotoId,precio = Globals.precioFotos[fotoId], comprada = false) {}

                    Spacer(modifier = Modifier.height(5.dp))
                    if (Globals.Coins.toInt() >= 25) {
                        Text(
                            text = "¿Estas seguro? ",
                            fontSize = 14.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    } else {
                        Text(
                            text = "Saldo Insuficiente ",
                            fontSize = 14.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    if (Globals.Coins.toInt() >= 2*Globals.precioFotos[fotoId]) {
                        Row() {

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro)

                            ) {
                                Text(
                                    text = "Cancelar",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )

                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = {
                                    if(buyPersonaje(fotoId.toString())){

                                        Toast.makeText(context, "Personaje comprado correctamente", Toast.LENGTH_SHORT).show()

                                        onConfirmed(fotoId)
                                    } else{
                                        Toast.makeText(context, "ERROR el personaje no se ha comprado", Toast.LENGTH_SHORT).show()
                                    }
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "Comprar",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {

                                        Text(
                                            text = "   ${precios}",
                                            style = TextStyle(
                                                color = AzulOscuro, fontWeight = FontWeight.Bold
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
                        }
                    } else {
                        Button(
                            onClick = { setShowDialog(false) },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro)

                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TiendaPOPpiezas(
    fotoId: Int,
    setShowDialog: (Boolean) -> Unit,
    onConfirmed: (Int) -> Unit,
    precios: Int
) {

    val context = LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elemento seleccionado",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    RackItem(foto = fotoId,precio = 25, comprada = false) {}

                    Spacer(modifier = Modifier.height(5.dp))
                    if (Globals.Coins.toInt() >= 25) {
                        Text(
                            text = "¿Estas seguro? ",
                            fontSize = 14.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    } else {
                        Text(
                            text = "Saldo Insuficiente ",
                            fontSize = 14.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    if (Globals.Coins.toInt() >= precios) {
                        Row() {

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro)

                            ) {
                                Text(
                                    text = "Cancelar",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )

                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = {
                                    if(buyPiezas(fotoId.toString())){

                                        Toast.makeText(context, "Piezas compradas correctamente", Toast.LENGTH_SHORT).show()
                                        println("clickado putos")
                                        onConfirmed(fotoId)
                                    } else{
                                        Toast.makeText(context, "ERROR las piezas no se han comprado", Toast.LENGTH_SHORT).show()
                                    }
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "Comprar",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {

                                        Text(
                                            text = "   ${precios}",
                                            style = TextStyle(
                                                color = AzulOscuro, fontWeight = FontWeight.Bold
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
                        }
                    } else {
                        Button(
                            onClick = { setShowDialog(false) },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro)

                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TiendaPOPmapa(
    fotoId: Int,
    setShowDialog: (Boolean) -> Unit,
    onConfirmed: (Int) -> Unit,
    precios: Int
) {

    val context = LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elemento seleccionado",
                            color = Blanco,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    RackItem(foto = fotoId,precio = 25, comprada = false) {}

                    Spacer(modifier = Modifier.height(5.dp))
                    if (Globals.Coins.toInt() >= 25) {
                        Text(
                            text = "¿Estas seguro? ",
                            fontSize = 14.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    } else {
                        Text(
                            text = "Saldo Insuficiente ",
                            fontSize = 14.sp,
                            style = TextStyle(
                                color = Blanco,
                                fontWeight = FontWeight.Bold

                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    if (Globals.Coins.toInt() >= precios) {
                        Row() {

                            Button(
                                onClick = { setShowDialog(false) },
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro)

                            ) {
                                Text(
                                    text = "Cancelar",
                                    style = TextStyle(
                                        color = AzulOscuro, fontWeight = FontWeight.Bold
                                    )
                                )

                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = {
                                    if(buyMapa(fotoId.toString())){

                                        Toast.makeText(context, "Mapa comprado correctamente", Toast.LENGTH_SHORT).show()

                                        onConfirmed(fotoId)
                                    } else{
                                        Toast.makeText(context, "ERROR el mapa no se ha comprado", Toast.LENGTH_SHORT).show()
                                    }
                                    setShowDialog(false)
                                },
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Verde),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(3.dp, AzulOscuro),

                                ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "Comprar",
                                        style = TextStyle(
                                            color = AzulOscuro, fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {

                                        Text(
                                            text = "   ${precios}",
                                            style = TextStyle(
                                                color = AzulOscuro, fontWeight = FontWeight.Bold
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
                        }
                    } else {
                        Button(
                            onClick = { setShowDialog(false) },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rojo),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(3.dp, AzulOscuro)

                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    color = AzulOscuro, fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RackItem( foto: Int , precio: Int,  comprada: Boolean,   onCardClick: () -> Unit ){



    var painterID : Painter
    //Estoes muy cutre pero no se hacerlo mejor
    if(foto==0){
        painterID = painterResource(R.drawable.skin1)
    }
    else if(foto==1){
        painterID = painterResource(R.drawable.skin2)
    }
    else if(foto==2){
        painterID = painterResource(R.drawable.skin3)
    }
    else if(foto==3){
        painterID = painterResource(R.drawable.skin4)
    }
    else if(foto==4){
        painterID = painterResource(R.drawable.skin5)
    }
    else if(foto==5){
        painterID = painterResource(R.drawable.skin6)
    }
    else if(foto==6){
        painterID = painterResource(R.drawable.skin7)
    }
    else if(foto==7){
        painterID = painterResource(R.drawable.skin8)
    }else {
        painterID = painterResource(R.drawable.skin9)
    }

    Card(
        modifier = Modifier
            .clickable {
                if (!comprada)
                    onCardClick()
            }
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
                        text =  (2*precio).toString(),
                        fontSize = 14.sp,
                        style = TextStyle(
                            color = Blanco,
                            fontWeight = FontWeight.Bold

                        )
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Image( painter = painterResource(R.drawable.moneda),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
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