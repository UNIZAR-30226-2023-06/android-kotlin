package com.example.mycatan.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycatan.others.Routes
import com.example.mycatan.ui.theme.AzulClaro
import com.example.mycatan.ui.theme.AzulOscuro
import com.example.mycatan.ui.theme.Blanco
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Chat(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = Blanco,
                        )
                    },
                    selected = true,
                    onClick = {
                        navController.navigate(Routes.CatanBoard.route)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Blanco,
                            modifier = Modifier.padding(5.dp)
                        )
                        /*if (pendiente.toInt() > 0) { SI HAY MENSAJES
                            Badge(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text(text = pendiente)
                            }
                        }*/
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.Chat.route)
                    }

                )

            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(AzulClaro).padding(0.dp,0.dp,0.dp,55.dp)
        ) {
            ChatScreen()
        }
    }
}

@Composable
fun ChatScreen() {
    var messages by remember { mutableStateOf(mutableStateListOf<Message>()) }
    var newMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
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
                            text = "Usuario",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = message.content,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = message.time,
                            fontSize = 12.sp,
                            color = Color.Gray
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
                    backgroundColor = Color.Transparent),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            IconButton(
                onClick = {
                    messages.add(
                        Message(
                            content = newMessage,
                            time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                        )
                    )
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

data class Message(
    val content: String,
    val time: String
)


