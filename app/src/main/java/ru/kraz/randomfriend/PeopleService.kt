package ru.kraz.randomfriend

import retrofit2.http.GET
import ru.kraz.randomfriend.data.RandomPeople

interface PeopleService {

    @GET("api/?results=50")
    suspend fun fetchRandomPeople(): RandomPeople
}