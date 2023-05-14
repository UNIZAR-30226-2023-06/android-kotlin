package com.example.mycatan.dBaux

import android.R.integer
import android.provider.Settings.Global
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.auth0.jwt.JWT
import com.example.mycatan.others.Globals
import com.example.mycatan.others.Partida
import com.example.mycatan.others.ipBackend
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.CountDownLatch


/*
    Envia un mensaje al chat
    @param: token del jugador
    @return: true si se ha enviado, false si no
 */
fun send_chat_message(message: String){
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/send_chat_message?message=$message")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer ${Globals.Token}")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("ERROR al conectar con backend")
            latch.countDown()
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Message sent") {
                println("Mensaje enviado")
            } else {
                println("Mensaje no enviado")

            }
            latch.countDown()
        }
    })
    latch.await()
}

data class Message(val id: String, val username: String, val message: String)

fun get_chat_messages(): List<Message>{
    var result = mutableListOf<Message>()
    val jsonArray = Globals.gameState.getJSONArray("chat")
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val id = jsonObject.getString("id")
        val username = jsonObject.getString("username")
        val message = jsonObject.getString("message")
        result.add(Message(id, username, message))
    }

    return result
}

/*fun get_chat_messages(): List<Message>{
    val latch = CountDownLatch(1)
    var result = mutableListOf<Message>()

    val request = Request.Builder()
        .url("$ipBackend/get_chat_messages")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer ${Globals.Token}")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("ERROR al conectar con backend")
            latch.countDown()
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            var error = false
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("messages")
            } catch (e: JSONException) {
                error  = true
            }

            if(!error){
                println("Hemos cogido correctamente los mensajes")
                val jsonArray = JSONArray(status)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val id = jsonObject.getString("id")
                    val username = jsonObject.getString("username")
                    val message = jsonObject.getString("message")
                    result.add(Message(id, username, message))
                }

            }
            else{
                println("Error al coger los mensajes")
            }

            latch.countDown()
        }
    })
    latch.await()
    return result
}*/
