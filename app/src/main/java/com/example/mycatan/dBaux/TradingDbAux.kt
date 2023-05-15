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
    Tira los dados y además actualiza los recursos de los jugadores
    @param: token del jugador
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun trade_with_bank(resource_type: String, amount: String, requested_type: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/game_phases/trade_with_bank?lobby_id=${Globals.lobbyId}&resource_type=$requested_type&amount=$amount&requested_type=$resource_type")
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
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("message")
            } catch (e: JSONException) {
                error  = true
            }

            if (!error && status == "Trade with bank successful") {
                println("Trade with bank successful")
                result = true
            } else {
                println("No se han podido intercambiar")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun trade_with_player(playerid : String, madera1 : Int, clay1: Int, oveja1: Int, trigo1: Int, roca1: Int, madera2 : Int, clay2: Int, oveja2: Int, trigo2: Int, roca2: Int): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/game_phases/propose_trade?lobby_id=${Globals.lobbyId}&player2_id=$playerid&wood_amount_p1=$madera1&clay_amount_p1=$clay1&sheep_amount_p1=$oveja1&wheat_amount_p1=$trigo1&stone_amount_p1=$roca1&wood_amount_p2=$madera2&clay_amount_p2=$clay2&sheep_amount_p2=$oveja2&wheat_amount_p2=$trigo2&stone_amount_p2=$roca2")
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
            var error = false
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("detail")
            } catch (e: JSONException) {
                error  = true
            }

            if (!error && status == "trade proposed successfully") {
                println("Trade purpose successfully")
                result = true
            } else {
                println("No se han podido proponer el intercambio ")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun accept_trade(player2: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/game_phases/accept_trade?lobby_id=${Globals.lobbyId}&player2_id=$player2")
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
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("detail")
            } catch (e: JSONException) {
                error  = true
            }

            if (!error && status == "trade accepted successfully") {
                println("Trade aceptado ")
                result = true
            } else {
                println("No se han podido aceptar el trade")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun reject_trade(player2: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/game_phases/reject_trade?lobby_id=${Globals.lobbyId}&player2_id=$player2")
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
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("detail")
            } catch (e: JSONException) {
                error  = true
            }

            if (!error && status == "trade rejected successfully") {
                println("Trade rechazado ")
                result = true
            } else {
                println("No se han podido aceptar el trade")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}