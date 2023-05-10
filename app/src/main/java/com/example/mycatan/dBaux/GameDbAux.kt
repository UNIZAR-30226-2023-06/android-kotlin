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

/*
    Da el estado de la partida del jugador
    @param: token del jugador
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun getGameState( idlobby: String ): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/get_game_state?lobby_id=$idlobby")
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
                json.getString("player_0")
            } catch (e: JSONException) {
                "error"
            }

            if (status == "error") {
                println("Error al coger el status del juego")
            } else {
                //println("Game started")
                result = true
                Globals.gameState = json
               // println("JSON DEL GAME STATUS ${Globals.gameState}")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}

/*
    Da el estado de la partida del jugador
    @param: token del jugador
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun firstPhaseBuildVillage( nodo: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/build-village?node=$nodo")
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
            
            if (status == "Village built") {
                println("Poblado construido")
            } else {
                println("Error, no se ha podido construir el poblado ")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun firstPhaseBuildRoad( edge: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/build-road?edge=$edge")
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

            if (status == "Road built") {
                println("Poblado construido")
            } else {
                println("Error, no se ha podido construir el poblado ")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}
//TODO: analogo para las aristas






fun getlegalNodesINI( color: String ): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/get-legal-building-nodes?lobby_id=${Globals.lobbyId}&color=$color")
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

            println(respuesta)
            //transform the string to json array
            val jsonArray = JSONArray(respuesta)

            //Se reemplaza la lista de nodos legales con la actual
            Partida.nodosLegales.clear()
            for (i in 0 until jsonArray.length()) {
                val value = jsonArray.getInt(i)
                //println(value)
                Partida.nodosLegales.add(value.toString())
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun getlegalEdges( color: String ): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/get-legal-building-edges?lobby_id=${Globals.lobbyId}&color=$color")
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

            println(respuesta)
            //transform the string to json array
            val jsonArray = JSONArray(respuesta)

            //Se reemplaza la lista de nodos legales con la actual
            Partida.edgesLegales.clear()
            for (i in 0 until jsonArray.length()) {
                val value = jsonArray.getInt(i)
                //println(value)
                Partida.edgesLegales.add(value.toString())
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}

