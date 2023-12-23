package com.example.risqiikhsanicatndog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 */
@Serializable
data class DogPhoto(
    val id: String,
    val width: Int,
    val height: Int,
    @SerialName(value = "url")
    val imgSrc: String
)
