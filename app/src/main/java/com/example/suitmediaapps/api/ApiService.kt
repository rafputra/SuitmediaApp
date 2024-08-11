package com.example.suitmediaapps.api

import com.example.suitmediaapps.data.ResponseUsername
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<ResponseUsername>
}