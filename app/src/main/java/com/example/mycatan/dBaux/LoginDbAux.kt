package com.example.mycatan.dBaux

import android.provider.Settings.Global
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

fun enviarLogin(username: String, password: String):Boolean {

    var result = false
    val latch = CountDownLatch(1) //esto que es
    println("username: $username")
    println("password: $password")

    // Inicie un subproceso en segundo plano

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        "&grant_type=password&username=$username&password=$password&scope=&client_id=client&client_secret=secret"
    )
    val request = Request.Builder()

        .url("http://$ipBackend:8000/login")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores

            println("ERROR al conectar con backend")
            latch.countDown()
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")
            if(status == "Incorrect email"){
                //TODO: gestionar email incorrecto
                println("EMAIL INCORRECTO")
            }else if (status == "Incorrect password"){
                //TODO: gestional contraseña incorrecta
                println("CONTRASEÑA INCORRECTA")
            }else if (status == "Logged in successfully"){

                result=true

                Globals.Token = json.getString("access_token")
                println("TOKEN DE ACCESO ${Globals.Token}")
                val user = JWT.decode(Globals.Token)
                var tempId = user.getClaim("id").asInt()
                Globals.Id = tempId.toString()
                Globals.Email = user.getClaim("email").asString()
                Globals.Username = user.getClaim("username").asString()

                //SOLICITAMOS INFO DEL USUARIO
                addCoins(50)
                //subCoins(100)
                getUserData(Globals.Id);

                //forthemoment
                Globals.fotosCompradas = BooleanArray(9)
                Globals.fotosCompradas.fill(false)
                //TODO: terminar esto
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun getUserData( userId: String ){
    println("userID: $userId")

    val request = Request.Builder()
        .url("http://$ipBackend:8000/get-user-from-id/$userId")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    //val response = client.newCall(request).execute()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // manejo de errores
            println(call)
            println("ERROR al conectar con backend")
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("detail")
            } catch (e: JSONException) {
                null
            }

            if(status == "User not found"){
                println("USUARIO NO  ENCONTRADO")
            } else {
                Globals.Coins = json.getString("coins")

                var temp = json.getString("profile_picture")
                if (temp == "default")
                    Globals.Personaje =temp
                else
                    Globals.Personaje =temp.substring(4)

                temp = json.getString("selected_grid_skin")
                if (temp == "default")
                    Globals.Mapa =temp
                else
                    Globals.Mapa = temp.substring(4)

                temp = json.getString("selected_pieces_skin")
                if (temp == "default")
                    Globals.Piezas =temp
                else
                    Globals.Piezas = temp.substring(4)
            }
        }
    })
}