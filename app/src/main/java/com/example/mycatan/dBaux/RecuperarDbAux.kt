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

fun recover_password(correo: String):Boolean {

    var result = false
    val latch = CountDownLatch(1)

    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()

        .url("$ipBackend/recover-password?email=$correo")
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
            if(status == "Password changed successfully"){
                println("CORREO ENVIADO")
                result=true
            }else {
                println("CORREO NO ENVIADO")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}
