package ru.kraz.randomfriend.data

import retrofit2.http.GET
import ru.kraz.randomfriend.data.model.people.RandomPeople

interface PeopleService {

    @GET("api/?results=50")
    suspend fun fetchRandomPeople(): RandomPeople
}