package com.example.mycatan

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.ui.theme.*

@Composable
fun TiendaPage(navController: NavHostController) {

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

                Spacer(modifier = Modifier.height(15.dp))

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
                        /*var foto: String
                        foto = "personaje$it"*/
                        RackItem(/*foto*/)
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

                    items(9) {
                        RackItem()
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
                .padding(10.dp,5.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {

                Icon(imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Amarillo)

                Text(
                    text = "12$",
                    style = TextStyle(
                        color = Blanco,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
    }
}

@Composable
fun RackItem( /*foto: String*/ ){



    Card(
        modifier = Modifier
            .clickable {/*movidas*/ }
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
                painter = painterResource(R.drawable.personaje1), // HAY QUE HACER QUE PINTE R.drawable.foto PERO NO SE COMO
                contentDescription = null,
                modifier = Modifier.height(76.dp)

            )


            //PRECIO
            Box(modifier = Modifier
                .background(AzulOscuro)
                .fillMaxSize()
                .border(
                        width = 4.dp,
                        color=AzulOscuro),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = " 25$",
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
    }
}