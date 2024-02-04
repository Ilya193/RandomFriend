package ru.kraz.randomfriend.data.friends

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ru.kraz.randomfriend.data.model.friends.FriendCloud
import ru.kraz.randomfriend.data.people.ToRandomPeopleDataMapper
import ru.kraz.randomfriend.domain.friends.FriendDomain
import ru.kraz.randomfriend.domain.friends.FriendsRepository
import ru.kraz.randomfriend.domain.people.RandomPersonDomain

class FriendsRepositoryImpl(
    private val database: FirebaseDatabase,
    private val mapper: ToRandomPeopleDataMapper
) : FriendsRepository {
    override suspend fun addOrRemove(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean) {
        if (isFavorite)
            database.reference.child("friends/$uuid/${friend.id}")
                .setValue(mapper.map(friend).toFriendCloud()).await()
        else
            database.reference.child("friends/$uuid/${friend.id}").removeValue().await()
    }

    override suspend fun fetchFriends(uuid: String): Flow<List<FriendDomain>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<FriendData>()
                for (item in snapshot.children) {
                    val itemCloud = item.getValue(FriendCloud::class.java)!!
                    list.add(itemCloud.toFriendData())
                }
                trySend(list.map { it.toFriendDomain() })
            }

            override fun onCancelled(error: DatabaseError) {}

        }
        database.reference.child("friends/$uuid")
            .addValueEventListener(listener)
        awaitClose { database.reference.removeEventListener(listener) }
    }
}