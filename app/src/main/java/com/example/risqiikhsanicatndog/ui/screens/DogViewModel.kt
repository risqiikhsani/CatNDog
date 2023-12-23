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
package com.example.risqiikhsanicatndog.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.risqiikhsanicatndog.MarsPhotosApplication
import com.example.risqiikhsanicatndog.data.DogPhotosRepository
import com.example.risqiikhsanicatndog.model.DogPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface DogUiState {
    data class Success(val photos: List<DogPhoto>) : DogUiState
    object Error : DogUiState
    object Loading : DogUiState
}

class DogViewModel(private val dogPhotosRepository: DogPhotosRepository) : ViewModel() {

    var dogUiState: DogUiState by mutableStateOf(DogUiState.Loading)
        private set

    init {
        getDogPhotos()
    }

    fun getDogPhotos() {

        viewModelScope.launch {
            dogUiState = DogUiState.Loading
            dogUiState = try {
                DogUiState.Success(dogPhotosRepository.getDogPhotos())
            } catch (e: IOException) {
                DogUiState.Error
            } catch (e: HttpException) {
                DogUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val dogPhotosRepository = application.container.dogPhotosRepository
                DogViewModel(dogPhotosRepository = dogPhotosRepository)
            }
        }
    }
}
