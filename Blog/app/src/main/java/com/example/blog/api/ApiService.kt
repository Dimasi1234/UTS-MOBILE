package com.example.newsapp.api

import com.example.newsapp.model.BeritaResponse
import com.example.newsapp.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("berita")
    fun getBerita(): Call<BeritaResponse>
}
