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
