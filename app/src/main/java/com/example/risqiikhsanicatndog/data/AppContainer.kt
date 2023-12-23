/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.risqiikhsanicatndog.data

import com.example.risqiikhsanicatndog.network.DogApiService
import com.example.risqiikhsanicatndog.network.CatApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


interface AppContainer {
    val dogPhotosRepository: DogPhotosRepository
    val catPhotosRepository: CatPhotosRepository
}


class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.thedogapi.com/v1/"
    private val baseUrlCat = "https://api.thecatapi.com/v1/"

    // menggunakan retrofit untuk bekerja dengan rest api
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // menggunakan retrofit untuk bekerja dengan rest api
    private val retrofit2: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlCat)
        .build()

    private val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }

    private val retrofitCatService: CatApiService by lazy {
        retrofit2.create(CatApiService::class.java)
    }

    override val dogPhotosRepository: DogPhotosRepository by lazy {
        // pass retrofit ke network repository parameter
        NetworkDogPhotosRepository(retrofitService)
    }
    override val catPhotosRepository: CatPhotosRepository by lazy {
        // pass retrofit ke network repository parameter
        NetworkCatPhotosRepository(retrofitCatService)
    }
}
