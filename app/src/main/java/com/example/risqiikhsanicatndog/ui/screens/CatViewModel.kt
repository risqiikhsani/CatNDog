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
import com.example.risqiikhsanicatndog.data.CatPhotosRepository
import com.example.risqiikhsanicatndog.model.CatPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface CatUiState {
    data class Success(val photos: List<CatPhoto>) : CatUiState
    object Error : CatUiState
    object Loading : CatUiState
}

class CatViewModel(private val catPhotosRepository: CatPhotosRepository) : ViewModel() {

    // merupakan state
    var catUiState: CatUiState by mutableStateOf(CatUiState.Loading)
        private set


    init {
        getCatPhotos()
    }

    fun getCatPhotos() {
        viewModelScope.launch {
            catUiState = CatUiState.Loading
            catUiState = try {
                CatUiState.Success(catPhotosRepository.getCatPhotos())
            } catch (e: IOException) {
                CatUiState.Error
            } catch (e: HttpException) {
                CatUiState.Error
            }
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val catPhotosRepository = application.container.catPhotosRepository
                CatViewModel(catPhotosRepository = catPhotosRepository)
            }
        }
    }
}
