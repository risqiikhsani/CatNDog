package com.example.risqiikhsanicatndog.network

import com.example.risqiikhsanicatndog.model.CatPhoto
import retrofit2.http.GET


interface CatApiService {

    @GET("images/search?limit=10")
    suspend fun getPhotos(): List<CatPhoto>
}