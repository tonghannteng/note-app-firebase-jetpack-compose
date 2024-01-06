package com.tonghannteng.noteapp.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.tonghannteng.noteapp.data.Note
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
class RealtimeDatabaseImpl @Inject constructor(
    private val databaseReference: DatabaseReference
) : IRealtimeDatabase {

    override suspend fun getTodoNote(): Flow<List<Note>> =
        callbackFlow {
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val todoData = snapshot.children.map {
                        it.getValue<Note>()!!
                    }
                    trySend(todoData)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
            databaseReference.addValueEventListener(listener)
            awaitClose { databaseReference.removeEventListener(listener) }
        }
}
