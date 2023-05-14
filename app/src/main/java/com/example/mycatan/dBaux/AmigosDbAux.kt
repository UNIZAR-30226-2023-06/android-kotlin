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
import java.util.concurrent.CountDownLatch

data class Persona(val id: String, val name: String, val photo: String)
// MIRAMOS QUE USUARIOS SON NUESTROS AMIGOS
fun getAmigosTodos(token: String): List<Persona>{
    val result = mutableListOf<Persona>()
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("$ipBackend/get_friends")
        .put(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("ERROR al conectar con backend")
            latch.countDown()
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
                // AÑADIMOS A LA LISTA RESULT, EL ID DE LOS USUARIOS AMIGOS
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    result.add(
                        Persona(
                            jsonObject.getString("friend_id"),
                            jsonObject.getString("friend_name"),
                            jsonObject.getString("profile_picture")
                        )
                    )
                }
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

// MIRAMOS LAS SOLICITUDES DE AMISTAD QUE TENEMOS PENDIENTES
fun getAmigosPendiente(token: String): List<String>{
    val result = mutableListOf<String>()
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("$ipBackend/get_friend_requests")
        .put(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("ERROR al conectar con backend")
            latch.countDown()
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
                // AÑADIMOS A LA LISTA RESULT, EL ID DE LOS USUARIOS QUE TE SOLICITAN SEGUIR
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    result.add(jsonObject.getString("requester_id"))
                }
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

// MIRAMOS LAS SOLICITUDES DE AMISTAD QUE TENEMOS PENDIENTES
fun getNumAmigosPendiente(token: String): String{
    var result = ""
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("$ipBackend/get_friend_requests")
        .put(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("ERROR al conectar con backend")
            latch.countDown()
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("number_of_requests")

            if (status.toInt() == 0){
                println("NO TIENES PETICIONES DE AMISTAD")
                result = "0"
            } else{
                result = status
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

// ENVIAR A UN USUARIO UNA PETICIÓN DE AMISTAD INDICANDO SU ID DE USUARIO
fun postSendRequestFriend( userId: String, token: String ): Boolean{
    var result = false
    val latch = CountDownLatch(1)
    println("userID: $userId")
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/send_friend_request?friend_id=$userId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
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
            val status = try {
                json.getString("message")
            }catch (e: JSONException) {
                json.getString("detail")
            }

            if(status == "User not found"){
                println("NO EXISTE USUARIO CON ESE ID, ENVIO PETICION FALLIDO")
                //Toast.makeText(LocalContext.current, "ERROR la petición no ha sido enviada", Toast.LENGTH_SHORT).show()
                // IDEA, devolver TRUE O FALSE y mostrar el TOAST desde el otro lado.
            } else if(status == "Friend request sent"){
                println("ENVIO PETICION AMISTAD CORRECTO")
                result = true
            } else {
                println("PETICION YA ENVIADA O ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

// ACEPTAR UNA PETICIÓN DE AMISTAD
fun postAcceptRequestFriend( requestId: String, token: String ): Boolean{
    var result = false
    val latch = CountDownLatch(1)
    println("userID: $requestId")
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/accept_friend_request?requester_id=$requestId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
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
            val status = try {
                json.getString("message")
            }catch (e: JSONException) {
                json.getString("detail")
            }

            if(status == "Friend request accepted"){
                println("PETICION ACEPTADA CORRECTAMENTE")
                result = true
            } else if (status == "User not found"){
                println("NO EXISTE ESTA PETICION")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

// RECHAZAR UNA PETICIÓN DE AMISTAD
fun postRejectRequestFriend( requestId: String, token: String ): Boolean{
    println("userID: $requestId")
    var result = false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/reject_friend_request?requester_id=$requestId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
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


            if(status == "User not found"){
                println("NO SE HA PODIDO RECHAZAR LA PETICION")

            } else if ( status == "Friend request rejected") {
                println("SE HA RECHAZADO LA PETICIÓN DE AMISTAD")
                result = true
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

// ENVIAR A UN USUARIO UNA PETICIÓN DE AMISTAD INDICANDO SU ID DE USUARIO
fun postdeleteFriend( userId: String, token: String ): Boolean{
    var result = false
    val latch = CountDownLatch(1)
    println("userID: $userId")
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/delete_friend?friend_id=$userId")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
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

            if(status == "Friend deleted"){
                println("USUARIO BORRADO")
                result = true
            } else if(status == "User not found"){
                println("EL USUARIO NO SE HA PODIDO BORRAR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

