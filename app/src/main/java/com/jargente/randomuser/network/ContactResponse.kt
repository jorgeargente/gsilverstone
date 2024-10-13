package com.jargente.randomuser.network

import kotlinx.serialization.Serializable

@Serializable
data class ContactListResponse(
    val results: List<ResponseContact>
)

@Serializable
data class ResponseContact(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val phone: String,
    val registered: Registered,
    val login: Login,
    val picture: Picture
)

@Serializable
data class Name(
    val title: String,
    val first: String,
    val last: String
)

@Serializable
data class Location(
    val coordinates: Coordinates
)

@Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String
)

@Serializable
data class Registered(
    val date: String
)

@Serializable
data class Login(
    val uuid: String
)

@Serializable
data class Picture(
    val large: String
)