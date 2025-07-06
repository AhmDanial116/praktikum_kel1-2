package com.app.praktikum_kel1_2.service.api

import com.app.praktikum_kel1_2.model.request.RegisterRequest
import com.app.praktikum_kel1_2.model.response.NotesResponse
import com.app.praktikum_kel1_2.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService{
    @POST("/api/register")
    suspend fun register(@Body request: RegisterRequest) : Response<RegisterResponse>

    @GET("/api/notes")
    suspend fun getAllNotes() : NotesResponse
}