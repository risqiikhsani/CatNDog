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

import com.example.risqiikhsanicatndog.fake.FakeDataSource
import com.example.risqiikhsanicatndog.fake.FakeNetworkDogPhotosRepository
import com.example.risqiikhsanicatndog.rules.TestDispatcherRule
import com.example.risqiikhsanicatndog.ui.screens.DogUiState
import com.example.risqiikhsanicatndog.ui.screens.DogViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DogViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val marsViewModel = DogViewModel(
                dogPhotosRepository = FakeNetworkDogPhotosRepository()
            )
            assertEquals(
                DogUiState.Success(FakeDataSource.photosList),
                marsViewModel.dogUiState
            )
        }
}
