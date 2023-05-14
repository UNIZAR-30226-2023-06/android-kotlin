package com.example.mycatan.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mycatan.dBaux.Message
import com.example.mycatan.dBaux.deleteLobby
import com.example.mycatan.dBaux.get_chat_messages
import com.example.mycatan.dBaux.send_chat_message
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.AzulClaro
import com.example.mycatan.ui.theme.AzulOscuro
import com.example.mycatan.ui.theme.Blanco
import com.example.mycatan.ui.theme.Verde
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun showChat(setShowDialog: (Boolean) -> Unit){
    Dialog(onDismissRequest = { setShowDialog(false)}) { // PARA QUE SOLO SE CIERRE CON LA X QUITAR ESTO JEJE
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AzulOscuro
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    ChatScreen()
                }
            }
        }
    }
}

@Composable
fun ChatScreen() {
    var messages by remember { mutableStateOf(get_chat_messages()) }
    var newMessage by remember { mutableStateOf("") }

    LaunchedEffect(messages) {
        while (true) {
            delay(1000) // espera un segundo antes de hacer la siguiente solicitud GET
            val newMessages = get_chat_messages()
            if (newMessages != messages) {
                messages = newMessages
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).height(400.dp)
        ) {
            items(messages) { message ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = message.username,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = message.message,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newMessage,
                onValueChange = { newMessage = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = Blanco,

                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            IconButton(
                onClick = {
                    messages = messages + listOf(
                        Message(
                            id = Globals.Id,
                            username = Globals.Username,
                            message = newMessage
                        )
                    )
                    send_chat_message(newMessage)
                    newMessage = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null
                )
            }
        }
    }
}




