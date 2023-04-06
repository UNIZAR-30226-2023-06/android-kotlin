package com.example.mycatan.dBaux

import android.media.session.MediaSession.Token
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


// MIRAMOS QUE USUARIOS SON NUESTROS AMIGOS
fun changeUsername(newName: String): Boolean {
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/change-username?new_username=$newName")
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
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if (status=="Username changed successfully"){
                println("Username changed successfully")
                result= true
                Globals.Username=newName
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

