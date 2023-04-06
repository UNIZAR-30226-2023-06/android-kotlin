package com.example.mycatan.dBaux

import android.media.session.MediaSession.Token
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


fun changeUsername(newName: String): Boolean {
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/change-username?new_username=$newName")
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
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if (status=="Username changed successfully"){
                println("Username changed successfully")
                result= true
                Globals.Username=newName
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun changePassword(newPsswd: String): Boolean {
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/change-password?new_password=$newPsswd")
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
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if (status=="Password changed successfully"){
                println("Password changed successfully")
                result= true
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}


//profile pic y pieces son iguales
fun changeGridSkin(newSkin: String): Boolean {
    var skin = "skin$newSkin"
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/change-grid-skin?new_grid_skin=$skin")
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
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Grid skin not found"){
                println("GRID SKIN NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if (status == "User does not own this skin"){
                println("User does not own this skin")
            } else if (status=="Grid skin changed successfully"){
                println("Grid skin changed successfully")
                result= true
                Globals.Mapa=newSkin
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun changeProfilePicture(newSkin: String): Boolean {
    var skin = "skin$newSkin"
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/change-profile-picture?new_profile_picture=$skin")
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
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Profile picture not found"){
                println("PROFILE PICTURE NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if (status == "User does not own this profile picture"){
                println("User does not own this skin")
            } else if (status=="Profile picture changed successfully"){
                println("Profile picture skin changed successfully")
                result= true
                Globals.Personaje=newSkin
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}

fun changePiecesSkin(newSkin: String): Boolean {
    var skin = "skin$newSkin"
    var result= false
    val latch = CountDownLatch(1)
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val body = RequestBody.create(
        mediaType,
        ""
    )

    val request = Request.Builder()
        .url("http://$ipBackend:8000/change-pieces-skin?new_pieces_skin=$skin")
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
            //println("STATUS: $status")

            if(status == "User not found"){
                println("USER NOT FOUND")
            } else if(status == "Pieces skin not found"){
                println("PIECES SKIN NOT FOUND")
            } else if(status == "Not authenticated"){
                println("USER NOT AUTHENTICATED")
            } else if (status == "User does not own this skin"){
                println("User does not own this skin")
            } else if (status=="Pieces skin changed successfully"){
                println("Pieces skin changed successfully")
                result= true
                Globals.Piezas=newSkin
            } else {
                println("ERROR")
            }
            latch.countDown()
        }
    })
    latch.await()
    return result
}