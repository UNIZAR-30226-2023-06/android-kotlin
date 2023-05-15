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
    Se crea un lobby en la base de datos
    @return: id del lobby creado
 */

fun createLoby(): Boolean {
    var lobby_id = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/create-lobby")
        .post(body)
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

            //println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if(status == "Lobby created"){
                println("SE HA CREADO EL LOBBY CORRECTAMENTE")
                Globals.lobbyId = json.getString("lobby_id")
                lobby_id = true
            } else {
                println("ERROR AL CREAR EL LOBBY")
            }
            latch.countDown()
        }
    })
    latch.await()
    return lobby_id
}

/*
    Eliminar un lobby de la base de datos
    @param: id del lobby
 */
fun deleteLobby(lobby_id: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/delete-lobby?lobby_id=$lobby_id")
        .delete(body)
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

            //println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if(status == "Lobby deleted"){
                println("SE HA ELIMINADO EL LOBBY CORRECTAMENTE")
                result = true
            } else if (status == "Lobby not found"){
                println("YA SE HABIA BORRADO EL LOBBY")
                result = true
            } else{
                println("ERROR AL ELIMINAR EL LOBBY")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Se une un jugador a un lobby
    @param: id del lobby
    @param: token del jugador
    @return: numero de jugadores en el lobby
 */

fun joinLobby(lobby_id: Int): Boolean {
    //var num_players = 0
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    println("LOBBY ID: $lobby_id")
    val request = Request.Builder()
        .url("$ipBackend/join-lobby?lobby_id=$lobby_id")
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

            if (status == "Lobby joined") {
                println("SE HA UNIDO EL JUGADOR CORRECTAMENTE")
                //num_players = json.getInt("num_players")
                result = true
            } else {
                println("ERROR AL UNIR EL JUGADOR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

/*
    Pone a un jugador buscando partida, y cuando hay 4 jugadores, crea una partida
    @param: token del jugador
    @return: true si se esta buscando partida, false si ya esta en un lobby y no busca
 */
fun searchLobby( token: String ): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/search-lobby")
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

            //println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")

            if (status == "Searching for a lobby") {
                println("BUSCANDO PARTIDA ...")
                result = true
            } else {
                println("ERROR AL BUSCAR PARTIDA/ YA ESTA EN UN LOBBY")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}

/*
    Deja de buscar partida
    @param: token del jugador
    @return: true si ha ido bien, false en caso contrario
 */
fun stopSearchingLobby(token: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("$ipBackend/stop-searching-lobby")
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

            if (status == "Stopped searching for a lobby") {
                println("SE DEJO DE BUSCAR LOBBY")
                result = true
            } else {
                println("NO SE ESTABA BUSCANDO LOBBY O HA OCURRIDO UN ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}


/*
    Devuelve el lobby en el que esta un jugador
    @param: token del jugador
    @return: true si se puede comenzar la partida, es decir, hay 4 jugadores en ese lobby, false si no están los 4
 */
fun getLobbyFromPlayer( token: String): Boolean {
        var result = false
        val latch = CountDownLatch(1)

        val request = Request.Builder()
            .url("$ipBackend/get-lobby-from-player")
            .get()
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
                val status = try {
                    json.getString("detail")
                }catch (e: JSONException) {
                    "estan en un lobby"
                }
                println("status $status")
                if (status == "estan en un lobby") {
                    println("SE HA CREADO EL LOBBY CON TODOS LOS JUGADORES")
                    if (json.getInt("current_Players") == 4){
                        result = true
                        //TODO: AQUI SE DEBERIAN COGER TODOS LOS DATOS DEL TABLERO Y DE LOS JUGADORES
                        Globals.lobbyId = json.getString("id")
                        Globals.juego = json
                        //println("JSON DEL GAME STATUS ${Globals.juego}")
                    }
                } else {
                    println("PLAYER YA ESTÁ EN UN LOBBY O ERROR AL BUSCARLO")
                }
                latch.countDown()
            }
        })
    latch.await()
    return result

}

fun getLobby( token: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/get-lobby-from-player")
        .get()
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
            val status = try {
                json.getString("detail")
            }catch (e: JSONException) {
                "estan en un lobby"
            }
            println("status $status")
            if (status == "estan en un lobby") {
                println("SE CREO EL LOBBY")
                Globals.lobbyId = json.getString("id")
                Globals.juego = json
                result = true
            } else {
                println("PLAYER YA ESTÁ EN UN LOBBY O ERROR AL BUSCARLO")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}

/*
    Devuelve si hay jugadores en el lobby listos para jugar
    @param: token del jugador
    @return: true si hay 4 jugadores en el lobby listos para jugar, false si no están los 4
 */
fun numOfReadyPlayers( token: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/get-lobby-from-player")
        .get()
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
            val status = try {
                json.getString("detail")
            }catch (e: JSONException) {
                "estan en un lobby"
            }
            println("status $status")
            if (status == "estan en un lobby") {
                println("SE HA CREADO EL LOBBY CON TODOS LOS JUGADORES")
                val game = json.getJSONObject("game")
                val jugadores = game.getJSONArray("jugadores")

                var num_activos = 0

                for (i in 0 until jugadores.length()) {
                    val jugador = jugadores.getJSONObject(i)
                    if (jugador.getBoolean("esta_preparado")) {
                        num_activos++
                    }
                }
                println("Número de jugadores activos: $num_activos")
                if (num_activos == 4){
                    // LOS 4 JUGADORES ESTAN LISTOS PARA JUGAR
                    result = true

                }
            } else {
                println("PLAYER YA ESTÁ EN UN LOBBY O ERROR AL BUSCARLO")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}

/*
    Devuelve si hay jugadores en el lobby listos para jugar
    @param: token del jugador
    @return: true si hay 4 jugadores en el lobby listos para jugar, false si no están los 4
 */
fun numReadyPlayers( token: String): Int {
    var result = 0
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/get-lobby-from-player")
        .get()
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
            val status = try {
                json.getString("detail")
            }catch (e: JSONException) {
                "estan en un lobby"
            }
            println("status $status")
            if (status == "estan en un lobby") {
                println("SE HA CREADO EL LOBBY CON TODOS LOS JUGADORES")
                val game = json.getJSONObject("game")
                val jugadores = game.getJSONArray("jugadores")

                var num_activos = 0

                for (i in 0 until jugadores.length()) {
                    val jugador = jugadores.getJSONObject(i)
                    if (jugador.getBoolean("esta_preparado")) {
                        num_activos++
                    }
                }
                println("Número de jugadores activos: $num_activos")
                result = num_activos
            } else {
                println("PLAYER YA ESTÁ EN UN LOBBY O ERROR AL BUSCARLO")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}

/*
    Devuelve si existe el lobby
    @param: id del lobby
    @param: token del jugador
    @return: true si existe el lobby, false si no
 */
fun getLobbyFromId(lobby_id: String): Boolean {
    var result = false
    val latch = CountDownLatch(1)

    val request = Request.Builder()
        .url("$ipBackend/get-lobby-from-id?lobby_id=$lobby_id")
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
                json.getString("detail")
            }catch (e: JSONException) {
                "EXISTE EL LOBBY"
            }
            //println("status $status")
            if (status == "EXISTE EL LOBBY") {
                    result = true
            } else {
                println("PLAYER YA ESTÁ EN UN LOBBY O ERROR AL BUSCARLO")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result

}



