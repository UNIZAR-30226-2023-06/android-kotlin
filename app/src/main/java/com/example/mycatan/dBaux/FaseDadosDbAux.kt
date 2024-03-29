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
        .url("$ipBackend/game_phases/resource_production?lobby_id=${Globals.lobbyId}")
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
            var error = false
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("die1")
            } catch (e: JSONException) {
                error  = true
            }

            if (!error) {
                println("Dados lanzados correctamente")
                Globals.dado1.value = json.getString("die1")
                Globals.dado2.value = json.getString("die2")
                Globals.newDados.value = true
                println("NEW DADOOOOOOOOOOOOOS")
            } else {
                println("No se han podido lanzar los dados")

            }
            latch.countDown()
        }
    })
    latch.await()
}

fun moverLadron(  hexagono: Int,stolenPlayer: Int, reOpen: () -> Unit) : Boolean{
    val latch = CountDownLatch(1)
    var result = false

    println(stolenPlayer)
    println(hexagono)

    val request = Request.Builder()
        .url("$ipBackend/game_phases/move_thief?lobby_id=${Globals.lobbyId}&stolen_player_id=$stolenPlayer&new_thief_position_tile_coord=$hexagono")
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
            var error1 = false
            var error2 = false
            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("message")
            } catch (e: JSONException) {
                error1  = true
            }
            val detail = try {
                json.getString("detail")
            } catch (e: JSONException) {
                error2  = true
            }

            if (!error1) {
                if(status == "Thief moved successfully"){
                    println("Ladron movido")
                    result = true
                }
                else{
                    println("No se han podido mover el ladron")
                    reOpen()
                }

            } else if(!error2) {
                if(detail == "Error: No hay ningún edificio del jugador robado en los nodos adyacentes al ladrón"){
                    println("Ladron movido pero no robando a nadie")
                    result = true
                } else {
                    println("No se han podido mover el ladron ")
                    reOpen()
                }

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}
