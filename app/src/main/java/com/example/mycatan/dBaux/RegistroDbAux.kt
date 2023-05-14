package com.example.mycatan.dBaux

import com.auth0.jwt.JWT
import com.example.mycatan.others.Globals
import com.example.mycatan.others.ipBackend
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

fun enviarRegistro(email: String, password: String, name:String, onErrorClick: (err: Boolean) -> Unit) {
    println("correo: $email")
    println("username: $name")
    println("password: $password")

    // Inicie un subproceso en segundo plano

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )
    val request = Request.Builder()
        .url("$ipBackend/register?name=$name&email=$email&password=$password&coins=0&selected_grid_skin=default&selected_piece_skin=default&saved_music=default&elo=500")
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
            var invalidEmail = ""
            val respuesta = response.body?.string().toString()

            println(respuesta)
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = json.getString("detail")
            val jsonArray = try {
                JSONArray(status)
            } catch (e: JSONException) {
                null
            }
            if(jsonArray != null){
                val jsonObject = jsonArray.getJSONObject(0)
                invalidEmail = jsonObject.getString("msg")
            }

            if(invalidEmail == "value is not a valid email address"){     // Correo invalido
                //TODO: gestionar email incorrecto
                println("EMAIL INCORRECTO")
                onErrorClick(true)
            }else if (status == "Email already exists"){            // Ya existe el email
                //TODO: gestional email ya existente
                println("EMAIL YA EXISTE")
                onErrorClick(true)
            }else if (status == "User created"){                    // Usuario creado
                onErrorClick(false)

                val accessToken = json.getString("access_token")
                println("TOKEN DE ACCESO $accessToken")
                val user = JWT.decode(accessToken)
                var tempId = user.getClaim("id").asInt()
                Globals.Id = tempId.toString()
                Globals.Email = user.getClaim("email").asString()
                Globals.Username = user.getClaim("username").asString()
                println(" ID: ${Globals.Id}, EMAIL: ${Globals.Email}, USERNAME: ${Globals.Username}")
            }
        }
    })

}
