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

import com.example.risqiikhsanicatndog.model.DogPhoto
import com.example.risqiikhsanicatndog.network.DogApiService

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface DogPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getDogPhotos(): List<DogPhoto>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkDogPhotosRepository(private val dogApiService: DogApiService) : DogPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getDogPhotos(): List<DogPhoto> = dogApiService.getPhotos()
}
