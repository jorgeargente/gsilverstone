package com.jargente.randomuser.network

import com.jargente.randomuser.models.Contact
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiService {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getContacts(page: Int): List<Contact> {
         val response: ContactListResponse = client.get("https://randomuser.me/api/"){
             parameter("page", page)
             parameter("results", 10)
         }.body()

        return response.results.map { apiContact ->
            Contact(
                id = apiContact.login.uuid,
                name = "${apiContact.name.first} ${apiContact.name.last}",
                gender = apiContact.gender,
                latitude = apiContact.location.coordinates.latitude,
                longitude = apiContact.location.coordinates.longitude,
                email = apiContact.email,
                phone = apiContact.phone,
                registration = apiContact.registered.date,
                picture = apiContact.picture.large
            )
        }
    }
}