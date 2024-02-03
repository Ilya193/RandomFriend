package ru.kraz.randomfriend.data

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.kraz.randomfriend.domain.FriendsRepository
import ru.kraz.randomfriend.domain.RandomPersonDomain

class FriendsRepositoryImpl(
    private val database: FirebaseDatabase,
    private val mapper: ToRandomPeopleDataMapper
) : FriendsRepository {
    override suspend fun addOrRemove(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean) {
        if (isFavorite)
            database.reference.child("friends/$uuid/${friend.id}")
                .setValue(mapper.map(friend).toRandomPersonCloud()).await()
        else
            database.reference.child("friends/$uuid/${friend.id}").removeValue().await()
    }
}