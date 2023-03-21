package com.example.mycatan.dBaux

import android.provider.Settings.Global
import com.auth0.jwt.JWT
import com.example.mycatan.others.Globals
import com.example.mycatan.others.ipBackend
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

fun enviarLogin(username: String, password: String, onErrorClick: (err: Boolean) -> Unit) {
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
            println(call)
            println("ERROR al conectar con backend")
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
                onErrorClick(true)
            }else if (status == "Incorrect password"){
                //TODO: gestional contraseña incorrecta
                println("CONTRASEÑA INCORRECTA")
                onErrorClick(true)
            }else if (status == "Logged in successfully"){
                onErrorClick(false)

                val accessToken = json.getString("access_token")
                println("TOKEN DE ACCESO $accessToken")
                val user = JWT.decode(accessToken)
                var tempId = user.getClaim("id").asInt()
                Globals.Id = tempId.toString()
                Globals.Email = user.getClaim("email").asString()
                Globals.Username = user.getClaim("username").asString()

                //de momento xq no esta en el backend
                //getUserData(Globals.Id);
                Globals.fotosCompradas = BooleanArray(9)
                Globals.fotosCompradas.fill(false)
                Globals.Coins = "30"
                //TODO: terminar esto
            }
        }
    })
}

/*fun getUserData( userId: String ){
    println("userID: $userId")


    // Inicie un subproceso en segundo plano

    /*val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )*/

    val request = Request.Builder()
        .url("http://$ipBackend:8000/get-user-from-id?user-id=$userId")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    println(request)

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
            val status = json.getString("detail")
            if(status == "User not found"){
                //TODO: gestionar email incorrecto
                println("USUARIO NO  ENCONTRADO")
                onErrorClick(true)
            } else if (status == "Logged in successfully"){
                onErrorClick(false)

                val accessToken = json.getString("access_token")
                println("TOKEN DE ACCESO $accessToken")
                val user = JWT.decode(accessToken)
                var tempId = user.getClaim("id").asInt()
                Globals.Id = tempId.toString()
                Globals.Email = user.getClaim("email").asString()
                Globals.Username = user.getClaim("username").asString()

                //de momento xq no esta en el backend
                getUserData(Globals.Id);
                Globals.fotosCompradas = BooleanArray(9)
                Globals.fotosCompradas.fill(false)
                Globals.Coins = "20"
                //TODO: terminar esto
            }
        }
    })
}*/