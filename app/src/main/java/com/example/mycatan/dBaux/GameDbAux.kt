package com.example.mycatan.dBaux

import android.R.integer
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


/*
    Pone a un jugador buscando partida, y cuando hay 4 jugadores, crea una partida
    @param: token del jugador
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun setPlayerReady( token: String ): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/set-player-ready")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $token")
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

            if (status == "Player ready and Game started") {
                println("JUGADOR LE HA DADO A LISTO")
                result = true
            } else {
                println("PLAYER NOT READY")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}
