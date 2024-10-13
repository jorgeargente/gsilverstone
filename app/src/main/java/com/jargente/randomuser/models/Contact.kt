package com.jargente.randomuser.models

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: String,
    val name: String,
    val gender: String,
    val latitude: String,
    val longitude: String,
    val email: String,
    val phone: String,
    val registration: String,
    val picture: String
)