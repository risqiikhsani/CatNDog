package com.example.risqiikhsanicatndog.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.risqiikhsanicatndog.model.CatPhoto
import com.example.risqiikhsanicatndog.ui.theme.CatNDogPhotosTheme
import com.example.risqiikhsanicatndog.R
import com.example.risqiikhsanicatndog.ui.components.TopAppBar
import com.example.risqiikhsanicatndog.ui.components.LoadingScreen
import com.example.risqiikhsanicatndog.ui.components.ErrorScreen

@Composable
// menampilkan cat screen (top app bar + foto list)
fun CatScreen(navController: NavHostController,retryAction: () -> Unit,catUiState: CatUiState, modifier: Modifier = Modifier) {
    Column {
        TopAppBar(navController, "Cat Images")
        when (catUiState) {
            is CatUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is CatUiState.Success -> CatPhotosGridScreen(catUiState.photos, modifier)
            is CatUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
// menampilkan banyak catPhotoCard dalam lazy vertical grid
fun CatPhotosGridScreen(photos: List<CatPhoto>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            CatPhotoCard(
                photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}


@Composable
// menampilkan foto cat
fun CatPhotoCard(photo: CatPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        // menggunakan asyncImage dari coil compose untuk menampilkan image dari rest api.
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.mars_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}



/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultCatScreen(photos: String, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Text(text = photos)
    }
}


@Preview(showBackground = true)
@Composable
fun PhotosCatGridScreenPreview() {
    CatNDogPhotosTheme {
        val mockData = List(10) { CatPhoto("$it", 10,10,"") }
        CatPhotosGridScreen(mockData)
    }
}
