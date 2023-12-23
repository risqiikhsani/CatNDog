package com.example.risqiikhsanicatndog.data

import com.example.risqiikhsanicatndog.model.CatPhoto
import com.example.risqiikhsanicatndog.network.CatApiService

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface CatPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getCatPhotos(): List<CatPhoto>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkCatPhotosRepository(private val catApiService: CatApiService) : CatPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getCatPhotos(): List<CatPhoto> = catApiService.getPhotos()
}
