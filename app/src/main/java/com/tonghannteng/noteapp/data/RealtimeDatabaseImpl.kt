package com.tonghannteng.noteapp.data

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeDatabaseImpl @Inject constructor(
    private val firebase: Firebase
) : IRealtimeDatabase {

    private val realtimeDatabase = firebase.database
    private val data = realtimeDatabase.getReference("note")

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
            data.addValueEventListener(listener)
            awaitClose { data.removeEventListener(listener) }
        }
}