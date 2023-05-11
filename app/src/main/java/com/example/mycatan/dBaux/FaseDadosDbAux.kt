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
fun tirarDados(){
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game-phases/resource_production")
        .get()
        .addHeader("accept", "application/json")
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
            val status = try {
                json.getString("die1")
            } catch (e: JSONException) {
                "Error al lanzar dados"
            }

            if (status == "Error al lanzar dados") {
                println("No se han podido lanzar los dados")
            } else {
                println("Dados lanzados correctamente")
                Globals.dado1 = json.getString("die1")
                Globals.dado2 = json.getString("die2")
            }
            latch.countDown()
        }
    })
    latch.await()
}
