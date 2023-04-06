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

//no funca
fun buyMapa(skinName: String): Boolean {
    var skin = "skin$skinName"
    println("SKIN: $skin")
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/buy-board-skin?board_skin_name=$skin")
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
            //la respuesta es not found por algun motivo
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")
            println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if(status == "User already has this board skin"){
                println("USER ALREADY HAS THIS BOARD SKIN")
            } else if(status == "Not enough money"){
                println("NOT ENOUGH MONEY")
            } else if (status=="Board skin bought successfully"){
                println("Board skin bought successfully")
                result= true
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun addCoins(coins: Int): Boolean {

    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/add-coins?amount=$coins")
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
            val actualCoins = json.getInt("coins")
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Not authenticated") {
                println("USER NOT AUTHENTICATED")
            } else if (status== "Coins added successfully"){
                println("Coins added successfully")
                Globals.Coins=actualCoins.toString()
                result= true
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun subCoins(coins: Int): Boolean {

    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/remove-coins?amount=$coins")
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
            val actualCoins = json.getInt("coins")
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Not authenticated") {
                println("USER NOT AUTHENTICATED")
            } else if(status == "Not enough coins") {
                println("NOT ENOUGH COINS")
            } else if (status== "Coins removed successfully"){
                println("Coins added successfully")
                Globals.Coins=actualCoins.toString()
                result= true
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}