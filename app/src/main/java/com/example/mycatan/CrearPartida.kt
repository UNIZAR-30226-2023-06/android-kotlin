package com.example.mycatan

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
fun CrearPartidaPage(navController: NavHostController) {
    var numberValue: Int by rememberSaveable { mutableStateOf(15) }

    Row(){
        // MENU LATERAL
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.50f)
                .background(color = AzulOscuro)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),

            ) {
            Text(text = "CONFIGURACIÓN",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Row(modifier = Modifier
                .padding(5.dp, 5.dp, 5.dp, 5.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Tiempo de turno",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(5.dp))
                // INCREMENTADOR/DECREMENTADOR NUMERO


            }

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = AzulClaro)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

        }


    }

}

/*
data class QuantityState(
    val quantity: String,
    val minusDisabled: Boolean,
    val moreDisabled: Boolean
)

@Composable
fun QuantitySelection(
    value: Int,
    maxValue: Int = Int.MAX_VALUE,
    onValueChanged: (newValue: Int) -> Unit,
    onMinusClicked: (newValue: Int) -> Unit = {},
    onMoreClicked: (newValue: Int) -> Unit = {}
) {
    val quantityState = state {
        QuantityState(
            quantity = "$value",
            minusDisabled = value == 0,
            moreDisabled = value == maxValue
        )
    }
    // You code of your composable here...
}*/


