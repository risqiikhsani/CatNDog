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

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.risqiikhsanicatndog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.risqiikhsanicatndog.R
import com.example.risqiikhsanicatndog.ui.screens.CatScreen
import com.example.risqiikhsanicatndog.ui.screens.DogViewModel
import com.example.risqiikhsanicatndog.ui.screens.CatViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.risqiikhsanicatndog.ui.screens.DogScreen

@Composable
// merupakan home screen tempat 2 tombol untuk melihat dog and cat images
fun HomeScreen(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.dogandcat2), // Replace 'your_image' with your image resource
                contentDescription = "Cat and dog",
                modifier = Modifier.padding(40.dp)
            )
            Button(onClick = { navController.navigate("dogs") }) {
                Text("View Dog Images")
            }
            Button(onClick = { navController.navigate("cats") }) {
                Text("View Cat Images")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatAndDogPhotosApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = { CatAndDogTopAppBar(scrollBehavior = scrollBehavior) }) {
        Surface(modifier = Modifier.fillMaxSize().padding(it)) {
            NavHost(navController, startDestination = "home") {
                // merupakan navhost "home"
                composable("home") {
                    HomeScreen(navController)
                }
                // merupakan navhost "dogs"
                composable("dogs") {
                    // view homescreen di pass oleh dogUiState (dari dogviewmodel)
                    val dogViewModel: DogViewModel = viewModel(factory = DogViewModel.Factory)
                    DogScreen(navController,dogUiState = dogViewModel.dogUiState,retryAction = dogViewModel::getDogPhotos)
                }
                // merupakan navhost "cats"
                composable("cats") {
                    val catViewModel: CatViewModel = viewModel(factory = CatViewModel.Factory)
                    CatScreen(navController,catUiState = catViewModel.catUiState,retryAction = catViewModel::getCatPhotos)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// top bar untuk memperlihatkan nama app
fun CatAndDogTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}


















