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
package com.example.risqiikhsanicatndog

import com.example.risqiikhsanicatndog.data.NetworkDogPhotosRepository
import com.example.risqiikhsanicatndog.fake.FakeDataSource
import com.example.risqiikhsanicatndog.fake.FakeDogApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkDogRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = NetworkDogPhotosRepository(
                dogApiService = FakeDogApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getDogPhotos())
        }
}
