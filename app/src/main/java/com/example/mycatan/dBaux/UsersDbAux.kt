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

fun getUserID(id: String): String {
    var name = ""
    val latch = CountDownLatch(1)
    val request = Request.Builder()

        .url("http://$ipBackend:8000/get-user-from-id/$id")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    val client = OkHttpClient()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(call)
            println("ERROR al conectar con backend")
            latch.countDown()
        }

        override fun onResponse(call: Call, response: Response) {
            val respuesta = response.body?.string().toString()
            //transform the string to json object
            val json = JSONObject(respuesta)
            //get the string from the response
            val status = try {
                json.getString("detail")
            } catch (e: JSONException) {
                json.getString("username")
            }
            println(status)
            if(status == "User not found"){
                println("NO EXISTE USUARIO CON ESE ID")
            }else {
                println("ok")
                name = json.getString("username")
            }
            latch.countDown()
        }
    })
    latch.await()
    println("$name NAME")
    return name
}
