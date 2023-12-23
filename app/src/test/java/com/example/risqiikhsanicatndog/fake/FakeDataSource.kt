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
package com.example.risqiikhsanicatndog.fake

import com.example.risqiikhsanicatndog.model.DogPhoto

object FakeDataSource {

    private const val idOne = "img1"
    private const val idTwo = "img2"
    private const val imgOne = "url.one"
    private const val imgTwo = "url.two"
    val photosList = listOf(
        DogPhoto(
            id = idOne,
            height = 20,
            width = 20,
            imgSrc = imgOne
        ),
        DogPhoto(
            id = idTwo,
            height = 20,
            width = 20,
            imgSrc = imgTwo
        )
    )
}