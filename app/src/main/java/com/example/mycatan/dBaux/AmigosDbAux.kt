package com.example.mycatan.dBaux

import android.provider.Settings.Global
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.auth0.jwt.JWT
import com.example.mycatan.others.Globals
import com.example.mycatan.others.ipBackend
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

// MIRAMOS QUE USUARIOS SON NUESTROS AMIGOS
fun getAmigosTodos() {
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("http://$ipBackend:8000/get_friends")
        .put(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores
            println(call)
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            var idSolicitud = ""
            val respuesta = response.body?.string().toString()
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("friends")
            val jsonArray = JSONArray(status)

            if (jsonArray.length() == 0){
                println("NO TIENES AMIGOS")
            } else{
                val jsonObject = jsonArray.getJSONObject(0)
                idSolicitud = jsonObject.getString("friend_id")
            }
        }
    })
}

// MIRAMOS LAS SOLICITUDES DE AMISTAD QUE TENEMOS PENDIENTES
fun getAmigosPendiente(): String{
    var idSolicitud = ""
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("http://$ipBackend:8000/get_friend_requests")
        .put(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores
            println(call)
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("friend_requests")
            val jsonArray = JSONArray(status)

            if (jsonArray.length() == 0){
                println("NO TIENES PETICIONES DE AMISTAD")
            } else{
                val jsonObject = jsonArray.getJSONObject(0)
                idSolicitud = jsonObject.getString("requester_id")
            }
        }
    })
    return idSolicitud
}

// ENVIAR A UN USUARIO UNA PETICIÓN DE AMISTAD INDICANDO SU ID DE USUARIO
fun postSendRequestFriend( userId: String ): Boolean{
    var result = false
    println("userID: $userId")
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/send_friend_request?friend_id=$userId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores
            println(call)
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()

            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")


            if(status == "User not found"){
                println("NO EXISTE USUARIO CON ESE ID, ENVIO PETICION FALLIDO")
                //Toast.makeText(LocalContext.current, "ERROR la petición no ha sido enviada", Toast.LENGTH_SHORT).show()
                // IDEA, devolver TRUE O FALSE y mostrar el TOAST desde el otro lado.
            } else if(status == "Friend request sent to user with id $userId"){
                println("ENVIO PETICION AMISTAD CORRECTO")
                result = true
            } else {
                println("PETICION YA ENVIADA O ERROR")
            }
        }
    })
    return result
}

// ACEPTAR UNA PETICIÓN DE AMISTAD
fun postAcceptRequestFriend( requestId: String ){
    println("userID: $requestId")
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/accept_friend_request?requester_id=$requestId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()

            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("message")
            }catch (e: JSONException) {
                json.getString("detail")
            }

            if(status == "Friend request accepted from user with id $requestId"){
                println("PETICION ACEPTADA CORRECTAMENTE")

            } else if (status == "User not found"){
                println("NO EXISTE ESTA PETICION")

            }
        }
    })
}

// RECHAZAR UNA PETICIÓN DE AMISTAD
fun postRejectRequestFriend( requestId: String ){
    println("userID: $requestId")
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/reject_friend_request?requester_id=$requestId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores
            println(call)
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()

            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")


            if(status == "Not authenticated"){
                println("NO EXISTE USUARIO CON ESE ID, ENVIO PETICION FALLIDO")

            } else if ( status == "Friend request sent to user with id 5554") {
                println("ENVIO PETICION AMISTAD CORRECTO")

            }
        }
    })
}

