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
    Utilizar carta de caballero
    @param: stolen_player_id  id del jugador al que se quiere robar
    @param: lobby_id id del lobby
    @param: new_thief_position_tile_coord coordenadas de la nueva posicion del ladron
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun use_knight_card(stolen_player_id: String, new_thief_position_tile_coord: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/use_knight_card?lobby_id=${Globals.lobbyId}&stolen_player_id=$stolen_player_id&new_thief_position_tile_coord=$new_thief_position_tile_coord")
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
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")


            if (status == "Knight card used successfully") {
                println("Se ha usado la carta de caballero correctamente")
                result = true
            } else {
                println("ERROR al usar la carta de caballero")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Utilizar carta de invencion
    @param: resource1  primer recurso que se quiere
    @param: lobby_id id del lobby
    @param: resource2 segundo recurso que se quiere
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun use_invention_card(resource1: String, resource2: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/use_invention_card?lobby_id=${Globals.lobbyId}&resource1=$resource1&resource2=$resource2")
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
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Invention card used successfully") {
                println("Se ha usado la carta de invencion correctamente")
                result = true
            } else {
                println("ERROR al usar la carta de invencion")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Utilizar carta de camino
    @param: lobby_id id del lobby
    @param: coord coordenada de la carretera
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun use_road_progress_card(coord: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/use_road_progress_card?lobby_id=${Globals.lobbyId}&coord=$coord")
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
            val status = json.getString("detail")

            if (status == "Road built successfully") {
                println("Se ha usado la carta de carretera correctamente")
                result = true
            } else {
                println("ERROR al usar la carta de carretera")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Utilizar carta de progreso de monopolio
    @param: resource1  primer recurso que se quiere
    @param: lobby_id id del lobby
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun use_monopoly_progress_card(resource1: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/use_monopoly_progress_card?lobby_id=${Globals.lobbyId}&resource1=$resource1")
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
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Monopoly card used successfully") {
                println("Se ha usado la carta de monopolio correctamente")
                result = true
            } else {
                println("ERROR al usar la carta de monopolio")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Utilizar carta de progreso de punto de victoria
    @param: lobby_id id del lobby
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun use_victory_point_progress_card(): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/use_victory_point_progress_card?lobby_id=${Globals.lobbyId}")
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
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Victory point card used successfully") {
                println("Se ha usado la carta de punto de victoria correctamente")
                result = true
            } else {
                println("ERROR al usar la carta de punto de victoria")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Comparar cartas de desarrollo
    @param: lobby_id id del lobby
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun buy_development_card(): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/buy_development_card?lobby_id=${Globals.lobbyId}")
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
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Development card bought successfully") {
                println("Carta de desarrollo comprada correctamente")
                result = true
            } else {
                println("ERROR al comprar carta de desarrollo")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Consturir/comprar carretera
    @param: coord coordenadas de la carretera
    @param: lobby_id id del lobby
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun buy_and_build_road(coord: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/buy_and_build_road?lobby_id=${Globals.lobbyId}&coord=$coord")
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
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Road bought and built successfully") {
                println("Carretera comprada y construida correctamente")
                result = true
            } else {
                println("ERROR al comprar y construir carretera")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Construir/comprar aldea
    @param: coord coordenadas de la aldea
    @param: lobby_id id del lobby
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun buy_and_build_village(coord: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/buy_and_build_village?lobby_id=${Globals.lobbyId}&coord=$coord")
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
            val status = json.getString("detail")

            if (status == "Village bought and built successfully") {
                println("Se ha comprado y construido la aldea correctamente")
                result = true
            } else {
                println("ERROR al comprar y construir aldea")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Construir/comprar ciudad
    @param: lobby_id id del lobby
    @param: coord de la ciudad
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun buy_and_build_city(coord: String): Boolean {
    var result = false;
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("http://$ipBackend:8000/game_phases/buy_and_build_city?lobby_id=${Globals.lobbyId}&coord=$coord")
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
            val status = json.getString("detail")

            if (status == "City bought and built successfully") {
                println("Se ha comprado y construido la ciudad correctamente")
                result = true
            } else {
                println("ERROR al comprar y construir ciudad")

            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}
